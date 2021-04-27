package abstractmodel;

/**
 * The axial coordinate for a hex grid, which contains a q and r value, representing
 * the coordinates (x, y, z) of a cube grid where x + y + z = 0, except we are omitting
 * one of the three values for brevity.
 */
public class AxialHexCoord {
    public int q;
    public int r;

    public AxialHexCoord(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public AxialHexCoord Direction(AxialHexCoord direction_unit) {
        AxialHexCoord result = new AxialHexCoord(this.q, this.r);
        result.q += direction_unit.q;
        result.r += direction_unit.r;
        return result;
    }
}
