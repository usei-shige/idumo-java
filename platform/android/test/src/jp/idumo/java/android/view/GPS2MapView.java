package jp.idumo.java.android.view;

import jp.idumo.java.android.core.exec.AndroidComponent;
import jp.idumo.java.android.core.exec.AndroidWrapper;
import jp.idumo.java.android.core.util.AndroidLogger;
import jp.idumo.java.android.parts.provider.AndroidGPSProvider;
import jp.idumo.java.android.parts.receiptor.AndroidMapViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.util.LogManager;

public class GPS2MapView extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidGPSProvider idumo0 = new AndroidGPSProvider();
				add(idumo0);
				AndroidMapViewReceiptor idumor = new AndroidMapViewReceiptor();
				add(idumor);
				
				connect(idumo0, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(-1);
				setSleepTime(1000);
			}
		});
	}
	
}
