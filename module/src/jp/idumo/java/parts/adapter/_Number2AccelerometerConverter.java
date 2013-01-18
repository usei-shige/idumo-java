package jp.idumo.java.parts.adapter;

import java.util.ArrayList;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.primitive.IfNumberPrimitiveElement;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.IfReceiveValidator;
import jp.idumo.java.validator.ReceiveValidatorSize;
import jp.idumo.java.validator.ReceiveValidatorType;


//@IDUMOAdaptor(author="Hiroyoshi HOUCHI",name="Number->Accelerometer",receive={NumberPrimitiveElement.class,NumberPrimitiveElement.class},send=A)
public class _Number2AccelerometerConverter implements IfSendable, IfReceivable {
	
	private ArrayList<IfSendable> sender = new ArrayList<IfSendable>();
	private IfReceiveValidator vSize = new ReceiveValidatorSize(3);
	private IfReceiveValidator vType1 = new ReceiveValidatorType(1, IfNumberPrimitiveElement.class);
	private IfReceiveValidator vType2 = new ReceiveValidatorType(2, IfNumberPrimitiveElement.class);
	private IfReceiveValidator vType3 = new ReceiveValidatorType(3, IfNumberPrimitiveElement.class);
	
	public _Number2AccelerometerConverter() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		for (IfSendable s : sender) {
			flag = flag && (s != null) && s.isReady();
		}
		return flag;
	}
	
	@Override
	public FlowingData onCall() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return null;
	}
	
	@Override
	public ConnectDataType sendableType() {
		// return new
		// IDUMODataTypeConnectSingle(AndroidAccelerometerData.class);
		return null;
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		vType3.validate(senders);
		for (IfSendable s : senders) {
			sender.add(s);
		}
	}
	
}
