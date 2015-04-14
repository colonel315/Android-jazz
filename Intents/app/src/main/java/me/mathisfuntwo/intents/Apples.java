package me.mathisfuntwo.intents;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class Apples extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apples);
	}


	//in xml file of apple and bacon used android attribute of android:onClick="onClick"
	public void onClick(View view) {
		//  create instance of intent class
		Intent baconIntent = new Intent(this, Bacon.class);

		//  send data to bacon activity
		final EditText applesInput = (EditText)findViewById(R.id.applesInput);
		String userMessage = applesInput.getText().toString();
		baconIntent.putExtra("appleMessage", userMessage);

		startActivity(baconIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_apples, menu);
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
