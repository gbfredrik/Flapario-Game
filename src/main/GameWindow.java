package main;

import javax.swing.*;

import menu.*;

/**
 * Denna klass skapar programmets frame och en MenuHandler som beskrivs separat.
 * 
 * @author frebo147
 *
 */
public class GameWindow {

	private JFrame frame;
	private MenuHandler menuHandler;
	private final int width = 640, height = 400;

	public GameWindow() {
		createGame();
	}

	private void createGame() {
		// Load highscores
		Highscore.loadHighScores();

		// Create window
		frame = new JFrame("Flapario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		// Add menuhandler
		menuHandler = new MenuHandler(frame.getContentPane(), width, height,
				frame);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() { // För säkerhet
					public void run() {
						new GameWindow();
					}
				});
	}
}