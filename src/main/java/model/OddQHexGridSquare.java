package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class OddQHexGridSquare implements IOddQHexGrid {
    
    /**
     * The {@code grid} is a map of {@code AxialHexCoord} hash codes to lists of {@code AxialHexCoord} that are the neighbors of each node
     */
    public Map<OddQHexCoord, ArrayList<OddQHexCoord>> grid;

    /**
     * the {@code members} is a set of {@code AxialHexCoord} that exist in the grid
     */
    public Set<OddQHexCoord> members;


    private static Map<String, OddQHexCoord> OddColUnitDirections = new HashMap<String, OddQHexCoord>() {{
        put( "N",   new OddQHexCoord(  0,  1 ) );
        put( "S",   new OddQHexCoord(  0, -1 ) );
        put( "NE",  new OddQHexCoord(  1,  1 ) );
        put( "NW",  new OddQHexCoord( -1,  1 ) );
        put( "SE",  new OddQHexCoord(  1,  0 ) );
        put( "SW",  new OddQHexCoord( -1,  0 ) );
    }};

    private static Map<String, OddQHexCoord> EvenColUnitDirections = new HashMap<String, OddQHexCoord>() {{
        put( "N",   new OddQHexCoord(  0,  1 ) );
        put( "S",   new OddQHexCoord(  0, -1 ) );
        put( "NE",  new OddQHexCoord(  1,  0 ) );
        put( "NW",  new OddQHexCoord( -1,  0 ) );
        put( "SE",  new OddQHexCoord(  1, -1 ) );
        put( "SW",  new OddQHexCoord( -1, -1 ) );
    }};

    /**
     * This constructor initializes an "odd-r" grid of hexagonal nodes with equal rows and columns.
     * 
     * @param targetSize
     *      This is the number of nodes that are desirable for the grid.
     */
    public OddQHexGridSquare( int targetSize ) {
        int square_root = nextGreatestPerfectSquare( targetSize );
        this.grid = new HashMap<OddQHexCoord, ArrayList<OddQHexCoord>>();
        this.members = new HashSet<OddQHexCoord>();

        makeSquareGrid( square_root );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<OddQHexCoord> getNeighbors(OddQHexCoord coordinate) {
        return grid.get( coordinate );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNeighbor(OddQHexCoord coordinate_one, OddQHexCoord coordinate_two) {
        return this.grid.get( coordinate_one ).contains( coordinate_two );
    }

    /**
     * This method creates an "odd-r" horizontal grid of hexagonal nodes with the same number of rows as columns.
     * 
     * @param side
     *      The number of nodes in a row or column of the square grid
     */
    public void makeSquareGrid(int side) {
        for( int col = 0; col < side; col++ ) {
            for( int row = 0; row < side; row++ ) {
                OddQHexCoord temp = new OddQHexCoord(col, row);
                this.grid.put( temp, new ArrayList<OddQHexCoord>() );
                this.members.add(temp);
            }
        }
        addSquareGridEdges( side );
    }

    /**
     * {@inheritDoc}
     */
    public Set<OddQHexCoord> members() {
        return this.members;
    }

    /**
     * {@inheritDoc}
     */
    public Map<OddQHexCoord, ArrayList<OddQHexCoord>> grid() {
        return this.grid;
    }

    private int nextGreatestPerfectSquare( int product ) {
        int square_root = 0;
        while( Math.pow(square_root, 2) < product ) {
            square_root++;
        }
        return square_root;
    }

    private void addSquareGridEdges( int side ) {
        for( int col = 0; col < side; col++ ) {
            for( int row = 0; row < side; row++ ) {
                OddQHexCoord current = new OddQHexCoord( col, row );
                //first column
                if( col == 0 ) {
                    // bottom row
                    if( row == 0 ) {
                        addEdge( current, "N" );
                        addEdge( current, "NE" );
                    }
                    // top row
                    else if ( row == side - 1 ) {
                        addEdge( current, "S" );
                        addEdge( current, "SE" );
                        addEdge( current, "NE" );
                    }
                    // other rows
                    else {
                        addEdge( current, "N" );
                        addEdge( current, "NE" );
                        addEdge( current, "SE" );
                        addEdge( current, "S" );
                    }
                }
                // last column if odd
                else if( col == side - 1 && (col&1) == 1) {
                    // bottom row
                    if( row == 0 ) {
                        addEdge( current, "N" );
                        addEdge( current, "NW" );
                        addEdge( current, "SW" );
                    }
                    // top row
                    else if ( row == side - 1 ) {
                        addEdge( current, "S" );
                        addEdge( current, "SW" );
                    }
                    // other rows
                    else {
                        addEdge( current, "N" );
                        addEdge( current, "NW" );
                        addEdge( current, "SW" );
                        addEdge( current, "S" );
                    }
                }
                // last column if even
                else if( col == side - 1 && (col&1) != 1) {
                    // bottom row
                    if( row == 0 ) {
                        addEdge( current, "N" );
                        addEdge( current, "NW" );
                    }
                    // top row
                    else if ( row == side - 1 ) {
                        addEdge( current, "S" );
                        addEdge( current, "SW" );
                        addEdge( current, "NW" );
                    }
                    // Other rows
                    else {
                        addEdge( current, "N" );
                        addEdge( current, "NW" );
                        addEdge( current, "SW" );
                        addEdge( current, "S" );
                    }
                }
                // other odd columns
                else if ( (col&1) == 1 ) {
                    // bottom row
                    if( row == 0 ) {
                        addEdge( current, "N" );
                        addEdge( current, "NE" );
                        addEdge( current, "NW" );
                        addEdge( current, "SW" );
                        addEdge( current, "SE" );
                    }
                    // top row
                    else if (row == side - 1 ) {
                        addEdge( current, "S" );
                        addEdge( current, "SE" );
                        addEdge( current, "SW" );
                    }
                    // Other rows
                    else {
                        addEdge( current, "S" );
                        addEdge( current, "SE" );
                        addEdge( current, "NE" );
                        addEdge( current, "N" );
                        addEdge( current, "NW" );
                        addEdge( current, "SW" );
                    }
                }
                //other even columns
                else if ( (col&1) != 1 ) {
                    // bottom row
                    if( row == 0 ) {
                        addEdge( current, "N" );
                        addEdge( current, "NE" );
                        addEdge( current, "NW" );
                    }
                    // Bottom row
                    else if (row == side - 1 ) {
                        addEdge( current, "S" );
                        addEdge( current, "SE" );
                        addEdge( current, "SW" );
                        addEdge( current, "NW" );
                        addEdge( current, "NE" );
                    }
                    // Other rows
                    else {
                        addEdge( current, "S" );
                        addEdge( current, "SE" );
                        addEdge( current, "NE" );
                        addEdge( current, "N" );
                        addEdge( current, "NW" );
                        addEdge( current, "SW" );
                    }
                }
            }
        }
    }

    private void addEdge(OddQHexCoord node, String direction) {
        OddQHexCoord unit;
        if( (node.col() & 1) != 1) {
            unit = EvenColUnitDirections.get( direction );
        }
        else {
            unit = OddColUnitDirections.get( direction );
        }
        OddQHexCoord neighbor = node.add( unit );
        if( members.contains(neighbor) ) {
            this.grid.get( node ).add( neighbor );
            return;
        }
        throw new IllegalArgumentException( String.format("You cannot add an edge between %s and %s because %s does not exist in the members\n %s", node.toString(), neighbor.toString(), neighbor.toString(), members.toString() ) );
    }
}