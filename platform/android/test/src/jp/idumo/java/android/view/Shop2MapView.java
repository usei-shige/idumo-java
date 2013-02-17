package jp.idumo.java.android.view;

import jp.idumo.java.android.core.exec.AndroidComponent;
import jp.idumo.java.android.core.exec.AndroidWrapper;
import jp.idumo.java.android.core.util.AndroidLogger;
import jp.idumo.java.android.parts.provider.AndroidGPSProvider;
import jp.idumo.java.android.parts.receiptor.AndroidPinMapViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.handler.HotpepperHandler;
import jp.idumo.java.util.LogManager;

public class Shop2MapView extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidGPSProvider idumo0 = new AndroidGPSProvider();
				add(idumo0);
				// NumberProvider idumo0 = new NumberProvider(34.67);
				// add(idumo0);
				// NumberProvider idumo1 = new NumberProvider(135.52);
				// add(idumo1);
				// Number2GPSConverter idumo2 = new Number2GPSConverter();
				// add(idumo2);
				
				HotpepperHandler idumoh = new HotpepperHandler();
				add(idumoh);
				AndroidPinMapViewReceiptor idumor = new AndroidPinMapViewReceiptor();
				add(idumor);
				
				// connect(idumo0,idumo2);
				// connect(idumo1,idumo2);
				// connect(idumo2,idumoh);
				connect(idumo0, idumoh);
				connect(idumoh, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
	
}
