package ninjacoder.stuckinthemud;

import java.io.Serializable;

/**
 * Created by Trey on 4/14/2015.
 */
public class Player implements Serializable {
	private int score;
	private String playerName;

	public Player(String playerName) {
		this.score = 0;
		this.playerName = playerName;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
