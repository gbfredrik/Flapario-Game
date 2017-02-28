package menus.Highscore;

import java.io.Serializable;
import java.util.Arrays;

public class Highscore implements Serializable {
	private static final long serialVersionUID = 1L;

	private final int listSize = 10;
	private int[] highscores = new int[listSize];

	public Highscore() {
		for (int i = 0; i < listSize; i++) {
			highscores[i] = 0;
		}
	}

	public void addScore(int score) {
		compareScores(score);
	}

	public int compareScores(int score) {

		return 0;
	}

	public void sortScores() {
		Arrays.sort(highscores);
	}

	public int getSize() {
		return listSize;
	}

	public void printHighscores() {

	}
}