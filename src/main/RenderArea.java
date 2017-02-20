package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RenderArea extends JPanel {

	public RenderArea() {
		setBackground(Color.getHSBColor(175, 50, 75));
	}
	
	public void addSprite(Sprite sprite) {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
