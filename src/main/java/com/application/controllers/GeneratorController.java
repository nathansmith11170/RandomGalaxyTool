package com.application.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import model.OddQHexCoord;

import com.vaadin.flow.server.StreamResource;

import org.javatuples.Pair;

import configurationmodel.GeneratorConfig;
import model.OddQHexGridSquare;
import model.FactionPlacer;
import model.Galaxy;
import model.HexagonalMaze;
import model.MazeBitmap;

public class GeneratorController {
    private OddQHexGridSquare grid;
    private HexagonalMaze map;
    private Galaxy outputObject = new Galaxy();

    public StreamResource generateMap( GeneratorConfig generatorConfig ) {
        grid = new OddQHexGridSquare( generatorConfig.getClusters() );
        map = new HexagonalMaze( grid, generatorConfig.getPasses(), generatorConfig.getDeadPercent().doubleValue() );
        outputObject.setClusters( map.toClusterList() );
        
        outputObject.setGalaxyName( "X4SecondRealignment" );
        outputObject.setDate( Date.from( Instant.now() ).toString() );
        outputObject.setSave( "0" );
        outputObject.setSeed( Instant.now().getEpochSecond() );
        outputObject.setVersion( "1.0.0" );
        MazeBitmap mapImg = new MazeBitmap( map );

        StreamResource previewResource = new StreamResource( "preview.png", () -> mapImg.getStream() );

        return previewResource;
    }

    public StreamResource populateMap( GeneratorConfig generatorConfig ) {
        Set<Pair<String, OddQHexCoord>> ownedSectors = new FactionPlacer().placeFactions( outputObject, generatorConfig );

        MazeBitmap mapImg = new MazeBitmap( map );

        StreamResource previewResource = new StreamResource( "preview2.png", () -> mapImg.getStream( ownedSectors ) );

        return previewResource;
    }

    public int getClusterCount() {
        return this.outputObject.getClusters().size();
    }
}
