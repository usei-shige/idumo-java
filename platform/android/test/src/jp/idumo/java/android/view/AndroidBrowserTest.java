package jp.idumo.java.android.view;

import jp.idumo.java.android.core.exec.AndroidComponent;
import jp.idumo.java.android.core.exec.AndroidWrapper;
import jp.idumo.java.android.core.util.AndroidLogger;
import jp.idumo.java.android.parts.receiptor.AndroidBrowserReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.provider.StringProvider;
import jp.idumo.java.util.LogManager;

public class AndroidBrowserTest extends AndroidWrapper {

	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO)");
		setExecutionWithComponent(new AndroidComponent() {

			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				StringProvider idumo0 = new StringProvider("http://www.google.co.jp");
				add(idumo0);
				AndroidBrowserReceiptor idumo1 = new AndroidBrowserReceiptor();
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
