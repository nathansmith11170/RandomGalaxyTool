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

import javax.imageio.ImageIO;

public class MazeBitmap {
    private HexagonalMaze HexMaze;
    private Graphics2D g2;

    public BufferedImage img;
    public final int size;
    public final int width;
    public final int height;

    public MazeBitmap(HexagonalMaze maze) {
        this.HexMaze = maze;
        this.size = 50;

        this.width = (int) Math.sqrt(this.HexMaze.OddQHexGrid.members().size()) * this.size * 3/2;
        this.height = (int) Math.round(this.size * Math.sqrt(3) * (Math.sqrt(this.HexMaze.OddQHexGrid.members().size()) + 0.5 * (Math.round(Math.sqrt(this.HexMaze.OddQHexGrid.members().size()))&1))) + this.size/2;
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
    public double x;
    public double y;

    public Vector2D( OddQHexCoord oddQ, int size ) {
        this.x = size * 3/2 * oddQ.col();
        this.y = size * Math.sqrt(3) * (oddQ.row() + 0.5 * (oddQ.col()&1));
    }
}