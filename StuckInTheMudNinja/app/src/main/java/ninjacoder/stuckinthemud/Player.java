package ninjacoder.stuckinthemud;

import java.io.Serializable;

/**
 * Created by Trey on 4/14/2015.
 */
public class Player implements Serializable {
	private int score;  //  stores the players score
	private String playerName;  //  stores the players name

	/**
	 * initializes this.score and this.playerName
	 * @param playerName - sets this.playerName
	 */
	public Player(String playerName) {
		this.score = 0;
		this.playerName = playerName;
	}

	/**
	 * returns the player's score
	 * @return int
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Sets the player's score
	 * @param score - sets this.score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * returns the player's name
	 * @return String
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	 * Sets the player name
	 * @param playerName - sets this.playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
