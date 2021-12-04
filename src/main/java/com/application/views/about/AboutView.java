package com.application.views.about;

import com.application.views.main.MainView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        addClassName("about-view");
            // Paragraphs of text.
            H3 header = new H3( "Description" );
            Paragraph p1 = new Paragraph( "This web app is a tool that allows you to create a galaxy map for the game X4 Foundations. It requires both of the DLCs, Split Vendetta and Cradle of Humanity, because I used the asset files from those.\n" );
            H3 header2 = new H3( "Usage Tips" );
            Paragraph p2 = new Paragraph( "I limited the number of clusters to 256 because I am using the free tier of heroku and wanted to limit the computational stress. Even at 256, you may notice performance issues in game. The engine used by X4 struggles with large sector counts, large ship count, and large faction count. So keep that in mind as you design your galaxy." );
            Paragraph p3 = new Paragraph( "I should note that, at this time, non-Xenon factions struggle to have a good game if they start with fewer than 3-5 sectors. This is because the actual production stations that they will receive at game start are rather randomly chosen if they have too few zones under their control, and they might not even start with the ability to build additional stations." );
            Paragraph p4 = new Paragraph( "Also, Xenon have a shipyard and a wharf in every sector they own at game start, because I did not have the Xenon sectors spawn adjacent to one another. I would caution against adding too many Xenon sectors, because they expand more quickly than other factions.\n" );
            H3 header3 = new H3( "Mod Compatibility" );
            Paragraph p5 = new Paragraph( "I am working on making certain mods compatible with this, particularly Mysterial's Faction enhancer, DeadAir's Dynamic Wars, and VRO. However, this will take time." );
            Paragraph p6 = new Paragraph( "I have tested the following mods and found no issues: Sector Satellites, Sir Nuke's Mod Support APIs, Sir Nuke's Station Kill Helper, Sir Nuke's Remove Dock Glow, Sir Nuke's Remove Dock Symbol, Faction Enhancer Econ AI Module." );
            Paragraph p7 = new Paragraph( "I am aware of the following issues with mod compatibility: DeadAir Dynamic War has events for factions that don't exist, Faction Enhancer base module causes factions to spawn mid-game.\n" );
            H3 header4 = new H3( "Special Thanks" );
            Paragraph p8 =  new Paragraph( "Special thanks to Celludriel, who designed most of the xml templates that I used to create the game files. Also, a huge thanks to DeadAir, without whose modding experience and advice this application would not have been possible. And, of course, thanks to all the modders on the Egosoft Discord and all the wonderful fellows who will check out my work.\n" );
            H3 header5 = new H3( "Source, Bug Reporting" );
            Paragraph p9 = new Paragraph( "Check out my source over on Git! Also, if you experience any issues using this application please report them under the issues tab of the git repository. Thank you." );
            Anchor git = new Anchor( "https://github.com/nathansmith11170/RandomGalaxyTool", "Git Repo" );

            add( header );
            add( p1 );
            add( header2 );
            add( p2 );
            add( p3 );
            add( p4 );
            add( header3 );
            add( p5 );
            add( p6 );
            add( p7 );
            add( header4 );
            add( p8 );
            add( header5 );
            add( p9 );
            add( git );

    }

}
