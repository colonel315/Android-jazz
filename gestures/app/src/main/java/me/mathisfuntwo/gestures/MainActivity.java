package me.mathisfuntwo.gestures;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.view.AccessibilityDelegateCompat;

public class MainActivity extends ActionBarActivity implements GestureDetector.OnGestureListener,
																GestureDetector.OnDoubleTapListener {

	private TextView message;
	private GestureDetectorCompat gestureDetectorCompat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		message = (TextView)findViewById(R.id.message);
		//  the object is now able to detect gestures
		this.gestureDetectorCompat = new GestureDetectorCompat(this, this);
		//  allows detection of double tap
		gestureDetectorCompat.setOnDoubleTapListener(this);

		Button button = (Button)findViewById(R.id.textChanger);

		button.setOnClickListener(
				new Button.OnClickListener() {
					public void onClick(View view) {
						message.setText("Button tap");
					}
				}
		);

		button.setOnLongClickListener(
				new Button.OnLongClickListener() {
					public boolean onLongClick(View view) {
						message.setText("Button held");
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

	//////////////////////////BEGIN GESTURES////////////////////////////////////////
	/**
	 * Notified when a single-tap occurs.
	 * <p/>
	 * Unlike {@link OnGestureListener#onSingleTapUp(android.view.MotionEvent)}, this
	 * will only be called after the detector is confident that the user's
	 * first tap is not followed by a second tap leading to a double-tap
	 * gesture.
	 *
	 * @param e The down motion event of the single-tap.
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		message.setText("onSingleTapConfirmed");
		return true;
	}

	/**
	 * Notified when a double-tap occurs.
	 *
	 * @param e The down motion event of the first tap of the double-tap.
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		message.setText("onDoubleTap");
		return true;
	}

	/**
	 * Notified when an event within a double-tap gesture occurs, including
	 * the down, move, and up events.
	 *
	 * @param e The motion event that occurred during the double-tap gesture.
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		message.setText("onDoubleTapEvent");
		return true;
	}

	/**
	 * Notified when a tap occurs with the down {@link android.view.MotionEvent}
	 * that triggered it. This will be triggered immediately for
	 * every down event. All other events should be preceded by this.
	 *
	 * @param e The down motion event.
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		message.setText("onDown");
		return true;
	}

	/**
	 * The user has performed a down {@link android.view.MotionEvent} and not performed
	 * a move or up yet. This event is commonly used to provide visual
	 * feedback to the user to let them know that their action has been
	 * recognized i.e. highlight an element.
	 *
	 * @param e The down motion event
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		message.setText("onShowPress");
	}

	/**
	 * Notified when a tap occurs with the up {@link android.view.MotionEvent}
	 * that triggered it.
	 *
	 * @param e The up motion event that completed the first tap
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		message.setText("onSingleTapUp");
		return true;
	}

	/**
	 * Notified when a scroll occurs with the initial on down {@link android.view.MotionEvent} and the
	 * current move {@link android.view.MotionEvent}. The distance in x and y is also supplied for
	 * convenience.
	 *
	 * @param e1        The first down motion event that started the scrolling.
	 * @param e2        The move motion event that triggered the current onScroll.
	 * @param distanceX The distance along the X axis that has been scrolled since the last
	 *                  call to onScroll. This is NOT the distance between {@code e1}
	 *                  and {@code e2}.
	 * @param distanceY The distance along the Y axis that has been scrolled since the last
	 *                  call to onScroll. This is NOT the distance between {@code e1}
	 *                  and {@code e2}.
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		message.setText("onScroll");
		return true;
	}

	/**
	 * Notified when a long press occurs with the initial on down {@link android.view.MotionEvent}
	 * that trigged it.
	 *
	 * @param e The initial on down motion event that started the longpress.
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		message.setText("onLongPress");
	}

	/**
	 * Notified of a fling event when it occurs with the initial on down {@link android.view.MotionEvent}
	 * and the matching up {@link android.view.MotionEvent}. The calculated velocity is supplied along
	 * the x and y axis in pixels per second.
	 *
	 * @param e1        The first down motion event that started the fling.
	 * @param e2        The move motion event that triggered the current onFling.
	 * @param velocityX The velocity of this fling measured in pixels per second
	 *                  along the x axis.
	 * @param velocityY The velocity of this fling measured in pixels per second
	 *                  along the y axis.
	 * @return true if the event is consumed, else false
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		message.setText("onFling");
		return true;
	}
	/////////////////END GESTURES///////////////////////////


	/**
	 * Called when a touch screen event was not handled by any of the views
	 * under it.  This is most useful to process touch events that happen
	 * outside of your window bounds, where there is no view to receive it.
	 *
	 * @param event The touch screen event being processed.
	 * @return Return true if you have consumed the event, false if you haven't.
	 * The default implementation always returns false.
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//  checks to see if it is a gesture
		this.gestureDetectorCompat.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
}
