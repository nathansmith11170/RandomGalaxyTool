package model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

public class HexagonalMaze {

    private static Map<String, AxialHexCoord> UnitDirections = new HashMap<String, AxialHexCoord>() {{
        put( "left",      new AxialHexCoord( -1,  0 ) );
        put( "right",     new AxialHexCoord(  1,  0 ) );
        put( "upright",   new AxialHexCoord(  1, -1 ) );
        put( "downright", new AxialHexCoord(  0,  1 ) );
        put( "upleft",    new AxialHexCoord(  0, -1 ) );
        put( "downleft",  new AxialHexCoord( -1,  1 ) );
    } };
    
    public IAxialHexGrid AxialHexGrid;

    public Map<AxialHexCoord, ArrayList<AxialHexCoord>> maze;

    public Set<AxialHexCoord> members;

    private Set<AxialHexCoord> deadZones;
    private Random rand;

    /**
     * This constructor takes an existing hexagonal grid and generates a maze from it. 
     * 
     * @param grid
     *      The grid which will be used for the maze.
     */
    public HexagonalMaze( IAxialHexGrid grid, int passes, double deadPercent ) {
        this.rand = new Random();
        this.rand.setSeed( System.currentTimeMillis() );
        this.AxialHexGrid = grid;
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
        Stack<AxialHexCoord> workingStack = new Stack<>();
        Set<AxialHexCoord> visited = new HashSet<>();

        // Random starting node
        Optional<AxialHexCoord> start = this.AxialHexGrid.members().stream()
                                                .skip( (int) (this.AxialHexGrid.members().size() * rand.nextDouble()) )
                                                .findFirst();
        if ( !start.isEmpty() ) {
            if( !this.maze.keySet().contains( start.get() ) ) {
                this.maze.put( start.get(), new ArrayList<AxialHexCoord>() );
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
            AxialHexCoord current = workingStack.pop();
            ArrayList<AxialHexCoord> neighbors = getNeighborsWithoutDeadZones( current );

            // If any neighbor is unvisited and not a deadzone, push current onto stack
            if ( neighbors.stream().anyMatch( (n) -> !visited.contains( n ) ) ) {
                workingStack.push( current );

                //Choose random unvisited neighbor
                ArrayList<AxialHexCoord> unvisitedNeighbors = new ArrayList<>(neighbors.stream().filter( (n) -> !visited.contains(n) ).collect(Collectors.toList()));
                Optional<AxialHexCoord> neighbor = unvisitedNeighbors.stream().skip( (int) (unvisitedNeighbors.size() * rand.nextDouble()) ).findFirst();
                
                // if edge doesn't exist, add edge between current and chosen neighbor
                if( !this.maze.keySet().contains( neighbor.get() ) ) {
                    this.maze.put( neighbor.get(), new ArrayList<AxialHexCoord>() );
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

    private ArrayList<AxialHexCoord> getNeighborsWithoutDeadZones( AxialHexCoord current ) {
        ArrayList<AxialHexCoord> temp = this.AxialHexGrid.grid().get( current );
        ArrayList<AxialHexCoord> result = new ArrayList<>( temp.stream().filter( (n) -> !this.deadZones.contains( n ) ).collect(Collectors.toList()) );
        return result;
    }

    public List<Cluster> toClusterList() {
        List<Cluster> result = new ArrayList<>();

        // Get the names and descriptions from resources
        Stack<Sector> namesAndDescriptions = new Stack<>();
        try{
            String obj = Files.readString( Path.of("..\\..\\resources\\XStars.json") );

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
        this.maze.entrySet().stream().forEach( (node) -> {
            Cluster temp = axialCoordToCluster( node.getKey() );
            List<Connection> connectionList = new ArrayList<>();
            temp.setId( String.valueOf( node.getKey().q() ) + " " + String.valueOf( node.getKey().r() ) );

            node.getValue().forEach( neighbor -> {
                Connection conn = new Connection();
                conn.setTargetClusterId( String.valueOf( neighbor.q() ) + " " + String.valueOf( neighbor.r() ) );
                conn.setConnectionType( getConnectionType( node.getKey(), neighbor ) );
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
        } );

        return result;
    }

    private Cluster axialCoordToCluster( AxialHexCoord axial ) {
        Cluster result = new Cluster();
        OddQHexCoord coordinates = cubeToOddQHexCoord( axialToCubeHexCoord(axial) );
        result.setX( coordinates.col() );
        result.setY( -1 * coordinates.row() );
        return result;
    }

    private CubeHexCoord axialToCubeHexCoord( AxialHexCoord axial) {
        int x = axial.q();
        int z = axial.r();
        int y = -x-z;
        return new CubeHexCoord( x, y, z );
    }

    private OddQHexCoord cubeToOddQHexCoord( CubeHexCoord cube ) {
        int col = cube.x();
        int row = cube.z() + Math.floorDiv( ( cube.x() - (cube.x()&1 ) ) , 2 );
        return new OddQHexCoord( col, row ); 
    }

    private ConnectionType getConnectionType( AxialHexCoord source, AxialHexCoord dest ) {
        AxialHexCoord unit = dest.diff( source );
        Map.Entry<String, AxialHexCoord> entry = UnitDirections.entrySet().stream().filter( (dir) -> dir.getValue().equals(unit) ).findFirst().orElseThrow();
        switch( entry.getKey() ) {
            case "up":
                return ConnectionType.N;
            case "down":
                return ConnectionType.S;
            case "upright":
                return ConnectionType.NE;
            case "upleft":
                return ConnectionType.NW;
            case "downright":
                return ConnectionType.SE;
            case "downleft":
                return ConnectionType.SW;
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