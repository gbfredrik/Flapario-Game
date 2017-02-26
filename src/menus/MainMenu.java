package menus;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//import sceneAreas.Scene1;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private MenuHandler menuHandler;
	private BufferedImage imgStartButton;
	private BufferedImage imgHighscoreButton;
	private BufferedImage imgExitButton;
	

	// private Scene1 scene1;

	public MainMenu(MenuHandler menuHandler) {
		this.menuHandler = menuHandler;
		// this.scene1 = scene1;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getImages();

		JButton startButton = new JButton(new ImageIcon(imgStartButton));
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		BufferedImage imgStartButtonRollover = menuHandler.darkenImage(imgStartButton, 10, 10, 10);
		startButton.setRolloverEnabled(true);
		startButton.setRolloverSelectedIcon(new ImageIcon(imgStartButtonRollover));
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setContentAreaFilled(false);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("play");
			}
		});
		add(startButton);
		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JButton highscoreButton = new JButton(new ImageIcon(imgHighscoreButton));
		
		BufferedImage imgHighscoreButtonRollover = menuHandler.darkenImage(imgHighscoreButton, 10, 10, 10);
		highscoreButton.setRolloverEnabled(true);
		highscoreButton.setRolloverSelectedIcon(new ImageIcon());
		highscoreButton.setBorder(BorderFactory.createEmptyBorder());
		highscoreButton.setContentAreaFilled(false);
		highscoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("highscoreMenu");
			}
		});
		add(highscoreButton);
		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JButton exitButton = new JButton(new ImageIcon(imgExitButton));
		
		BufferedImage imgExitButtonRollover = menuHandler.darkenImage(imgExitButton, 10, 10, 10);
		exitButton.setRolloverEnabled(true);
		exitButton.setRolloverSelectedIcon(new ImageIcon(imgExitButtonRollover));
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButtonPressed();
			}
		});
		add(exitButton);
	}

	private void getImages() {
		System.out.println("fail1");
		imgStartButton = menuHandler.getSprite(1).getImage();
		System.out.println("fail2");
		imgHighscoreButton = menuHandler.getSprite(2).getImage();
		System.out.println("fail3");
		imgExitButton = menuHandler.getSprite(4).getImage();
		System.out.println("fail4");
	}

	protected void buttonPressed(String cardName) {
		menuHandler.onPressShow(cardName);
	}

	protected void exitButtonPressed() {
		menuHandler.executeExit();
	}
}