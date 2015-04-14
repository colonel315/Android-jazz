package com.example.mjayp_000.stuckinthemud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;


public class GameActivity extends ActionBarActivity {
    //Make the Game object global for easy use
    public Game game;
    //All of our dice
    public ImageView[] dice;
    //sound effect for button click
    public MediaPlayer buttonClickSound;
    //sound effect for dice roll
    public MediaPlayer diceRollSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Hide the action bar (it's ugly)
        this.getSupportActionBar().hide();
        //Set the background color to a nice light grey
        this.getWindow().getDecorView().setBackgroundColor(0xFFF3F3F3);

        //start loading the sound effects
        this.buttonClickSound = MediaPlayer.create(this.getApplicationContext(), R.raw.bloop_sound);
        this.diceRollSound = MediaPlayer.create(this.getApplicationContext(), R.raw.roll_sound);

        //Get the game
        this.game = (Game)getIntent().getSerializableExtra("game");

        //Make the dice
        this.dice = new ImageView[5];
        this.setDiceDefaults();

        //Prepare the field for the first player's turn
        this.prepTurn();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Reset the GameActivity view components to match the new player.
     * - changes the button text from "Roll Again" to the "Roll"
     * - changes the heading to show the new current player's name
     * - resets the dice to their default values
     */
    public void prepTurn() {
        Button rollButton = (Button)this.findViewById(R.id.roll_dice);
        rollButton.setText("Roll");
        TextView currentPlayerHeading = (TextView)findViewById(R.id.current_player_turn_string);
        this.setDiceDefaults();
        this.game.setIsTurn(true);

        //let the user's know that it is now the next player's turn
        currentPlayerHeading.setText("It's your turn " + this.game.getPlayer().getName());
        Toast.makeText(this.getApplicationContext(), "It is now " + this.game.getPlayer().getName() + "'s turn.", Toast.LENGTH_LONG).show();
    }


    /**
     * Handle the click for the "Roll" button.
     * @param v
     */
    public void rollClickButton(View v) {
        //play the sound effect
        this.buttonClickSound.start();

        this.buttonClickSound.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        diceRollSound.start();
                    }
                }
        );

        //play the current player's turn
        this.game.playTurn(this);

        //switch turns when the current player runs out of dice
        if(this.game.isTurnOver()) {
            //Each game has 5 rotations. End it after the 5th.
            if(this.game.getRotation() >= 5) {
                Toast.makeText(this.getApplicationContext(),
                        "GAME OVER! " + this.game.getHighestScorePlayer().getName() + " wins with a score of " + this.game.getHighestScorePlayer().getScore(),
                        Toast.LENGTH_LONG).show();
                this.goToMainActivity();
                return;
            }

            //switch to the next player
            this.game.nextPlayer();
            this.prepTurn();
        }
    }


    /**
     * Reset all of the dice images to the default
     * "Die 1" image resource.
     */
    public void setDiceDefaults() {
        //Get the dice
        this.dice[0] = (ImageView)findViewById(R.id.dice_1_image);
        this.dice[1] = (ImageView)findViewById(R.id.dice_2_image);
        this.dice[2] = (ImageView)findViewById(R.id.dice_3_image);
        this.dice[3] = (ImageView)findViewById(R.id.dice_4_image);
        this.dice[4] = (ImageView)findViewById(R.id.dice_5_image);

        //Render images
        this.dice[0].setImageResource(R.drawable.die_1);
        this.dice[1].setImageResource(R.drawable.die_1);
        this.dice[2].setImageResource(R.drawable.die_1);
        this.dice[3].setImageResource(R.drawable.die_1);
        this.dice[4].setImageResource(R.drawable.die_1);
    }


    /**
     * Navigate to the Main Activity
     */
    public void goToMainActivity() {
        Toast.makeText(this.getApplicationContext(), "Loading new game...", Toast.LENGTH_LONG).show();
        Intent mainActivity = new Intent(this, MainActivity.class);
        this.game = new Game();
        startActivity(mainActivity);
    }
}
