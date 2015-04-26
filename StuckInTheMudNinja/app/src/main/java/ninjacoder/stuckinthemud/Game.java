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
	private ArrayList<Player> players = new ArrayList<Player>();    //  keeps track of all Player objects
	private int currentPlayer;  //  stores the current player
	private int round;  //  stores what round it is on
	private Player highestScoringPlayer;    //  stores the highest scoring player
	private boolean isTurnOver; //  stores if the turn is over or not
	private boolean sound;  //  stores if user wants sound or not

	/**
	 * initializes any variables that are needed to be initialized.
	 */
	public Game() {
		this.players = new ArrayList<Player>();
		this.currentPlayer = 0;
		this.round = 1;
		this.isTurnOver = false;
		this.sound = true;
	}

	/**
	 * gets the next player in the game.
	 */
	public void nextPlayers() {
		if(this.currentPlayer == this.players.size() - 1) {
			this.round++;
			this.currentPlayer = 0;
		}
		else {
			this.currentPlayer++;
		}
	}

	/**
	 * playing the game
	 * @param playGameActivity - the current instance of the game activity
	 */
	public void playZeGame(PlayGameActivity playGameActivity) {
		int score;  //  stores how much the user could possibly score
		int imageId;    //  keeps track of the imageId
		boolean diceAvailable = false;  //  stores if there are any dice available
		Random randScore = new Random();    //  needed for random scores


		for(int i = 0; i < playGameActivity.getDiceView().length; i++) {
			//  if the visibility of the die is View.GONE then continue to the next iteration
			if(playGameActivity.getDie(i).getVisibility() == View.GONE) {
				continue;
			}

			score = randScore.nextInt(5) + 1;   //  some random score

			if(score == 2 || score == 5) {  //  if score is 2 or 5 no points for you
				playGameActivity.getDie(score - 1).setVisibility(View.GONE);    //  make visibility View.GONE
			}
			else {
				//  set the new score to the current players score
				this.players.get(this.currentPlayer).setScore(this.players.get(this.currentPlayer).getScore() + score);

				//  set the text to the new score
				playGameActivity.getScoreView().setText("Score: " + this.players.get(this.currentPlayer).getScore());

				//  time to rotate an image
				this.rotateImage(playGameActivity.getDie(i), playGameActivity);

				//  go into the resources and get the die image I need
				imageId = playGameActivity.getApplicationContext().getResources()
						.getIdentifier("die_" + score, "drawable", playGameActivity.getPackageName());

				//  set the image to the needed die image.
				playGameActivity.getDie(i).setImageResource(imageId);

				//  if the highestScoringPlayer is either null or the current player's score is higher,
				//  change the variable to the current player and reset the text.
				if(this.highestScoringPlayer == null ||
						this.players.get(this.currentPlayer).getScore() > this.highestScoringPlayer.getScore()) {
					this.highestScoringPlayer = this.players.get(this.currentPlayer);

					playGameActivity.getHighestScoring().setText("Highest Score: " +
							this.highestScoringPlayer.getPlayerName());
				}

				diceAvailable = true;   //  dice available is true
			}
		}


		if(diceAvailable) { //  if true set the button text to let user know if they can roll again
			Button rollButton = (Button) playGameActivity.findViewById(R.id.rollButton);
			rollButton.setText("Roll that bad boy again!");
		}
		else {
			isTurnOver = true;  //  turn is over now, let player know that their turn is over
			Toast.makeText(playGameActivity.getApplicationContext(), "Your turn is over!",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * loads an Animation from the xml file called rotate_once and then start the animation
	 * @param die - contains the ImageView to rotate
	 * @param playGameActivity  -   get the application context of this instance of the activity
	 */
	public void rotateImage(ImageView die, PlayGameActivity playGameActivity) {
		//  loads the aninimation to the context of playGameActivity with the xml of rotate_once
		final Animation animRotate = AnimationUtils.loadAnimation(playGameActivity.getApplicationContext(),
																	R.anim.rotate_once);

		die.startAnimation(animRotate); //  start the animation
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
	 * returns the current round
	 * @return int
	 */
	public int getRound() {
		return this.round;
	}

	/**
	 * sets the current round
	 * @param round - sets the round
	 */
	public void setRound(int round) {
		this.round = round;
	}

	/**
	 * gets isTurnOver
	 * @return boolean
	 */
	public boolean isTurnOver() {
		return isTurnOver;
	}

	/**
	 * set if the users turn is over or not.
	 * @param isTurnOver - used to set this.isTurnOver
	 */
	public void setTurnOver(boolean isTurnOver) {
		this.isTurnOver = isTurnOver;
	}

	/**
	 * get the highest scoring player
	 * @return Player
	 */
	public Player getHighestScoringPlayer() {
		return highestScoringPlayer;
	}

	/**
	 * Returns if the user wants sound or not
	 * @return boolean
	 */
	public boolean haveSound() {
		return this.sound;
	}

	/**
	 * set if the user wants sound or not
	 * @param sound - sets the users sound preference
	 */
	public void setSound(boolean sound) {
		this.sound = sound;
	}
}
