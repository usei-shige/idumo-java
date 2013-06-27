package jp.idumo.java.android.component.sensor;

import android.telephony.gsm.SmsManager;
import jp.idumo.java.android.core.AndroidActivityResource;

public class AndroidSMSSendComponent {
	private boolean isReady;
	private boolean isRegister;
	private boolean isInit;

	public boolean isReady() {
		return isReady;
	}

	public boolean isInit() {
		return isInit;
	}

	public void send(String sendAddress, String sendText) {
		if(!isRegister) {
			isRegister = true;
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(sendAddress, null, sendText, null, null);
		}
	}

	public void init(AndroidActivityResource activityResource) {
		if(!isInit) {
			isInit = true;
		}
	}
}
