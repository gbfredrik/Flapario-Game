package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public final class Highscore {

	private static class HighscoreData implements Serializable {
		private static final long serialVersionUID = 1L;

		private final int listSize = 5;
		private ArrayList<Integer> highscores;
		private int latestScore;

		public HighscoreData() {
			highscores = new ArrayList<Integer>();
			for (int i = 0; i < listSize; i++) {
				highscores.add(0);
			}
		}

		public void addScore(int score) {
			latestScore = score;
			highscores.add(score);
			sortScores();
			highscores.remove(listSize);
		}

		public void sortScores() {
			Collections.sort(highscores);
			Collections.reverse(highscores);
		}

		public int getSize() {
			return listSize;
		}

		public int getScore(int index) {
			return highscores.get(index);
		}

		public int getLatestScore() {
			return latestScore;
		}
	}

	private static HighscoreData highscoreData;

	private Highscore() { // This class cannot be instantiated.
	}

	// Load saved highscores from file. Do this once when the game starts.
	public static void loadHighScores() {
		// System.out.println("Loading highscores from file.");
		highscoreData = new HighscoreData();

		File saveFile = new File("save.flapario");
		if (saveFile.exists() && !saveFile.isDirectory()) {
			try {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(saveFile));
				highscoreData = (HighscoreData) in.readObject();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			saveHighscores();
		}
	}

	public static void saveHighscores() {
		File saveFile = new File("save.flapario");
		try {
			ObjectOutputStream newSaveFile = new ObjectOutputStream(
					new FileOutputStream(saveFile));
			newSaveFile.writeObject(highscoreData);
			newSaveFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("Saved highscores to file.");
	}

	public static void clearHighscores() {
		highscoreData = new HighscoreData();
	}

	public static void addHighScore(int score) {
		highscoreData.addScore(score);
	}

	public static int getSize() {
		return highscoreData.getSize();
	}

	public static int getScore(int index) {
		return highscoreData.getScore(index);
	}

	public static int getLatestScore() {
		return highscoreData.getLatestScore();
	}
}