package abstractmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
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
    public HexagonalMaze( IAxialHexGrid grid ) {
        this.AxialHexGrid = grid;
        this.maze = new HashMap<>();
        this.members = new HashSet<>();

        generateMaze();
    }

    private void generateMaze() {
        Random rand = new Random();
        rand.setSeed( System.currentTimeMillis() );

        HashSet<AxialHexCoord> visited = new HashSet<>();
        Stack<AxialHexCoord> workingStack = new Stack<>();

        Optional<AxialHexCoord> start = this.AxialHexGrid.members().stream().skip( (int) (this.AxialHexGrid.members().size() * rand.nextDouble()) ).findFirst();
        if ( !start.isEmpty() ) {
            visited.add( start.get() );
            this.maze.put( start.get(), new ArrayList<AxialHexCoord>() );
            workingStack.push( start.get() );
        }
        else {
            throw new IllegalStateException("This method cannot be called on an empty grid.");
        }

        while ( !workingStack.isEmpty() ) {
            AxialHexCoord current = workingStack.pop();
            ArrayList<AxialHexCoord> neighbors = this.AxialHexGrid.grid().get( current );

            if ( neighbors.stream().anyMatch( (n) -> !visited.contains( n ) ) ) {
                workingStack.push( current );
                Optional<AxialHexCoord> neighbor = neighbors.stream().skip( (int) ( neighbors.size() * rand.nextDouble() ) ).findFirst();
                
                if ( this.maze.keySet().contains( neighbor.get() ) ) {
                    this.maze.get( neighbor.get() ).add( current );
                    this.maze.get( current ).add( neighbor.get() );
                }
                else {
                    this.maze.put( neighbor.get(), new ArrayList<AxialHexCoord>() );
                    this.maze.get( neighbor.get() ).add( current );
                    this.maze.get( current ).add( neighbor.get() );
                }

                visited.add( neighbor.get() );
                workingStack.push( neighbor.get() );
            }
        }
    }
}
