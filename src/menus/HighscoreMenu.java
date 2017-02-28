package menus;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import menus.Highscore.Highscore;

public class HighscoreMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private Highscore highscore = new Highscore();
	private MenuHandler menuHandler;
	private BufferedImage imgBackButton;
	private BufferedImage imgBackButtonRollover;

	public HighscoreMenu(MenuHandler menuHandler) {
		this.menuHandler = menuHandler;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		readFileHighscore();
		getImages();
		this.add(Box.createRigidArea(new Dimension(0, 300)));

		JButton backButton = new JButton(new ImageIcon(imgBackButton));
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setRolloverEnabled(true);
		backButton
				.setRolloverSelectedIcon(new ImageIcon(imgBackButtonRollover));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("mainMenu");
			}
		});
		add(backButton);
	}

	protected void buttonPressed(String cardName) {
		menuHandler.onPressShow(cardName);
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

	private void getImages() {
		imgBackButton = menuHandler.getSprite(5).getImage();
		imgBackButtonRollover = menuHandler.getSprite(6).getImage();
	}
}