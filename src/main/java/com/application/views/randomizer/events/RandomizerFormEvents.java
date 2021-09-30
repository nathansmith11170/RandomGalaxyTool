package com.application.views.randomizer.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.formlayout.FormLayout;

import configurationmodel.RandomizerConfig;

public abstract class RandomizerFormEvents extends ComponentEvent<FormLayout> {
    private RandomizerConfig randomizerConfig;

    protected RandomizerFormEvents( FormLayout source, RandomizerConfig randomizerConfig ) {
        super( source, false );
        this.randomizerConfig = randomizerConfig;
    }

    public RandomizerConfig getRandomizerConfig() {
        return this.randomizerConfig;
    }
}