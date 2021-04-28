package abstractmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;



public class GridTests {

    public record AxialHexCoord (int q, int r) {}

    @Test
    public void PopulateRectangularGrid_DoesNotThrow() {
        new AxialHexGrid( 1, 1 );
    }

    @Test
    public void PopulateRectangularGrid_With4x4_CreatesProperGrid() {
        AxialHexCoord[][] reference = new AxialHexCoord[][] {
            { null, new AxialHexCoord(0,0), new AxialHexCoord(1,0), new AxialHexCoord(2,0), new AxialHexCoord(3,0) },
            { null, new AxialHexCoord(0,1), new AxialHexCoord(1,1), new AxialHexCoord(2,1), new AxialHexCoord(3,1) },
            { new AxialHexCoord(-1,2), new AxialHexCoord(0,2), new AxialHexCoord(1,2), new AxialHexCoord(2,2), null },
            { new AxialHexCoord(-1,3), new AxialHexCoord(0,3), new AxialHexCoord(1,3), new AxialHexCoord(2,3), null }
        };

        AxialHexGrid unitUnderTest = new AxialHexGrid(4,4);

        assertTrue( Arrays.deepEquals(unitUnderTest.Grid, reference) );
    }

    @Test
    public void GetNeighbors_WithValidParameters_DoesNotThrow() {
        
    }

    @Test
    public void GetNeighbors_WithZeroZeroAndLargeGrid_ReturnsProperNeighbors() {

    }
    
    @Test
    public void GetNeighbors_WithEdgeCoord_OmitsNonexistentNeighbors() {

    }

    @Test
    public void IsNeighbor_WithValidParams_DoesNotThrow() {

    }

    @Test
    public void IsNeighbor_WithNeighbors_ReturnsTrue() {

    }

    @Test
    public void IsNeighbor_WithNonNeighbors_ReturnsFalse() {

    }
}