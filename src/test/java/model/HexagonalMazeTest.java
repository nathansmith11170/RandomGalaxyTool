package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

public class HexagonalMazeTest {
    @Test
    public void HexagonalMaze_WithValidGrid_DoesNotThrow() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        new HexagonalMaze( grid, 1 , .10 );
    }

    @Test
    public void HexagonalMaze_GeneratesValidEdges() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        HexagonalMaze maze = new HexagonalMaze( grid, 1 , .10 );

        for( Entry<OddQHexCoord, ArrayList<OddQHexCoord>> entry : maze.maze.entrySet() ) {
            for( OddQHexCoord neighbor : entry.getValue() ) {
                assertTrue( maze.maze.get( neighbor ).contains( entry.getKey() ) );
            }
        }
    }
}
