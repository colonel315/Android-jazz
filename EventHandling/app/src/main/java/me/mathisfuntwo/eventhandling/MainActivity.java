package me.mathisfuntwo.eventhandling;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//  grabs the button
		Button textChanger = (Button)findViewById(R.id.changeButton);

		//  adding click event listener
		textChanger.setOnClickListener(
			new Button.OnClickListener() {  //  interface
				public void onClick(View view) {    //  callback method inside interface
					TextView changingText = (TextView)findViewById(R.id.changingText);
					changingText.setText("Good job yo");
				}
			}
		);

		//  adding holding event listener
		textChanger.setOnLongClickListener(
				new Button.OnLongClickListener() {
					public boolean onLongClick(View view) {
						TextView changingText = (TextView)findViewById(R.id.changingText);
						changingText.setText("You are still holding me?");

						//  handles the event, if true only displays holding text, if false displays
						//  on click text
						return true;
					}
				}
		);
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
