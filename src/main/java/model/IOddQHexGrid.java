package model;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map;

public interface IOddQHexGrid {
    /**
     * Return a list of the neighbors of {@code coordinate} that exist in the grid.
     * 
     * @param coordinate
     *          The coordinate whose neighbors we are finding
     * @return
     *          An {@code ArrayList} of {@code AxialHexCoord} objects
     *          representing the neighbors which exist in the grid.
     */
    public ArrayList<OddQHexCoord> getNeighbors( OddQHexCoord coordinate );

    /**
     * Determines if two coordinates are neighbors
     * 
     * @param coordinate_one
     *          The first coordinate
     * @param coordinate_two
     *          The second coordinate
     * @requires
     *          Both coordinates must exist in the grid
     * @return
     *          True if the coordinate are neighbors, otherwise false.
     */
    public boolean isNeighbor( OddQHexCoord coordinate_one, OddQHexCoord coordinate_two );

    /**
     * 
     * @return
     *      The coordinate members of the grid.
     */
    public Set<OddQHexCoord> members();

    /**
     * 
     * @return
     *      the actual map representation of the grid.
     */
    public Map<OddQHexCoord, ArrayList<OddQHexCoord>> grid();

}