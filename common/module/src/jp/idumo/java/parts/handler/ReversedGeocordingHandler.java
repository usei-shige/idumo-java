package jp.idumo.java.parts.handler;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOHandler;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.component.ReversedGeocordingComponent;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.element.IfLatLngElement;
import jp.idumo.java.model.element.IfLatLngElement.LatLngModel;
import jp.idumo.java.model.primitive.IfStringPrimitiveElement;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.ReceiveValidatorSize;
import jp.idumo.java.validator.ReceiveValidatorType;

/**
 * 逆ジオコーディング(gps->住所)のハンドラです．
 * 
 * @author Hiroyoshi
 */
@IDUMOCommon
@IDUMOHandler(send = IfStringPrimitiveElement.class, receive = IfLatLngElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "逆ジオコーディング", summary = "LatLngから住所を調べる")
public class ReversedGeocordingHandler implements IfSendable, IfReceivable {
	
	private IfSendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);
	private ReceiveValidatorType	v1Type	= new ReceiveValidatorType(1, LatLngModel.class);
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		IfLatLngElement gd = (IfLatLngElement) sender.onCall().next();
		
		ReversedGeocordingComponent rg = new ReversedGeocordingComponent(gd.getLatitude(), gd.getLongitude());
		
		FlowingData p = new FlowingData();
		p.add(new IfStringPrimitiveElement.StringPrimitiveModel(rg.getLocation()));
		
		return p;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(IfLatLngElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfStringPrimitiveElement.class);
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		v1Type.validate(senders);
		sender = senders[0];
	}
	
}
