package menu;

import gameRendering.RenderArea;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Highscore;
import main.MusicHandler;

public class DeathMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private MenuHandler menuHandler;
	private BufferedImage imgBackButton;
	private BufferedImage imgBackButtonRollover;
	private BufferedImage imgPlayButton;
	private BufferedImage imgPlayButtonRollover;
	private BufferedImage imgSplashBackground;
	
	private JLabel scoreText;

	public DeathMenu(MenuHandler menuHandler, MusicHandler musicHandler,
			Font font, RenderArea renderArea) {
		this.menuHandler = menuHandler;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getImages();

		add(Box.createRigidArea(new Dimension(0, 25)));

		JLabel titleText = new JLabel("GAME OVER!", JLabel.CENTER);
		titleText.setFont(font);
		titleText.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(titleText);

		add(Box.createRigidArea(new Dimension(0, 25)));

		scoreText = new JLabel("SCORE: " + Highscore.getLatestScore(),
				JLabel.CENTER);
		scoreText.setFont(font);
		scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(scoreText);

		this.add(Box.createRigidArea(new Dimension(0, 75)));

		JButton playButton = new JButton();
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

		JButton backButton = new JButton(new ImageIcon(imgBackButton));
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setRolloverEnabled(true);
		backButton
				.setRolloverSelectedIcon(new ImageIcon(imgBackButtonRollover));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("mainMenu", true);
			}
		});
		add(backButton);
	}
	
	public void refresh() {
		scoreText.setText("SCORE: " + Highscore.getLatestScore());
	}

	protected void buttonPressed(String cardName, boolean clickSound) {
		menuHandler.onPressShow(cardName, clickSound);
	}

	private void getImages() {
		imgPlayButton = menuHandler.getSprite(1).getImage();
		imgPlayButtonRollover = menuHandler.getSprite(2).getImage();
		imgBackButton = menuHandler.getSprite(5).getImage();
		imgBackButtonRollover = menuHandler.getSprite(6).getImage();
		imgSplashBackground = menuHandler.getSprite(400).getImage();
	}
}
