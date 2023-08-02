/**
 * Creates and draws collectable coins for Dodge Adventure.
 * @author Max Hartfield, Nathan Kim, Amit Kancherla
 * Collaborators: None
 * Teacher Name: Bailey
 * Period: 5
 * Due Date: 5/19/2022
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import java.util.*;
import java.util.List;

public class Coin extends JComponent {

    private Ellipse2D elli;
    static List<Rectangle> boundingRect = new ArrayList<>();
    private static final int SIDE_LENGTH = 10;

    /**
     * Constructs a coin with a given x and y coordinate.
     * @param x x-coordinate to place coin
     * @param y y-coordinate to place coin
     */
    public Coin(int x, int y)
    {
    	elli = new Ellipse2D.Double(x, y, SIDE_LENGTH, SIDE_LENGTH);
		boundingRect.add(elli.getBounds());
    }

    /**
     * Draws the coin.
     * @param gr Graphics to draw coins on
     */
    public void draw(Graphics2D gr)
    {
		gr.setColor(Color.YELLOW);
        gr.fill(elli);
    }

}