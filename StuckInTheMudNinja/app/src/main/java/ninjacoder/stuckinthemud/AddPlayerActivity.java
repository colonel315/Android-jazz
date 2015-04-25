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
	private Game game;
	private MediaPlayer bloopSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_player);

		this.getSupportActionBar().hide();

		this.bloopSound = MediaPlayer.create(this, R.raw.bloop_sound);
	}

	@Override
	protected void onStart() {
		super.onStart();

		Bundle bundle = getIntent().getExtras();

		Serializable gameContent = bundle.getSerializable("game");

		if(gameContent != null) {
			this.game = (Game) gameContent;
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		Serializable gameContent = getIntent().getSerializableExtra("game");

		if(gameContent != null) {
			this.game = (Game) gameContent;
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

	public void clearPlayers(View view) {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		this.game.getPlayerList().clear();
	}

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

	public void cancel(View view) {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		this.goToMainActivity();
	}

	public void goToMainActivity() {
		if(this.game.haveSound()) {
			this.bloopSound.start();
		}

		Intent mainActivity = new Intent(this, MainActivity.class);

		mainActivity.putExtra("game", (Serializable)this.game);

		startActivity(mainActivity);
	}
}
