package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Spritesheet {
	private static BufferedImage spritesheet;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	
	public Spritesheet(String path) {
		try {
			spritesheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createSprite() {
		
	}
}
