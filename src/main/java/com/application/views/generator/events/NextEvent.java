package com.application.views.generator.events;

import com.application.views.generator.FactionPlacementForm;
import com.application.views.generator.SectorPlacementForm;

import configurationmodel.GeneratorConfig;

public class NextEvent extends GeneratorFormEvents {
    public NextEvent( SectorPlacementForm source, GeneratorConfig generatorConfig ) {
        super( source, generatorConfig );
    }

    public NextEvent( FactionPlacementForm source, GeneratorConfig generatorConfig ) {
        super( source, generatorConfig );
    }
}
