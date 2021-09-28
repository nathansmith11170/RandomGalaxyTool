package com.application.views.generator;

import com.application.views.generator.components.FactionSelectField;
import com.application.views.generator.components.IntToFactionSelectFieldConverter;
import com.application.views.generator.events.PopulateEvent;
import com.application.views.generator.events.NextEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import configurationmodel.GeneratorConfig;

public class FactionPlacementForm extends FormLayout {

    FactionSelectField argonSelect = new FactionSelectField( "Argon Federation" );
    FactionSelectField antigoneSelect = new FactionSelectField( "Antigone Republic" );
    FactionSelectField holyOrderSelect = new FactionSelectField( "Holy Order of the Pontifex" );
    FactionSelectField godrealmSelect = new FactionSelectField( "Godrealm of the Paranid" );
    FactionSelectField teladiSelect = new FactionSelectField( "Teladi Company" );
    FactionSelectField ministrySelect = new FactionSelectField( "Ministry of Finance" );
    FactionSelectField zyarthSelect = new FactionSelectField( "Zyarth Patriarchy" );
    FactionSelectField freeFamiliesSelect = new FactionSelectField( "Free Families" );
    FactionSelectField terranSelect = new FactionSelectField( "Terran Protectorate" );
    FactionSelectField segarisSelect = new FactionSelectField( "Segaris Pioneers" );

    Binder<GeneratorConfig> binder = new Binder<>();

    GeneratorConfig config = new GeneratorConfig();

    Button populate = new Button("Populate");
    Button next = new Button("Next");

    public FactionPlacementForm( ) {
        binder.forField( argonSelect )
            .withConverter( new IntToFactionSelectFieldConverter( argonSelect ) )
            .bind( GeneratorConfig::getArgonSectors, GeneratorConfig::setArgonSectors );
        binder.forField( antigoneSelect )
            .withConverter( new IntToFactionSelectFieldConverter( antigoneSelect ) )
            .bind( GeneratorConfig::getAntigoneSectors, GeneratorConfig::setAntigoneSectors );
        binder.forField( holyOrderSelect )
            .withConverter( new IntToFactionSelectFieldConverter( holyOrderSelect ) )
            .bind( GeneratorConfig::getHolyOrderSectors, GeneratorConfig::setHolyOrderSectors );
        binder.forField( godrealmSelect )
            .withConverter( new IntToFactionSelectFieldConverter( godrealmSelect ) )
            .bind( GeneratorConfig::getGodrealmSectors, GeneratorConfig::setGodrealmSectors );
        binder.forField(teladiSelect)
            .withConverter( new IntToFactionSelectFieldConverter( teladiSelect ) )
            .bind( GeneratorConfig::getTeladiSectors, GeneratorConfig::setTeladiSectors );
        binder.forField( ministrySelect )
            .withConverter( new IntToFactionSelectFieldConverter( ministrySelect ) )
            .bind( GeneratorConfig::getMinistrySectors, GeneratorConfig::setMinistrySectors );
        binder.forField( zyarthSelect )
            .withConverter( new IntToFactionSelectFieldConverter( zyarthSelect ) )
            .bind( GeneratorConfig::getZyarthSectors, GeneratorConfig::setZyarthSectors );
        binder.forField( freeFamiliesSelect )
            .withConverter( new IntToFactionSelectFieldConverter( freeFamiliesSelect ) )
            .bind( GeneratorConfig::getFreeFamiliesSectors, GeneratorConfig::setFreeFamiliesSectors );
        binder.forField( terranSelect )
            .withConverter( new IntToFactionSelectFieldConverter( terranSelect ) )
            .bind( GeneratorConfig::getTerranSectors, GeneratorConfig::setTerranSectors );
        binder.forField( segarisSelect )
            .withConverter( new IntToFactionSelectFieldConverter( segarisSelect ) )
            .bind( GeneratorConfig::getSegarisSectors, GeneratorConfig::setSegarisSectors );
        binder.setBean( this.config );

        add(
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

        return new HorizontalLayout( populate, next );
    }
    
}
