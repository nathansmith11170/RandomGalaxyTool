package application.views.generator;

import java.util.Iterator;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller.ScrollDirection;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout.Orientation;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;

import abstractmodel.AxialHexMapSquare;
import abstractmodel.HexagonalMaze;
import abstractmodel.MazeBitmap;
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

        TextField generatorPasses = new TextField();
        generatorPasses.setId("passes");
        generatorPasses.setLabel( "Gate Density:" );
        generatorPasses.setHelperText("Integer value between 1 and 5.");
        generatorPasses.addValueChangeListener( event -> { 
            try { 
                int passes = Integer.parseInt( generatorPasses.getValue() );
                if ( passes > 5 ) {
                    generatorPasses.setHelperText( "Value too large, enter smaller value." );
                }
                else if ( passes < 1 ) {
                    generatorPasses.setHelperText( "Value too small, enter larger value." );
                }
                else {
                    generatorPasses.setHelperText("Value accepted.");
                }
            } catch(NumberFormatException e) {
                generatorPasses.setHelperText( "Invalid contents, enter integer value" );
            }
            
        });

        configForm.add(sectorsTextField);
        configForm.add(generatorPasses);

        Scroller mapPreviewContainer = new Scroller();
        Image mapPreview = new Image();

        mapPreviewContainer.setContent( mapPreview );
        mapPreviewContainer.setScrollDirection( ScrollDirection.BOTH );
        mapPreviewContainer.setVisible( false );

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
                    case "passes":
                        TextField text2 = (TextField) item;
                        generatorConfig.passes = Integer.parseInt( text2.getValue() );
                }
            }
            AxialHexMapSquare grid = new AxialHexMapSquare(generatorConfig.clusters);
            HexagonalMaze map = new HexagonalMaze( grid, generatorConfig.passes );
            MazeBitmap mapImg = new MazeBitmap(map);

            StreamResource previewResource = new StreamResource( "preview.png", () -> mapImg.getStream());

            mapPreview.setSrc( previewResource );

            mapPreviewContainer.setVisible( true );
            previewLabel.setVisible( false );
        } );

        configLayout.add( configurationLabel );
        configLayout.add( configForm );

        previewLayout.add( previewLabel );
        previewLayout.add( mapPreviewContainer );
        previewLayout.add( generateButton );

        add( layout );
    }

}
