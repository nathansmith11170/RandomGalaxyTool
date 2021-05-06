package abstractmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Random;

public class AxialHexGridSquare implements IAxialHexGrid{
    
    /**
     * The {@code Grid} is a list of lists, representing rows of the grid.
     */
    public Hashtable<Integer, AxialHexCoord> Grid;

    /**
     * The {@code DeadZones} represent holes in a grid.
     */
    public Set<AxialHexCoord> DeadZones;

    private static Map<String, AxialHexCoord> UnitDirections = new HashMap<String, AxialHexCoord>() {{
        put( "left",      new AxialHexCoord( -1,  0 ) );
        put( "right",     new AxialHexCoord(  1,  0 ) );
        put( "upright",   new AxialHexCoord(  1, -1 ) );
        put( "downright", new AxialHexCoord(  0,  1 ) );
        put( "upleft",    new AxialHexCoord(  0, -1 ) );
        put( "downleft",  new AxialHexCoord( -1,  1 ) );
    }};

    public AxialHexGridSquare( int targetSize ) {
        int square_root = nextGreatestPerfectSquare( targetSize );
        int deadzone_number = (int) Math.abs( Math.pow(square_root, 2) - targetSize );
        this.Grid = new Hashtable<Integer, AxialHexCoord>();
        this.DeadZones = new HashSet<AxialHexCoord>();

        makeSquareGrid( square_root, deadzone_number );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<AxialHexCoord> getNeighbors(AxialHexCoord coordinate) {
        ArrayList<AxialHexCoord> neighbors = new ArrayList<AxialHexCoord>();
        for( AxialHexCoord direction : UnitDirections.values() ) {
            AxialHexCoord potentialNeighbor = new AxialHexCoord( direction.q() + coordinate.q(), direction.r() + coordinate.r() );
            if ( Grid.containsKey( potentialNeighbor.hashCode() ) ) {
                neighbors.add( potentialNeighbor );
            }
        }
        return neighbors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNeighbor(AxialHexCoord coordinate_one, AxialHexCoord coordinate_two) {
        for( AxialHexCoord direction : UnitDirections.values() ) {
            AxialHexCoord result = new AxialHexCoord( direction.q() + coordinate_one.q(), direction.r() + coordinate_one.r() );
            if ( result.equals( coordinate_two ) ) {
                return true;
            }
        }
        return false;
    }


    public void makeSquareGrid(int side, int deadzones) {
        for( int r = 0; r < side; r++ ) {
            for( int q = 0 - Math.floorDiv(r, 2); q < side - Math.floorDiv(r, 2); q++) {
                AxialHexCoord temp = new AxialHexCoord(q,r);
                this.Grid.put( temp.hashCode(), temp);
            }
        }

        createDeadZones(deadzones);
    }

    public void createDeadZones(int number_of_deadzones) {
        Object[] keys = Grid.keySet().toArray();
        Random rand = new Random();
        rand.setSeed( System.currentTimeMillis() );

        while( DeadZones.size() < number_of_deadzones ) {
            int access = rand.nextInt( keys.length );
            Integer key = (Integer) keys[access];
            AxialHexCoord potentialDeadZone = Grid.get(key);
            if( !DeadZones.contains(potentialDeadZone) ) {
                DeadZones.add( potentialDeadZone );
                if( !this.isConnectedWithoutDeadZones() ) {
                    DeadZones.remove( potentialDeadZone );
                }
            }
        }
    }

    private boolean isConnectedWithoutDeadZones() {
        ArrayList<Integer> discoveredKeys = new ArrayList<Integer>();
        AxialHexCoord start = new AxialHexCoord(0,0);

        depthFirstSearch(discoveredKeys, start);

        for( Integer key : Grid.keySet() ) {
            if( DeadZones.contains( Grid.get(key) ) ) {
                continue;
            }
            if( !discoveredKeys.contains(key) ) {
                return false;
            } 
        }
        return true;
    }

    private void depthFirstSearch(ArrayList<Integer> discoveredKeys, AxialHexCoord position) {
        discoveredKeys.add( position.hashCode() );
        for( AxialHexCoord neighbor : this.getNeighbors( position ) ) {
            if( DeadZones.contains( neighbor) ) {
                continue;
            }
            if( !discoveredKeys.contains( neighbor.hashCode() ) ) {
                depthFirstSearch(discoveredKeys, neighbor);
            }
        }
    }

    private int nextGreatestPerfectSquare( int product ) {
        int square_root = 1;
        do {
            square_root++;
        } while( Math.pow(square_root, 2) <= product );
        return square_root;
    }
}