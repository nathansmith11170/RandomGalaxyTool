package com.application.controllers;

import com.vaadin.flow.server.StreamResource;

import configurationmodel.GeneratorConfig;
import model.AxialHexMapSquare;
import model.HexagonalMaze;
import model.MazeBitmap;

public class GeneratorController {
    private AxialHexMapSquare grid;
    private HexagonalMaze map;

    public StreamResource generateMap( GeneratorConfig generatorConfig ) {
        grid = new AxialHexMapSquare( generatorConfig.getClusters() );
        map = new HexagonalMaze( grid, generatorConfig.getPasses(), generatorConfig.getDeadPercent().doubleValue() );
        MazeBitmap mapImg = new MazeBitmap(map);

        StreamResource previewResource = new StreamResource( "preview.png", () -> mapImg.getStream());

        return previewResource;
    }
}
