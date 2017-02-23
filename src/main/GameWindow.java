package main;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class GameWindow {

	private JFrame frame;

	public GameWindow() {
		createGame();
	}

	private void createGame() {
		frame = new JFrame("Flapario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(initialWidth, initialHeight);
		frame.setResizable(true);
		
		RenderArea renderArea = new RenderArea(frame, 1366, 768, 160);
		frame.add(renderArea);
		
		frame.pack();

		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				renderArea.rescale();
			}
		});

		Scene1 gameLogic = new Scene1(renderArea);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GameWindow paintProgram = new GameWindow();
	}
}
