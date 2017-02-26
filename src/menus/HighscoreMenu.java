package menus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;

import menus.Highscore.Highscore;

public class HighscoreMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private Highscore highscore = new Highscore();

	public HighscoreMenu(MenuHandler menuHandler) {
		readFileHighscore();
	}

	private void readFileHighscore() {
		File saveFile = new File("save.flapario");
		if (saveFile.exists() && !saveFile.isDirectory()) {
			try {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(saveFile));
				highscore = (Highscore) in.readObject();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				ObjectOutputStream newSaveFile = new ObjectOutputStream(
						new FileOutputStream(saveFile));
				newSaveFile.writeObject(highscore);
				newSaveFile.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}