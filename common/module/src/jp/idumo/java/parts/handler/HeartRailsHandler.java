package jp.idumo.java.parts.handler;

import java.util.List;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOHandler;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.component.HeartRailsComponent;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.HeartRailsModel;
import jp.idumo.java.model.connect.ArrayConnectDataType;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.element.IfLatLngElement;
import jp.idumo.java.model.element.IfLatLngElement.LatLngModel;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.validator.ReceiveValidatorSize;

@IDUMOCommon
@IDUMOHandler(receive = IfLatLngElement.class, send = HeartRailsModel.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "最寄り駅を検索", summary = "緯度経度から最寄り駅を検索する")
public class HeartRailsHandler implements IfSendable, IfReceivable {
	
	private HeartRailsComponent		train	= new HeartRailsComponent();
	private IfSendable				sender;
	private ReceiveValidatorSize	vSize		= new ReceiveValidatorSize(1);
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public FlowingData onCall() {
		LogManager.log();
		IfLatLngElement gd = (IfLatLngElement) sender.onCall().next();
		train.setLatLon(gd.getLatitude(), gd.getLongitude());
		FlowingData p = new FlowingData();
		List<HeartRailsModel> data = train.getData();
		for (HeartRailsModel d : data) {
			p.add(d);
		}
		return p;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(LatLngModel.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new ArrayConnectDataType(HeartRailsModel.class);
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}
}
