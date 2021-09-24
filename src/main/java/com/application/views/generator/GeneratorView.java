package com.application.views.generator;

import java.util.Iterator;

import com.application.views.generator.events.GenerateEvent;
import com.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller.ScrollDirection;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;

import abstractmodel.AxialHexMapSquare;
import abstractmodel.HexagonalMaze;
import abstractmodel.MazeBitmap;
import configurationmodel.GeneratorConfig;

@Route(value = "generator", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Generator")
public class GeneratorView extends Div {
    SectorPlacementForm sectorPlacementForm;

    MapPreview mapPreview;

    public GeneratorView() {
        addClassName( "generator-view" );

        SplitLayout pageLayout = new SplitLayout();
    
        sectorPlacementForm = new SectorPlacementForm();
        pageLayout.addToPrimary( sectorPlacementForm );

        mapPreview = new MapPreview();
        pageLayout.addToSecondary( mapPreview );

        sectorPlacementForm.addListener( GenerateEvent.class, this::generateMapPreview );

        add( pageLayout );
    }

    private void generateMapPreview(GenerateEvent event) {
        mapPreview.generatePreview( event.getGeneratorConfig() );
    }
}
