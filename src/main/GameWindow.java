package main;

import javax.swing.*;

public class GameWindow {
	
    private JFrame frame;
	
	public GameWindow() {
		createGame();
	}	

	private void createGame() {
		frame = new JFrame("Flapario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
		frame.setResizable(true);
		
		RenderArea renderArea = new RenderArea();
		frame.add(renderArea);
		
		GameLogic gameLogic = new GameLogic(renderArea);
		
        frame.setVisible(true);
    }
	
    public static void main(String[] args) {
    	GameWindow paintProgram = new GameWindow();
    }
}

