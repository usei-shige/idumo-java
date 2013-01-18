package jp.idumo.java.console.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfStringPrimitiveElement;
import jp.idumo.java.parts.IfSendable;

public class _ReceiveConsoleProvider implements IfSendable {
	
	private BufferedReader	br;
	
	public _ReceiveConsoleProvider() {
		br = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public FlowingData onCall() {
		FlowingData p = new FlowingData();
		try {
			p.add(new IfStringPrimitiveElement.StringPrimitiveModel(br.readLine()));
		} catch (IOException e) {
			throw new IDUMORuntimeException(e);
		}
		return p;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfStringPrimitiveElement.class);
	}
	
}
