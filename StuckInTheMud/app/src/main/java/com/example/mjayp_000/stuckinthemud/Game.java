package com.example.mjayp_000.stuckinthemud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

public class Game implements Serializable {
    //store the game's players
    private ArrayList<Player> players;
    //the player currently rolling
    private int currentPlayer;
    //the game round
    private int rotation;
    //the player with the highest score
    private Player highestScorePlayer;
    //the state of the current player's turn
    private boolean isTurn;
    private GameActivity gameActivity;

    public Game() {
        this.players = new ArrayList<Player>();
        //set the current player to the first index in the ArrayList of Players
        this.currentPlayer = 0;
        //set the round rotation to the 1 of 5
        this.rotation = 0;
        //show the the current player's turn is active
        this.isTurn = true;
    }


    /**
     * Switch to the next player
     */
    public void nextPlayer() {
        if(this.currentPlayer == this.players.size() - 1) {
            this.rotation++;
            this.currentPlayer = 0;
        } else {
            this.currentPlayer++;
        }
    }


    /**
     * Roll the dice and add up the score.
     * @param ga - the gameActivity instance
     */
    public void playTurn(GameActivity ga) {
        //the total score for this round
        int score;
        //will hold the resource ID for an ImageView
        int imageId;
        //the state of the available dice (once this is false, the turn will end)
        boolean diceAvailable = true;
        //use this to generate random integers for our dice
        Random randomInt = new Random();
        //the current GameActivity instance
        this.gameActivity = ga;
        //set this as false as default. If some dice still exists after a roll,
        //this will be set to true.
        diceAvailable = false;
        for (int i = 0; i < ga.dice.length; i++) {
            //skip dice that have been removed from play
            if (ga.dice[i] == null)
                continue;

            //generate a random integer between 1 and 6 inclusive to represent a dice
            score = randomInt.nextInt(6) + 1;

            //take the dice out of play if it rolls on a 2 or 5
            if (score == 2 || score == 5) {
                ga.dice[i].setImageDrawable(null);
                ga.dice[i] = null;
            } else {
                //add the new score to the player's current score
                this.getPlayer().addToScore(score);
                //get the resource id for the current dice
                imageId = ga.getApplicationContext().getResources().getIdentifier("die_" + score, "drawable", ga.getPackageName());
                //show the score's value on the dice ImageView
                ga.dice[i].setImageResource(imageId);
                //let the game know that this dice is still in play
                diceAvailable = true;

                //Set this Player's score as the high score if it is larger than the current high score
                if (this.highestScorePlayer == null || this.getPlayer().getScore() > this.highestScorePlayer.getScore()) {
                    this.highestScorePlayer = this.getPlayer();
                }
            }
        }

        //Let the user know that he/she can roll again because
        //there are still dice available.
        if(diceAvailable) {
            Button rollButton = (Button)ga.findViewById(R.id.roll_dice);
            rollButton.setText("Roll Again!");

        //No more dice available. Switch turns.
        } else {
            Toast.makeText(ga.getApplicationContext(), this.getPlayer().getName() + "'s score is " + this.getPlayer().getScore(), Toast.LENGTH_LONG).show();
            this.isTurn = false;
        }
    }


    /**
     * Return the Player object with the highest score.
     *
     * @return Player
     */
    public Player getHighestScorePlayer() {
        return this.highestScorePlayer;
    }


    /**
     * Get the game's current round.
     *
     * @return int
     */
    public int getRotation() {
        return this.rotation;
    }


    /**
     * Determines whether or not the current player's turn is over.
     * @return boolean
     */
    public boolean isTurnOver() {
        return (!this.isTurn);
    }


    /**
     * Set the state of the current players turn.
     * - true if it is their turn
     * - false if it is not.
     *
     * @param isTurn - the new value of this.isTurn (boolean)
     */
    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }


    /**
     * Add a player to the game.
     *
     * @param p - instance of Player
     */
    public void addPlayer(Player p) {
        this.players.add(p);
    }


    /**
     * Get the number of players in the game.
     *
     * @return int
     */
    public int getNumPlayers() {
        return this.players.size();
    }


    /**
     * Get the list of players.
     *
     * @return ArrayList<Player>
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }


    /**
     * Get the current player.
     *
     * @return Player
     */
    public Player getPlayer() {
        return this.players.get(this.currentPlayer);
    }
}
