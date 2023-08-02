/**
 * Creates, moves, draws, and resets the obstacle.
 * @author Max Hartfield, Nathan Kim, Amit Kancherla
 * Collaborators: None
 * Teacher Name: Bailey
 * Period: 5
 * Due Date: 5/19/2022
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

public class Obstacle extends JComponent {

    int speed;
    Rectangle rect;
    private int x;
    private int y;
    private int startX;
    private int startY;
    static List<Rectangle> boundingRect = new ArrayList<>();

    /**
     * Sets a Rectangle as an obstacle with a given speed.
     * @param rect the Rectangle to make into an obstacle
     * @param speed the speed to set the obstacle
     */
    public Obstacle(Rectangle rect, int speed)
    {
    	this.rect = rect;
        this.speed = speed;
        startX = (int)(rect.getX());
        x = startX;
        startY = (int)(rect.getY());
        y = startY;
        boundingRect.add(rect);
    }

    /**
     * Sets a Rectangle as an obstacle without a speed.
     * @param rect
     */
    public Obstacle(Rectangle rect) {
        this.rect = rect;
        this.speed = 0;
        startX = (int)(rect.getX());
        x = startX;
        startY = (int)(rect.getY());
        y = startY;
        boundingRect.add(rect);
    }

    /**
     * Changes the size of the obstacle.
     * @param width the width to set the obstacle
     * @param height the height to set the obstacle
     */
    public void changeSize(int width, int height)
    {
        rect.setSize(width, height);
    }

    /**
     * Draws the obstacle.
     * @param gr the Grpahics to paint on
     * @param clr the color to set the obstacle
     */
    public void draw(Graphics2D gr, Color clr)
    {
        gr.setColor(clr);
        gr.fill(rect);
    }

    /**
     * Moves the obstacle a given x and y amount.
     * @param x moves the obstacle in the x-direction
     * @param y moves the obstacle in the y-direction
     */
    public void moveObstacle(int x, int y)
    {
        this.x += x;
        this.y += y;
        boundingRect.remove(rect);
        rect.setLocation(this.x, this.y);
        boundingRect.add(rect);
    }

    /**
     * Resets the obstacle to a starting position.
     */
    public void reset()
    {
        x = startX;
        y = startY;
        moveObstacle(0, 0);
    }

}