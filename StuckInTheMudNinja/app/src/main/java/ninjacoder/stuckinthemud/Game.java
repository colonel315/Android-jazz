package ninjacoder.stuckinthemud;

import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Trey on 4/14/2015.
 */
public class Game implements Serializable {
	private ArrayList<Player> players = new ArrayList<Player>();
	private int currentPlayer;
	private int rotation;
	private Player highestScoringPlayer;
	private boolean isTurnOver;
	private boolean sound;

	public Game() {
		this.players = new ArrayList<Player>();
		this.currentPlayer = 0;
		this.rotation = 1;
		this.isTurnOver = false;
		this.sound = true;
	}

	public void nextPlayers() {
		if(this.currentPlayer == this.players.size() - 1) {
			this.rotation++;
			this.currentPlayer = 0;
		}
		else {
			this.currentPlayer++;
		}
	}

	public void playZeGame(PlayGameActivity playGameActivity) {
		int score;
		int imageId;
		boolean diceAvailable = false;
		Random randScore = new Random();


		for(int i = 0; i < playGameActivity.getDiceView().length; i++) {
			if(playGameActivity.getDie(i).getVisibility() == View.GONE) {
				continue;
			}

			score = randScore.nextInt(5) + 1;

			if(score == 2 || score == 5) {
				playGameActivity.getDie(score - 1).setVisibility(View.GONE);
			}
			else {
				this.players.get(this.currentPlayer).setScore(this.players.get(this.currentPlayer).getScore() + score);

				playGameActivity.getScoreView().setText("Score: " + this.players.get(this.currentPlayer).getScore());

				this.rotateImage(playGameActivity.getDie(i), playGameActivity);

				imageId = playGameActivity.getApplicationContext().getResources()
						.getIdentifier("die_" + score, "drawable", playGameActivity.getPackageName());

				playGameActivity.getDie(i).setImageResource(imageId);

				if(this.highestScoringPlayer == null ||
						this.players.get(this.currentPlayer).getScore() > this.highestScoringPlayer.getScore()) {
					this.highestScoringPlayer = this.players.get(this.currentPlayer);

					playGameActivity.getHighestScoring().setText("Highest Score: " +
							this.highestScoringPlayer.getPlayerName());
				}

				diceAvailable = true;
			}
		}


		if(diceAvailable) {
			Button rollButton = (Button) playGameActivity.findViewById(R.id.rollButton);
			rollButton.setText("Roll that bad boy again!");
		}
		else {
			isTurnOver = true;
			Toast.makeText(playGameActivity.getApplicationContext(), "Your turn is over!",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void rotateImage(ImageView die, PlayGameActivity playGameActivity) {
		final Animation animRotate = AnimationUtils.loadAnimation(playGameActivity.getApplicationContext(),
																	R.anim.rotate_once);

		die.startAnimation(animRotate);
	}

	/**
	 * resets all of the scores to 0
	 */
	public void resetScore() {
		int i = 0;

		while(playersIterator(i)) {
			this.players.get(i).setScore(0);
		}
	}

	/**
	 * helper class to iterate through the entire ArrayList, avoids null pointer exceptions
	 * @param idx
	 * @return
	 */
	public Boolean playersIterator(int idx) {
		return this.players != null && !this.players.isEmpty() && idx < this.players.size();
	}

	/**
	 * gets the player at an index
	 * @param idx
	 * @return
	 */
	public Player getPlayers(int idx) {
		return players.get(idx);
	}

	/**
	 * gets the player name at the index
	 * @param idx
	 * @return
	 */
	public String getPlayersName(int idx) {
		return players.get(idx).getPlayerName();
	}

	/**
	 * adds player to the ArrayList
	 * @param player
	 */
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	/**
	 * returns player array list
	 * @return player
	 */
	public ArrayList<Player> getPlayerList() {
		return players;
	}

	/**
	 * returns current player's index
	 * @return int
	 */
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * sets the current player
	 * @param currentPlayer - sets current player
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * returns the current rotation
	 * @return int
	 */
	public int getRotation() {
		return this.rotation;
	}

	/**
	 * sets the current rotation
	 * @param rotation - sets the rotation
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	/**
	 * gets isTurnOver
	 * @return boolean
	 */
	public boolean isTurnOver() {
		return isTurnOver;
	}

	public void setTurnOver(boolean isTurnOver) {
		this.isTurnOver = isTurnOver;
	}

	/**
	 * get the highest scoring player
	 * @return
	 */
	public Player getHighestScoringPlayer() {
		return highestScoringPlayer;
	}

	public boolean haveSound() {
		return this.sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}
}
