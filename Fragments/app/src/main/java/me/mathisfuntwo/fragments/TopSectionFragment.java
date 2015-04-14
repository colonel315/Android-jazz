package me.mathisfuntwo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Trey on 3/31/2015.
 */
public class TopSectionFragment extends Fragment {

	private static EditText topTextInput;
	private static EditText bottomTextInput;

	TopSectionListener activityCommander;

	public interface TopSectionListener {
		public void createMeme(String top, String bottom);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			activityCommander = (TopSectionListener)activity;
		}catch(ClassCastException e) {
			throw new ClassCastException(activity.toString());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//  this is the fragment view
		View topSection = inflater.inflate(R.layout.top_section_fragment, container, false);

		topTextInput = (EditText)topSection.findViewById(R.id.topTextInput);
		bottomTextInput = (EditText)topSection.findViewById(R.id.bottomTextInput);
		final Button button = (Button)topSection.findViewById(R.id.button);

		button.setOnClickListener(
			new Button.OnClickListener() {
				public void onClick(View view) {
					buttonClicked(view);
				}
			}
		);

		return topSection;
	}

	//  called when user clicks button
	public void buttonClicked(View view) {
		activityCommander.createMeme(topTextInput.getText().toString(), bottomTextInput.getText().toString());
	}
}
