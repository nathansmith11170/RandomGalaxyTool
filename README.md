# X4 Galaxy Generator

This is a simple web UI for a tool that generates diffs for a new galaxy map for X4 Foundations. Collaborated with user Celludriel on Egosoft discord for X4 galaxy model and xml generation.
Based on version 1.4.1 of his galaxy generation tool (https://github.com/Celludriel/X4_Universe_Generation_Tool), which takes configurations and produces the xml for X4.
My contribution being the UI and the random generation of the galaxy configurations.

This project was created from https://start.vaadin.com.

### Running the application from the command line.
To run from the command line, use `mvn` and open http://localhost:8080 in your browser.

## Project structure

- `MainView.java` in `src/main/java/com/application` contains the navigation setup. It uses [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java/com/application` contains the server-side Java views of your application.
- `model` package in `src/main/java/` contains Celludriel's model for the X4 galaxy in plain java objects, also the mathematical model for a hex graph, and logic for randomizing the map 
- 'configurationmodel' in 'src/main/java/' contains the configuration for the randomizer which lays out the map and places the factions, and any validators used for the forms
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.s
