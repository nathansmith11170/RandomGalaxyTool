package abstractmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class AxialHexMapSquare implements IAxialHexGrid {
    
    /**
     * The {@code grid} is a map of {@code AxialHexCoord} hash codes to lists of {@code AxialHexCoord} that are the neighbors of each node
     */
    public Map<AxialHexCoord, ArrayList<AxialHexCoord>> grid;

    /**
     * the {@code members} is a set of {@code AxialHexCoord} that exist in the grid
     */
    public Set<AxialHexCoord> members;


    private static Map<String, AxialHexCoord> UnitDirections = new HashMap<String, AxialHexCoord>() {{
        put( "left",      new AxialHexCoord( -1,  0 ) );
        put( "right",     new AxialHexCoord(  1,  0 ) );
        put( "upright",   new AxialHexCoord(  1, -1 ) );
        put( "downright", new AxialHexCoord(  0,  1 ) );
        put( "upleft",    new AxialHexCoord(  0, -1 ) );
        put( "downleft",  new AxialHexCoord( -1,  1 ) );
    }};

    /**
     * This constructor initializes an "odd-r" grid of hexagonal nodes with equal rows and columns.
     * 
     * @param targetSize
     *      This is the number of nodes that are desirable for the grid.
     */
    public AxialHexMapSquare( int targetSize ) {
        int square_root = nextGreatestPerfectSquare( targetSize );
        this.grid = new HashMap<AxialHexCoord, ArrayList<AxialHexCoord>>();
        this.members = new HashSet<AxialHexCoord>();

        makeSquareGrid( square_root );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<AxialHexCoord> getNeighbors(AxialHexCoord coordinate) {
        return grid.get( coordinate );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNeighbor(AxialHexCoord coordinate_one, AxialHexCoord coordinate_two) {
        return this.grid.get( coordinate_one ).contains( coordinate_two );
    }

    /**
     * This method creates an "odd-r" horizontal grid of hexagonal nodes with the same number of rows as columns.
     * 
     * @param side
     *      The number of nodes in a row or column of the square grid
     */
    public void makeSquareGrid(int side) {
        for( int r = 0; r < side; r++ ) {
            for( int q = 0 - Math.floorDiv(r,2); q < side-Math.floorDiv(r,2); q++ ) {
                AxialHexCoord temp = new AxialHexCoord(q, r);
                this.grid.put( temp, new ArrayList<AxialHexCoord>() );
                this.members.add(temp);
            }
        }
        addSquareGridEdges( side );
    }

    /**
     * {@inheritDoc}
     */
    public Set<AxialHexCoord> members() {
        return this.members;
    }

    /**
     * {@inheritDoc}
     */
    public Map<AxialHexCoord, ArrayList<AxialHexCoord>> grid() {
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
        for( int r = 0; r < side; r++ ) {
            // top row
            if( r == 0 ) {
                for( int q = 0 - Math.floorDiv(r,2); q < side-Math.floorDiv(r,2); q++ ) {
                    AxialHexCoord currentCoord = new AxialHexCoord(q, r);
                    // first column
                    if( q == 0 - Math.floorDiv(r, 2) ) {
                        //right
                        addEdge( currentCoord, "right" );

                        //downright
                        addEdge( currentCoord, "downright" );
                    }
                    // last column
                    else if ( q == side - Math.floorDiv(r, 2) - 1 ) {
                        //left
                        addEdge( currentCoord, "left" );

                        //downleft
                        addEdge( currentCoord, "downleft" );

                        //downright
                        addEdge( currentCoord, "downright" );
                    }
                    // other columns
                    else {
                        //left
                        addEdge( currentCoord, "left" );
                        
                        //right
                        addEdge( currentCoord, "right" );

                        //downright
                        addEdge( currentCoord, "downright" );

                        //downleft
                        addEdge( currentCoord, "downleft" );
                    }
                }
            }
            // last row
            else if ( r == side - 1 ) {
                for( int q = 0 - Math.floorDiv(r,2); q < side-Math.floorDiv(r,2); q++ ) {
                    AxialHexCoord currentCoord = new AxialHexCoord(q, r);
                    // even row
                    if ( (r&1) != 1 ) {
                        // first column
                        if( q == 0 - Math.floorDiv(r, 2) ) {
                            //upright
                            addEdge( currentCoord, "upright" );

                            //right
                            addEdge( currentCoord, "right" );
                        }
                        // last column
                        else if ( q == side - Math.floorDiv(r, 2) - 1 ) {
                            //left
                            addEdge( currentCoord, "left" );

                            //upleft
                            addEdge( currentCoord, "upleft" );

                            //upright
                            addEdge( currentCoord, "upright" );
                        }
                        // other columns
                        else {
                            //left
                            addEdge( currentCoord, "left" );

                            //right
                            addEdge( currentCoord, "right" );

                            //upright
                            addEdge( currentCoord, "upright" );

                            //upleft
                            addEdge( currentCoord, "upleft" );
                        }
                    }
                    //odd row
                    else {
                        // first column
                        if( q == 0 - Math.floorDiv(r, 2) ) {
                            //left
                            addEdge( currentCoord, "right" );

                            //upright
                            addEdge( currentCoord, "upright" );

                            //upleft
                            addEdge( currentCoord, "upleft" );
                        }
                        // last column
                        else if ( q == side - Math.floorDiv(r, 2) - 1 ) {
                            //left
                            addEdge( currentCoord, "left" );

                            //upleft
                            addEdge( currentCoord, "upleft" );
                        }
                        // other columns
                        else {
                            //left
                            addEdge( currentCoord, "left" );
                            
                            //right
                            addEdge( currentCoord, "right" );

                            //upright
                            addEdge( currentCoord, "upright" );

                            //upleft
                            addEdge( currentCoord, "upleft" );
                        }
                    }
                }
            }
            // other rows
            else {
                for( int q = 0 - Math.floorDiv(r,2); q < side-Math.floorDiv(r,2); q++ ) {
                    AxialHexCoord currentCoord = new AxialHexCoord(q, r);
                    // even row
                    if ( (r&1) != 1 ) {
                        // first column
                        if( q == 0 - Math.floorDiv(r, 2) ) {
                            //upright
                            addEdge( currentCoord, "upright" );

                            //right
                            addEdge( currentCoord, "right" );

                            //downright
                            addEdge( currentCoord, "downright" );
                        }
                        // last column
                        else if ( q == side - Math.floorDiv(r, 2) - 1 ) {
                            //left
                            addEdge( currentCoord, "left" );

                            //upleft
                            addEdge( currentCoord, "upleft" );

                            //upright
                            addEdge( currentCoord, "upright" );

                            //downleft
                            addEdge( currentCoord, "downleft" );

                            //downright
                            addEdge( currentCoord, "downright" );
                        }
                        // other columns
                        else {
                            //left
                            addEdge( currentCoord, "left" );

                            //right
                            addEdge( currentCoord, "right" );

                            //upright
                            addEdge( currentCoord, "upright" );

                            //upleft
                            addEdge( currentCoord, "upleft" );

                            //downright
                            addEdge( currentCoord, "downright" );

                            //downleft
                            addEdge( currentCoord, "downleft" );
                        }
                    }
                    //odd row
                    else {
                        // first column
                        if( q == 0 - Math.floorDiv(r, 2) ) {
                            //right
                            addEdge( currentCoord, "right" );

                            //upright
                            addEdge( currentCoord, "upright" );

                            //upleft
                            addEdge( currentCoord, "upleft" );

                            //downright
                            addEdge( currentCoord, "downright" );

                            //downleft
                            addEdge( currentCoord, "downleft" );
                        }
                        // last column
                        else if ( q == side - Math.floorDiv(r, 2) - 1 ) {
                            //left
                            addEdge( currentCoord, "left" );

                            //upleft
                            addEdge( currentCoord, "upleft" );

                            //downleft
                            addEdge( currentCoord, "downleft" );
                        }
                        // other columns
                        else {
                            //left
                            addEdge( currentCoord, "left" );

                            //right
                            addEdge( currentCoord, "right" );

                            //upright
                            addEdge( currentCoord, "upright" );

                            //upleft
                            addEdge( currentCoord, "upleft" );

                            //downleft
                            addEdge( currentCoord, "downleft" );

                            //downright
                            addEdge( currentCoord, "downright");
                        }
                    }
                }
            }
        }  
    }

    private void addEdge(AxialHexCoord node, String direction) {
        AxialHexCoord neighbor = node.add( UnitDirections.get(direction) );
        if( members.contains(neighbor) ) {
            this.grid.get( node ).add( neighbor );
            return;
        }
        throw new IllegalArgumentException( String.format("You cannot add an edge between %s and %s because %s does not exist in the members\n %s", node.toString(), neighbor.toString(), neighbor.toString(), members.toString() ) );
    }
}