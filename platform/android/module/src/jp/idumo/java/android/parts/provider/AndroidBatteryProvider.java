package jp.idumo.java.android.parts.provider;

import jp.idumo.java.android.component.sensor.AndroidBatteryComponent;
import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.core.IfAndroidActivityController;
import jp.idumo.java.android.core.IfAndroidController;
import jp.idumo.java.android.model.AndroidBatteryModel;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOProvider;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;

@IDUMOProvider(send = AndroidBatteryModel.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "Android端末のバッテリー情報の取得", summary = "AndroidのBattery情報を取得")
public class AndroidBatteryProvider implements IfSendable, IfAndroidController, IfAndroidActivityController{

	private AndroidBatteryComponent battery;

	public AndroidBatteryProvider() {
		battery = new AndroidBatteryComponent();
	}
	
	@Override
	public boolean isReady() {
		return battery.isReady();
	}

	@Override
	public void onIdumoStart() {}

	@Override
	public void onIdumoStop() {}

	@Override
	public void registActivity(AndroidActivityResource activity) {
		if(!battery.isInit()) {
			battery.init(activity);
		}
	}

	@Override
	public void onIdumoDestroy() {}

	@Override
	public void onIdumoPause() {
		battery.unregister();
	}

	@Override
	public void onIdumoRestart() {}

	@Override
	public void onIdumoResume() {
		battery.register();
	}

	@Override
	public FlowingData onCall() {
		LogManager.log();
		FlowingData p = new FlowingData();
		AndroidBatteryModel data = battery.getData();
		p.add(data);
		return p;
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(AndroidBatteryModel.class);
	}
}