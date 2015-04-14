package ninjacoder.additionflashcards;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	TextView additionProblem;
	FlashCards flashCards;
	EditText userAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//hide the action bar
		this.getSupportActionBar().hide();

		//create FlashCard object
		this.flashCards = new FlashCards();

		//get text field
		this.userAnswer = (EditText)findViewById(R.id.userAnswer);

		//find the TextView
		this.additionProblem = (TextView)findViewById(R.id.additionProblem);

		//Set text
		this.additionProblem.setText(this.flashCards.createAdditionProblem());
	}

//	@Override
//	protected void onStart() {
//
//	}

//	@Override
//	protected void onResume() {
//
//	}
//
//	@Override
//	protected void onPause() {
//
//	}

	public void checkAnswer(View view) {
		String user = userAnswer.getText().toString();

		if(!user.matches("")) {
			Intent answerSide = new Intent(getApplicationContext(), answerSide.class);

			Bundle bundle = new Bundle();

			bundle.putInt("answer", flashCards.getSum());

			if(Integer.parseInt(user) == flashCards.getSum()) {
				bundle.putBoolean("correct", true);
			}
			else {
				bundle.putBoolean("correct", false);
			}

			answerSide.putExtras(bundle);
			startActivity(answerSide);
		}
		else {
			Toast toast = Toast.makeText(this.getApplicationContext(), "Input a value", Toast.LENGTH_SHORT);
			toast.show();
		}
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
