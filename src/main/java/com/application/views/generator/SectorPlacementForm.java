package com.application.views.generator;


import com.application.views.generator.events.GenerateEvent;
import com.application.views.generator.events.NextEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.shared.Registration;

import configurationmodel.DecimalMagnitudeValidator;
import configurationmodel.GeneratorConfig;
import configurationmodel.IntegerMagnitudeValidator;

public class SectorPlacementForm extends FormLayout {  
    Binder<GeneratorConfig> binder = new Binder<>(GeneratorConfig.class);
    
    TextField txtClusters = new TextField( "Sector Count" );

    TextField txtPasses = new TextField( "Connection Density" );

    TextField txtDeadPercent = new TextField( "Percent of the Grid Which is Empty" );

    GeneratorConfig generatorConfig = new GeneratorConfig();

    Button generate = new Button( "Generate" );
    Button next = new Button( "Next" );

    public SectorPlacementForm() {
        addClassName( "sector-placement-form" );

        binder.forField(txtClusters)
            .withConverter(new StringToIntegerConverter("Enter a number"))
            .asRequired()
            .withValidator(new IntegerMagnitudeValidator(4, 256))
            .bind(GeneratorConfig::getClusters, GeneratorConfig::setClusters);
        binder.forField(txtPasses)
            .withConverter(new StringToIntegerConverter("Enter a number"))
            .asRequired()
            .withValidator(new IntegerMagnitudeValidator(1, 5))
            .bind(GeneratorConfig::getPasses, GeneratorConfig::setPasses);
        binder.forField(txtDeadPercent)
            .withConverter(new StringToBigDecimalConverter("Enter a decimal number"))
            .asRequired()
            .withValidator(new DecimalMagnitudeValidator("0", "0.50"))
            .bind(GeneratorConfig::getDeadPercent, GeneratorConfig::setDeadPercent);
        binder.setBean(generatorConfig);

        binder.addValueChangeListener( (event) -> {
            if(binder.isValid()) {
                generate.setEnabled(true);
            }
            else {
                generate.setEnabled(false);
            }
        } );

        txtClusters.setHelperText( "Enter an integer between 4 and 256." );
        txtPasses.setHelperText( "Enter an integer between 1 and 5, 5 is densest." );
        txtDeadPercent.setHelperText( "Enter an decimal between 0 and 0.50." );

        add(txtClusters,
            txtPasses,
            txtDeadPercent,
            createButtonsLayout() );
    }

    @Override
    protected <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return super.addListener( eventType, listener );
    }

    private HorizontalLayout createButtonsLayout() {
        generate.addClickShortcut( Key.ENTER );
        generate.setEnabled( false );
        next.setEnabled( false );

        generate.addClickListener( ( event ) -> {
            next.setEnabled( true );
            fireEvent( new GenerateEvent( this, generatorConfig ) );
        } );

        next.addClickListener( event -> fireEvent( new NextEvent( this, generatorConfig ) ) );

        return new HorizontalLayout( generate, next );
    }
}
