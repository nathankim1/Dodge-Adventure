/**
 * Puts all the Dodge Adventure objects onscreen.
 * @author Max Hartfield, Nathan Kim, Amit Kancherla
 * Collaborators: None
 * Teacher Name: Bailey
 * Period: 5
 * Due Date: 5/19/2022
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.*;

public class GameViewer extends JComponent {

	private static final int WIDTH = 1500;
	private static final int HEIGHT = 800;
	private static final int[] O1_VALUES = new int[]{490, 550, 25, 25, 75};
	private static final int[] O2_VALUES = new int[]{1000, 200, 75, 75, 10};
	private static final int[] O3_VALUES = new int[]{1050, 390, 50, 10, 5};
	private static final String DOWN = "DOWN";
	private static final String LEFT = "LEFT";
	private static final String DEATHS = "Total Deaths: ";
	private static final String COINS = " Total Coins: ";
	private static final String PLAY_AGAIN = "Congrats, you win! Play Again -->";
	private static final String DIRECTIONS = "Use WASD or Arrow Keys to move. " + 
	"Avoid obstacles and make it to the end to move on.";
	private static final int TEXT_X = 200;
	private static final int TEXT_X2 = 100;
	private static final int TEXT_Y = 100;
	private static final int TEXT_Y2 = 700;
	private static final int TEXT_Y3 = 720;
	private static final int COIN_GAP = 40;
	private static final int FONT_SIZE = 50;
	private static final int[] LEVEL1VALUES = new int[]{0, 0, 50, 800, 0, 0, 1500, 350, 0, 450,
			400, 350, 500, 350, 350, 100, 400, 550, 1100, 250, 950, 450, 550, 350};
	private static final int[] LEVEL2VALUES = new int[]{0, 0, 50, 800, 0, 0, 400, 350, 0,
			450, 280, 1000, 400, 0, 200, 550, 280, 700, 440, 1000, 720, 250, 300,
			1000, 600, 0, 500, 50, 1100, 0, 400, 500, 1020, 600, 80, 1000, 1100, 740, 400,
			400, 1160, 400, 500, 200};
	private static final int[] LEVEL3VALUES = new int[]{0, 0, 50, 800, 0 ,0 ,500 , 350, 0,
			450, 550, 1000, 0, 720, 1500, 50, 550, 100, 50, 700, 0, 0, 1500,
			50, 650, 0, 50, 670, 850, 200,650, 600};
	private static final int[] LEVEL4VALUES = new int[]{0, 0, 1500, 50, 0, 720, 1500, 70,
			0, 0, 50, 800, 0, 0, 350, 350, 0, 450, 1000, 350, 350, 0, 100, 300, 420, 350, 120, 300,
			540, 100, 120, 500, 1100, 0, 500, 500};
	private static final int[] LEVEL5VALUES = new int[]{0, 0, 1500, 50, 0, 720, 1500, 70, 0, 0, 
		    50, 800, 0, 440, 1000, 350, 0, 0, 1000, 350, 1100, 100, 200, 570, 1350, 0, 200, 350, 
			1350, 400, 200, 400};
	private static final int[] LEVEL6VALUES = new int[]{0, 0, 1500, 50, 0, 720, 1500, 70, 0, 0, 
			50, 500, 0,0, 600, 350, 0, 450, 300, 350, 400, 250, 200, 350, 670, 450, 470, 500, 
			600, 325, 270, 75, 650, 100, 500, 150 , 950, 100, 50, 350, 1220, 50, 500, 500, 
			1070, 300, 170, 100};
	private static final int[] LEVEL7VALUES = new int[]{0, 0, 1500, 50, 0, 720, 1500, 70, 
	0, 0, 50, 800};
	
	boolean level1Flag = true;
	boolean level2Flag = false;
	boolean level3Flag = false;
	boolean level4Flag = false;
	boolean level5Flag = false;
	boolean level6Flag = false;
	boolean level7Flag = false;

	int count;
	int deaths;
	int totalCoins;
	Player p;
	Obstacle o1, o2, o3;

	HashSet<Point> removedCoins = new HashSet<>();
	HashMap<Obstacle, String> movingO = new HashMap<>();
	HashMap<Obstacle, Boolean> onScreen = new HashMap<>();

	/**
	 * Creates the moving obstacles and sets death and coins to 0.
	 * @param p Player to add to game
	 */
	public GameViewer(Player p) {
		this.p = p;
		count = 0;
		o1 = new Obstacle(new Rectangle(O1_VALUES[0], O1_VALUES[1], O1_VALUES[2], O1_VALUES[3]), 
		O1_VALUES[4]);
		o2 = new Obstacle(new Rectangle(O2_VALUES[0], O2_VALUES[1], O2_VALUES[2], O2_VALUES[3]), 
		O2_VALUES[4]);
		o3 = new Obstacle(new Rectangle(O3_VALUES[0], O3_VALUES[1], O3_VALUES[2], O3_VALUES[3]), 
		O3_VALUES[4]);
		movingO.put(o1, DOWN);
		onScreen.put(o1, true);
		movingO.put(o2, LEFT);
		onScreen.put(o2, false);
		movingO.put(o3, LEFT);
		onScreen.put(o3, false);
		totalCoins = 0;
		deaths = 0;
	}

	/**
	 * Overrides JComponent paint to add coins and load levels.
	 * @param g Graphics to paint on
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		for(int x = 0; x < WIDTH; x += COIN_GAP)
		{
			for(int y = 0; y < HEIGHT; y += COIN_GAP)
			{
				if(!removedCoins.contains(new Point(x, y)) && !level7Flag)
					new Coin(x, y).draw(g2);
			}
		}

		if(level1Flag)
			level1(g2);
		else if(level2Flag)
			level2(g2);
		else if(level3Flag)
			level3(g2);
		else if(level4Flag)
			level4(g2);
		else if(level5Flag)
			level5(g2);
		else if(level6Flag)
			level6(g2);
		else if(level7Flag)
			level7(g2);
	}

	/**
	 * Starts the movement of obstacles.
	 */
	public void movement()
	{
		if(count < 1)
		{
			for(Obstacle o : movingO.keySet())
			{
				if(onScreen.get(o))
				{
					ActionListener listener = new TimerListener(this, o, movingO.get(o));
					Timer t = new Timer(o.speed, listener);
					t.start();
				}
			}
			count++;
		}
		Obstacle.boundingRect = new ArrayList<>();
	}

	/**
	 * Advances and repaints the player by a given amount.
	 * @param x move the player in the x-direction
	 * @param y move the player in the y-direction
	 */
	public void advancePlayer(int x, int y)
	{
		p.movePlayer(x, y);
		repaint();
	}

	/**
	 * Advances and repaints obstacles by a given amount.
	 * @param o the obstacle to move
	 * @param x move the obstacle in the x-direction
	 * @param y move the obstacle in the y-direction
	 */
	public void advanceObstacle(Obstacle o, int x, int y)
	{
		o.moveObstacle(x, y);
		repaint();
	}

	/**
	 * Removes the coin at the given point.
	 * @param point the location of the coin to remove
	 */
	public void removeCoin(Point point)
	{
		removedCoins.add(point);
		repaint();
	}

	/**
	 * Resets the map and the player.
	 */
	public void reset()
	{
		p = new Player(new Rectangle(DATester.X_START, DATester.Y_START, DATester.SIDE_LENGTH, DATester.SIDE_LENGTH), DATester.SPEED);
		Obstacle.boundingRect = new ArrayList<>();
		for(Obstacle o : movingO.keySet())
			resetObstacle(o);
		removedCoins = new HashSet<>();
		Coin.boundingRect = new ArrayList<>();
		repaint();
	}

	/**
	 * Resets the given obstacle.
	 * @param o the obstacle to reset
	 */
	public void resetObstacle(Obstacle o)
	{
		o.reset();
		repaint();
	}

	/**
	 * Level 1 to load
	 * @param g2 Graphics to paint level on
	 */
	public void level1(Graphics2D g2)
	{
		movement();
		for(int i = 0; i < LEVEL1VALUES.length; i += 4)
			helper(LEVEL1VALUES[i], LEVEL1VALUES[i + 1], LEVEL1VALUES[i + 2], LEVEL1VALUES[i + 3], g2);
		p.draw(g2);
        g2.drawString(DEATHS + deaths + COINS + totalCoins, TEXT_X2, TEXT_Y2);
        g2.drawString(DIRECTIONS, TEXT_X2, TEXT_Y3);
	}

	/**
	 * Level 2 to load
	 * @param g2 Graphics to paint level on
	 */
	public void level2(Graphics2D g2)
	{
		onScreen.put(o1, true);
		movement();
		Obstacle.boundingRect.add(o1.rect);
		for(int i = 0; i < LEVEL2VALUES.length; i += 4)
			helper(LEVEL2VALUES[i], LEVEL2VALUES[i + 1], LEVEL2VALUES[i + 2], LEVEL2VALUES[i + 3], g2);
		o1.draw(g2, Color.BLUE);
		p.draw(g2);
		g2.drawString(DEATHS + deaths + COINS + totalCoins, TEXT_X2, TEXT_Y2);
	}

	/**
	 * Level 3 to load
	 * @param g2 Graphics to paint level on
	 */
	public void level3(Graphics2D g2)
	{
		onScreen.put(o1, false);
		movement();
		for(int i = 0; i < LEVEL3VALUES.length; i += 4)
			helper(LEVEL3VALUES[i], LEVEL3VALUES[i + 1], LEVEL3VALUES[i + 2], LEVEL3VALUES[i + 3], g2);
		p.draw(g2);
		g2.drawString(DEATHS + deaths + COINS + totalCoins, TEXT_X2, TEXT_Y2);
	}

	/**
	 * Level 4 to load
	 * @param g2 Graphics to paint level on
	 */
	public void level4(Graphics2D g2)
	{
		onScreen.put(o2, true);
		movement();
		Obstacle.boundingRect.add(o2.rect);
		for(int i = 0; i < LEVEL4VALUES.length; i += 4)
			helper(LEVEL4VALUES[i], LEVEL4VALUES[i + 1], LEVEL4VALUES[i + 2], LEVEL4VALUES[i + 3], g2);
		o2.draw(g2, Color.BLUE);
		p.draw(g2);
		g2.drawString(DEATHS + deaths + COINS + totalCoins, TEXT_X2, TEXT_Y2);
	}

	/**
	 * Level 5 to load
	 * @param g2 Graphics to paint level on
	 */
	public void level5(Graphics2D g2)
	{
		onScreen.put(o2, false);
		onScreen.put(o3, true);
		movement();
		Obstacle.boundingRect.add(o3.rect);
		for(int i = 0; i < LEVEL5VALUES.length; i += 4)
			helper(LEVEL5VALUES[i], LEVEL5VALUES[i + 1], LEVEL5VALUES[i + 2], LEVEL5VALUES[i + 3], g2);
		o3.draw(g2, Color.BLUE);
		p.draw(g2);
		g2.drawString(DEATHS + deaths + COINS + totalCoins, TEXT_X2, TEXT_Y2);
	}

	/**
	 * Level 6 to load
	 * @param g2 Graphics to paint level on
	 */
	public void level6(Graphics2D g2)
	{
		onScreen.put(o3, false);
		movement();
		for(int i = 0; i < LEVEL6VALUES.length; i += 4)
			helper(LEVEL6VALUES[i], LEVEL6VALUES[i + 1], LEVEL6VALUES[i + 2], LEVEL6VALUES[i + 3], g2);
		p.draw(g2);
		g2.drawString(DEATHS + deaths + COINS + totalCoins, TEXT_X2, TEXT_Y2);
	}

	/**
	 * Level 7 to load
	 * @param g2 Graphics to paint level on
	 */
	public void level7(Graphics2D g2)
	{
		
		movement();
		for(int i = 0; i < LEVEL7VALUES.length; i += 4)
			helper(LEVEL7VALUES[i], LEVEL7VALUES[i + 1], LEVEL7VALUES[i + 2], LEVEL7VALUES[i + 3], g2);
		Font f = new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, FONT_SIZE);
		g2.setFont(f);
		g2.setColor(Color.GREEN);
		g2.drawString(PLAY_AGAIN, TEXT_X, HEIGHT / 2);
		g2.drawString(DEATHS + deaths + COINS + totalCoins, TEXT_X2, HEIGHT / 2 + TEXT_Y);
		p.draw(g2);
		
	}

	/**
	 * Helper method to create obstacles.
	 * @param x the x-coordinate of the obstacle
	 * @param y the y-coordinate of the obstacle
	 * @param length the length of the obstacle
	 * @param width the width of the obstacle
	 * @param g2 Graphics to paint on
	 */
	private void helper(int x, int y, int length, int width, Graphics2D g2)
	{
		Obstacle r1 = new Obstacle(new Rectangle(x, y, length, width));
		r1.draw(g2, Color.BLACK);
	}

}