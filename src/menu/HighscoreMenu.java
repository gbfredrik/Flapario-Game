package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.Highscore;
import main.MusicHandler;

public class HighscoreMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private MenuHandler menuHandler;
	private BufferedImage imgBackButton;
	private BufferedImage imgBackButtonRollover;

	private JPanel scoreTablePanel;
	private JTextArea scoreTable;

	public HighscoreMenu(MenuHandler menuHandler, MusicHandler musicHandler,
			Font font) {
		this.menuHandler = menuHandler;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getImages();
		setBackground(new Color(153, 204, 255));

		this.add(Box.createRigidArea(new Dimension(0, 25)));

		JLabel titleText = new JLabel("HIGHSCORES", JLabel.CENTER);
		titleText.setFont(font);
		titleText.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(titleText);

		this.add(Box.createRigidArea(new Dimension(0, 0)));

		scoreTablePanel = new JPanel(new GridBagLayout());
		scoreTablePanel.setBackground(new Color(153, 204, 255));
		scoreTable = new JTextArea();
		refresh();
		scoreTable.setFont(font);
		scoreTable.setOpaque(false);
		scoreTable.setEditable(false);
		scoreTable.setHighlighter(null);
		// scoreTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		scoreTablePanel.add(scoreTable);
		this.add(scoreTablePanel);

		this.add(Box.createRigidArea(new Dimension(0, 0)));

		JButton backButton = new JButton(new ImageIcon(imgBackButton));
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setRolloverEnabled(true);
		backButton
				.setRolloverSelectedIcon(new ImageIcon(imgBackButtonRollover));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed("mainMenu", true);
			}
		});
		add(backButton);
	}

	protected void buttonPressed(String cardName, boolean clickSound) {
		menuHandler.onPressShow(cardName, clickSound);
	}

	public void refresh() {
		scoreTable.setText("");
		for (int i = 1; i <= Highscore.getSize(); i++) {
			if (i == Highscore.getSize()) {
				scoreTable.append(i + ":    " + Highscore.getScore(i - 1));
			} else {
				scoreTable.append(i + ":    " + Highscore.getScore(i - 1)
						+ "\n");
			}
		}
	}

	private void getImages() {
		imgBackButton = menuHandler.getSprite(5).getImage();
		imgBackButtonRollover = menuHandler.getSprite(6).getImage();
	}
}