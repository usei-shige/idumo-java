package jp.idumo.java.android.parts.provider;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import jp.idumo.java.android.annotation.IDUMOAndroid;
import jp.idumo.java.android.component.sensor.AndroidDialogComponent;
import jp.idumo.java.android.component.sensor.AndroidOriginationComponent;
import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.core.IfAndroidActivityController;
import jp.idumo.java.android.core.IfAndroidController;
import jp.idumo.java.android.model.AndroidOriginationModel;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOProvider;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfStringPrimitiveElement;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;


@IDUMOAndroid()
@IDUMOProvider(send = IfStringPrimitiveElement.class)
//@IDUMOInfo(author = "Yusei SHIGENOBU", display = "検索ワードを入力", summary = "起動時に検索ワードを決め，次のモジュールへ送信")
public class AndroidOriginationProvider implements IfSendable, IfAndroidController, IfAndroidActivityController{

//	private AndroidOriginationComponent origination;
	private AndroidDialogComponent aDialog;
	private Activity     activity;
//	private TextView     view;
//	private Button   button;
//	private EditText etext;
	private boolean isClick;
	
	public AndroidOriginationProvider() {
//		origination = new AndroidOriginationComponent();
		aDialog = new AndroidDialogComponent();
	}
	
	@Override
	public boolean isReady() {
//		return origination.isReady();
		return aDialog.isReady();
	}
	
	public boolean isClick() {
//		isClick = origination.isClick();
		isClick = aDialog.isClick();
		return isClick;
	}

	@Override
	public FlowingData onCall() {		
		LogManager.log();
		FlowingData p = new FlowingData();
		
		if(isClick) {
			//		AndroidOriginationModel data = origination.getData();
			AndroidOriginationModel data = aDialog.getData();
			p.add(data);
			return p;
		} else {
//			onIdumoPause();
			return null;
		}
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfStringPrimitiveElement.class);
	}

	@Override
	public void onIdumoStart() {}

	@Override
	public void onIdumoStop() {}

	@Override
	public void registActivity(AndroidActivityResource activity) {
		if(!aDialog.isInit()) {
			LogManager.log();
//			// Viewの設定
//			view = new TextView(activity.getActivity());
//			activity.getActivity().setContentView(view);
//			view.setTextSize(10.0f);
//			view.setText("AndroidOriginationProvider");
//			etext = new EditText(activity.getActivity());
//			button = new Button(activity.getActivity());
//			button.setText("OK");
//			
//			LinearLayout layout = new LinearLayout(activity.getActivity());
//			layout.addView(etext, new LinearLayout.LayoutParams(
//					LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//			layout.addView(button, new LinearLayout.LayoutParams(
//					LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//			activity.getActivity().setContentView(layout);
//			LogManager.log();
//			
//			origination.init(activity);
			aDialog.init(activity);
		}
	}
	
	@Override
	public void onIdumoDestroy() {}

	@Override
	public void onIdumoPause() {
		LogManager.log();
	}

	@Override
	public void onIdumoRestart() {
		LogManager.log();
	}

	@Override
	public void onIdumoResume() {
		LogManager.log();
//		origination.resister();
		aDialog.resister();
	}
}
