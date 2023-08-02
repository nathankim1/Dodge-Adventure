/**
 * Moves obstacles on-screen for players to dodge.
 * @author Max Hartfield, Nathan Kim, Amit Kancherla
 * Collaborators: None
 * Teacher Name: Bailey
 * Period: 5
 * Due Date: 5/19/2022
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {

    private Obstacle o;
    private GameViewer game;
    private static final int SPEED = 10;
    private int x;
    private int y;

    /**
     * Determines how the obstacle moves.
     * @param game the game to display the obstacle
     * @param o the obstacle to move
     * @param dir the direction to move the obstacle
     */
    public TimerListener(GameViewer game, Obstacle o, String dir)
    {
        this.game = game;
        this.o = o;
        x = 0;
        y = 0;

        if(dir.equals("DOWN"))
            y = SPEED;
        if(dir.equals("UP"))
            y = SPEED * -1;
        if(dir.equals("LEFT"))
            x = SPEED * -1;
        if(dir.equals("RIGHT"))
            x = SPEED;
    }

    /**
     * Checks for obstacle collisions with the walls.
     * @param e the ActionEvent from the ActionListener class
     */
    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0; i < Obstacle.boundingRect.size(); i++)
        {
            if(Obstacle.boundingRect.get(i).equals(o.rect))
                continue;
            if(o.rect.intersects(Obstacle.boundingRect.get(i)))
                game.resetObstacle(o);
        }
        game.advanceObstacle(o, x, y);
    }

}
