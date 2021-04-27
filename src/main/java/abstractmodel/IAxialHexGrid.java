package abstractmodel;

import java.util.ArrayList;

public interface IAxialHexGrid {
    /**
     * Return a list of the neighbors of {@code coordinate} that exist in the grid.
     * 
     * @param coordinate
     *          The coordinate whose neighbors we are finding
     * @return
     *          An {@code ArrayList} of {@code AxialHexCoord} objects
     *          representing the neighbors which exist in the grid.
     */
    public ArrayList<AxialHexCoord> GetNeighbors( AxialHexCoord coordinate );

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
    public boolean IsNeighbor( AxialHexCoord coordinate_one, AxialHexCoord coordinate_two );

    /**
     * Generate a rectangular grid where the odd numbered rows are offset by
     * a half column
     * 
     * @param columns
     *          The number of columns in the grid
     * @param rows
     *          The number of rows in the grid
     */
    public void PopulateRectangularGrid( int columns, int rows );
}