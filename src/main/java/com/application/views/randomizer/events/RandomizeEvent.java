package com.application.views.randomizer.events;

import com.application.views.randomizer.SectorPlacementForm;

import configurationmodel.RandomizerConfig;

public class RandomizeEvent extends RandomizerFormEvents {
    public RandomizeEvent( SectorPlacementForm source, RandomizerConfig randomizerConfig ) {
        super( source, randomizerConfig );
    }
}