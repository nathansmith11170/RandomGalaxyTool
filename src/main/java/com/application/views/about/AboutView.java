package com.application.views.about;

import com.application.views.main.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        addClassName("about-view");
        add(new TextArea() {{
            setValue("This application is in development. The development roadmap is:\n"
                    +"Generate a psuedorandom strongly connected graph with up to 6 neighbors for each node -> completed\n"
                    +"Map the graph generated to the coordinate system used in X4 and construct the Cluster array in Galaxy from Celludriel's model -> Complete\n"
                    +"Get user input on factions, minor factions - In Progress\n"
                    +"Assign faction locations, place stations\n"
                    +"Assign sector resources, populations, zones\n"
                    +"Potentially generate faction jobs for the generated map (may be impossible or inordinately difficult)\n"
                    +"Supply resulting JSON to Celludriel's tool, get game files, offer download\n");
            setWidth("80%");
            setEnabled(false); 
        }} );
    }

}
