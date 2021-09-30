package com.application.views.randomizer.events;

import com.application.views.randomizer.FactionPlacementForm;
import com.application.views.randomizer.FinalStepForm;

import configurationmodel.RandomizerConfig;

public class BackEvent extends RandomizerFormEvents {
    public BackEvent( FactionPlacementForm source, RandomizerConfig generatorConfig ) {
        super( source, generatorConfig );
    }

    public BackEvent( FinalStepForm source, RandomizerConfig generatorConfig ) {
        super( source, generatorConfig );
    }
}
