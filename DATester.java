/**
 * Runs an instance of GameViewer and displays it in a JFrame.
 * @author Max Hartfield, Nathan Kim, Amit Kancherla
 * Collaborators: None
 * Teacher Name: Bailey
 * Period: 5
 * Due Date: 5/19/2022
 */

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Rectangle;

public class DATester extends JComponent {

	static final int WIDTH = 1500;
	static final int HEIGHT = 800;
	static final int X_START = 50;
	static final int Y_START = HEIGHT / 2 - 50 / 2;
	static final int SIDE_LENGTH = 35;
	static final int SPEED = 7;
	static final String NAME = "Dodge Adventure";
	
    public static void main(String[] args)
	{
		JFrame frame = new JFrame(NAME);
		GameViewer game = new  GameViewer(new Player
				(new Rectangle(X_START, Y_START, SIDE_LENGTH, SIDE_LENGTH), SPEED));
		PlayerMovement pm = new PlayerMovement(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(new DATester());
		frame.add(pm);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }
    
}