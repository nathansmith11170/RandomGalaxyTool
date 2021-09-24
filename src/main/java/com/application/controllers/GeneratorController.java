package com.application.controllers;

import com.vaadin.flow.server.StreamResource;

import abstractmodel.AxialHexMapSquare;
import abstractmodel.HexagonalMaze;
import abstractmodel.MazeBitmap;
import configurationmodel.GeneratorConfig;

public class GeneratorController {
    private AxialHexMapSquare grid;
    private HexagonalMaze map;

    public StreamResource generateMap( GeneratorConfig generatorConfig ) {
        grid = new AxialHexMapSquare(generatorConfig.clusters);
        map = new HexagonalMaze( grid, generatorConfig.passes, generatorConfig.deadPercent );
        MazeBitmap mapImg = new MazeBitmap(map);

        StreamResource previewResource = new StreamResource( "preview.png", () -> mapImg.getStream());

        return previewResource;
    }
}
