package abstractmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;



public class GridTests {

    @Test
    public void PopulateRectangularGrid_DoesNotThrow() {
        new AxialHexGridSquare( 1 );
    }

    @Test
    public void MakeSquareGrid_With4x4NoDeadzones_CreatesProperGrid() {
        Hashtable<Integer, AxialHexCoord> reference = new Hashtable<Integer, AxialHexCoord>() {{
            put( new AxialHexCoord(0,0).hashCode(), new AxialHexCoord(0,0) );
            put( new AxialHexCoord(1,0).hashCode(), new AxialHexCoord(1,0) );
            put( new AxialHexCoord(2,0).hashCode(), new AxialHexCoord(2,0) );
            put( new AxialHexCoord(3,0).hashCode(), new AxialHexCoord(3,0) );
            put( new AxialHexCoord(0,1).hashCode(), new AxialHexCoord(0,1) );
            put( new AxialHexCoord(1,1).hashCode(), new AxialHexCoord(1,1) );
            put( new AxialHexCoord(2,1).hashCode(), new AxialHexCoord(2,1) );
            put( new AxialHexCoord(3,1).hashCode(), new AxialHexCoord(3,1) );
            put( new AxialHexCoord(-1,2).hashCode(), new AxialHexCoord(-1,2) );
            put( new AxialHexCoord(0,2).hashCode(), new AxialHexCoord(0,2) );
            put( new AxialHexCoord(1,2).hashCode(), new AxialHexCoord(1,2) );
            put( new AxialHexCoord(2,2).hashCode(), new AxialHexCoord(2,2) );
            put( new AxialHexCoord(-1,3).hashCode(), new AxialHexCoord(-1,3) );
            put( new AxialHexCoord(0,3).hashCode(), new AxialHexCoord(0,3) );
            put( new AxialHexCoord(1,3).hashCode(), new AxialHexCoord(1,3) );
            put( new AxialHexCoord(2,3).hashCode(), new AxialHexCoord(2,3) );
    }};

        AxialHexGridSquare unitUnderTest = new AxialHexGridSquare( 9 );

        assertTrue( Objects.deepEquals(unitUnderTest.Grid, reference) );
    }

    @Test
    public void GetNeighbors_WithValidParameters_DoesNotThrow() {
        AxialHexGridSquare unitUnderTest = new AxialHexGridSquare( 16 );

        unitUnderTest.getNeighbors( new AxialHexCoord(0,0) );
    }

    @Test
    public void GetNeighbors_WithOneOneAndLargeGrid_ReturnsProperNeighbors() {
        AxialHexGridSquare unitUnderTest = new AxialHexGridSquare( 16 );
        ArrayList<AxialHexCoord> reference = new ArrayList<AxialHexCoord>() {{
            add( new AxialHexCoord(1,0) );
            add( new AxialHexCoord(0,1) );
            add( new AxialHexCoord(2,0) );
            add( new AxialHexCoord(2,1) );
            add( new AxialHexCoord(1,2) );
            add( new AxialHexCoord(0,2) );
        }};

        ArrayList<AxialHexCoord> result = unitUnderTest.getNeighbors( new AxialHexCoord(1,1) );

        boolean allCoordsInReference = true;
        for ( AxialHexCoord coord : result ) {
            if( !reference.contains( coord ) ) {
                allCoordsInReference = false;
            }
        }
        assertTrue( allCoordsInReference );
    }
    
    @Test
    public void GetNeighbors_WithEdgeCoord_OmitsNonexistentNeighbors() {
        AxialHexGridSquare unitUnderTest = new AxialHexGridSquare( 16 );
        ArrayList<AxialHexCoord> reference = new ArrayList<AxialHexCoord>() {{
            add( new AxialHexCoord(0,1) );
            add( new AxialHexCoord(0,2) );
            add( new AxialHexCoord(-1, 3) );
        }};

        ArrayList<AxialHexCoord> result = unitUnderTest.getNeighbors( new AxialHexCoord(-1,2) );

        boolean allCoordsInReference = true;
        for ( AxialHexCoord coord : result ) {
            if( !reference.contains( coord ) ) {
                allCoordsInReference = false;
            }
        }
        assertTrue( allCoordsInReference );
    }

    @Test
    public void IsNeighbor_WithValidParams_DoesNotThrow() {
        AxialHexGridSquare unitUnderTest = new AxialHexGridSquare( 16 );

        unitUnderTest.isNeighbor( new AxialHexCoord(0,0), new AxialHexCoord(1,1) );
    }

    @Test
    public void IsNeighbor_WithNeighbors_ReturnsTrue() {
        AxialHexGridSquare unitUnderTest = new AxialHexGridSquare( 16 );

        assertTrue( unitUnderTest.isNeighbor( new AxialHexCoord(0,0), new AxialHexCoord(0,1) ) );
    }

    @Test
    public void IsNeighbor_WithNonNeighbors_ReturnsFalse() {
        AxialHexGridSquare unitUnderTest = new AxialHexGridSquare( 16 );

        assertFalse( unitUnderTest.isNeighbor( new AxialHexCoord(0,0), new AxialHexCoord(1,1) ) );
    }
}