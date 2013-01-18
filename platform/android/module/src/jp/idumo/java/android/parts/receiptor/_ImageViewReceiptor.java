package jp.idumo.java.android.parts.receiptor;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;


// TODO 非検証
/**
 * 画像を表示するためのクラス
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _ImageViewReceiptor extends ImageView implements IfReceivable, IfExecutable {
	
	private IfSendable	sender;
	private Activity	activity;
	
	public _ImageViewReceiptor(Context context) {
		super(context);
		activity = (Activity) context;
		activity.setContentView(this);
	}
	
	@Override
	public boolean isReady() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	@Override
	public ConnectDataType receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public void run() {
		FlowingData p = sender.onCall();
		// Bitmap image = (Bitmap) p.get(0);
		// setImageBitmap(image);
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		IfSendable sender = senders[0];
		// ArrayList<Class<?>> list = new
		// ArrayList<Class<?>>(sender.getDataType());
		// if (list.get(0) == Bitmap.class) {
		// return true;
		// }
	}
	
}
