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
	private Game game;
	private Bundle bundle;
	private MediaPlayer bloopSound;

	public MainActivity() {
		this.bundle = new Bundle();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//hide the action bar
		this.getSupportActionBar().hide();

		this.game = new Game();
		this.bloopSound = MediaPlayer.create(this, R.raw.bloop_sound);

		this.printPlayers();
	}

	@Override
	public void onResume() {
		super.onResume();

		Serializable gameContent = getIntent().getSerializableExtra("game");

		if(gameContent != null) {
			this.game = (Game) gameContent;
		}


		ImageView soundIcon = (ImageView)findViewById(R.id.sound);

		if(this.game.haveSound()) {
			soundIcon.setImageResource(R.drawable.sound);
		}
		else {
			soundIcon.setImageResource(R.drawable.no_sound);
		}

		this.printPlayers();
	}

	/**
	 * Dispatch onPause() to fragments.
	 */
	@Override
	protected void onPause() {
		super.onPause();

		
	}

	private void printPlayers() {
		PlayerList playerList = (PlayerList) getSupportFragmentManager().findFragmentById(R.id.playerList);
		playerList.printPlayerList(this.game);
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

	public void playGame(View view) {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		if(this.game.getPlayerList().size() >= 2) {
			Intent playGameIntent = new Intent(this, PlayGameActivity.class);

			this.bundle.putSerializable("game", (Serializable)this.game);
			playGameIntent.putExtras(this.bundle);

			startActivity(playGameIntent);
		}
		else {
			Toast.makeText(this, "need at least two players", Toast.LENGTH_SHORT).show();
		}
	}

	public void addNewPlayer(View view) {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		Intent addNewPlayerIntent = new Intent(this, AddPlayerActivity.class);

		this.bundle.putSerializable("game", (Serializable)this.game);
		addNewPlayerIntent.putExtras(this.bundle);

		startActivity(addNewPlayerIntent);
	}

	public void sound(View view) {
		ImageView soundIcon = (ImageView)findViewById(R.id.sound);

		if(this.game.haveSound()) {
			soundIcon.setImageResource(R.drawable.no_sound);
		}
		else {
			soundIcon.setImageResource(R.drawable.sound);
		}

		this.game.setSound(!this.game.haveSound());
	}
}
