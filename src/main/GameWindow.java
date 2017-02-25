package main;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import sceneAreas.Scene1;
import menus.*;

public class GameWindow {
	private JFrame frame;
	TestMenu menu;

	public GameWindow() {
		createGame();
	}

	private void createGame() {
		// Create window
		frame = new JFrame("Flapario");
		/*
		 * try { frame.setIconImage(ImageIO.read(new
		 * File("./src/assets/png/mainchar/mainchar1.png"))); } catch
		 * (IOException e1) { e1.printStackTrace(); }
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		menu = new TestMenu(this, 640, 400);
		frame.add(menu);
		frame.pack();

		frame.setVisible(true);
		
		frame.setFocusable(true);
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ESCAPE) {
					setFullscreen(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

	public void addScene1() {
		frame.remove(menu);

		final Scene1 scene1 = new Scene1(frame, frame.getContentPane().getWidth(), frame.getContentPane().getHeight(), 160);
		frame.add(scene1);
		frame.pack();

		// Add window resize listener
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				scene1.rescale();
			}
		});
		
		scene1.start();
	}
	
	// Fungerar inte så bra än... 
	public void setFullscreen(boolean bool) {
		frame.dispose();
		//frame = new JFrame("Flapario");
		if (bool) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frame.setUndecorated(bool);
		} else {
			frame.setExtendedState(JFrame.NORMAL);
			frame.setUndecorated(bool);
		}
//		menu = new TestMenu(this, frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
//		frame.add(menu);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GameWindow();
	}
}