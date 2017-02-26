package main;

public class Player {
	//HANTERAR player-sprite, dess rörelser samt poäng
	private int score;
	
	public Player() {
		score = 0;
		
	}
	
	public void setScore(int point) {
		this.score += point;
	}
	
	public int getScore() {
		return score;
	}
	
	
	
}
