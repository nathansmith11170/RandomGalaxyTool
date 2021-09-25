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


    private static Map<String, OddQHexCoord> UnitDirections = new HashMap<String, OddQHexCoord>() {{
        put( "N",   new OddQHexCoord(  0, -1 ) );
        put( "S",   new OddQHexCoord(  0,  1 ) );
        put( "even-NE",  new OddQHexCoord(  1, -1 ) );
        put( "even-NW",  new OddQHexCoord( -1, -1 ) );
        put( "even-SE",  new OddQHexCoord(  1,  0 ) );
        put( "even-SW",  new OddQHexCoord( -1,  0 ) );
        put( "odd-NE",  new OddQHexCoord(  1,   0 ) );
        put( "odd-NW",  new OddQHexCoord( -1,   0 ) );
        put( "odd-SE",  new OddQHexCoord(  1,   1 ) );
        put( "odd-SW",  new OddQHexCoord( -1,   1 ) );
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
                    // top row
                    if( row == 0 ) {
                        addEdge( current, "S" );
                        addEdge( current, "even-SE" );
                    }
                    // bottom row
                    else if ( row == side -1 ) {
                        addEdge( current, "N" );
                        addEdge( current, "even-NE" );
                        addEdge( current, "even-SE" );
                    }
                    // other rows
                    else {
                        addEdge( current, "N" );
                        addEdge( current, "even-NE" );
                        addEdge( current, "even-SE" );
                        addEdge( current, "S" );
                    }
                }
                // last column if odd
                else if( col == side - 1 && (col&1) == 1) {
                    // top row
                    if( row == 0 ) {
                        addEdge( current, "S" );
                        addEdge( current, "odd-NW" );
                        addEdge( current, "odd-SW" );
                    }
                    // bottom row
                    else if ( row == side -1 ) {
                        addEdge( current, "N" );
                        addEdge( current, "odd-NW" );
                    }
                    // other rows
                    else {
                        addEdge( current, "N" );
                        addEdge( current, "odd-NW" );
                        addEdge( current, "odd-SW" );
                        addEdge( current, "S" );
                    }
                }
                // last column if even
                else if( col == side - 1 && (col&1) != 1) {
                    // Top row
                    if( row == 0 ) {
                        addEdge( current, "S" );
                        addEdge( current, "even-SW" );
                    }
                    // Bottom row
                    else if ( row == side -1 ) {
                        addEdge( current, "N" );
                        addEdge( current, "even-SW" );
                        addEdge( current, "even-NW" );
                    }
                    // Other rows
                    else {
                        addEdge( current, "N" );
                        addEdge( current, "even-NW" );
                        addEdge( current, "even-SW" );
                        addEdge( current, "S" );
                    }
                }
                // other odd columns
                else if ( (col&1) == 1 ) {
                    // Top row
                    if( row == 0 ) {
                        addEdge( current, "S" );
                        addEdge( current, "odd-NE" );
                        addEdge( current, "odd-NW" );
                        addEdge( current, "odd-SW" );
                        addEdge( current, "odd-SE" );
                    }
                    // Bottom row
                    else if (row == side -1 ) {
                        addEdge( current, "N" );
                        addEdge( current, "odd-NE" );
                        addEdge( current, "odd-NW" );
                    }
                    // Other rows
                    else {
                        addEdge( current, "S" );
                        addEdge( current, "odd-SE" );
                        addEdge( current, "odd-NE" );
                        addEdge( current, "N" );
                        addEdge( current, "odd-NW" );
                        addEdge( current, "odd-SW" );
                    }
                }
                //other even columns
                else if ( (col&1) != 1 ) {
                    // Top row
                    if( row == 0 ) {
                        addEdge( current, "S" );
                        addEdge( current, "even-SE" );
                        addEdge( current, "even-SW" );
                    }
                    // Bottom row
                    else if (row == side -1 ) {
                        addEdge( current, "N" );
                        addEdge( current, "even-SE" );
                        addEdge( current, "even-SW" );
                        addEdge( current, "even-NW" );
                        addEdge( current, "even-NE" );
                    }
                    // Other rows
                    else {
                        addEdge( current, "S" );
                        addEdge( current, "even-SE" );
                        addEdge( current, "even-NE" );
                        addEdge( current, "N" );
                        addEdge( current, "even-NW" );
                        addEdge( current, "even-SW" );
                    }
                }
            }
        }
    }

    private void addEdge(OddQHexCoord node, String direction) {
        OddQHexCoord neighbor = node.add( UnitDirections.get(direction) );
        if( members.contains(neighbor) ) {
            this.grid.get( node ).add( neighbor );
            return;
        }
        throw new IllegalArgumentException( String.format("You cannot add an edge between %s and %s because %s does not exist in the members\n %s", node.toString(), neighbor.toString(), neighbor.toString(), members.toString() ) );
    }
}