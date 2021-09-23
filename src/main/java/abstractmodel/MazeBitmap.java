package abstractmodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Vector;

public class MazeBitmap {
    private HexagonalMaze HexMaze;
    private Graphics2D g2;

    public BufferedImage img;
    public final int size;
    public final int width;
    public final int height;

    public MazeBitmap(HexagonalMaze maze) {
        this.size = 50;
        this.HexMaze = maze;
        this.width = (int) Math.sqrt( this.HexMaze.members.size() ) * 50;
        this.height = this.width;
        this.img = new BufferedImage( this.width, this.height, BufferedImage.TYPE_INT_RGB );

        this.g2 = (Graphics2D) this.img.getGraphics();

        draw();
    }

    private void draw() {
        g2.setColor( Color.WHITE );
        g2.fillRect( 0, 0, this.width, this.height );

        g2.setColor( Color.BLACK );
        // Draw ellipses
        this.HexMaze.maze.keySet().forEach( (n) -> {
            Vector2D node = new Vector2D(n);
            Ellipse2D sector = new Ellipse2D.Double(node.x, node.y, this.size, this.size);
            g2.draw(sector);
        });

        // Connect ellipses
        this.HexMaze.maze.keySet().forEach( (n) -> {
            Vector2D node = new Vector2D(n);
            this.HexMaze.maze.get(n).forEach( (m) -> {
                Vector2D node2 = new Vector2D(m);
                Line2D link = new Line2D.Double( node.x + this.size/2, node.y + this.size/2, node2.x + this.size/2, node2.y + this.size/2 );
                g2.draw(link);
            });
        });
    }
}

class Vector2D {
    final Vector<Double> qBasis = new Vector<>() {{ this.add( Math.sqrt(3) ); this.add( 0.0 ); }};
    final Vector<Double> rBasis = new Vector<>() {{ this.add( Math.sqrt(3)/2 ); this.add( 3.0/2 ); }};

    public double x;
    public double y;

    public Vector2D( AxialHexCoord axialCoord ) {
        this.x = axialCoord.q() * qBasis.get(0) + axialCoord.r() * qBasis.get(1);
        this.y = axialCoord.r() * rBasis.get(1);
    }
}