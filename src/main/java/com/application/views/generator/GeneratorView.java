package com.application.views.generator;

import com.application.controllers.GeneratorController;
import com.application.views.generator.events.GenerateEvent;
import com.application.views.generator.events.NextEvent;
import com.application.views.generator.events.PopulateEvent;
import com.application.views.main.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "generator", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Generator")
public class GeneratorView extends Div {
    SectorPlacementForm sectorPlacementForm;
    FactionPlacementForm factionPlacementForm;

    MapPreview mapPreview;
    SplitLayout pageLayout;

    GeneratorController controller;

    public GeneratorView() {
        addClassName( "generator-view" );
        controller = new GeneratorController();

        pageLayout = new SplitLayout();
    
        setupSectorPlacementForm();

        add( pageLayout );
    }

    private void setupSectorPlacementForm() {
        sectorPlacementForm = new SectorPlacementForm();

        sectorPlacementForm.addListener( GenerateEvent.class, this::generateMapPreview );
        sectorPlacementForm.addListener( NextEvent.class, this::sectorToFactionPlacementTransition );

        pageLayout.addToPrimary( sectorPlacementForm );
    }

    private void generateMapPreview(GenerateEvent event) {
        mapPreview = new MapPreview( controller );
        mapPreview.generatePreview( event.getGeneratorConfig() );
        pageLayout.addToSecondary( mapPreview );
    }

    private void sectorToFactionPlacementTransition( NextEvent event ) {
        factionPlacementForm = new FactionPlacementForm( controller );

        factionPlacementForm.addListener( PopulateEvent.class, this::updateMapPreview );
        factionPlacementForm.addListener( NextEvent.class, this::factionPlacementToFinalStepsTransition );

        pageLayout.remove( sectorPlacementForm );
        pageLayout.addToPrimary( factionPlacementForm );
    }

    private void updateMapPreview( PopulateEvent event ) {
        pageLayout.remove( mapPreview );
        mapPreview.populateMap( event.getGeneratorConfig() );
        pageLayout.addToSecondary( mapPreview );
    }

    private void factionPlacementToFinalStepsTransition( NextEvent event ) {

    }
}
