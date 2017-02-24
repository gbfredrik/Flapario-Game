package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import sceneAreas.Scene1;
import menus.*;

public class GameWindow {
	private JFrame frame;

	public GameWindow() {
		createGame();
	}

	private void createGame() {
		// Create window
		frame = new JFrame("Flapario");
		/*try {
			frame.setIconImage(ImageIO.read(new File("./src/assets/png/mainchar/mainchar1.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		// Create renderArea
		final Scene1 scene1 = new Scene1(frame, 1280, 800, 160);
		frame.add(scene1);
		frame.pack();

		// Add window resize listener
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				scene1.rescale();
			}
		});

		frame.setVisible(true);

		scene1.start();
	}

	public static void main(String[] args) {
		new GameWindow();
	}
}