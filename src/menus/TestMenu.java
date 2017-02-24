package menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.GameWindow;

public class TestMenu extends JPanel {
	
	GameWindow gameWindow;

	public TestMenu(final GameWindow gameWindow, int width, int height) {
		
		this.gameWindow = gameWindow;
		
		this.setPreferredSize(new Dimension(width, height));
		
		JButton button1 = new JButton("Play");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.addScene1();
			}
		});
		add(button1);
		
		JButton button2 = new JButton("Fullscreen");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setFullscreen(true);
			}
		});
		add(button2);
		
		JLabel label = new JLabel("Press ESC to exit fullscreen");
		add(label);
		
	}
}
