package menus;

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

import main.MusicHandler;

//import sceneAreas.Scene1;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private MenuHandler menuHandler;
	private BufferedImage imgPlayButton;
	private BufferedImage imgPlayButtonRollover;
	private BufferedImage imgHighscoreButton;
	private BufferedImage imgHighscoreButtonRollover;
	private BufferedImage imgExitButton;
	private BufferedImage imgExitButtonRollover;
	private BufferedImage imgSplashBackground;

	// private Scene1 scene1;

	public MainMenu(MenuHandler menuHandler, MusicHandler musicHandler) {
		this.menuHandler = menuHandler;
		// this.scene1 = scene1;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getImages();

		this.add(Box.createRigidArea(new Dimension(0, 100)));

		JButton playButton = new JButton();// new JButton(new
											// ImageIcon(imgStartButton));
		playButton.setIcon(new ImageIcon(imgPlayButton));
		playButton.setAlignmentX(CENTER_ALIGNMENT);
		playButton.setRolloverEnabled(true);
		playButton
				.setRolloverSelectedIcon(new ImageIcon(imgPlayButtonRollover));
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("gameSession", true);
			}
		});
		add(playButton);
		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JButton highscoreButton = new JButton(new ImageIcon(imgHighscoreButton));
		highscoreButton.setAlignmentX(CENTER_ALIGNMENT);
		highscoreButton.setRolloverEnabled(true);
		highscoreButton.setRolloverSelectedIcon(new ImageIcon(
				imgHighscoreButtonRollover));
		highscoreButton.setBorder(BorderFactory.createEmptyBorder());
		highscoreButton.setContentAreaFilled(false);
		highscoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("highscoreMenu", true);
			}
		});
		add(highscoreButton);
		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JButton exitButton = new JButton(new ImageIcon(imgExitButton));
		exitButton.setAlignmentX(CENTER_ALIGNMENT);
		exitButton.setRolloverEnabled(true);
		exitButton
				.setRolloverSelectedIcon(new ImageIcon(imgExitButtonRollover));
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButtonPressed(true);
			}
		});
		add(exitButton);
	}

	private void getImages() {
		imgPlayButton = menuHandler.getSprite(1).getImage();
		imgPlayButtonRollover = menuHandler.getSprite(2).getImage();
		imgHighscoreButton = menuHandler.getSprite(3).getImage();
		imgHighscoreButtonRollover = menuHandler.getSprite(4).getImage();
		imgExitButton = menuHandler.getSprite(7).getImage();
		imgExitButtonRollover = menuHandler.getSprite(8).getImage();
		imgSplashBackground = menuHandler.getSprite(400).getImage();
	}

	protected void buttonPressed(String cardName, boolean clickSound) {
		System.out.println("Setting menu: " + cardName);
		menuHandler.onPressShow(cardName, clickSound);
		System.out.println("Set menu: " + cardName);
	}

	protected void exitButtonPressed(boolean clickSound) {
		menuHandler.executeExit(clickSound);
	}
}