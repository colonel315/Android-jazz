package com.example.mjayp_000.stuckinthemud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.Override;


public class AddPlayerActivity extends ActionBarActivity {
    //Make the Game object global for easy use
    public Game game;
    //use this to serialize data across objects
    public Bundle bundle;
    //sound effect for button click
    public MediaPlayer buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        //Hide the action bar (it's ugly)
        this.getSupportActionBar().hide();
        //Set the background color to a nice light grey
        this.getWindow().getDecorView().setBackgroundColor(0xFFF3F3F3);

        //start loading the sound effects
        this.buttonClickSound = MediaPlayer.create(this.getApplicationContext(), R.raw.bloop_sound);
    }

	@Override
	public void onStart() {
		super.onStart();

		//Retrieve the Game instance from the bundle passed to this
		//activity from MainActivity.
		this.bundle = getIntent().getExtras();
		if(this.bundle.getSerializable("game") != null) {
			this.game = (Game) this.bundle.getSerializable("game");
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_player, menu);
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
     * Handle the click event for the "Cancel" button.
     * @param v
     */
    public void cancelButtonClick(View v) {
        //play the click sound
        this.buttonClickSound.start();
        this.goToMainActivity();
    }

    /**
     * Switch to the main screen
     */
    public void goToMainActivity() {
        Intent mainActivity = new Intent(this, MainActivity.class);

        mainActivity.putExtra("game", (Serializable)this.game);
        startActivity(mainActivity);
    }

    /**
     * Validate user input, then create the new Player object and
     * return to the Main Activity
     * @param v
     */
    public void processNewPlayer(View v) {
        //play the click sound
        this.buttonClickSound.start();

        TextView playerName = (TextView)findViewById(R.id.add_player_input);

        if(playerName.getText().toString().equals("")) {
            Toast.makeText(this.getApplicationContext(), "Please enter a name for this player.", Toast.LENGTH_LONG).show();
            return;
        }

        this.game.addPlayer(new Player(playerName.getText().toString()));
        this.goToMainActivity();
    }
}
