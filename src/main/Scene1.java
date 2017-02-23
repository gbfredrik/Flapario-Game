package main;

public class Scene1 {
	
	Sprite mainCharacter;
	
	public Scene1(RenderArea renderArea) {
		
		mainCharacter = new Sprite();
		renderArea.addSprite(mainCharacter);
		mainCharacter.setPosition(0, 0);
		
		Sprite mainCharacter2 = new Sprite();
		renderArea.addSprite(mainCharacter2);
		mainCharacter2.setPosition(-renderArea.getGameWidth()/2, renderArea.getGameHeight()/2);
		
		Sprite mainCharacter3 = new Sprite();
		renderArea.addSprite(mainCharacter3);
		mainCharacter3.setPosition(-renderArea.getGameWidth()/2, -renderArea.getGameHeight()/2);
		
		Sprite mainCharacter4 = new Sprite();
		renderArea.addSprite(mainCharacter4);
		mainCharacter4.setPosition(renderArea.getGameWidth()/2, renderArea.getGameHeight()/2);
		
		Sprite mainCharacter5 = new Sprite();
		renderArea.addSprite(mainCharacter5);
		mainCharacter5.setPosition(renderArea.getGameWidth()/2, -renderArea.getGameHeight()/2);
	}
	
	// TODO: Bättre loop
	private void loop() {
		while(true) {
			
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
