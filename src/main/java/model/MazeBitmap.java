package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

public class MazeBitmap {
    private HexagonalMaze HexMaze;
    private Graphics2D g2;
    private ArrayList<Vector2D> vectors;
    private int greatestX = 0;
    private int greatestY = 0;

    public BufferedImage img;
    public final int size;
    public final int width;
    public final int height;

    public MazeBitmap(HexagonalMaze maze) {
        this.HexMaze = maze;
        this.vectors = new ArrayList<Vector2D>();
        this.size = 50;
        this.HexMaze.maze.keySet().forEach( (n) -> {
            Vector2D node = new Vector2D( n, this.size);
            this.vectors.add(node);
            if( node.x > this.greatestX ) {
                this.greatestX = (int) Math.ceil( node.x );
            }
            if( node.y > this.greatestY ) {
                this.greatestY = (int) Math.ceil( node.y );
            }
        });

        this.width = this.greatestX + this.size;
        this.height = this.greatestY + this.size;
        this.img = new BufferedImage( this.width, this.height, BufferedImage.TYPE_INT_RGB );

        this.g2 = (Graphics2D) this.img.getGraphics();
    }

    public InputStream getStream() {
        g2.setColor( Color.WHITE );
        g2.fillRect( 0, 0, this.width, this.height );

        g2.setColor( Color.BLACK );
        // Draw ellipses
        this.HexMaze.maze.keySet().forEach( (n) -> {
            Vector2D node = new Vector2D( n, this.size );
            Ellipse2D sector = new Ellipse2D.Double(node.x, node.y, this.size, this.size);
            g2.draw(sector);
        });

        // Connect ellipses
        this.HexMaze.maze.keySet().forEach( (n) -> {
            Vector2D node = new Vector2D( n, this.size );
            this.HexMaze.maze.get(n).forEach( (m) -> {
                Vector2D node2 = new Vector2D( m, this.size );
                Line2D link = new Line2D.Double( node.x + this.size/2, node.y + this.size/2, node2.x + this.size/2, node2.y + this.size/2 );
                g2.draw(link);
            });
        });

        try {
            ByteArrayOutputStream imgBuffer = new ByteArrayOutputStream();
            ImageIO.write( this.img, "png", imgBuffer );

            return new ByteArrayInputStream( imgBuffer.toByteArray() );
        }
        catch (IOException e) {
            return null;
        }
    }
}

class Vector2D {
    final Vector<Double> qBasis = new Vector<>() {{ this.add( Math.sqrt(3) ); this.add( 0.0 ); }};
    final Vector<Double> rBasis = new Vector<>() {{ this.add( Math.sqrt(3)/2 ); this.add( 3.0/2 ); }};

    public double x;
    public double y;

    public Vector2D( AxialHexCoord axialCoord, int size ) {
        this.x = size * ( axialCoord.q() * qBasis.get(0) + axialCoord.r() * qBasis.get(1) ) + size * axialCoord.r();
        this.y = size * ( axialCoord.r() * rBasis.get(1) );
    }
}