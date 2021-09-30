package com.application.views.randomizer.events;

import com.application.views.randomizer.FactionPlacementForm;

import configurationmodel.RandomizerConfig;

public class PopulateEvent extends RandomizerFormEvents {
    public PopulateEvent(FactionPlacementForm source, RandomizerConfig randomizerConfig) {
        super(source, randomizerConfig);
    }
}
