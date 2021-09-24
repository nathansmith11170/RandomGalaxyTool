package com.application.views.generator.events;

import com.application.views.generator.SectorPlacementForm;

import configurationmodel.GeneratorConfig;

public class GenerateEvent extends SectorPlacementFormEvents {
    public GenerateEvent( SectorPlacementForm source, GeneratorConfig generatorConfig ) {
        super( source, generatorConfig );
    }
}