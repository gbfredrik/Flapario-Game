package menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gameRendering.RenderArea;
import gameRendering.Sprite;
import gameRendering.Spritesheet;
import main.FontHandler;
import main.MusicHandler;

public class MenuHandler extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private MusicHandler musicHandler;
	private FontHandler fontHandler;
	private Font boxyBoldFont;
	private final JPanel cardMenu;
	protected final MainMenu mainMenu;
	protected final HighscoreMenu highscoreMenu;
	protected RenderArea renderArea;
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
		fontHandler = new FontHandler();
		boxyBoldFont = new Font("Boxy Bold", Font.BOLD, 32);
		spritesheet = new Spritesheet(pathSpritesheet, animationSpritesheet);

		mainMenu = new MainMenu(this, musicHandler, boxyBoldFont);
		highscoreMenu = new HighscoreMenu(this, musicHandler, boxyBoldFont);
		renderArea = new RenderArea(frame, frame.getContentPane().getWidth(),
				frame.getContentPane().getHeight(), 160, this, musicHandler,
				boxyBoldFont);
		deathMenu = new DeathMenu(this, musicHandler, boxyBoldFont, renderArea);

		cardMenu = new JPanel(new CardLayout());
		cardMenu.add(mainMenu, "mainMenu");
		cardMenu.add(highscoreMenu, "highscoreMenu");
		cardMenu.add(renderArea, "gameSession");
		cardMenu.add(deathMenu, "deathMenu");
		pane.add(cardMenu, BorderLayout.CENTER);

		cards = (CardLayout) (cardMenu.getLayout());
		cards.show(cardMenu, mainMenu.getName());

		playMusic();

		this.setVisible(true);
	}

	private void playMusic() {
		musicHandler.playSongClip("ChibiNinja"); // Constant loop
	}

	public void onPressShow(String cardName, boolean clickSound) {
		doClickSound(clickSound);
		changeCard(cardName);
	}

	public void changeCard(String cardName) {
		if (cardName.equals("gameSession")) {
			cardMenu.remove(renderArea);
			renderArea = new RenderArea(frame, frame.getContentPane()
					.getWidth(), frame.getContentPane().getHeight(), 160, this,
					musicHandler, boxyBoldFont);
			cardMenu.add(renderArea, "gameSession");
			renderArea.startLoop();
		} else if (cardName.equals("highscoreMenu")) {
			highscoreMenu.refresh();
		} else if (cardName.equals("deathMenu")) {
			deathMenu.refresh();
		}
		cards.show(cardMenu, cardName);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// TODO: Flytta
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