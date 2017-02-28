package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Spritesheet {
	private static BufferedImage spritesheet;
	private static BufferedImage animationSheet;
	private final int spriteSize = 16;
	private ArrayList<Sprite> buttonSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> textSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> platformSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> npcSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> backgroundSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> particleSprites = new ArrayList<Sprite>();

	public Spritesheet(String pathSpritesheet, String pathAnimationSheet) {
		try {
			spritesheet = ImageIO.read(new File(pathSpritesheet));
			animationSheet = ImageIO.read(new File(pathAnimationSheet));
			createAllSprites();
			scaleSpriteImages(buttonSprites);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createAllSprites() {
		// Hex spriteID:
		// 1-99 för knappar
		// 100-199 för bokstäver och text
		// 200-299 för plattformar
		// 300-399 för player och framtida NPC:s
		// 400-499 för bakgrunder
		// 500-599 för partiklar

		// Knappar, 1-99
		createSprite(
				spritesheet.getSubimage(48, 0, 3 * spriteSize, spriteSize), 1); // playButton
		createSprite(
				spritesheet.getSubimage(96, 0, 3 * spriteSize, spriteSize), 2); // -Rollover
		createSprite(
				spritesheet.getSubimage(144, 0, 3 * spriteSize, spriteSize), 3); // scoreButton
		createSprite(
				spritesheet.getSubimage(192, 0, 3 * spriteSize, spriteSize), 4); // -Rollover
		createSprite(
				spritesheet.getSubimage(240, 0, 3 * spriteSize, spriteSize), 5); // backButton
		createSprite(
				spritesheet.getSubimage(288, 0, 3 * spriteSize, spriteSize), 6); // -Rollover
		createSprite(
				spritesheet.getSubimage(336, 0, 3 * spriteSize, spriteSize), 7); // exitButton
		createSprite(
				spritesheet.getSubimage(384, 0, 3 * spriteSize, spriteSize), 8); // -Rollover

		// Bokstäver och text, 100-199

		// Plattformar, 200-299
		createSprite(
				spritesheet.getSubimage(176, 32, 2 * spriteSize, spriteSize),
				200); // Size 2
		System.out.println("added");
		createSprite(
				spritesheet.getSubimage(208, 32, 3 * spriteSize, spriteSize),
				201); // Size 3
		createSprite(
				spritesheet.getSubimage(256, 32, 4 * spriteSize, spriteSize),
				202); // Size 4
		createSprite(
				spritesheet.getSubimage(320, 32, 5 * spriteSize, spriteSize),
				203); // Size 5
		createSprite(
				spritesheet.getSubimage(400, 32, 10 * spriteSize, spriteSize),
				204); // Size 10
		createSprite(
				spritesheet.getSubimage(176, 48, 2 * spriteSize, spriteSize),
				210); // Size 2
		System.out.println("added");
		createSprite(
				spritesheet.getSubimage(208, 48, 3 * spriteSize, spriteSize),
				211); // Size 3
		createSprite(
				spritesheet.getSubimage(256, 48, 4 * spriteSize, spriteSize),
				212); // Size 4
		createSprite(
				spritesheet.getSubimage(320, 48, 5 * spriteSize, spriteSize),
				213); // Size 5
		createSprite(
				spritesheet.getSubimage(400, 48, 10 * spriteSize, spriteSize),
				214); // Size 10
		createSprite(
				spritesheet.getSubimage(176, 64, 2 * spriteSize, spriteSize),
				220); // Size 2
		System.out.println("added");
		createSprite(
				spritesheet.getSubimage(208, 64, 3 * spriteSize, spriteSize),
				221); // Size 3
		createSprite(
				spritesheet.getSubimage(256, 64, 4 * spriteSize, spriteSize),
				222); // Size 4
		createSprite(
				spritesheet.getSubimage(320, 64, 5 * spriteSize, spriteSize),
				223); // Size 5
		createSprite(
				spritesheet.getSubimage(400, 64, 10 * spriteSize, spriteSize),
				224); // Size 10

		// Player och NPC, 300-399
		createSprite(animationSheet.getSubimage(0, 0, spriteSize, spriteSize),
				300);
		createSprite(animationSheet.getSubimage(16, 0, spriteSize, spriteSize),
				301);
		createSprite(animationSheet.getSubimage(32, 0, spriteSize, spriteSize),
				302);
		createSprite(animationSheet.getSubimage(48, 0, spriteSize, spriteSize),
				303);
		createSprite(animationSheet.getSubimage(64, 0, spriteSize, spriteSize),
				304);
		createSprite(animationSheet.getSubimage(80, 0, spriteSize, spriteSize),
				305);
		createSprite(animationSheet.getSubimage(96, 0, spriteSize, spriteSize),
				306);
		createSprite(
				animationSheet.getSubimage(112, 0, spriteSize, spriteSize), 307);
		createSprite(
				animationSheet.getSubimage(16, 16, spriteSize, spriteSize), 308);
		createSprite(
				animationSheet.getSubimage(32, 16, spriteSize, spriteSize), 309);

		// Bakgrunder, 400-499
		createSprite(
				spritesheet.getSubimage(0, 144, 9 * spriteSize, 7 * spriteSize),
				400); // Default game background
		createSprite(spritesheet.getSubimage(160, 144, 9 * spriteSize,
				7 * spriteSize), 410); // Splashscreen

		// Partiklar, 500-599

	}

	private void createSprite(BufferedImage spriteImage, int id) {
		Sprite sprite = new Sprite(spriteImage, id);

		if (0 <= id && id <= 99) {
			buttonSprites.add(sprite);
		} else if (100 <= id && id <= 199) {
			textSprites.add(sprite);
		} else if (200 <= id && id <= 299) {
			platformSprites.add(sprite);
		} else if (300 <= id && id <= 399) {
			npcSprites.add(sprite);
		} else if (400 <= id && id <= 499) {
			backgroundSprites.add(sprite);
		} else if (500 <= id && id <= 599) {
			particleSprites.add(sprite);
		} else {
			System.err.println("@adding: No such sprite list. @ID: " + id);
			System.err.flush();
		}
	}

	public ArrayList<Sprite> getSpriteList(int id) {
		if (0 <= id && id <= 99) {
			return buttonSprites;
		} else if (100 <= id && id <= 199) {
			return textSprites;
		} else if (200 <= id && id <= 299) {
			return platformSprites;
		} else if (300 <= id && id <= 399) {
			return npcSprites;
		} else if (400 <= id && id <= 499) {
			return backgroundSprites;
		} else if (500 <= id && id <= 599) {
			return particleSprites;
		} else {
			System.err.println("@getting1: No such sprite list. @ID: " + id);
			System.err.flush();
			return null;
		}
	}

	public Sprite getSprite(int id) {
		System.out.println("Trying to get sprite from spritesheet");
		ArrayList<Sprite> tempGetFromList;
		tempGetFromList = getSpriteList(id);

		for (Sprite sp : tempGetFromList) {
			if (sp.getId() == id) {
				System.out.println("getID = " + sp.getId() + ". id = " + id);
				return sp;
			}
		}
		System.err.println("@getting2: No such sprite. @ID: " + id);
		System.err.flush();
		return null;
	}

	public void scaleSpriteImages(ArrayList<Sprite> rescaleList) {
		int width, height = 0;
		BufferedImage rescaledImage = null;
		if (!rescaleList.isEmpty()) {
			for (Sprite rescaleThis : rescaleList) {
				width = 3 * rescaleThis.getWidth();
				height = 3 * rescaleThis.getHeight();
				rescaledImage = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_ARGB);
				// _ARGB bibehåller transparens, _RGB gör ej.
				Graphics2D g = rescaledImage.createGraphics();
				AffineTransform at = AffineTransform.getScaleInstance(3, 3);
				g.drawRenderedImage(rescaleThis.getImage(), at);
				rescaleThis.setImage(rescaledImage);
			}
		}
	}
}
