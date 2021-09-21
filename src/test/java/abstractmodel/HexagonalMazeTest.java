package abstractmodel;

import org.junit.jupiter.api.Test;

public class HexagonalMazeTest {
    @Test
    public void HexagonalMaze_WithValidGrid_DoesNotThrow() {
        IAxialHexGrid grid = new AxialHexMapSquare(25);

        new HexagonalMaze( grid );
    }
}
