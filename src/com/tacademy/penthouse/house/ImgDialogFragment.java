package com.tacademy.penthouse.house;

import java.io.File;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.penthouse.R;
import com.tacademy.penthouse.itemlike.CreateNewRoomActivity;

public class ImgDialogFragment extends DialogFragment {

	File mSavedFile;
	ImageView cancel;
	//	   TextView deleteImg;

	public interface OnItemDataClickListener{
		public void bringCamera(int cameraResult);
		public void bringGallery(int galleryResult);
		public void deletePicture(int deleteResult);
	}

	OnItemDataClickListener mListener;
	public void setOnItemDataClickListener(OnItemDataClickListener listener){
		mListener = listener;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.img_dialog_fragment, container, false);
		cancel = (ImageView)v.findViewById(R.id.cancel_editUserImg);
		//	      deleteImg = (TextView)v.findViewById(R.id.delete_img);
		//	      if(CreateNewRoomActivity.FROM_EDIT_ROOM == 1){
		//	    	  deleteImg.setVisibility(View.GONE);
		//	    	  CreateNewRoomActivity.FROM_EDIT_ROOM = -1;
		//	      }else{
		//	    	  deleteImg.setVisibility(View.VISIBLE);
		//	      }

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		Dialog d = getDialog();
		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		d.getWindow().setAttributes(layoutParams);

		TextView bring_from_gallery = (TextView)v.findViewById(R.id.bring_from_gallery);
		bring_from_gallery.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v)
			{
				if(mListener != null){
					mListener.bringGallery(1);
				}

				dismiss();
			}
		});

		TextView bring_from_camera = (TextView)v.findViewById(R.id.bring_from_camera);
		bring_from_camera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				if(mListener != null){
					mListener.bringCamera(1);
				}
				dismiss();

			}
		});

//		deleteImg.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if(mListener != null){
//					mListener.deletePicture(1);
//				}
//				dismiss();
//			}
//		});

		return v;
	}

	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),"temp_" + System.currentTimeMillis()/1000);

		return Uri.fromFile(mSavedFile);
	}   



	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
	}

}