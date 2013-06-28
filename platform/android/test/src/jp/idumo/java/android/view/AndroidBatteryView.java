package jp.idumo.java.android.view;

import jp.idumo.java.android.core.exec.AndroidComponent;
import jp.idumo.java.android.core.exec.AndroidWrapper;
import jp.idumo.java.android.core.util.AndroidLogger;
import jp.idumo.java.android.parts.provider.AndroidBatteryProvider;
import jp.idumo.java.android.parts.receiptor.AndroidTextViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.util.LogManager;

public class AndroidBatteryView extends AndroidWrapper {

	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO)");
		setExecutionWithComponent(new AndroidComponent() {

			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidBatteryProvider idumo0 = new AndroidBatteryProvider();
				add(idumo0);
				AndroidTextViewReceiptor idumo1 = new AndroidTextViewReceiptor();
				add(idumo1);
				
				connect(idumo0, idumo1);
			}

			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
			
		});
	}
}
