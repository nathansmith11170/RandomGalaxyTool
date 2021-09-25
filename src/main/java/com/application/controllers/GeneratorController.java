package com.application.controllers;

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
        MazeBitmap mapImg = new MazeBitmap(map);

        StreamResource previewResource = new StreamResource( "preview.png", () -> mapImg.getStream());

        return previewResource;
    }
}
