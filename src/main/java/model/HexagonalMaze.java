package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import java.util.HashSet;
import java.util.Random;


public class HexagonalMaze {
    
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

}
