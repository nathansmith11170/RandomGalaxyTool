package com.application.views.generator;

import com.application.controllers.GeneratorController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller.ScrollDirection;

import configurationmodel.GeneratorConfig;

public class MapPreview extends VerticalLayout {
    Image img;
    Scroller container = new Scroller( ScrollDirection.BOTH );

    public MapPreview() {
        Text previewLabel = new Text( "Map Preview" );
        container.setContent( previewLabel );

        add( container );
    }

    public void generatePreview( GeneratorConfig generatorConfig ) {
        GeneratorController genController = new GeneratorController();
        img = new Image();
        img.setSrc( genController.generateMap(generatorConfig) );
        container.setContent( img );
    }
}
