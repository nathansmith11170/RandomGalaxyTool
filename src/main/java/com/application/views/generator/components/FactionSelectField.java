package com.application.views.generator.components;

import org.javatuples.Triplet;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.textfield.TextField;

public class FactionSelectField extends CustomField<Triplet<Boolean, String, Integer>> {
    private Checkbox includeFaction = new Checkbox();
    private TextField sectorCount = new TextField();

    private Triplet<Boolean, String, Integer> internalValue = new Triplet<Boolean, String, Integer>( false, "", 0 );

    public FactionSelectField( String faction ) {
        setLabel( faction );
        addValueChangeListener( event -> updateValue() );
        
        sectorCount.setLabel( String.format( "%s Sectors", faction ) );

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
    public void setValue( Triplet<Boolean, String, Integer> value ) {
        this.internalValue = value;
    }

    @Override
    public Triplet<Boolean, String, Integer> getValue() {
        return this.internalValue;
    }

    @Override
    protected Triplet<Boolean, String, Integer> generateModelValue() {
        return new Triplet<Boolean, String, Integer>( includeFaction.getValue(), this.getLabel(), sectorCount.getValue().isEmpty() ? 0 : Integer.parseInt( sectorCount.getValue() ) );
    }

    @Override
    protected void setPresentationValue(Triplet<Boolean, String, Integer> newPresentationValue) {
        includeFaction.setValue( newPresentationValue.getValue0() );
        this.setLabel( newPresentationValue.getValue1() );
        sectorCount.setValue( newPresentationValue.getValue2().toString() );
        
    }
}
