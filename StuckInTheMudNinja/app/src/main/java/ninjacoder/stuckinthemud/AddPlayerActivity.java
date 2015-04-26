package ninjacoder.stuckinthemud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;


public class AddPlayerActivity extends ActionBarActivity {
	private Game game;  //  stores the game instance
	private MediaPlayer bloopSound; //  stores the sound

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_player);

		this.getSupportActionBar().hide();  //  hide the action bar

		this.bloopSound = MediaPlayer.create(this, R.raw.bloop_sound);  //  create the bloop sound
	}

	@Override
	protected void onStart() {
		super.onStart();

		Bundle bundle = getIntent().getExtras();    //  get the extras and put it in the bundle

		Serializable gameContent = bundle.getSerializable("game");  //  get the serializable data from the bundle

		if(gameContent != null) {   //  check if it is null
			this.game = (Game) gameContent; //  store the instance inside of the current game instance
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		Serializable gameContent = getIntent().getSerializableExtra("game");    //  get the serializable content

		if(gameContent != null) {   //  check if null
			this.game = (Game) gameContent; //  store the instance inside of the current game instance
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
		if(id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * clears out any player's the game instance may have and resets the round to 1.
	 * @param view
	 */
	public void clearPlayers(View view) {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		this.game.getPlayerList().clear();
		this.game.setRound(1);
	}

	/**
	 * add's a new player to the game instance, otherwise displays a text to the user.
	 * @param view
	 */
	public void addNewPlayer(View view) {
		EditText newPlayer = (EditText)findViewById(R.id.newPlayer);

		String player = newPlayer.getText().toString();

		if(!player.matches("")) {
			Toast.makeText(this, "Player added.", Toast.LENGTH_SHORT).show();

			this.game.addPlayer(new Player(player));

			this.goToMainActivity();
		}
		else {
			Toast.makeText(this, "Please input something.", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * User changed their mind and going to MainActivity
	 * @param view
	 */
	public void cancel(View view) {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		this.goToMainActivity();
	}

	/**
	 * puts the current instance of game into the intent and then starts the main activity
	 */
	public void goToMainActivity() {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		Intent mainActivity = new Intent(this, MainActivity.class);

		mainActivity.putExtra("game", (Serializable)this.game);

		startActivity(mainActivity);
	}
}
