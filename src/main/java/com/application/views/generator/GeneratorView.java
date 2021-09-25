package com.application.views.generator;

import com.application.views.generator.events.GenerateEvent;
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
