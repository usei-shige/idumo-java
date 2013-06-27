package jp.idumo.java.android.parts.receiptor;

import jp.idumo.java.android.annotation.IDUMOAndroid;
import jp.idumo.java.android.component.sensor.AndroidSMSSendComponent;
import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.core.IfAndroidActivityController;
import jp.idumo.java.android.manifest.AndroidPermission;
import jp.idumo.java.annotation.IDUMOConstructor;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfBoolPrimitiveElement;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.validator.ReceiveValidatorSize;

@IDUMOAndroid(permissions = { AndroidPermission.SEND_SMS })
@IDUMOReceiptor(receive= IfBoolPrimitiveElement.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "SMSの送信", summary = "AndroidからSMSを送信します")
public class AndroidSMSSendReceiptor implements IfReceivable, IfExecutable, IfAndroidActivityController{

	private AndroidSMSSendComponent	sms;
	private IfSendable				sender;
	private ReceiveValidatorSize	vSize		= new ReceiveValidatorSize(1);
	public String address;
	public String text;
	public boolean isBool;

	@IDUMOConstructor({"送信先番号", "送信するメッセージ"})
	public AndroidSMSSendReceiptor(String sendAddress, String sendText) {
		address = sendAddress;
		text = sendText;
	}

	@Override
	public boolean isReady() {
		return sms.isReady();
	}

	@Override
	public void registActivity(AndroidActivityResource activity) {
		if(!sms.isInit()) {
			sms.init(activity);
		}
	}

	@Override
	public void run() {
		// boolean判定によってsmsを送信するかどうかを判断
		LogManager.log();
		FlowingData idf = sender.onCall();
		IfBoolPrimitiveElement boolPrimitive = (IfBoolPrimitiveElement) idf.next();
		isBool = boolPrimitive.getBool();
		if(isBool) {
			sms.send(address, text);
		}
	}

	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(IfBoolPrimitiveElement.class);
	}

	@Override
	public void setSender(IfSendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}	
}