package com.application.views.randomizer.events;

import com.application.views.randomizer.FactionPlacementForm;

import configurationmodel.RandomizerConfig;

public class DownloadEvent extends RandomizerFormEvents {
    public DownloadEvent( FactionPlacementForm source, RandomizerConfig generatorConfig ) {
        super( source, generatorConfig );
    }
}
