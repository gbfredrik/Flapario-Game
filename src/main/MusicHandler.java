package main;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MusicHandler {
	private String pathChibiNinja = "./src/assets/sounds/Songs/Chibi_Ninja.wav";
	private String pathPickCoin = "./src/assets/sounds/FX/Pick_Coin.wav";
	private String pathPowerUp = "./src/assets/sounds/FX/Power_Up.wav";
	private String pathGameOver = "./src/assets/sounds/FX/Game_Over.wav";
	private String pathButtonClick = "./src/assets/sounds/FX/Button_Click.wav";
	private String pathJump = "./src/assets/sounds/FX/Jump.wav";

	private String mainSong = "ChibiNinja";

	// private Clip chibiNinja, pickCoin, powerUp, gameOver, buttonClick;

	private class Audio {
		Clip clip;
		String ID;
	}

	ArrayList<Audio> AudioList = new ArrayList<Audio>();

	public MusicHandler() {
		loadAudio(pathChibiNinja, "ChibiNinja");
		loadAudio(pathPickCoin, "PickCoin");
		loadAudio(pathPowerUp, "PowerUp");
		loadAudio(pathGameOver, "GameOver");
		loadAudio(pathButtonClick, "ButtonClick");
		loadAudio(pathJump, "Jump");
	}

	private void loadAudio(String filePath, String ID) {
		Audio newAudio = new Audio();
		try {
			AudioInputStream audioStream = AudioSystem
					.getAudioInputStream(new File(filePath));
			DataLine.Info info = new DataLine.Info(Clip.class,
					audioStream.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioStream);
			newAudio.clip = clip;
			newAudio.ID = ID;
			AudioList.add(newAudio);
			// clip.start();
			System.out.println("Loaded audio: " + ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Clip getClip(String ID) {
		for (Audio audio : AudioList) {
			if (ID.equals(audio.ID)) {
				return audio.clip;
			}
		}
		System.err.println("Audio ID not found!");
		return null;
	}

	public void playClip(String ID) {

		getClip(ID).start();
	}

	public void playClipFX(String ID) {
		Clip clip;
		clip = getClip(ID);
		clip.setFramePosition(0);
		clip.loop(0);
		clip.start();
	}

	public void stopClip(String ID) {
		getClip(ID).stop();
	}

	public void stopAll() {
		for (Audio audio : AudioList) {
			audio.clip.stop();
		}
	}

	public void closeAll() {
		for (Audio audio : AudioList) {
			audio.clip.close();
		}
	}

	public void resetFX() {
		for (Audio audio : AudioList) {
			if (!mainSong.equals(audio.ID)) {
				audio.clip.setFramePosition(0);
				System.out.println("set frames");
			}
		}
	}

	public void playSongClip(String ID) {
		Clip clip;
		clip = getClip(ID);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		// clip.;
	}

}
