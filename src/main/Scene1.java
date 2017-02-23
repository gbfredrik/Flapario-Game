package main;

public class GameLogic {
	
	Sprite mainCharacter;
	
	public GameLogic(RenderArea renderArea) {
		mainCharacter = new Sprite();
		renderArea.addSprite(mainCharacter);
	}
	
	// TODO: Bättre loop
	private void loop() {
		while(true) {
			
		}
	}
}
