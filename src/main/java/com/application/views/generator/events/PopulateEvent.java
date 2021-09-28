package com.application.views.generator.events;

import com.application.views.generator.FactionPlacementForm;

import configurationmodel.GeneratorConfig;

public class PopulateEvent extends GeneratorFormEvents {
    public PopulateEvent(FactionPlacementForm source, GeneratorConfig generatorConfig) {
        super(source, generatorConfig);
    }
}
