package abstractmodel;

import java.util.Objects;

public class AxialHexCoord {
    private final int q;
    private final int r;

    public AxialHexCoord( int q, int r ) {
        this.q = q;
        this.r = r;
    }

    public int q() {
        return this.q;
    }

    public int r() {
        return this.r;
    }

    @Override
    public boolean equals( Object o ) {
        if( o.getClass() == AxialHexCoord.class ) {
            AxialHexCoord param = (AxialHexCoord) o;
            if( param.q() == this.q() && param.r() == this.r() ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.q, this.r );
    }

    @Override
    public String toString() {
        return "AxialHexCoord{" + this.q + "," + this.r + "}";
    }

    public AxialHexCoord add(AxialHexCoord n2) {
        return new AxialHexCoord( this.q() + n2.q(), this.r() + n2.r() );
    }
}