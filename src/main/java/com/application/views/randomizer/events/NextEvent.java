package com.application.views.randomizer.events;

import com.application.views.randomizer.FactionPlacementForm;
import com.application.views.randomizer.SectorPlacementForm;

import configurationmodel.RandomizerConfig;

public class NextEvent extends RandomizerFormEvents {
    public NextEvent( SectorPlacementForm source, RandomizerConfig generatorConfig ) {
        super( source, generatorConfig );
    }

    public NextEvent( FactionPlacementForm source, RandomizerConfig generatorConfig ) {
        super( source, generatorConfig );
    }
}
