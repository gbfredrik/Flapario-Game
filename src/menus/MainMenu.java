package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainWindow extends Sprite {
	private BufferedImage imgStartButton;
	private BufferedImage imgHighscoreButton;
	private BufferedImage imgExitButton;

	public MainWindow() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

		try {
			imgStartButton = ImageIO.read(new File(""));
			imgHighscoreButton = ImageIO.read(new File(""));
			imgExitButton = ImageIO.read(new File(""));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JButton startButton = new JButton(new ImageIcon(imgStartButton));
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setContentAreaFilled(false);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonPressed();
			}
		});
		jPanel.add(startButton);

		JButton highscoreButton = new JButton(new ImageIcon(imgHighscoreButton));
		highscoreButton.setBorder(BorderFactory.createEmptyBorder());
		highscoreButton.setContentAreaFilled(false);
		highscoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highscoreButtonPressed();
			}
		});
		jPanel.add(highscoreButton);

		JButton exitButton = new JButton(new ImageIcon(imgExitButton));
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButtonPressed();
			}
		});
		jPanel.add(exitButton);

	}

	protected void startButtonPressed() {
		// TODO Auto-generated method stub

	}

	protected void highscoreButtonPressed() {
		// TODO Auto-generated method stub

	}

	protected void exitButtonPressed() {
		// TODO Auto-generated method stub

	}
}
