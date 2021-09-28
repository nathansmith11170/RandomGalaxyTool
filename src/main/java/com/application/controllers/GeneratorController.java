package com.application.controllers;

import java.time.Instant;
import java.util.Date;

import com.vaadin.flow.server.StreamResource;

import configurationmodel.GeneratorConfig;
import model.OddQHexGridSquare;
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
        MazeBitmap mapImg = new MazeBitmap(map);

        StreamResource previewResource = new StreamResource( "preview.png", () -> mapImg.getStream());

        return previewResource;
    }
}
