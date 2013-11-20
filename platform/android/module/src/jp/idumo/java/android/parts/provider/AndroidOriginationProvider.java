package jp.idumo.java.android.parts.provider;

import android.view.View;
import jp.idumo.java.android.annotation.IDUMOAndroid;
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
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "検索ワードを入力", summary = "起動時に検索ワードを決め，次のモジュールへ送信")
public class AndroidOriginationProvider implements IfSendable, IfAndroidController, IfAndroidActivityController{

	private AndroidOriginationComponent origination;
	private View view;
	private boolean isClick;
	
	public AndroidOriginationProvider() {
		origination = new AndroidOriginationComponent();
	}
	
	@Override
	public boolean isReady() {
		return origination.isReady();
	}
	
	public boolean isClick() {
		isClick = origination.isClick();
		return isClick;
	}

	@Override
	public FlowingData onCall() {
		LogManager.log();
		origination.onClick(view);
		FlowingData p = new FlowingData();
		if(isClick()) {
			AndroidOriginationModel data = origination.getData();
			p.add(data);
			return p;
		} else {
			onCall();
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
		if(!origination.isInit()) {
			LogManager.log();
			view = new View(activity.getActivity());
			activity.getActivity().setContentView(view);
			origination.init(activity);
		}
	}
	
	@Override
	public void onIdumoDestroy() {}

	@Override
	public void onIdumoPause() {}

	@Override
	public void onIdumoRestart() {}

	@Override
	public void onIdumoResume() {
		LogManager.log();
		origination.resister();
	}
}
