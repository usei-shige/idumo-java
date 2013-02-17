package jp.idumo.java.android.web;

import jp.idumo.java.android.core.exec.AndroidComponent;
import jp.idumo.java.android.core.exec.AndroidWrapper;
import jp.idumo.java.android.core.util.AndroidLogger;
import jp.idumo.java.android.parts.receiptor.AndroidTextViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.provider.LivedoorWeatherProvider;
import jp.idumo.java.util.LogManager;


public class LivedoorWeather extends AndroidWrapper {
	@Override
	public void init() {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new AndroidLogger("IDUMO");
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63); 
				add(idumo0);
				AndroidTextViewReceiptor idumor = new AndroidTextViewReceiptor();
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
