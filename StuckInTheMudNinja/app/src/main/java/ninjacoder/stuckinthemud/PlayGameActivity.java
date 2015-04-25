package ninjacoder.stuckinthemud;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

import org.w3c.dom.Text;

import java.io.Serializable;


public class PlayGameActivity extends ActionBarActivity implements Serializable {
	private Game game;
	private ImageView diceView[];
	private TextView playerName;
	private TextView rotation;
	private TextView score;
	private TextView highestScoring;
	private MediaPlayer bloopSound;
	private MediaPlayer rollSound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);

		this.getSupportActionBar().hide();

		this.diceView = new ImageView[5];
		this.playerName = (TextView)findViewById(R.id.playerName);
		this.rotation = (TextView)findViewById(R.id.rotation);
		this.score = (TextView)findViewById(R.id.score);
		this.highestScoring = (TextView)findViewById(R.id.highestPlayer);
		this.bloopSound = MediaPlayer.create(this, R.raw.bloop_sound);
		this.rollSound = MediaPlayer.create(this, R.raw.roll_sound);

		this.setDice();
	}

	/**
	 * Dispatch onStart() to all fragments.  Ensure any created loaders are
	 * now started.
	 */
	@Override
	protected void onStart() {
		super.onStart();

		Bundle bundle = getIntent().getExtras();

		Serializable gameContent = bundle.getSerializable("game");

		if(gameContent != null) {
			this.game = (Game) gameContent;
		}

		this.playerName.setText("Player: " + this.game.getPlayersName(this.game.getCurrentPlayer()));
		this.rotation.setText("Rotation: " + this.game.getRotation());
		this.score.setText("Score: " + this.game.getPlayers(this.game.getCurrentPlayer()).getScore());

		this.setDice();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_play_game, menu);
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

	public void setDice() {
		//  get the ImageView
		this.diceView[0] = (ImageView)findViewById(R.id.die1);
		this.diceView[1] = (ImageView)findViewById(R.id.die2);
		this.diceView[2] = (ImageView)findViewById(R.id.die3);
		this.diceView[3] = (ImageView)findViewById(R.id.die4);
		this.diceView[4] = (ImageView)findViewById(R.id.die5);
	}

	public void resetDice() {
		this.diceView[0].setVisibility(View.VISIBLE);
		this.diceView[1].setVisibility(View.VISIBLE);
		this.diceView[2].setVisibility(View.VISIBLE);
		this.diceView[3].setVisibility(View.VISIBLE);
		this.diceView[4].setVisibility(View.VISIBLE);
	}

	public void rollDice(View view) {
		if(this.game.haveSound()) {
			this.bloopSound.start();

			this.bloopSound.setOnCompletionListener(
					new MediaPlayer.OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mediaPlayer) {
							rollSound.start();
						}
					}
			);

		}

		this.game.playZeGame(this);

		if(this.game.isTurnOver()) {
			this.game.nextPlayers();

			if(this.game.getRotation() > 5) {
				Toast.makeText(this,
						this.game.getHighestScoringPlayer().getPlayerName() +
								" is the highest scoring player with a score of " +
								this.game.getHighestScoringPlayer().getScore(), Toast.LENGTH_SHORT)
						.show();

				this.gameFinishMainMenu();
			}
			else {
				Toast.makeText(this, "Preparing next player", Toast.LENGTH_SHORT).show();

				this.game.setTurnOver(false);

				this.resetDice();

				Button rollButton = (Button) findViewById(R.id.rollButton);
				rollButton.setText("Roll Them Bad Boys");

				this.playerName.setText("Player: " + this.game.getPlayersName(this.game.getCurrentPlayer()));
				this.score.setText("Score: " + this.game.getPlayers(this.game.getCurrentPlayer()).getScore());

				if(this.game.getRotation() >
						Integer.parseInt(this.rotation.getText().toString().replaceAll("[\\D]", ""))) {
					this.rotation.setText("Rotation: " + this.game.getRotation());
				}
			}
		}
	}

	public void goToMainMenu(View view) {
		Intent mainMenu = new Intent(this, MainActivity.class);
		mainMenu.putExtra("game", (Serializable)this.game);
		startActivity(mainMenu);
	}

	public void gameFinishMainMenu() {
		Toast.makeText(this, "Loading new game", Toast.LENGTH_SHORT).show();

		Intent mainMenu = new Intent(this, MainActivity.class);

		this.game.setRotation(1);
		this.game.resetScore();
		mainMenu.putExtra("game", (Serializable)this.game);

		startActivity(mainMenu);
	}

	public ImageView getDie(int index) {
		return this.diceView[index];
	}

	public ImageView[] getDiceView() {
		return this.diceView;
	}

	public void setDiceView(ImageView[] diceView) {
		this.diceView = diceView;
	}

	public TextView getHighestScoring() {
		return this.highestScoring;
	}

	public TextView getScoreView() {
		return this.score;
	}
}
