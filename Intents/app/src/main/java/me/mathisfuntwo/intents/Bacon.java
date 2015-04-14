package me.mathisfuntwo.intents;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Bacon extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bacon);

		//  store extra data from apples class into applesData
		Bundle applesData = getIntent().getExtras();

		if(applesData == null) {
			return;
		}

		String appleMessage = applesData.getString("appleMessage");
		final TextView baconText = (TextView)findViewById(R.id.baconChangeText);
		baconText.setText(appleMessage);
	}

	//in xml file of apple and bacon used android attribute of android:onClick="onClick"
	public void onClick(View view) {
		//  create instance of intent class
		Intent intent = new Intent(this, Apples.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_bacon, menu);
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
