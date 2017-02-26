package menus;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Sprite;
import main.Spritesheet;
import sceneAreas.RenderArea;

public class MenuHandler extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private final JPanel cardMenu = new JPanel(new CardLayout());
	protected final MainMenu mainMenu = new MainMenu(this);
	protected final HighscoreMenu highscoreMenu = new HighscoreMenu(this);
	// protected final RenderArea renderArea = new RenderArea();
	protected CardLayout cards;
	private Spritesheet spritesheet;

	private final String pathSpritesheet = "./src/assets/png/gameTilesAndButtons/gameTilesAndButtons.png";
	private final String animationSpritesheet = "./src/assets/png/animations/animations.png";

	public MenuHandler(Container pane, int width, int height, JFrame frame) {
		this.frame = frame;
		pane.setPreferredSize(new Dimension(width, height));
		spritesheet = new Spritesheet(pathSpritesheet, animationSpritesheet);
		cardMenu.add(mainMenu, "mainMenu");
		cardMenu.add(highscoreMenu, "highscoreMenu");

		pane.add(cardMenu, BorderLayout.CENTER);
		cards = (CardLayout) (cardMenu.getLayout());
		cards.show(cardMenu, mainMenu.getName());
		this.setVisible(true);
	}

	public void onPressShow(String cardName) {
		changeCard(cardName);
	}

	public void changeCard(String cardID) {
		cards.show(cardMenu, cardID);
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
		System.out.println("MenuHandler.getSprite() - @id: " + id);
		return spritesheet.getSprite(id);
	}

	public void executeExit() {
		frame.dispose();
	}

} // SKALL KORRIGERAS. Bygger om RenderArea före detta.