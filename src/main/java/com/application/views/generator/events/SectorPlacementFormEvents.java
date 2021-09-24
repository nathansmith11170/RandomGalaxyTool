package com.application.views.generator.events;

import com.application.views.generator.SectorPlacementForm;
import com.vaadin.flow.component.ComponentEvent;

import configurationmodel.GeneratorConfig;

public abstract class SectorPlacementFormEvents extends ComponentEvent<SectorPlacementForm> {
    private GeneratorConfig generatorConfig;

    protected SectorPlacementFormEvents( SectorPlacementForm source, GeneratorConfig generatorConfig ) {
        super( source, false );
        this.generatorConfig = generatorConfig;
    }

    public GeneratorConfig getGeneratorConfig() {
        return this.generatorConfig;
    }
}