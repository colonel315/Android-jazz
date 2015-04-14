package me.mathisfuntwo.recievebroadcastintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RecieveBroadcast extends BroadcastReceiver {
	public RecieveBroadcast() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		//  makes a pop up
		Toast.makeText(context, "Got that bad boy", Toast.LENGTH_LONG).show();
	}
}
