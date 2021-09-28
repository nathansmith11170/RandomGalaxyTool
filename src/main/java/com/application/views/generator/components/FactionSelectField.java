package com.application.views.generator.components;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.textfield.TextField;

public class FactionSelectField extends CustomField<Integer> {
    public Checkbox includeFaction = new Checkbox();
    private String faction;
    private TextField sectorCount = new TextField();

    public FactionSelectField( String factionLabel, String factionId ) {
        setLabel( factionLabel );
        this.faction = factionId;
        addValueChangeListener( event -> updateValue() );
        
        sectorCount.setLabel( String.format( "%s Sectors", factionLabel ) );

        sectorCount.setPreventInvalidInput( true );
        sectorCount.setPattern( "[0-9]*" );
        sectorCount.setRequired( false );
        sectorCount.setEnabled( false );

        includeFaction.addValueChangeListener( event -> {
            sectorCount.setEnabled( !sectorCount.isEnabled() );
            sectorCount.setRequired( !sectorCount.isEnabled() );
        } );

        add( 
            includeFaction,
            sectorCount
        );
    }

    @Override
    protected Integer generateModelValue() {
        return sectorCount.getValue().isEmpty() ? 0 : Integer.parseInt(sectorCount.getValue());
    }

    @Override
    protected void setPresentationValue( Integer newPresentationValue ) {
        this.sectorCount.setValue( String.valueOf( newPresentationValue ) );        
    }

    public String getFaction() {
        return this.faction;
    }
}
