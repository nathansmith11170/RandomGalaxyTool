package com.application.views.randomizer;

import java.io.ByteArrayInputStream;

import com.application.controllers.GeneratorController;
import com.application.views.main.MainView;
import com.application.views.randomizer.events.BackEvent;
import com.application.views.randomizer.events.DownloadEvent;
import com.application.views.randomizer.events.NextEvent;
import com.application.views.randomizer.events.PopulateEvent;
import com.application.views.randomizer.events.RandomizeEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;

@Route(value = "generator", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Generator")
public class RandomizerView extends Div {
    SectorPlacementForm sectorPlacementForm;
    FactionPlacementForm factionPlacementForm;

    MapPreview mapPreview;
    SplitLayout pageLayout;

    GeneratorController controller;

    Button downloadBtn = new Button( "Download", new Icon(VaadinIcon.DOWNLOAD_ALT) );
    Anchor download = new Anchor();

    public RandomizerView() {
        addClassName( "generator-view" );
        controller = new GeneratorController();

        pageLayout = new SplitLayout();
    
        setupSectorPlacementForm();


        downloadBtn.setEnabled( false );

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
        factionPlacementForm.addListener( DownloadEvent.class, this::factionPlacementDownload );
        factionPlacementForm.addListener( BackEvent.class, this::factionPlacementToSectorPlacementTransition );

        pageLayout.remove( sectorPlacementForm );
        pageLayout.addToPrimary( factionPlacementForm );
    }

    private void updateMapPreview( PopulateEvent event ) {
        pageLayout.remove( mapPreview );
        mapPreview.populateMap( event.getRandomizerConfig() );
        pageLayout.addToSecondary( mapPreview );
    }

    private void factionPlacementToSectorPlacementTransition( BackEvent event ) {
        pageLayout.remove( factionPlacementForm );
        pageLayout.addToPrimary( sectorPlacementForm );
    }
    
    private void factionPlacementDownload( DownloadEvent event ) {
        // Call controller to get files
        byte[] zipOutput = controller.generateOutputFile( );

        // Give user download
        StreamResource content = new StreamResource("output.zip", () -> { return new ByteArrayInputStream( zipOutput ); } );
        content.setContentType( "download" );
        download.setHref( content );
        download.add(downloadBtn);
        
        // show Download Button
        downloadBtn.setEnabled(true);
        pageLayout.addToPrimary(download);
    }
}
