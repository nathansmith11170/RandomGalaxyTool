package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class HexagonalMazeTest {
    @Test
    public void HexagonalMaze_WithValidGrid_DoesNotThrow() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        new HexagonalMaze( grid, 1 , .10 );
    }

    @Test
    public void HexagonalMaze_ToClusterList_DoesNotThrow() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        new HexagonalMaze( grid, 1 , .10 ).toClusterList();
    }

    @Test
    public void HexagonalMaze_ToClusterList_ContainsNumberOfClustersEqualToMazeNodes() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        HexagonalMaze hexMaze = new HexagonalMaze( grid, 1 , .10 );
        List<Cluster> clusters = hexMaze.toClusterList();

        assertEquals( hexMaze.members.size(), clusters.size() );
    }

    @Test
    public void HexagonalMaze_ToClusterList_AssignsConnectionCountMatchingMaze() {
        IOddQHexGrid grid = new OddQHexGridSquare(25);

        HexagonalMaze hexMaze = new HexagonalMaze( grid, 1 , .10 );
        List<Cluster> clusters = hexMaze.toClusterList();

        assertTrue( clusters.stream().allMatch( (cluster) -> {
            String[] coord = cluster.getId().split( " " );
            return cluster.getConnections().size() == hexMaze.maze.get( new OddQHexCoord( Integer.parseInt(coord[0]), Integer.parseInt(coord[1]) ) ).size();
        } ) );
    }

    @Test
    public void HexagonalMaze_ToClusterList_AssignsOnlyOneConnectionPerHexDirection() {
        IOddQHexGrid grid = new OddQHexGridSquare(9);

        HexagonalMaze hexMaze = new HexagonalMaze( grid, 5, 0.0 );
        List<Cluster> clusters = hexMaze.toClusterList();

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
