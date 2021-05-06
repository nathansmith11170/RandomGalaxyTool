package application.views.generator;

import java.util.Iterator;

import javax.swing.plaf.PanelUI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginI18n.Form;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;

import application.views.main.MainView;

import configurationmodel.GeneratorConfig;

@Route(value = "generator", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Generator")
public class GeneratorView extends Div {

    public GeneratorView() {
        addClassName( "generator-view" );

        //Set up config panel
        SplitLayout layout = new SplitLayout();
        VerticalLayout configLayout = new VerticalLayout();
        VerticalLayout previewLayout = new VerticalLayout();
        layout.addToPrimary( configLayout );
        layout.addToSecondary( previewLayout );

        Label configurationLabel = new Label();
        configurationLabel.setWidth( "25%" );
        configurationLabel.add( "Configuration" );

        Label previewLabel = new Label();
        previewLabel.setWidth( "75%" );
        previewLabel.add( "Preview" );
        
        Image previewImage = new Image();
        
        layout.setOrientation( Orientation.HORIZONTAL );

        FormLayout configForm = new FormLayout();

        TextField sectorsTextField = new TextField();
        sectorsTextField.setId("clusters");
        sectorsTextField.setLabel( "Sectors:" );
        sectorsTextField.setHelperText("Integer value less than 100, but greater than 4.");
        sectorsTextField.addValueChangeListener( event -> { 
            try { 
                int sectors = Integer.parseInt( sectorsTextField.getValue() );
                if ( sectors > 100 ) {
                    sectorsTextField.setHelperText( "Value too large, enter smaller value." );
                }
                else if ( sectors < 4 ) {
                    sectorsTextField.setHelperText( "Value too small, enter larger value." );
                }
                else {
                    sectorsTextField.setHelperText("Value accepted.");
                }
            } catch(NumberFormatException e) {
                sectorsTextField.setHelperText( "Invalid contents, enter integer value" );
            }
            
        });

        configForm.add(sectorsTextField);

        Button generateButton = new Button();
        generateButton.setText( "Generate!" );
        generateButton.addClickListener( event -> {
            GeneratorConfig generatorConfig = new GeneratorConfig();
            Iterator<Component> formItems = configForm.getChildren().iterator();
            while( formItems.hasNext() ) {
                Component item = formItems.next();
                switch( item.getId().get() ) {
                    case "clusters":
                        TextField text = (TextField) item;
                        generatorConfig.clusters = Integer.parseInt( text.getValue() );
                        break;
                }
            }
            
            previewLabel.setVisible( false );
        } );

        configLayout.add( configurationLabel );
        configLayout.add( configForm );

        previewLayout.add( previewLabel );
        previewLayout.add( generateButton );

        add( layout );
    }

}
