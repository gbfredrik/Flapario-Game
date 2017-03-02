package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Highscore implements Serializable {
	private static final long serialVersionUID = 1L;

	private final int listSize = 10;
	private ArrayList<Integer> highscores = new ArrayList<Integer>();

	public Highscore() {
		for (int i = 0; i < listSize; i++) {
			highscores.add(0);
		}
	}

	public void addScore(int score) {
		highscores.add(score);
		sortScores();
		highscores.remove(listSize - 1);
	}

	public void sortScores() {
		Collections.sort(highscores);
		Collections.reverse(highscores);
	}

	public int getSize() {
		return listSize;
	}

	public void printHighscores() {
		for (int i = 0; i < listSize; i++) {
			System.out.println(highscores.get(i));
		}
	}
}