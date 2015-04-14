package ninjacoder.additionflashcards;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class answerSide extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_side);

		// hide the action bar
		this.getSupportActionBar().hide();

		//  set text on page
		TextView sum = (TextView)findViewById(R.id.sum);
		sum.setText(this.getIntent().getExtras().getInt("answer")+"");

		// get boolean if it is correct or not
		boolean correct = this.getIntent().getExtras().getBoolean("correct");

		// create a toast object
		Toast toast = new Toast(this);

		RelativeLayout background = (RelativeLayout)findViewById(R.id.backBackground);

		if(correct) {
			toast = Toast.makeText(this.getApplicationContext(), "Got the problem correct!",
					Toast.LENGTH_SHORT);
			toast.show();
		}
		else {
			background.setBackgroundColor(Color.rgb(138, 21, 27));
			sum.setTextColor(Color.rgb(250,255,252));

			toast = Toast.makeText(this.getApplicationContext(), "Sorry, try again.",
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

//	@Override
//	protected void onStart() {
//		//  set text on page
//		TextView sum = (TextView)findViewById(R.id.sum);
//		sum.setText(this.getIntent().getExtras().getInt("answer")+"");
//
//		// get boolean if it is correct or not
//		boolean correct = this.getIntent().getExtras().getBoolean("correct");
//
//		// create a toast object
//		Toast toast = new Toast(this);
//
//		View background = findViewById(R.id.backBackground).getRootView();
//
//		if(correct) {
//			background.setBackgroundColor(Color.rgb(172, 206, 205));
//
//			sum.setTextColor(Color.rgb(138,21,27));
//
//			toast = Toast.makeText(this.getApplicationContext(), "Got the problem correct!",
//					Toast.LENGTH_SHORT);
//			toast.show();
//		}
//		else {
//			background.setBackgroundColor(Color.rgb(138, 21, 27));
//			sum.setTextColor(Color.rgb(250,255,252));
//
//			toast = Toast.makeText(this.getApplicationContext(), "Sorry, try again.",
//					Toast.LENGTH_SHORT);
//			toast.show();
//		}
//	}

	public void nextProblem(View view) {
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_answer_side, menu);
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
