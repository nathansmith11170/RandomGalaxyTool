package com.application.views.generator.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.formlayout.FormLayout;

import configurationmodel.GeneratorConfig;

public abstract class GeneratorFormEvents extends ComponentEvent<FormLayout> {
    private GeneratorConfig generatorConfig;

    protected GeneratorFormEvents( FormLayout source, GeneratorConfig generatorConfig ) {
        super( source, false );
        this.generatorConfig = generatorConfig;
    }

    public GeneratorConfig getGeneratorConfig() {
        return this.generatorConfig;
    }
}