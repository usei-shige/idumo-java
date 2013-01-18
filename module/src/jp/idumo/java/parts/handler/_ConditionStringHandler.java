package jp.idumo.java.parts.handler;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfBoolPrimitiveElement;
import jp.idumo.java.model.primitive.IfStringPrimitiveElement;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.ReceiveValidatorSize;
import jp.idumo.java.validator.ReceiveValidatorType;

public class _ConditionStringHandler implements IfSendable, IfReceivable {

	private IfSendable sender;
	private String condition;
	private ReceiveValidatorSize validator = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1, IfStringPrimitiveElement.class);

	public _ConditionStringHandler(String condition) {
		this.condition = condition;
	}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public FlowingData onCall() {
		IfStringPrimitiveElement data = (IfStringPrimitiveElement) sender.onCall().next();
		String str = data.getString();
		if (condition.equals(str)) {
			return new FlowingData(new IfBoolPrimitiveElement.BoolPrimitiveModel(true));
		}
		return new FlowingData(new IfBoolPrimitiveElement.BoolPrimitiveModel(false));
	}

	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(IfStringPrimitiveElement.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfBoolPrimitiveElement.class);
	}

	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}
}
