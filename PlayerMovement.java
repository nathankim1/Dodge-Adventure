/**
 * Moves the player with user input, checking for collisions.
 * @author Max Hartfield, Nathan Kim, Amit Kancherla
 * Collaborators: None
 * Teacher Name: Bailey
 * Period: 5
 * Due Date: 5/19/2022
 */

import javax.swing.*;
import java.awt.event.*;

public class PlayerMovement extends JPanel implements ActionListener, KeyListener {
	
	Timer t = new Timer(5, this);
	private int x;
	private int y;
	private GameViewer game;

	/**
	 * Sets up the player movement.
	 * @param game the game to set the player in
	 */
	public PlayerMovement(GameViewer game)
	{
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		this.game = game;
	}

	/**
	 * Moves the player according to user-input and checks for collisions.
	 * @param e the ActionEvent from the user
	 */
	public void actionPerformed(ActionEvent e)
    {
		game.advancePlayer(x, y);
		for(int i = 0; i < Coin.boundingRect.size(); i++)
		{
			if(game.p.rect.intersects(Coin.boundingRect.get(i)))
			{
				game.removeCoin(Coin.boundingRect.get(i).getLocation());
			}
		}
		for(int i = 0; i < Obstacle.boundingRect.size(); i++)
		{
			if(game.p.rect.intersects(Obstacle.boundingRect.get(i)))
			{
				game.deaths++;
				game.reset();
				x = 0;
				y = 0;
			}
		}

		if((int)(game.p.rect.getX()) > game.getWidth())
		{
			game.count = 0;
			game.totalCoins = game.removedCoins.size();
			game.reset();
			if(game.level1Flag)
			{
				game.level1Flag = false;
				game.level2Flag = true;
			}
			else if(game.level2Flag)
			{
				game.level2Flag = false;
				game.level3Flag = true;
			}
			else if(game.level3Flag)
			{
				game.level3Flag = false;
				game.level4Flag = true;
			}
			else if(game.level4Flag)
			{
				game.level4Flag = false;
				game.level5Flag = true;
			}
			else if(game.level5Flag)
			{
				game.level5Flag = false;
				game.level6Flag = true;
			}
			else if(game.level6Flag)
			{
				  game.level6Flag = false;
				  game.level7Flag = true;
			}
			else if(game.level7Flag)
			{
				game.level7Flag = false;
				game.level1Flag = true;
				game.totalCoins = 0;
				game.deaths = 0;
			}
		}
    }

	/**
	 * Moves the player up.
	 */
	public void up()
    {
    	x = 0;
    	y = game.p.speed * -1;
    }

	/**
	 * Moves the player down.
	 */
	public void down()
    {
    	x = 0;
    	y = game.p.speed;
    }

	/**
	 * Moves the player left.
	 */
	public void left()
    {
    	x = game.p.speed * -1;
    	y = 0;
    }

	/**
	 * Moves the player right.
	 */
    public void right()
    {
    	x = game.p.speed;
    	y = 0;
    }

	/**
	 * Moves the player according to user-input of arrow keys or WASD.
	 * @param e the KeyEvent from the user
	 */
	public void keyPressed(KeyEvent e)
    {
    	int code = e.getKeyCode();
    	if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W)
    		up();
    	if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S)
    		down();
    	if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
    		left();
    	if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
    		right();
    }

	/**
	 * Checks for any keys typed.
	 * @param e the KeyEvent from the user
	 */
    public void keyTyped(KeyEvent e){}

	/**
	 * Determines if the player auto-moves or not.
	 * @param e the KeyEvent from the user
	 */
    public void keyReleased(KeyEvent e)
    {
    	//x = 0;
    	//y = 0;
    }

}