package ninjacoder.stuckinthemud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;


public class MainActivity extends ActionBarActivity {
	private Game game;  //  Declaring the object game
	private Bundle bundle;  //  Declaring the object bundle
	private MediaPlayer bloopSound; //  Gotta have that bloop sound

	/**
	 * MainActivity constructor that will initialize bundle to a new bundle.
	 */
	public MainActivity() {
		this.bundle = new Bundle();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//hide the action bar
		this.getSupportActionBar().hide();

		this.game = new Game(); //  initialize game to a new game
		this.bloopSound = MediaPlayer.create(this, R.raw.bloop_sound); //   initialize the bloop sound

		this.printPlayers();    //  print out any players
	}

	@Override
	public void onResume() {
		super.onResume();

		//  get any information passed from a previous intent that was serializable
		Serializable gameContent = getIntent().getSerializableExtra("game");

		if(gameContent != null) {   //  make sure the info isn't null
			this.game = (Game) gameContent; //  set game as that previous information
		}


		ImageView soundIcon = (ImageView)findViewById(R.id.sound);  //  Get the sound icon

		if(this.game.haveSound()) { //  if it has sound
			soundIcon.setImageResource(R.drawable.sound);   //  show this picture
		}
		else {  //  otherwise
			soundIcon.setImageResource(R.drawable.no_sound);    //  show this picture
		}

		this.printPlayers();    //  print any player names.
	}

	/**
	 * calls that printPlayerList method inside of the PlayerList fragment
	 */
	private void printPlayers() {
		//  get the PlayerList fragment and support it
		PlayerList playerList = (PlayerList) getSupportFragmentManager().findFragmentById(R.id.playerList);
		playerList.printPlayerList(this.game);  //  call the printPlayerList method
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
		if(id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * starts the game activity if user has at least 2 players
	 * @param view -
	 */
	public void playGame(View view) {
		if(this.game.haveSound()) { //  if it got sound play it
			this.bloopSound.start();
		}

		if(this.game.getPlayerList().size() >= 2) { //  need at least two players
			Intent playGameIntent = new Intent(this, PlayGameActivity.class);   //  get the intent

			this.bundle.putSerializable("game", (Serializable)this.game);   //  pass through this.game info
			playGameIntent.putExtras(this.bundle);  //  put the game info in the intent

			startActivity(playGameIntent);  //  start activity
		}
		else {
			//  print out a message to the user saying they need two players.
			Toast.makeText(this, "need at least two players", Toast.LENGTH_SHORT).show();
		}
	}


	public void addNewPlayer(View view) {
		if(this.game.haveSound()) { //  play that sound
			this.bloopSound.start();
		}

		//  create a new intent that points towards the AddPlayer activity
		Intent addNewPlayerIntent = new Intent(this, AddPlayerActivity.class);

		//  put the game instance as a serializable in a bundle
		this.bundle.putSerializable("game", (Serializable)this.game);
		addNewPlayerIntent.putExtras(this.bundle);  //  put the bundle inside the intent

		startActivity(addNewPlayerIntent);  //  start activity AddPlayer
	}

	/**
	 * changes the sound icon and turns sound on and off.
	 * @param view -
	 */
	public void sound(View view) {
		//  get the soundIcon
		ImageView soundIcon = (ImageView)findViewById(R.id.sound);

		if(this.game.haveSound()) { //  if does not want sound
			soundIcon.setImageResource(R.drawable.no_sound);    //  display no sound icon
		}
		else {
			soundIcon.setImageResource(R.drawable.sound);   //  otherwise print sound icon
		}

		this.game.setSound(!this.game.haveSound()); //  to sound or not to sound
	}
}