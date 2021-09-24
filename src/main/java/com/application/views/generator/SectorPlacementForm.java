package com.application.views.generator;

import java.util.ArrayList;
import java.util.List;

import com.application.views.generator.events.GenerateEvent;
import com.application.views.generator.events.NextEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

import configurationmodel.GeneratorConfig;

public class SectorPlacementForm extends FormLayout {
    TextField sectorCount = new TextField( "Sector Count" );
    TextField connectionDensity = new TextField( "Connection Density" );
    TextField percentDeadZones = new TextField( "Percent of the Grid Which is Empty" );

    GeneratorConfig generatorConfig = new GeneratorConfig();

    Details edits = new Details();

    Button generate = new Button( "Generate" );
    Button next = new Button( "Next" );

    public SectorPlacementForm() {
        addClassName( "sector-placement-form" );

        edits.setVisible( false );
        edits.setOpened( false );
        edits.setSummaryText( "Form Errors" );

        sectorCount.setHelperText( "Enter an integer between 4 and 256." );
        connectionDensity.setHelperText( "Enter an integer between 1 and 5, 5 is densest." );
        percentDeadZones.setHelperText( "Enter an integer between 0 and 50." );

        add( edits,
            sectorCount,
            connectionDensity,
            percentDeadZones,
            createButtonsLayout() );
    }

    @Override
    protected <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return super.addListener( eventType, listener );
    }

    private HorizontalLayout createButtonsLayout() {
        generate.addClickShortcut( Key.ENTER );

        next.setEnabled( false );

        generate.addClickListener( ( event ) -> {
            List<String> editList = validate();
            if ( editList.size() == 0 ) {
                edits.setVisible( false );
                next.setEnabled( true );
                generatorConfig.clusters = Integer.parseInt( sectorCount.getValue() );
                generatorConfig.passes = Integer.parseInt( connectionDensity.getValue() );
                generatorConfig.deadPercent = Integer.parseInt( percentDeadZones.getValue() ) / 100.0;
                fireEvent( new GenerateEvent( this, generatorConfig ) );
            }
            else {
                edits.setVisible( true );
                editList.forEach( ( m ) -> {
                    edits.addContent( new Text( String.format( "%s\n", m ) ) );
                } );
            }
        } );

        next.addClickListener( event -> fireEvent( new NextEvent( this, generatorConfig ) ) );

        return new HorizontalLayout( generate, next );
    }

    private List<String> validate() {
        List<String> edits = new ArrayList<>();

        try { 
            int sectors = Integer.parseInt( sectorCount.getValue() );
            if ( sectors > 256 ) {
                edits.add( "Sector count is too large." );
            }
            else if ( sectors < 4 ) {
                edits.add( "Sector count is too small." );
            }
        } catch(NumberFormatException e) {
            sectorCount.setHelperText( "Invalid contents, enter integer value" );
            edits.add( "Sector count is not a number." );
        }

        try { 
            int passes = Integer.parseInt( connectionDensity.getValue() );
            if ( passes > 5 ) {
                edits.add( "Connection density is too large." );
            }
            else if ( passes < 1 ) {
                edits.add( "Connection density cannot be 0 or negative." );
            }
        } catch(NumberFormatException e) {
            edits.add( "Connection density is not a number." );
        }

        try { 
            int passes = Integer.parseInt( percentDeadZones.getValue() );
            if ( passes > 50 ) {
                edits.add( "The percent of the grid which is empty cannot be more than 50%" );
            }
            else if ( passes < 0 ) {
                edits.add( "The percent of the grid which is empty cannot be negative." );
            }
        } catch(NumberFormatException e) {
            edits.add( "The percent of the grid which is empty must be a number." );
        }

        return edits;
    }
}
