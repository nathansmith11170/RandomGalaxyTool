package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OddQHexGridSquareTests {


    @Test
    public void OddQHexGridSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith4DesiredSize() {
        int expected = 4;

        OddQHexGridSquare test = new OddQHexGridSquare(4);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }

    @Test
    public void OddQHexGridSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith9DesiredSize() {
        int expected = 9;

        OddQHexGridSquare test = new OddQHexGridSquare(9);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }

    @Test
    public void OddQHexGridSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith16DesiredSize() {
        int expected = 16;

        OddQHexGridSquare test = new OddQHexGridSquare(16);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }


    @Test
    public void OddQHexGridSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith25DesiredSize() {
        int expected = 25;

        OddQHexGridSquare test = new OddQHexGridSquare(25);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }

    @Test
    public void OddQHexGridSquare_GeneratesVeryLargeGridWithoutErrors_OnConstructorWith2500DesiredSize() {
        int expected = 2500;

        OddQHexGridSquare test = new OddQHexGridSquare(2500);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }
    
    @Test
    public void OddQHexGridSquare_CreatesValidGrid_OnConstructor() {
        Map<Integer, ArrayList<OddQHexCoord>> expected = new HashMap<Integer, ArrayList<OddQHexCoord>>();
        
        OddQHexCoord one = new OddQHexCoord(0, 0);
        OddQHexCoord two = new OddQHexCoord(0, 1);
        OddQHexCoord three = new OddQHexCoord(0, 2);
        OddQHexCoord four = new OddQHexCoord(1, 0);
        OddQHexCoord five = new OddQHexCoord(1, 1);
        OddQHexCoord six = new OddQHexCoord(1, 2);
        OddQHexCoord seven = new OddQHexCoord(2, 0);
        OddQHexCoord eight = new OddQHexCoord(2, 1);
        OddQHexCoord nine = new OddQHexCoord(2, 2);

        ArrayList<OddQHexCoord> oneNeighbors = new ArrayList<OddQHexCoord>();
        oneNeighbors.add(two);
        oneNeighbors.add(four);
        expected.put( one.hashCode(), oneNeighbors);

        ArrayList<OddQHexCoord> twoNeighbors = new ArrayList<OddQHexCoord>();
        twoNeighbors.add(one);
        twoNeighbors.add(three);
        twoNeighbors.add(five);
        twoNeighbors.add(four);
        expected.put( two.hashCode(), twoNeighbors);

        ArrayList<OddQHexCoord> threeNeighbors = new ArrayList<OddQHexCoord>();
        threeNeighbors.add(two);
        threeNeighbors.add(six);
        threeNeighbors.add(five);
        expected.put( three.hashCode(), threeNeighbors);

        ArrayList<OddQHexCoord> fourNeighbors = new ArrayList<OddQHexCoord>();
        fourNeighbors.add(one);
        fourNeighbors.add(two);
        fourNeighbors.add(five);
        fourNeighbors.add(seven);
        fourNeighbors.add(eight);
        expected.put( four.hashCode(), fourNeighbors);

        ArrayList<OddQHexCoord> fiveNeighbors = new ArrayList<OddQHexCoord>();
        fiveNeighbors.add(two);
        fiveNeighbors.add(four);
        fiveNeighbors.add(three);
        fiveNeighbors.add(nine);
        fiveNeighbors.add(six);
        fiveNeighbors.add(eight);
        expected.put( five.hashCode(), fiveNeighbors);

        ArrayList<OddQHexCoord> sixNeighbors = new ArrayList<OddQHexCoord>();
        sixNeighbors.add(three);
        sixNeighbors.add(five);
        sixNeighbors.add(nine);
        expected.put( six.hashCode(), sixNeighbors );

        ArrayList<OddQHexCoord> sevenNeighbors = new ArrayList<OddQHexCoord>();
        sevenNeighbors.add(four);
        sevenNeighbors.add(eight);
        expected.put( seven.hashCode(), sevenNeighbors );

        ArrayList<OddQHexCoord> eightNeighbors = new ArrayList<OddQHexCoord>();
        eightNeighbors.add(seven);
        eightNeighbors.add(four);
        eightNeighbors.add(five);
        eightNeighbors.add(nine);
        expected.put( eight.hashCode(), eightNeighbors );

        ArrayList<OddQHexCoord> nineNeighbors = new ArrayList<OddQHexCoord>();
        nineNeighbors.add(eight);
        nineNeighbors.add(six);
        nineNeighbors.add(five);
        expected.put( nine.hashCode(), nineNeighbors );

        OddQHexGridSquare test = new OddQHexGridSquare(9);

        Map<OddQHexCoord, ArrayList<OddQHexCoord>> mapOfArrays = new HashMap<OddQHexCoord, ArrayList<OddQHexCoord>>();
        mapOfArrays.put(one, oneNeighbors);
        mapOfArrays.put(two, twoNeighbors);
        mapOfArrays.put(three, threeNeighbors);
        mapOfArrays.put(four, fourNeighbors);
        mapOfArrays.put(five, fiveNeighbors);
        mapOfArrays.put(six, sixNeighbors);
        mapOfArrays.put(seven, sevenNeighbors);
        mapOfArrays.put(eight, eightNeighbors);
        mapOfArrays.put(nine, nineNeighbors);

        boolean allArraysEqual = true;
        for(Map.Entry<OddQHexCoord, ArrayList<OddQHexCoord>> entry : test.grid.entrySet()) {
            if( ! ( mapOfArrays.get( entry.getKey() ).containsAll( entry.getValue() ) && entry.getValue().containsAll( mapOfArrays.get( entry.getKey() ) ) ) ) {
                allArraysEqual = false;
                break;
            }
        }
        assertTrue(allArraysEqual);
    }
}
