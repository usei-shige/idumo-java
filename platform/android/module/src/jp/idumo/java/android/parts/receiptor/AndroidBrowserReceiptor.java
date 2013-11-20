package jp.idumo.java.android.parts.receiptor;

import jp.idumo.java.android.annotation.IDUMOAndroid;
import jp.idumo.java.android.core.IfAndroidActivityController;
import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.manifest.AndroidPermission;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ArrayConnectDataType;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.validator.ReceiveValidatorSize;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Android上でブラウザを呼び出すReceiptorです
 * 10/18 未検証(Activity，Intentの受け渡し部分が微妙)
 * 
 * @author Yusei SHIGENOBU
 * 
 */
@IDUMOAndroid(permissions = { AndroidPermission.INTERNET })
@IDUMOReceiptor(receive = IfDataElement.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "URLをブラウザに表示", summary = "受け取ったURLをブラウザに表示する")
public class AndroidBrowserReceiptor implements IfReceivable, IfExecutable, IfAndroidActivityController {
	
	private Activity             activity;
	private Intent               intnt;
	private IfSendable           sender;
	private ReceiveValidatorSize vSize	= new ReceiveValidatorSize(1);
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(IfDataElement.class);
	}
	
	@Override
	public void run() {
		LogManager.log();
		FlowingData idf = sender.onCall();
		StringBuilder sb = new StringBuilder();
		for (IfDataElement d : idf) {
			sb.append(d.getText());
		}
		Uri uri = Uri.parse(sb.toString());
		LogManager.debug(uri);
		intnt = new Intent(Intent.ACTION_VIEW, uri);
	}
	

	@Override
	public void setSender(IfSendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
	@Override
	public void registActivity(AndroidActivityResource activity) {
		this.activity = activity.getActivity();
//		this.intnt = this.activity.getIntent();
	}
	
}
