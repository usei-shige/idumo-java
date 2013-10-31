package jp.idumo.java.android.parts.provider;

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


@IDUMOAndroid()
@IDUMOProvider(send = IfStringPrimitiveElement.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "検索ワードを入力", summary = "起動時に検索ワードを決め，次のモジュールへ送信")
public class AndroidOriginationProvider implements IfSendable, IfAndroidController, IfAndroidActivityController{

	private AndroidOriginationComponent origination;
	
	@Override
	public boolean isReady() {
		return origination.isReady();
	}

	@Override
	public FlowingData onCall() {
		// IDUMOLogManager.log();
		FlowingData p = new FlowingData();
		AndroidOriginationModel data = origination.getData();
		p.add(data);
		return p;
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
	public void onIdumoResume() {}

}
