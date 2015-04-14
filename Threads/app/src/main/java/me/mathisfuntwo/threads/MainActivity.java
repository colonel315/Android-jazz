package me.mathisfuntwo.threads;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends ActionBarActivity {

	Handler handler = new Handler() {
		/**
		 * Subclasses must implement this to receive messages.
		 *
		 * @param msg
		 */
		@Override
		public void handleMessage(Message msg) {
			TextView text = (TextView)findViewById(R.id.text);
			text.setText("Nice job.");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		// allows more than one thing going on at once
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				long futureTime = System.currentTimeMillis() + 10000;
				while(System.currentTimeMillis() < futureTime) {
					synchronized(this) {
						try {
							wait(futureTime-System.currentTimeMillis());
						}catch(Exception e){}
					}
				}
				handler.sendEmptyMessage(0);
			}
		};

		Thread threadHandler = new Thread(runnable);
		threadHandler.start();
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
