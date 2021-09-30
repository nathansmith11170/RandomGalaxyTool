package model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

public class HexagonalMaze {

    private static Map<String, OddQHexCoord> OddColUnitDirections = new HashMap<String, OddQHexCoord>() {{
        put( "N",   new OddQHexCoord(  0,  1 ) );
        put( "S",   new OddQHexCoord(  0, -1 ) );
        put( "NE",  new OddQHexCoord(  1,  1 ) );
        put( "NW",  new OddQHexCoord( -1,  1 ) );
        put( "SE",  new OddQHexCoord(  1,  0 ) );
        put( "SW",  new OddQHexCoord( -1,  0 ) );
    }};

    private static Map<String, OddQHexCoord> EvenColUnitDirections = new HashMap<String, OddQHexCoord>() {{
        put( "N",   new OddQHexCoord(  0,  1 ) );
        put( "S",   new OddQHexCoord(  0, -1 ) );
        put( "NE",  new OddQHexCoord(  1,  0 ) );
        put( "NW",  new OddQHexCoord( -1,  0 ) );
        put( "SE",  new OddQHexCoord(  1, -1 ) );
        put( "SW",  new OddQHexCoord( -1, -1 ) );
    }};
    
    public IOddQHexGrid OddQHexGrid;

    public Map<OddQHexCoord, ArrayList<OddQHexCoord>> maze;

    public Set<OddQHexCoord> members;

    private Set<OddQHexCoord> deadZones;
    private Random rand;

    /**
     * This constructor takes an existing hexagonal grid and generates a maze from it. 
     * 
     * @param grid
     *      The grid which will be used for the maze.
     * @param passes
     *      The number of times the recursive backtracker will run, adding edges that don't already exist
     * @Param deadPercent
     *      The percent of the grid that will be marked impassable before the recursive backtracker runs
     */
    public HexagonalMaze( IOddQHexGrid grid, int passes, double deadPercent ) {
        this.rand = new Random();
        this.rand.setSeed( System.currentTimeMillis() );
        this.OddQHexGrid = grid;
        this.deadZones = new HashSet<>();

        // Deaden a percent of the grid
        int numDeadZones = (int) Math.floor( grid.members().size() * deadPercent );
        for ( int i = 0; i < numDeadZones; i++ ) {
            deadZones.add( grid.grid().keySet().stream().skip( (int) (grid.members().size() * this.rand.nextDouble()) ).findFirst().get() );
        }

        this.maze = new HashMap<>();
        this.members = new HashSet<>();

        for (int i = 0; i < passes; i++) {
            generateMaze();
        }
    }

    private void generateMaze() {
        Stack<OddQHexCoord> workingStack = new Stack<>();
        Set<OddQHexCoord> visited = new HashSet<>();

        // Random starting node
        Optional<OddQHexCoord> start = this.OddQHexGrid.members().stream()
                                                .skip( (int) (this.OddQHexGrid.members().size() * rand.nextDouble()) )
                                                .findFirst();
        if ( !start.isEmpty() ) {
            if( !this.maze.keySet().contains( start.get() ) ) {
                this.maze.put( start.get(), new ArrayList<OddQHexCoord>() );
            }
            visited.add( start.get() );
            this.members.add( start.get() );
            workingStack.push( start.get() );
        }
        else {
            throw new IllegalStateException("This method cannot be called on an empty grid.");
        }

        // While there are nodes in the stack
        while ( !workingStack.isEmpty() ) {

            // Pop top node, get neighbors
            OddQHexCoord current = workingStack.pop();
            ArrayList<OddQHexCoord> neighbors = getNeighborsWithoutDeadZones( current );

            // If any neighbor is unvisited and not a deadzone, push current onto stack
            if ( neighbors.stream().anyMatch( (n) -> !visited.contains( n ) ) ) {
                workingStack.push( current );

                //Choose random unvisited neighbor
                ArrayList<OddQHexCoord> unvisitedNeighbors = new ArrayList<>(neighbors.stream().filter( (n) -> !visited.contains(n) ).collect(Collectors.toList()));
                Optional<OddQHexCoord> neighbor = unvisitedNeighbors.stream().skip( (int) (unvisitedNeighbors.size() * rand.nextDouble()) ).findFirst();
                
                // if edge doesn't exist, add edge between current and chosen neighbor
                if( !this.maze.keySet().contains( neighbor.get() ) ) {
                    this.maze.put( neighbor.get(), new ArrayList<OddQHexCoord>() );
                }
                visited.add( neighbor.get() );
                
                this.members.add( neighbor.get() );
                
                if( !this.maze.get( neighbor.get() ).contains( current ) ) {
                    this.maze.get( neighbor.get() ).add( current );
                    this.maze.get( current ).add( neighbor.get() );
                }

                // place neigbor onto stack
                workingStack.push( neighbor.get() );
            }
        }
    }

