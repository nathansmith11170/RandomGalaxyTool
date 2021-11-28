package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class FactionPlacerTests {
    @Test
    public void HexagonalMaze_ToClusterList_DoesNotThrow() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        HexagonalMaze map = new HexagonalMaze( grid, 1 , .10 );
        
        new FactionPlacer().setClusters( map );
    }

    @Test
    public void HexagonalMaze_ToClusterList_ContainsNumberOfClustersEqualToMazeNodes() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        HexagonalMaze hexMaze = new HexagonalMaze( grid, 1 , .10 );
        List<Cluster> clusters = new FactionPlacer().setClusters( hexMaze );;

        assertEquals( hexMaze.members.size(), clusters.size() );
    }

    @Test
    public void HexagonalMaze_ToClusterList_AssignsConnectionCountMatchingMaze() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        HexagonalMaze hexMaze = new HexagonalMaze( grid, 1 , .10 );
        List<Cluster> clusters = new FactionPlacer().setClusters( hexMaze );;

        assertTrue( clusters.stream().allMatch( (cluster) -> {
            String[] coord = cluster.getId().split( "_" );
            return cluster.getConnections().size() == hexMaze.maze.get( new OddQHexCoord( Integer.parseInt(coord[0]), Integer.parseInt(coord[1]) ) ).size();
        } ) );
    }

    @Test
    public void HexagonalMaze_ToClusterList_AssignsConnectionWithDestinationsThatExist() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        HexagonalMaze hexMaze = new HexagonalMaze( grid, 1 , .10 );
        List<Cluster> clusters = new FactionPlacer().setClusters( hexMaze );;

        for( Cluster cluster : clusters) {
            String[] sourceCoord = cluster.getId().split( "_" );
            OddQHexCoord current = new OddQHexCoord( Integer.parseInt( sourceCoord[0] ), Integer.parseInt( sourceCoord[1] ) );
            for( Connection conn : cluster.getConnections() ) {
                String[] destCoord = conn.getTargetClusterId().split( "_" );
                OddQHexCoord prospectiveNeighbor = new OddQHexCoord( Integer.parseInt( destCoord[0] ), Integer.parseInt( destCoord[1] ) );
                if( !hexMaze.maze.get( current ).contains( prospectiveNeighbor ) ) {
                    throw new AssertionError( String.format( "The neighbor %s was added to %s, but it does not exist in the maze", prospectiveNeighbor.toString(), cluster.getId() ) );
                }
            }
        }
    }

    @Test
    public void HexagonalMaze_ToClusterList_AssignsOnlyOneConnectionPerHexDirection() {
        IOddQHexGrid grid = new OddQHexGridSquare(9);

        HexagonalMaze hexMaze = new HexagonalMaze( grid, 5, 0.0 );
        List<Cluster> clusters = new FactionPlacer().setClusters( hexMaze );;

        Map<String, Boolean> directionsNotEncountered = new HashMap<>();


        for( Cluster cluster : clusters) {
            directionsNotEncountered.clear();
            directionsNotEncountered.put( "N", true );
            directionsNotEncountered.put( "S", true );
            directionsNotEncountered.put( "NW", true );
            directionsNotEncountered.put( "NE", true );
            directionsNotEncountered.put( "SW", true );
            directionsNotEncountered.put( "SE", true );
            for( Connection conn : cluster.getConnections() ) {
                String connType = conn.getConnectionType().toString();
                if( directionsNotEncountered.get( connType ) ) {
                    directionsNotEncountered.replace( connType, false );
                }
                else {
                    throw new AssertionError( String.format( "The direction %s was encountered twice for coordinate %s", connType, cluster.getId() ) );
                }
            }
        }
    }
}