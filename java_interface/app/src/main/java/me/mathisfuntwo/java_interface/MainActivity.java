package me.mathisfuntwo.java_interface;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;
import android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//  create a relative layout object
		RelativeLayout relativeLayout = new RelativeLayout(this);
		//  background is now green
		relativeLayout.setBackgroundColor(Color.rgb(65,163,13));

		//  create a button object
		Button button = new Button(this);
		//  give button some text
		button.setText("Log in");
		//  add id to button
		button.setId(1);
		//  button is now red
		button.setBackgroundColor(Color.rgb(110,188,188));
		//  text is now black
		button.setTextColor(Color.rgb(255,206,56));

		//  create input field for username
		EditText username = new EditText(this);
		//  give background color
		username.setBackgroundColor(Color.rgb(250,168,24));
		//  give text color
		username.setTextColor(Color.rgb(55,82,109));
		//  gets information about app
		Resources resources = getResources();
		//  convert dip value to pixel
		int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, resources.getDisplayMetrics());
		//  set that input width
		username.setWidth(px);
		//  add id to username
		username.setId(2);

		//  sets attributes to an object
		RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams(
				//  WRAP_CONTENT automatically gets height and width of content
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		//  sets attributes to an object
		RelativeLayout.LayoutParams usernameDetails = new RelativeLayout.LayoutParams(
				//  WRAP_CONTENT automatically gets height and width of content
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		//  gives rules to usernameDetails object
		//  put username object above button
		usernameDetails.addRule(RelativeLayout.ABOVE, button.getId());
		usernameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		//  set margine for usernameDetails object
		usernameDetails.bottomMargin = 75;

		//  adds rules to button object
		buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
		buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);


		//  adds a widget and attributes of widget, inheritst from view class
		relativeLayout.addView(button, buttonDetails);
		relativeLayout.addView(username, usernameDetails);

		//  puts layout on screen, and puts button on screen
		setContentView(relativeLayout);
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
