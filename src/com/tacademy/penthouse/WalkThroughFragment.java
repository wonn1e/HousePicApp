package com.tacademy.penthouse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class WalkThroughFragment extends Fragment{
	ImageView iv;
	Button btn;
	int resId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		resId = b.getInt("img");
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.walkthrough_fragment, container,false);
		iv = (ImageView)v.findViewById(R.id.walkthrough);
		iv.setImageResource(resId);
		
		
		return v;
	}
}
