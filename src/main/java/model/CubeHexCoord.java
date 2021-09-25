package model;

import java.util.Objects;

public class CubeHexCoord {
    private final int x;
    private final int y;
    private final int z;

    public CubeHexCoord( int x, int y, int z ) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public int z() {
        return this.z;
    }

    @Override
    public boolean equals( Object o ) {
        if( o.getClass() == CubeHexCoord.class ) {
            CubeHexCoord param = (CubeHexCoord) o;
            if( param.x() == this.x() && param.y() == this.y() && param.z() == this.z()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.x, this.y );
    }

    @Override
    public String toString() {
        return "OddQHexCoord{" + this.x + "," + this.y + "}";
    }

    public CubeHexCoord add(CubeHexCoord n2) {
        return new CubeHexCoord( this.x() + n2.x(), this.y() + n2.y(), this.z() + n2.z() );
    }
}