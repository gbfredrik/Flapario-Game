package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 * Denna klass läser endast in den använda fonten och behandlas sedan från
 * menuHandler.
 * 
 * @author frebo147
 *
 */
public class FontHandler {
	private final String pathBoxyBoldFont = "./src/assets/font/Boxy_Bold.ttf";

	public FontHandler() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(
					pathBoxyBoldFont)));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
}
