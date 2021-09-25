package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AxialHexCoordTests {
    @Test
    public void Equals_WIthIdenticalCoords_ReturnsTrue() {
        AxialHexCoord one = new AxialHexCoord(1, 1);
        AxialHexCoord two = new AxialHexCoord(1, 1);

        assertTrue( one.equals(two) );
    }

    @Test void Equals_WithDifferingCoords_ReturnsFalse() {
        AxialHexCoord one = new AxialHexCoord(1, 1);
        AxialHexCoord two = new AxialHexCoord(2, 2);

        assertFalse( one.equals(two) );
    }
}
