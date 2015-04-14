package me.mathisfuntwo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Trey on 4/1/2015.
 */
public class BottomPictureFragment extends Fragment{

	private static TextView topMemeText;
	private static TextView bottomMemeText;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//  this is the fragment view
		View view = inflater.inflate(R.layout.bottom_picture_fragment, container, false);

		topMemeText = (TextView)view.findViewById(R.id.topMemeText);
		bottomMemeText = (TextView)view.findViewById(R.id.bottomMemeText);

		return view;
	}

	public void setMemeText(String top, String bottom) {
		topMemeText.setText(top);
		bottomMemeText.setText(bottom);
	}
}
