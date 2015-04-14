/**
 * Author: Michael-James Parsons
 * Due Date: 3/27/2015
 * Description: An app that plays the "Stuck in the mud" game.
 *      - this game also integrates sound effects and graphics
 */

package com.example.mjayp_000.stuckinthemud;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import java.io.Serializable;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    //the game object
    public Game game;
    //use this to serialize data across objects
    public Bundle bundle;
    //sound effect for button click
    public MediaPlayer buttonClickSound;

    public MainActivity() {
        this.bundle = new Bundle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide the action bar (it's ugly)
        this.getSupportActionBar().hide();
        //Set the background color to a nice light grey
        this.getWindow().getDecorView().setBackgroundColor(0xFFF3F3F3);

        //start loading the sound effects
        this.buttonClickSound = MediaPlayer.create(this.getApplicationContext(), R.raw.bloop_sound);

        //Make the game
        this.game = new Game();

        this.showPlayerList();
    }

    public void onResume() {
        super.onResume();

        //When this activity resumes, reinstate the Game object
        //passed from the previous activity.
        if(getIntent().getSerializableExtra("game") != null) {
            this.game = (Game)getIntent().getSerializableExtra("game");
        }

        this.showPlayerList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
     * Switch to the "Add Player" screen
     * @param view
     */
    public void goToAddPlayerActivity(View view) {
        //play the click sound
        this.buttonClickSound.start();

        Intent addPlayerActivity = new Intent(this, AddPlayerActivity.class);
        //send the Game instance to the next activity
        this.bundle.putSerializable("game",(Serializable)this.game);
        addPlayerActivity.putExtras(this.bundle);

        //start the activity
        startActivity(addPlayerActivity);
    }


    /**
     * List all of the players in the game.
     */
    public void showPlayerList() {
        TextView listOfPlayersField = (TextView)findViewById(R.id.list_of_players);
        String playerRoster = "";
        int count = 1;
        if(this.game.getNumPlayers() > 0) {
            for(Player p : this.game.getPlayers()) {
                playerRoster += count + ". " + p.getName() + "\n";
                count++;
            }
        } else {
            playerRoster += "No players have been added to this game.";
        }

        listOfPlayersField.setText(playerRoster);
    }


    /**
     * Start the game when the user clicks the "Start Game" button.
     *
     * @param v
     */
    public void startGameButtonClick(View v) {
        //play the click sound
        this.buttonClickSound.start();

        //prevent the game from starting until at least 2 players have joined.
        if(this.game.getNumPlayers() < 2) {
            Toast.makeText(this.getApplicationContext(), "You must have at least 2 players!", Toast.LENGTH_LONG).show();
            return;
        }

        Intent gameActivity = new Intent(this, GameActivity.class);

        //send the Game instance to the next activity
        this.bundle.putSerializable("game",(Serializable)this.game);
        gameActivity.putExtras(this.bundle);

        //show the game activity
        startActivity(gameActivity);
    }
}
