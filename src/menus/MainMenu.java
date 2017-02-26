package menus;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//import sceneAreas.Scene1;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage imgSpriteSheet;
	private final int spriteSize = 16;
	private BufferedImage imgStartButton;
	private BufferedImage imgHighscoreButton;
	private BufferedImage imgExitButton;

	// private Scene1 scene1;

	public MainMenu() {
		// this.scene1 = scene1;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		readImages();

		JButton startButton = new JButton(new ImageIcon(imgStartButton));
		startButton.setRolloverEnabled(true);
		// startButton.setRolloverSelectedIcon(new ImageIcon());
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setContentAreaFilled(false);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonPressed();
			}
		});
		add(startButton);
		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JButton highscoreButton = new JButton(new ImageIcon(imgHighscoreButton));
		highscoreButton.setRolloverEnabled(true);
		// highscoreButton.setRolloverSelectedIcon(new ImageIcon());
		highscoreButton.setBorder(BorderFactory.createEmptyBorder());
		highscoreButton.setContentAreaFilled(false);
		highscoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highscoreButtonPressed();
			}
		});
		add(highscoreButton);
		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JButton exitButton = new JButton(new ImageIcon(imgExitButton));
		exitButton.setRolloverEnabled(true);
		// exitButton.setRolloverSelectedIcon(new ImageIcon());
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButtonPressed();
			}
		});
		add(exitButton);
		System.out.println("sdfv");
	}

	private void readImages() {
		// TODO: Flytta till egen Spritesheet-klass
		try {
			imgSpriteSheet = ImageIO
					.read(new File(
							"./src/assets/png/gameTilesAndButtons/gameTilesAndButtons.png"));
			imgStartButton = imgSpriteSheet.getSubimage(48, 0, 3 * spriteSize,
					spriteSize);
			imgHighscoreButton = imgSpriteSheet.getSubimage(96, 0,
					3 * spriteSize, spriteSize);
			imgExitButton = imgSpriteSheet.getSubimage(192, 0, 3 * spriteSize,
					spriteSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void startButtonPressed() {
		// TODO: ?
		// scene1.start();
	}

	protected void highscoreButtonPressed() {
		// TODO: Öppna highscoremeny och dölj nuvarande genom cardlayout

	}

	protected void exitButtonPressed() {

	}
}