    private ArrayList<OddQHexCoord> getNeighborsWithoutDeadZones( OddQHexCoord current ) {
        ArrayList<OddQHexCoord> temp = this.OddQHexGrid.grid().get( current );
        ArrayList<OddQHexCoord> result = new ArrayList<>( temp.stream().filter( (n) -> !this.deadZones.contains( n ) ).collect(Collectors.toList()) );
        return result;
    }

    public List<Cluster> toClusterList() {
        List<Cluster> result = new ArrayList<>();

        // Get the names and descriptions from resources
        Stack<Sector> namesAndDescriptions = new Stack<>();
        try{
            String obj = Files.readString( Path.of("./src/main/java/model/XStars.json") );

            JSONObject jsonObject = new JSONObject( new JSONTokener(obj) );

            JSONArray rawSectors = (JSONArray) jsonObject.get("Sectors");

            List<Sector> sectors = new ArrayList<>();
            rawSectors.forEach( (jObj) -> {
                JSONObject item = (JSONObject) jObj;
                sectors.add( new Sector( item.get("Name").toString(), item.get("Description").toString() ) );
            } );

            Collections.shuffle(sectors );
            Iterator<Sector> iterator = sectors.iterator();

            while( iterator.hasNext() ) {
                namesAndDescriptions.push(  iterator.next() );
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // Add each node to cluster list with an id
        // Set connections for each cluster
        // Set name and description

        for( Entry<OddQHexCoord, ArrayList<OddQHexCoord>> entry : this.maze.entrySet() ) {
            Cluster temp = oddQCoordToCluster( entry.getKey() );
            List<Connection> connectionList = new ArrayList<>();
            temp.setId( String.valueOf( entry.getKey().col() ) + " " + String.valueOf( entry.getKey().row() ) );

            entry.getValue().forEach( neighbor -> {
                Connection conn = new Connection();
                conn.setTargetClusterId( String.valueOf( neighbor.col() ) + " " + String.valueOf( neighbor.row() ) );
                conn.setConnectionType( getConnectionType( entry.getKey(), neighbor ) );
                connectionList.add( conn );
            } );

            temp.setConnections( connectionList );

            if( namesAndDescriptions.size() != 0) {
                Sector name = namesAndDescriptions.pop();
                temp.setName( name.Name );
                temp.setDescription( name.Description );
            }
            else {
                temp.setName( String.format("Unknown Sector %s", temp.getId().replace(' ', '-') ) );
                temp.setDescription( "No description available." );
            }

            result.add( temp );
        }

        return result;
    }

    private Cluster oddQCoordToCluster( OddQHexCoord oddQ ) {
        Cluster result = new Cluster();
        result.setX( oddQ.col() );
        result.setY( oddQ.row() );
        return result;
    }

    private ConnectionType getConnectionType( OddQHexCoord source, OddQHexCoord dest ) {
        Cluster sourceX4Offset = oddQCoordToCluster( source );
        Cluster destX4Offset = oddQCoordToCluster( dest );
        
        int unitX = destX4Offset.getX() - sourceX4Offset.getX();
        int unitY = destX4Offset.getY() - sourceX4Offset.getY();
        OddQHexCoord x4UnitDirection = new OddQHexCoord( unitX, unitY );
        Map.Entry<String, OddQHexCoord> entry;
        if( (source.col()&1) != 1 ) {
            entry = EvenColUnitDirections.entrySet().stream()
                .filter( (dir) -> dir.getValue().equals( x4UnitDirection ) )
                .findFirst().orElseThrow( () -> { 
                    throw new NoSuchElementException( 
                        String.format( "Unit direction %s from (%s,%s) to (%s,%s)", 
                            x4UnitDirection.toString(), 
                            sourceX4Offset.getX(), 
                            sourceX4Offset.getY(), 
                            destX4Offset.getX(), 
                            destX4Offset.getY() 
                        ) );
                } );
        }
        else {
            entry = OddColUnitDirections.entrySet().stream()
            .filter( (dir) -> dir.getValue().equals( x4UnitDirection ) )
            .findFirst().orElseThrow( () -> { 
                throw new NoSuchElementException( 
                    String.format( "Unit direction %s from (%s,%s) to (%s,%s)", 
                        x4UnitDirection.toString(), 
                        sourceX4Offset.getX(), 
                        sourceX4Offset.getY(), 
                        destX4Offset.getX(), 
                        destX4Offset.getY() 
                    ) );
            } );
        }
        
        switch( entry.getKey() ) {
            case "N":
                return ConnectionType.N;
            case "S":
                return ConnectionType.S;
            case "NE":
                return ConnectionType.NE;
            case "SW":
                return ConnectionType.SW;
            case "SE":
                return ConnectionType.SE;
            case "NW":
                return ConnectionType.NW;
            default:
                return ConnectionType.CUSTOM;
        }
    }
}

class Sector {
    final String Name;
    final String Description;

    Sector( String name, String desc ) {
        this.Name = name;
        this.Description = desc;
    }

    public String getName() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }
}