package com.tacademy.penthouse.item;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import com.kakao.KakaoTalkLinkMessageBuilder;
import com.tacademy.penthouse.R;
import com.tacademy.penthouse.SplashActivity;
import com.tacademy.penthouse.entity.ItemData;
import com.tacademy.penthouse.entity.ResultData;
import com.tacademy.penthouse.entity.UserData;
import com.tacademy.penthouse.manager.NetworkManager;
import com.tacademy.penthouse.manager.UserManager;

public class ItemShareDialog extends DialogFragment {
	ImageView fb, kakao, cancel;
	ItemData iData;
	UserData myData = UserManager.getInstance().getuData();
	ProgressDialog dialog;
	private KakaoLink kakaoLink;
	private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;

	//fb
	public static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
	public static final List<String> READ_PERMISSIONS = Arrays.asList("read_stream");

	private boolean isSubsetOf(Collection<String> subset,
			Collection<String> superset) {
		for (String string : subset) {
			if (!superset.contains(string)) {
				return false;
			}
		}
		return true;
	}
	///////////////////////////////////
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, R.style.HouseDialog);
		try {
			kakaoLink = KakaoLink.getKakaoLink(getActivity());
			kakaoTalkLinkMessageBuilder = kakaoLink
					.createKakaoTalkLinkMessageBuilder();
		} catch (KakaoParameterException e) {
			alert(e.getMessage());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Dialog d = getDialog();
		View v = inflater.inflate(R.layout.item_share_layout, container, false);
		cancel = (ImageView) v.findViewById(R.id.cancel_share);
		fb = (ImageView) v.findViewById(R.id.share_facebook);

		Bundle itemB = getArguments();
		if (itemB != null) {
			iData = itemB.getParcelable("iData");
		}

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		fb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (myData == null) {
					Session.openActiveSession(getActivity(), ItemShareDialog.this, true, new StatusCallback() {
						@Override
						public void call(Session session, SessionState state,
								Exception exception) {
							if (session.isOpened()) {

								List<String> permission = session
										.getPermissions();
								if (!isSubsetOf(PERMISSIONS, permission)) {
									session.requestNewPublishPermissions(new Session.NewPermissionsRequest(
											ItemShareDialog.this, PERMISSIONS));
									return;
								}
								Bundle postParams = new Bundle();
								postParams.putString("message", "혼자서도 예쁘게 잘 산다! HousePic");
								postParams.putString("name",
										"1인 가구를 위한 인테리어 상품 추천 서비스:하우스픽 - ["+ iData.brand + "]:["+ iData.item_name + "]");
								postParams.putString("caption","HousePic(Mobile Application)");
								postParams.putString("link", iData.link);
								postParams.putString("picture",
										iData.item_img_url[0]);
								Request request = new Request(session,
										"me/feed", postParams, HttpMethod.POST,
										new Request.Callback() {

									@Override
									public void onCompleted(
											Response response) {
										if (response.getGraphObject() != null) {
											JSONObject obj = response
													.getGraphObject()
													.getInnerJSONObject();
											try {
												String id = obj.getString("id");
											} catch (JSONException e) {
												e.printStackTrace();
											}
											dialog.dismiss();
											dismiss();
										} else {
											FacebookRequestError error = response.getError();
											Toast.makeText(getActivity(),"공유하기에  실패하였습니다.", Toast.LENGTH_SHORT).show();
											dialog.dismiss();
										}

									}
								});
								request.executeAsync();
								dialog = new ProgressDialog(getActivity());
								dialog.setTitle("로딩중");
								dialog.setMessage("잠시만 기다려 주세요.");
								dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
								dialog.show();
							}
						}
					});
				}else{
					if(SplashActivity.LOGIN_TYPE == 1){
						Session.openActiveSession(getActivity(), ItemShareDialog.this,  true, new StatusCallback() {
							@Override
							public void call(Session session, SessionState state,
									Exception exception) {
								if (session.isOpened()) {

									List<String> permission = session
											.getPermissions();
									if (!isSubsetOf(PERMISSIONS, permission)) {
										session.requestNewPublishPermissions(new Session.NewPermissionsRequest(
												ItemShareDialog.this, PERMISSIONS));
										return;
									}
									Bundle postParams = new Bundle();
									postParams.putString("message", "혼자서도 예쁘게 잘 산다! HousePic");
									postParams.putString("name",
											"1인 가구를 위한 인테리어 상품 추천 서비스:하우스픽 - ["
													+ iData.brand + "]:["
													+ iData.item_name + "]");
									postParams.putString("caption",
											"HousePic(Mobile Application)");
									postParams.putString("link", iData.link);
									postParams.putString("picture",
											iData.item_img_url[0]);
									Request request = new Request(session,
											"me/feed", postParams, HttpMethod.POST,
											new Request.Callback() {

										@Override
										public void onCompleted(
												Response response) {
											if (response.getGraphObject() != null) {
												JSONObject obj = response
														.getGraphObject()
														.getInnerJSONObject();
												try {
													String id = obj.getString("id");

												} catch (JSONException e) {
													e.printStackTrace();
												}
												dialog.dismiss();
												dismiss();

											} else {
												FacebookRequestError error = response.getError();
												Toast.makeText(getActivity(),"공유하기에  실패하였습니다.", Toast.LENGTH_SHORT).show();
												dialog.dismiss();
											}

										}
									});
									request.executeAsync();
									dialog = new ProgressDialog(getActivity());
									dialog.setTitle("로딩중");
									dialog.setMessage("잠시만 기다려 주세요.");
									dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
									dialog.show();
								}
							}
						});
					}
					//facebook login
					else{
						Session.openActiveSession(getActivity(), ItemShareDialog.this, true, new StatusCallback() {
							@Override
							public void call(Session session, SessionState state,
									Exception exception) {
								if (session.isOpened()) {

									List<String> permission = session
											.getPermissions();
									if (!isSubsetOf(PERMISSIONS, permission)) {
										session.requestNewPublishPermissions(new Session.NewPermissionsRequest(
												ItemShareDialog.this, PERMISSIONS));
										return;
									}
									Bundle postParams = new Bundle();
									postParams.putString("message", "혼자서도 예쁘게 잘 산다! HousePic");
									postParams.putString("name",
											"1인 가구를 위한 인테리어 상품 추천 서비스:하우스픽 - ["
													+ iData.brand + "]:["
													+ iData.item_name + "]");
									postParams.putString("caption",
											"HousePic(Mobile Application)");
									postParams.putString("link", iData.link);
									postParams.putString("picture",
											iData.item_img_url[0]);
									Request request = new Request(session,
											"me/feed", postParams, HttpMethod.POST,
											new Request.Callback() {

										@Override
										public void onCompleted(
												Response response) {
											if (response.getGraphObject() != null) {
												JSONObject obj = response
														.getGraphObject()
														.getInnerJSONObject();
												try {
													String id = obj.getString("id");
													Toast.makeText(getActivity(), "공유하였습니다.", Toast.LENGTH_SHORT).show();
												} catch (JSONException e) {
													e.printStackTrace();
												}
												dialog.dismiss();
												dismiss();
											} else {
												FacebookRequestError error = response
														.getError();
												Toast.makeText(getActivity(),"공유하기에  실패하였습니다.", Toast.LENGTH_SHORT).show();
												dialog.dismiss();

											}

										}
									});
									request.executeAsync();
									dialog = new ProgressDialog(getActivity());
									dialog.setTitle("로딩중");
									dialog.setMessage("잠시만 기다려 주세요.");
									dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
									dialog.show();

								}
							}
						});
					}
				}
			}
		});

		kakao = (ImageView) v.findViewById(R.id.share_kakao);
		kakao.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
				//sendKakaoTalkLink();
				//dismiss();
			}
		});

		return v;
	}

	private void sendKakaoTalkLink() {// String textType, String linkType,
//		try {
			NetworkManager.getInstance().getItemShare(getActivity(), iData.item_num, new NetworkManager.OnResultListener<ResultData>() {

				@Override
				public void onSuccess(ResultData result) {
					if(getActivity()!=null)
						Toast.makeText(getActivity(),"공유성공", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onFail(int code) {
					if(getActivity()!=null)
						Toast.makeText(getActivity(),"공유하기에  실패하였습니다.", Toast.LENGTH_SHORT).show();
				}
			});
//			kakaoTalkLinkMessageBuilder.addText("1인 가구를 위한 인테리어 상품 추천 서비스: 하우스픽 - [" + iData.brand+"]"+iData.item_name);// getString(string.kakaolink_text));
//			kakaoTalkLinkMessageBuilder.addImage(iData.item_img_url[0], 180, 180);
//			kakaoTalkLinkMessageBuilder.addWebButton("아이템 구경가기", null);
//			kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder.build(),
//					getActivity());
//		} catch (KakaoParameterException e) {
//			alert(e.getMessage());
//		}
	}

	private void alert(String message) {
		new AlertDialog.Builder(getActivity())
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(R.string.app_name).setMessage(message)
		.setPositiveButton(android.R.string.ok, null).create().show();
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		Dialog d = getDialog();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (Session.getActiveSession() != null) {
			Session.getActiveSession().onActivityResult(getActivity(),
					requestCode, resultCode, data);
		}
	}
	@Override
	public void onResume() {
		super.onResume();
	}
}
