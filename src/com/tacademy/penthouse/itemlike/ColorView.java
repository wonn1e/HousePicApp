package com.tacademy.penthouse.itemlike;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.entity.ColorData;

public class ColorView extends FrameLayout implements Checkable{
	public ColorView(Context context){
		super(context);
		init();
	}

	ImageView colorView;
	TextView colorName;
	ColorData cData;

	Drawable mDrawable;
	//private int[] CHECKED_STATE = { android.R.attr.state_checked };
	public boolean isChecked;

	private void init(){
		LayoutInflater.from(getContext()).inflate(R.layout.create_new_room_color, this);
		colorView = (ImageView)findViewById(R.id.colorImg);
		colorName = (TextView)findViewById(R.id.color);
	}

	public void setColorData(ColorData data){
		cData = data;
		colorName.setText(data.colorName);
		mDrawable = getContext().getResources().getDrawable(data.colorResId);
			drawIsCheck();
		
	}

	private void drawIsCheck() {
		if(isChecked){
			colorView.setImageResource(cData.checked);
		}else{
			colorView.setImageResource(cData.colorResId);
		}
	}

	@Override
	public void setChecked(boolean checked) {
		if(isChecked != checked){
			isChecked = checked;
			drawIsCheck();
		}
	}

	@Override
	public boolean isChecked() {
		return isChecked;
	}

	@Override
	public void toggle() {
		setChecked(!isChecked);
	}
}
