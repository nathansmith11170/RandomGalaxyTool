package model;

import org.junit.jupiter.api.Test;

public class HexagonalMazeTest {
    @Test
    public void HexagonalMaze_WithValidGrid_DoesNotThrow() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        new HexagonalMaze( grid, 1 , .10 );
    }
}
