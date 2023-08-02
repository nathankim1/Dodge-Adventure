/**
 * Creates the player as a movable Rectangle object.
 * @author Max Hartfield, Nathan Kim, Amit Kancherla
 * Collaborators: None
 * Teacher Name: Bailey
 * Period: 5
 * Due Date: 5/19/2022
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class Player extends JComponent {

    int totalCoins;
    int speed;
    Rectangle rect;
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Creates the player with a given Rectangle and speed.
     * @param rect the Rectangle to make the player
     * @param speed the speed to set the player
     */
    public Player(Rectangle rect, int speed)
    {
        totalCoins = 0;
        this.speed = speed;
        this.rect = rect;
        x = (int)rect.getX();
        y = (int)rect.getY();
    }

    /**
     * Changes the size of the player.
     * @param width the width of the player
     * @param height the height of the player
     */
    public void changeSize(int width, int height)
    {
        this.width += width;
        this.height += height;
        rect.setSize(this.width, this.height);
    }

    /**
     * Draws the player on the Graphics.
     * @param gr the Graphics to draw on
     */
    public void draw(Graphics2D gr)
    {
    	gr.setColor(Color.RED);
    	gr.fill(rect);
    }

    /**
     * Moves the player a given x and y amount.
     * @param x moves the player in the x-direction
     * @param y moves the player in the y-direction
     */
    public void movePlayer(int x, int y)
    {
        this.x += x;
    	this.y += y;
    	rect.setLocation(this.x, this.y);
    }

}