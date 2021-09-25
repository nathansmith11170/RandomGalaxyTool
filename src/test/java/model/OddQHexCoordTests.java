package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OddQHexCoordTests {
    @Test
    public void Equals_WIthIdenticalCoords_ReturnsTrue() {
        OddQHexCoord one = new OddQHexCoord(1, 1);
        OddQHexCoord two = new OddQHexCoord(1, 1);

        assertTrue( one.equals(two) );
    }

    @Test void Equals_WithDifferingCoords_ReturnsFalse() {
        OddQHexCoord one = new OddQHexCoord(1, 1);
        OddQHexCoord two = new OddQHexCoord(2, 2);

        assertFalse( one.equals(two) );
    }
}
