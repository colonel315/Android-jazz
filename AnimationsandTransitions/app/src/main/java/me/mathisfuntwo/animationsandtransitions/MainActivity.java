package me.mathisfuntwo.animationsandtransitions;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.transition.TransitionManager;

public class MainActivity extends ActionBarActivity {

	ViewGroup screen;
	View button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		screen = (ViewGroup)findViewById(R.id.screen);

		Button restartButton = (Button)findViewById(R.id.restart);

		screen.setOnTouchListener(
				new RelativeLayout.OnTouchListener() {
					@Override
					public boolean onTouch(View view, MotionEvent motionEvent) {
						moveButton();
						return true;
					}
				}
		);

		restartButton.setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View view) {
						restart();
					}
				}
		);
	}

	/**
	 * moves the button
	 */
	public void moveButton() {
		button = findViewById(R.id.button);

		TransitionManager.beginDelayedTransition(screen);

		//  Change the position of the button
		RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		positionRules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		positionRules.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		button.setLayoutParams(positionRules);

		//  Change size of the button
		ViewGroup.LayoutParams sizeRules = button.getLayoutParams();
		sizeRules.width = 450;
		sizeRules.height = 300;
		button.setLayoutParams(sizeRules);
	}

	public void restart() {
		button = findViewById(R.id.button);

		TransitionManager.beginDelayedTransition(screen);

		//  Change the position of the button
		RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		positionRules.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		positionRules.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		button.setLayoutParams(positionRules);

		//  Change size of the button
		ViewGroup.LayoutParams sizeRules = button.getLayoutParams();
		sizeRules.width = 350;
		sizeRules.height = 150;
		button.setLayoutParams(sizeRules);
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
}
