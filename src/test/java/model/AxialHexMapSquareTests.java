package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AxialHexMapSquareTests {


    @Test
    public void AxialHexMapSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith4DesiredSize() {
        int expected = 4;

        AxialHexMapSquare test = new AxialHexMapSquare(4);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }

    @Test
    public void AxialHexMapSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith9DesiredSize() {
        int expected = 9;

        AxialHexMapSquare test = new AxialHexMapSquare(9);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }

    @Test
    public void AxialHexMapSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith16DesiredSize() {
        int expected = 16;

        AxialHexMapSquare test = new AxialHexMapSquare(16);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }


    @Test
    public void AxialHexMapSquare_GeneratesCorrectSizeOfGrid_OnConstructorWith25DesiredSize() {
        int expected = 25;

        AxialHexMapSquare test = new AxialHexMapSquare(25);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }

    @Test
    public void AxialHexMapSquare_GeneratesVeryLargeGridWithoutErrors_OnConstructorWith2500DesiredSize() {
        int expected = 2500;

        AxialHexMapSquare test = new AxialHexMapSquare(2500);

        assertEquals( expected, test.members.size() );
        assertEquals( expected, test.grid.values().size() );
    }
    
    @Test
    public void AxialHexMapSquare_CreatesValidGrid_OnConstructor() {
        Map<Integer, ArrayList<AxialHexCoord>> expected = new HashMap<Integer, ArrayList<AxialHexCoord>>();
        
        AxialHexCoord one = new AxialHexCoord(0, 0);
        AxialHexCoord two = new AxialHexCoord(1, 0);
        AxialHexCoord three = new AxialHexCoord(2, 0);
        AxialHexCoord four = new AxialHexCoord(0, 1);
        AxialHexCoord five = new AxialHexCoord(1, 1);
        AxialHexCoord six = new AxialHexCoord(2, 1);
        AxialHexCoord seven = new AxialHexCoord(-1, 2);
        AxialHexCoord eight = new AxialHexCoord(0, 2);
        AxialHexCoord nine = new AxialHexCoord(1, 2);

        ArrayList<AxialHexCoord> oneNeighbors = new ArrayList<AxialHexCoord>();
        oneNeighbors.add(two);
        oneNeighbors.add(four);
        expected.put( one.hashCode(), oneNeighbors);

        ArrayList<AxialHexCoord> twoNeighbors = new ArrayList<AxialHexCoord>();
        twoNeighbors.add(one);
        twoNeighbors.add(three);
        twoNeighbors.add(four);
        twoNeighbors.add(five);
        expected.put( two.hashCode(), twoNeighbors);

        ArrayList<AxialHexCoord> threeNeighbors = new ArrayList<AxialHexCoord>();
        threeNeighbors.add(two);
        threeNeighbors.add(five);
        threeNeighbors.add(six);
        expected.put( three.hashCode(), threeNeighbors);

        ArrayList<AxialHexCoord> fourNeighbors = new ArrayList<AxialHexCoord>();
        fourNeighbors.add(one);
        fourNeighbors.add(two);
        fourNeighbors.add(five);
        fourNeighbors.add(seven);
        fourNeighbors.add(eight);
        expected.put( four.hashCode(), fourNeighbors);

        ArrayList<AxialHexCoord> fiveNeighbors = new ArrayList<AxialHexCoord>();
        fiveNeighbors.add(two);
        fiveNeighbors.add(three);
        fiveNeighbors.add(four);
        fiveNeighbors.add(six);
        fiveNeighbors.add(eight);
        fiveNeighbors.add(nine);
        expected.put( five.hashCode(), fiveNeighbors);

        ArrayList<AxialHexCoord> sixNeighbors = new ArrayList<AxialHexCoord>();
        sixNeighbors.add(three);
        sixNeighbors.add(five);
        sixNeighbors.add(nine);
        expected.put( six.hashCode(), sixNeighbors );

        ArrayList<AxialHexCoord> sevenNeighbors = new ArrayList<AxialHexCoord>();
        sevenNeighbors.add(four);
        sevenNeighbors.add(eight);
        expected.put( seven.hashCode(), sevenNeighbors );

        ArrayList<AxialHexCoord> eightNeighbors = new ArrayList<AxialHexCoord>();
        eightNeighbors.add(seven);
        eightNeighbors.add(four);
        eightNeighbors.add(five);
        eightNeighbors.add(nine);
        expected.put( eight.hashCode(), eightNeighbors );

        ArrayList<AxialHexCoord> nineNeighbors = new ArrayList<AxialHexCoord>();
        nineNeighbors.add(eight);
        nineNeighbors.add(six);
        nineNeighbors.add(five);
        expected.put( nine.hashCode(), nineNeighbors );

        AxialHexMapSquare test = new AxialHexMapSquare(9);

        Map<AxialHexCoord, ArrayList<AxialHexCoord>> mapOfArrays = new HashMap<AxialHexCoord, ArrayList<AxialHexCoord>>();
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
        for(Map.Entry<AxialHexCoord, ArrayList<AxialHexCoord>> entry : test.grid.entrySet()) {
            if( ! ( mapOfArrays.get( entry.getKey() ).containsAll( entry.getValue() ) && entry.getValue().containsAll( mapOfArrays.get( entry.getKey() ) ) ) ) {
                allArraysEqual = false;
                break;
            }
        }
        assertTrue(allArraysEqual);
    }
}
