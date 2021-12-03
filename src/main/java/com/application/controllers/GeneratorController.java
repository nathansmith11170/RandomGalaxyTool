package com.application.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import model.OddQHexCoord;

import com.vaadin.flow.server.StreamResource;

import org.javatuples.Pair;

import configurationmodel.RandomizerConfig;
import freemarker.template.TemplateException;
import generator.mainGenerator.UniverseGeneratorMain;
import model.OddQHexGridSquare;
import model.Product;
import model.jobs.JsonJob;
import model.Cluster;
import model.EcoPlacer;
import model.Faction;
import model.FactionHqLocation;
import model.FactionPlacer;
import model.FactionStart;
import model.Galaxy;
import model.HexagonalMaze;
import model.MazeBitmap;

public class GeneratorController {
    private OddQHexGridSquare grid;
    private HexagonalMaze map;
    private Galaxy outputObject = new Galaxy();

    public StreamResource randomizeMap( RandomizerConfig randomizerConfig ) {
        grid = new OddQHexGridSquare( randomizerConfig.getClusters() );
        map = new HexagonalMaze( grid, randomizerConfig.getPasses(), randomizerConfig.getDeadPercent().doubleValue() );
        
        outputObject.setGalaxyName( "x4_secondalignment_universe" );
        outputObject.setGalaxyPrefix( "x4sr" );
        outputObject.setDescription( "The galaxy after a second realignment of the gates." );
        outputObject.setAuthor("General Vash and Celludriel");
        outputObject.setDate( Date.from( Instant.now() ).toString() );
        outputObject.setSave( "0" );
        outputObject.setSeed( Instant.now().getEpochSecond() );
        outputObject.setVersion( "1.0.0" );
        outputObject.setMinRandomBelts(2);
        outputObject.setMaxRandomBelts(5);
        MazeBitmap mapImg = new MazeBitmap( map );

        StreamResource previewResource = new StreamResource( "preview.png", () -> mapImg.getStream() );

        return previewResource;
    }

    public StreamResource populateMap( RandomizerConfig generatorConfig ) {
        FactionPlacer placer = new FactionPlacer();
        EcoPlacer ecoPlacer = new EcoPlacer();
        List<Cluster> clusterList = placer.setClusters( this.map );
        Set<Pair<String, OddQHexCoord>> ownedSectors = placer.placeFactions( this.map, generatorConfig );
        List<FactionStart> starts = placer.addFactionStarts( generatorConfig.getEnabledFactions() );

        outputObject.setClusters( clusterList );
        starts.forEach( ( start ) -> {
            outputObject.addFactionHqLocation( new FactionHqLocation( start.getFaction(), start.getClusterId() ) );
        } );

        outputObject.setClusters( ecoPlacer.placeMinistryOfFinance(ownedSectors, outputObject.getClusters() ) );

        List<Product> products = new ArrayList<Product>();
        List<JsonJob> jobs = new ArrayList<JsonJob>();
        generatorConfig.getEnabledFactions().forEach( ( faction ) -> {
            products.addAll( ecoPlacer.addEconomy( Faction.getEnum( faction ), ownedSectors ) );
            if( !faction.equals( "xenon" ) ) {
                outputObject.setClusters( ecoPlacer.placeTradeStations( Faction.getEnum( faction ), ownedSectors, outputObject.getClusters() ) );
            }
            jobs.addAll( ecoPlacer.addEcoJobs( Faction.getEnum( faction ), clusterList.size(), outputObject.getGalaxyName() ) );
            jobs.addAll( ecoPlacer.addMilitaryJobs( Faction.getEnum( faction ),  clusterList.size(), outputObject.getGalaxyName() ) );
        });
        
        outputObject.setJobs(jobs);
        outputObject.setProducts(products);
        MazeBitmap mapImg = new MazeBitmap( map );

        StreamResource previewResource = new StreamResource( "preview2.png", () -> mapImg.getStream( ownedSectors ) );

        return previewResource;
    }

    public byte[] generateOutputFile() {
        try {
            return new UniverseGeneratorMain().GenerateUniverse(outputObject);
        }
        catch( TemplateException e) {
            return null;
        }
        catch( IOException e ) {
            return null;
        }
        catch( URISyntaxException e) {
            return null;
        }
    }

    public int getClusterCount() {
        return this.map.members.size();
    }
}
