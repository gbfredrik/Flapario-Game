package menus;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.Spritesheet;
import sceneAreas.RenderArea;

public class MenuHandler extends JPanel {
	private static final long serialVersionUID = 1L;

	private final JPanel cardMenu = new JPanel(new CardLayout());
	protected final MainMenu mainMenu = new MainMenu();
	protected final HighscoreMenu highscoreMenu = new HighscoreMenu();
	// protected final RenderArea renderArea = new RenderArea();
	protected CardLayout cards;

	private final String pathSpritesheet = "./src/assets/png/gameTilesAndButtons/gameTilesAndButtons.png";
	private final String animationSpritesheet = "./src/assets/png/animations/animations.png";

	public MenuHandler(Container pane, int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		// this.setLayout(cardMenu);
		Spritesheet spritesheet = new Spritesheet(pathSpritesheet,
				animationSpritesheet);

		cardMenu.add(mainMenu, "mainMenu");
		cardMenu.add(highscoreMenu, "highscoreMenu");
		System.out.println("Added cards.");

		pane.add(cardMenu, BorderLayout.CENTER);
		cards = (CardLayout) (cardMenu.getLayout());
		cards.show(cardMenu, mainMenu.getName());
		this.setVisible(true);
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

} // SKALL KORRIGERAS. Bygger om RenderArea före detta.