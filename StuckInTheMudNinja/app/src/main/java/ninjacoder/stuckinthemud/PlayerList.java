package ninjacoder.stuckinthemud;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayerList extends android.support.v4.app.Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_player_list, container, false);
	}

	public void printPlayerList(Game game) {
		TextView playerColumn1 = (TextView) getView().findViewById(R.id.playerColumn1);
		TextView playerColumn2 = (TextView) getView().findViewById(R.id.playerColumn2);
		TextView playerColumn3 = (TextView) getView().findViewById(R.id.playerColumn3);

		String column1 = "";    //  used for playerColumn1
		String column2 = "";    //  used for playerColumn2
		String column3 = "";    //  used for playerColumn3

		int i = 0;

		while(game.playersIterator(i)) {
			if(i%3 == 0) {
				column1 += game.getPlayersName(i) + "\n";
			}
			else if(i%3 == 1) {
				column2 += game.getPlayersName(i) + "\n";
			}
			else {
				column3 += game.getPlayersName(i) + "\n";
			}

			i++;
		}

		playerColumn1.setText(column1);
		playerColumn2.setText(column2);
		playerColumn3.setText(column3);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}
