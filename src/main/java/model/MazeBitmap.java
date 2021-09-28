package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Set;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.imageio.ImageIO;

import org.javatuples.Pair;

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
        this.height = (int) Math.round(this.size * Math.sqrt(3) * (Math.sqrt(this.HexMaze.OddQHexGrid.members().size()) + 0.5 * (Math.round(Math.sqrt(this.HexMaze.OddQHexGrid.members().size()))&1))) + this.size/3;
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

    public InputStream getStream( Set<Pair<String, OddQHexCoord>> ownedSectors ) {
        g2.setColor( Color.WHITE );
        g2.fillRect( 0, 0, this.width, this.height );

        g2.setColor( Color.BLACK ); 
        // Draw ellipses
        this.HexMaze.maze.keySet().forEach( (n) -> {
            Vector2D node = new Vector2D( n, this.size );
            Optional<Pair<String, OddQHexCoord>> potentialSector = ownedSectors.stream().filter( sector -> sector.getValue1().equals( n ) ).findFirst();
            if( potentialSector.isPresent() ) {
                switch( potentialSector.get().getValue0() ) {
                    case "argon":
                        g2.setColor( Color.BLUE );
                        break;
                    case "antigone":
                        g2.setColor( Color.cyan);
                        break;
                    case "holyorder":
                        g2.setColor( Color.pink );
                        break;
                    case "paranid":
                        g2.setColor( Color.magenta );
                        break;
                    case "teladi":
                        g2.setColor( Color.yellow );
                        break;
                    case "ministry":
                        g2.setColor( Color.green );
                        break;
                    case "split":
                        g2.setColor( Color.ORANGE );
                        break;
                    case "freesplit":
                        g2.setColor( new Color( 239, 150, 0) );
                        break;
                    case "terran":
                        g2.setColor( Color.GRAY );
                        break;
                    case "pioneers":
                        g2.setColor( new Color( 32, 85, 77 ) );
                        break;
                    case "xenon":
                        g2.setColor( Color.RED );
                        break;
                    default:
                        g2.setColor( Color.LIGHT_GRAY );
                        break;
                }
            }
            else {
                g2.setColor( Color.BLACK );
            }
            Ellipse2D sector = new Ellipse2D.Double(node.x, node.y, this.size, this.size);
            g2.fill(sector);
        });

        // Connect ellipses
        this.HexMaze.maze.keySet().forEach( (n) -> {
            g2.setColor( Color.BLACK );
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