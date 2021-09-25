package model;

import java.util.Objects;

public class OddQHexCoord {
    private final int col;
    private final int row;

    public OddQHexCoord( int q, int r ) {
        this.col = q;
        this.row = r;
    }

    public int col() {
        return this.col;
    }

    public int row() {
        return this.row;
    }

    @Override
    public boolean equals( Object o ) {
        if( o.getClass() == OddQHexCoord.class ) {
            OddQHexCoord param = (OddQHexCoord) o;
            if( param.col() == this.col() && param.row() == this.row() ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.col, this.row );
    }

    @Override
    public String toString() {
        return "OddQHexCoord{" + this.col + "," + this.row + "}";
    }

    public OddQHexCoord add(OddQHexCoord n2) {
        return new OddQHexCoord( this.col() + n2.col(), this.row() + n2.row() );
    }

    public OddQHexCoord diff(OddQHexCoord n2) {
        return new OddQHexCoord( this.col() - n2.col(), this.row() - n2.row() );
    }
}