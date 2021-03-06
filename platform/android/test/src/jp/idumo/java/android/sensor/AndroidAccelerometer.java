package jp.idumo.java.android.sensor;

import jp.idumo.java.android.core.exec.AndroidComponent;
import jp.idumo.java.android.core.exec.AndroidWrapper;
import jp.idumo.java.android.parts.provider.AndroidAccelerometerProvider;
import jp.idumo.java.android.parts.receiptor.AndroidTextViewReceiptor;
import jp.idumo.java.exception.IDUMOException;

public class AndroidAccelerometer extends AndroidWrapper {
	@Override
	public void init() {
		setExecutionWithComponent(new AndroidComponent() {
			
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				AndroidAccelerometerProvider idumo1 = new AndroidAccelerometerProvider();
				add(idumo1);
				
				AndroidTextViewReceiptor idumor = new AndroidTextViewReceiptor();
				add(idumor);
				
				connect(idumo1, idumor);
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(-1);
				setSleepTime(1000);
			}
		});
	}
	
}
