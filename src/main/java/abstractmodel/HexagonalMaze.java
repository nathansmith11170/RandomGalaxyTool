package abstractmodel;

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
    
    public IAxialHexGrid AxialHexGrid;

    public Map<AxialHexCoord, ArrayList<AxialHexCoord>> maze;

    public Set<AxialHexCoord> members;

    /**
     * This constructor takes an existing hexagonal grid and generates a maze from it. 
     * 
     * @param grid
     *      The grid which will be used for the maze.
     */
    public HexagonalMaze( IAxialHexGrid grid, int passes ) {
        this.AxialHexGrid = grid;
        this.maze = new HashMap<>();
        this.members = new HashSet<>();

        for (int i = 0; i < passes; i++) {
            generateMaze();
        }
    }

    private void generateMaze() {
        Random rand = new Random();
        rand.setSeed( System.currentTimeMillis() );

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
            ArrayList<AxialHexCoord> neighbors = this.AxialHexGrid.grid().get( current );

            // If any neighbor is unvisited, push current onto stack
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
}
