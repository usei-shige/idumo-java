package jp.idumo.java.console.receiptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.exec.IfCoreController;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;

public class _SerialSendReceiptor implements IfExecutable, IfReceivable, IfCoreController {
	
	private OutputStream	out;
	private IfSendable		sender;
	private String			serial;
	
	public _SerialSendReceiptor(String serial) {
		this.serial = serial;
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public void onIdumoStart() {
		try {
			out = new FileOutputStream(serial);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoStop() {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ConnectDataType receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public void run() {
		LogManager.log();
		if (!sender.isReady()) {
			return;
		}
		FlowingData data = sender.onCall();
		if (data == null) {
			return;
		}
		byte[] bytedata = new byte[data.size()];
		int i = 0;
		LogManager.debug("size: " + data.size());
		for (Object o : data) {
			LogManager.debug(o.toString());
			bytedata[i] = (Byte) o;
			i++;
		}
		
		try {
			out.write(bytedata);
			out.flush();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		// if (senders.length != 1) {
		// return false;
		// }
		// for (Object o : senders[0].getDataType()) {
		// if (o != Byte.class) {
		// return false;
		// }
		// }
		sender = senders[0];
	}
	
}
