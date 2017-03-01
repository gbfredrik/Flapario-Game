package menus;

import gameEngine.GameEngine;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.MusicHandler;
import main.Sprite;
import main.Spritesheet;

public class MenuHandler extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private MusicHandler musicHandler;
	private final JPanel cardMenu = new JPanel(new CardLayout());
	protected final MainMenu mainMenu;
	protected final HighscoreMenu highscoreMenu;
	protected GameEngine renderArea;
	protected final DeathMenu deathMenu;
	protected CardLayout cards;
	private Spritesheet spritesheet;

	private int width, height;
	private final String pathSpritesheet = "./src/assets/png/gameTilesAndButtons/gameTilesAndButtons.png";
	private final String animationSpritesheet = "./src/assets/png/animations/animations.png";

	public MenuHandler(Container pane, int width, int height, JFrame frame) {
		this.width = width;
		this.height = height;
		this.frame = frame;
		pane.setPreferredSize(new Dimension(this.width, this.height));

		musicHandler = new MusicHandler();

		spritesheet = new Spritesheet(pathSpritesheet, animationSpritesheet);
		mainMenu = new MainMenu(this, musicHandler);
		highscoreMenu = new HighscoreMenu(this, musicHandler);
		renderArea = new GameEngine(frame, frame.getContentPane().getWidth(),
				frame.getContentPane().getHeight(), 160, this, musicHandler);
		deathMenu = new DeathMenu(this, musicHandler);

		cardMenu.add(mainMenu, "mainMenu");
		cardMenu.add(highscoreMenu, "highscoreMenu");
		cardMenu.add(renderArea, "gameSession");
		cardMenu.add(deathMenu, "deathMenu");
		pane.add(cardMenu, BorderLayout.CENTER);

		cards = (CardLayout) (cardMenu.getLayout());
		cards.show(cardMenu, mainMenu.getName());

		this.setVisible(true);

		playMusic();
	}

	private void playMusic() { // FUNGERAR EJ
		musicHandler.playSongClip("ChibiNinja"); // Constant loop
	}

	public void onPressShow(String cardName, boolean clickSound) {
		doClickSound(clickSound);
		changeCard(cardName);
	}

	public void changeCard(String cardName) {
		if (cardName.equals("gameSession")) {
			cardMenu.remove(renderArea);
			renderArea = new GameEngine(frame, frame.getContentPane()
					.getWidth(), frame.getContentPane().getHeight(), 160, this,
					musicHandler);
			cardMenu.add(renderArea, "gameSession");
			renderArea.startLoop();
		}
		cards.show(cardMenu, cardName);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage darkenImage(BufferedImage img, int redShift,
			int greenShift, int blueShift) {
		Color newColor;
		int newRed, newGreen, newBlue;
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				newColor = new Color(img.getRGB(i, j));
				newRed = (newColor.getRed() - redShift < 0) ? 0 : newColor
						.getRed() - redShift;
				newGreen = (newColor.getGreen() - greenShift < 0) ? 0
						: newColor.getGreen() - greenShift;
				newBlue = (newColor.getBlue() - blueShift < 0) ? 0 : newColor
						.getBlue() - blueShift;
				newColor = new Color(newRed, newGreen, newBlue);
				img.setRGB(i, j, newColor.getRGB());
			}
		}
		return img;
	}

	public Sprite getSprite(int id) {
		return spritesheet.getSprite(id);
	}

	public void executeExit(boolean clickSound) {
		doClickSound(clickSound);
		musicHandler.closeAll();
		frame.dispose();
	}

	public void doClickSound(boolean clickSound) {
		if (clickSound) {
			musicHandler.playClipFX("ButtonClick");
		}
	}

} // SKALL KORRIGERAS. Bygger om RenderArea före detta.