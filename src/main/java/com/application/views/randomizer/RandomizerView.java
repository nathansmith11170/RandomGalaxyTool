package com.application.views.randomizer;

import com.application.controllers.GeneratorController;
import com.application.views.main.MainView;
import com.application.views.randomizer.events.NextEvent;
import com.application.views.randomizer.events.PopulateEvent;
import com.application.views.randomizer.events.RandomizeEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "generator", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Generator")
public class RandomizerView extends Div {
    SectorPlacementForm sectorPlacementForm;
    FactionPlacementForm factionPlacementForm;

    MapPreview mapPreview;
    SplitLayout pageLayout;

    GeneratorController controller;

    public RandomizerView() {
        addClassName( "generator-view" );
        controller = new GeneratorController();

        pageLayout = new SplitLayout();
    
        setupSectorPlacementForm();

        add( pageLayout );
    }

    private void setupSectorPlacementForm() {
        sectorPlacementForm = new SectorPlacementForm();

        sectorPlacementForm.addListener( RandomizeEvent.class, this::generateMapPreview );
        sectorPlacementForm.addListener( NextEvent.class, this::sectorToFactionPlacementTransition );

        pageLayout.addToPrimary( sectorPlacementForm );
    }

    private void generateMapPreview(RandomizeEvent event) {
        mapPreview = new MapPreview( controller );
        mapPreview.generatePreview( event.getRandomizerConfig() );
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
        mapPreview.populateMap( event.getRandomizerConfig() );
        pageLayout.addToSecondary( mapPreview );
    }

    private void factionPlacementToFinalStepsTransition( NextEvent event ) {

    }
}
