package abstractmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HexagonalMaze {
    public final AxialHexGridSquare grid;
    public final Graph graph;
    public final Map<AxialHexCoord, String> coordinates;

    public HexagonalMaze( int nodeQuantity ) {
        graph = new Graph( nodeQuantity );
        grid = new AxialHexGridSquare( nodeQuantity );
        coordinates = new HashMap<AxialHexCoord, String>();

        this.assignGridCoords();
        this.generateEdges();
    }

    private void assignGridCoords() {
        Set<AxialHexCoord> assigned = new HashSet<AxialHexCoord>();
        for( ArrayList<String> node : graph.getGraph() ) {
            
        }
    }

    private void generateEdges() {

    }
}
