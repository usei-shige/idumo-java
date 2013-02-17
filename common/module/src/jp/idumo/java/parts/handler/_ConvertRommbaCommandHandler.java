package jp.idumo.java.parts.handler;

import jp.idumo.java.component._ConvertRoombaCommand;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfNumberPrimitiveElement;
import jp.idumo.java.model.primitive.IfStringPrimitiveElement;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.ReceiveValidatorSize;
import jp.idumo.java.validator.ReceiveValidatorType;

public class _ConvertRommbaCommandHandler implements IfSendable, IfReceivable {

	private IfSendable sender;
	private ReceiveValidatorSize vSize = new ReceiveValidatorSize(1);
	private ReceiveValidatorType vType = new ReceiveValidatorType(1, IfStringPrimitiveElement.class);

	public _ConvertRommbaCommandHandler() {}

	@Override
	public boolean isReady() {
		return sender.isReady();
	}

	@Override
	public FlowingData onCall() {
		IfStringPrimitiveElement data = (IfStringPrimitiveElement) sender.onCall().next();
		String command = data.getString();
		FlowingData p = new FlowingData();
		if (_ConvertRoombaCommand.containsKey(command)) {
			p.add(new IfNumberPrimitiveElement.NumberPrimitiveModel(_ConvertRoombaCommand.getCommand(command)));
		}
		return p;
	}

	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(IfStringPrimitiveElement.class);
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfNumberPrimitiveElement.class);
	}

	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}

}
