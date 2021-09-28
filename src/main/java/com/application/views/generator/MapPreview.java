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
    Image img2;
    Scroller container = new Scroller( ScrollDirection.BOTH );
    Scroller container2 = new Scroller( ScrollDirection.BOTH ); 

    GeneratorController controller;

    public MapPreview( GeneratorController controller) {
        this.controller = controller;

        Text previewLabel = new Text( "Map Preview" );
        container.setContent( previewLabel );

        container2.setVisible(false);

        add( container );
        add( container2 );

    }

    public void generatePreview( GeneratorConfig generatorConfig ) {
        img = new Image();
        img.setSrc( controller.generateMap(generatorConfig) );
        container.setContent( img );
    }

    public void populateMap( GeneratorConfig generatorConfig ) {
        container.setVisible(false);
        container2.setVisible(true);
        img2 = new Image();
        img2.setSrc( controller.populateMap( generatorConfig ) );
        container2.setContent( img2 );
    }
}
