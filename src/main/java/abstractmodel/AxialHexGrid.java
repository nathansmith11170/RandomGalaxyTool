package abstractmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AxialHexGrid implements IAxialHexGrid{
    
    /**
     * The {@code Grid} is a list of lists, representing rows of the grid.
     */
    public AxialHexCoord[][] Grid;

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

    public AxialHexGrid(int columns, int rows) {
        this.Grid = new AxialHexCoord[columns][rows];
        this.DeadZones = new HashSet<AxialHexCoord>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<AxialHexCoord> GetNeighbors(AxialHexCoord coordinate) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean IsNeighbor(AxialHexCoord coordinate_one, AxialHexCoord coordinate_two) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void PopulateRectangularGrid(int rows, int columns) {
        // TODO Auto-generated method stub
    }

    public void MakeSquareGrid(int side, int deadzones) {

    }

    public void CreateDeadZones() {

     }
}