package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Sprite {

	private BufferedImage image;

	public Sprite() {
		try {
			image = ImageIO.read(new File(""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
