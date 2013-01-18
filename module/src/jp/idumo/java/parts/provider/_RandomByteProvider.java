package jp.idumo.java.parts.provider;

import java.util.Random;

import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfNumberPrimitiveElement;
import jp.idumo.java.parts.IfSendable;


/**
 * ランダムなバイト情報を送るためのProvider(DebugClass)
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class _RandomByteProvider implements IfSendable {

	private Random ramdom;

	public _RandomByteProvider() {
		ramdom = new Random();
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public FlowingData onCall() {
		// LogUtil.d();
		FlowingData pipes = new FlowingData();
		byte buf[] = new byte[1];
		ramdom.nextBytes(buf);
		pipes.add(new IfNumberPrimitiveElement.NumberPrimitiveModel(buf[0]));
		return pipes;
	}

	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfNumberPrimitiveElement.class);
	}

}
