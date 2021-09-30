package com.application.views.randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.application.controllers.GeneratorController;
import com.application.views.randomizer.components.FactionSelectField;
import com.application.views.randomizer.events.BackEvent;
import com.application.views.randomizer.events.NextEvent;
import com.application.views.randomizer.events.PopulateEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import configurationmodel.RandomizerConfig;

public class FactionPlacementForm extends FormLayout {

    Label binderStatusLabel = new Label();
    FactionSelectField argonSelect = new FactionSelectField( "Argon Federation", "argon" );
    FactionSelectField antigoneSelect = new FactionSelectField( "Antigone Republic", "antigone" );
    FactionSelectField holyOrderSelect = new FactionSelectField( "Holy Order of the Pontifex", "holyorder" );
    FactionSelectField godrealmSelect = new FactionSelectField( "Godrealm of the Paranid", "paranid" );
    FactionSelectField teladiSelect = new FactionSelectField( "Teladi Company", "teladi" );
    FactionSelectField ministrySelect = new FactionSelectField( "Ministry of Finance", "ministry" );
    FactionSelectField zyarthSelect = new FactionSelectField( "Zyarth Patriarchy", "split" );
    FactionSelectField freeFamiliesSelect = new FactionSelectField( "Free Families", "freesplit" );
    FactionSelectField terranSelect = new FactionSelectField( "Terran Protectorate", "terran" );
    FactionSelectField segarisSelect = new FactionSelectField( "Segaris Pioneers", "pioneers" );
    FactionSelectField xenonSelect = new FactionSelectField( "Xenon", "xenon" );

    Checkbox connectedTerritoryCheckBox = new Checkbox( "Prioritize connected faction territory" );

    Binder<RandomizerConfig> binder = new Binder<>();

    RandomizerConfig config = new RandomizerConfig();

    Button back = new Button( "Back" );
    Button populate = new Button("Populate");
    Button next = new Button("Next");

    public FactionPlacementForm( GeneratorController controller ) {
        binder.forField( argonSelect )
            .bind( RandomizerConfig::getArgonSectors, RandomizerConfig::setArgonSectors );
        binder.forField( antigoneSelect )
            .bind( RandomizerConfig::getAntigoneSectors, RandomizerConfig::setAntigoneSectors );
        binder.forField( holyOrderSelect )
            .bind( RandomizerConfig::getHolyOrderSectors, RandomizerConfig::setHolyOrderSectors );
        binder.forField( godrealmSelect )
            .bind( RandomizerConfig::getGodrealmSectors, RandomizerConfig::setGodrealmSectors );
        binder.forField(teladiSelect)
            .bind( RandomizerConfig::getTeladiSectors, RandomizerConfig::setTeladiSectors );
        binder.forField( ministrySelect )
            .bind( RandomizerConfig::getMinistrySectors, RandomizerConfig::setMinistrySectors );
        binder.forField( zyarthSelect )
            .bind( RandomizerConfig::getZyarthSectors, RandomizerConfig::setZyarthSectors );
        binder.forField( freeFamiliesSelect )
            .bind( RandomizerConfig::getFreeFamiliesSectors, RandomizerConfig::setFreeFamiliesSectors );
        binder.forField( terranSelect )
            .bind( RandomizerConfig::getTerranSectors, RandomizerConfig::setTerranSectors );
        binder.forField( segarisSelect )
            .bind( RandomizerConfig::getSegarisSectors, RandomizerConfig::setSegarisSectors );
        binder.forField( xenonSelect )
            .bind( RandomizerConfig::getXenonShipyardCount, RandomizerConfig::setXenonShipyardCount );
        binder.forField( connectedTerritoryCheckBox )
            .bind( RandomizerConfig::getGenerateConnectedTerritory, RandomizerConfig::setGenerateConnectedTerritory);

        binder.setStatusLabel( binderStatusLabel );
        binder.withValidator( (bean) -> bean.getTotalOwnedSectors() < controller.getClusterCount(), "Owned sectors cannot exceed actual sector count." );


        binder.addValueChangeListener( event -> {
            List<String> enabled = new ArrayList<String>();
            
            for ( HasValue<?,?> field : binder.getFields().collect( Collectors.toList() ) ) {
                if( field instanceof FactionSelectField) {
                    FactionSelectField temp = (FactionSelectField) field;
                    if( temp.includeFaction.getValue() ) {
                        enabled.add( temp.getFaction() );
                    }
                }
            }
            this.config.setEnabledFactions( enabled );

            if( binder.isValid() ) {
                populate.setEnabled( true );
            }
            else {
                populate.setEnabled( false );
                next.setEnabled( false );
            }
        } );
        
        binder.setBean( this.config );

        add(
            binderStatusLabel,
            argonSelect,
            antigoneSelect,
            holyOrderSelect,
            godrealmSelect,
            teladiSelect,
            ministrySelect,
            zyarthSelect,
            freeFamiliesSelect,
            terranSelect,
            segarisSelect,
            xenonSelect,
            connectedTerritoryCheckBox,
            createButtonsLayout()
        );

    }

    @Override
    protected <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return super.addListener( eventType, listener );
    }

    private HorizontalLayout createButtonsLayout() {
        populate.addClickShortcut( Key.ENTER );
        populate.setEnabled( false );
        next.setEnabled( false );

        populate.addClickListener( ( event ) -> {
            next.setEnabled( true );
            fireEvent( new PopulateEvent( this, this.config ) );
        } );

        next.addClickListener( event -> fireEvent( new NextEvent( this, this.config ) ) );
        back.addClickListener( event -> fireEvent( new BackEvent( this, this.config ) ) );

        return new HorizontalLayout( back, populate, next );
    }
    
}
