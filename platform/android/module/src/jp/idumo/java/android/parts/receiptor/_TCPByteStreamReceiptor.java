package jp.idumo.java.android.parts.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jp.idumo.java.android.core.IfAndroidController;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;


/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public class _TCPByteStreamReceiptor implements IfReceivable, IfAndroidController, IfExecutable {
	private String			ip;
	private int				port;
	private Socket			socket;
	private OutputStream	outstream;
	private IfSendable		sender;
	
	public _TCPByteStreamReceiptor(String ip, int port) {
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady() && socket.isConnected();
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		try {
			socket.close();
			outstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		try {
			socket.connect(new InetSocketAddress(ip, port));
			outstream = socket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public ConnectDataType receivableType() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public void run() {
		LogManager.log();
		FlowingData data = sender.onCall();
		byte[] bytedata = new byte[data.size()];
		int i = 0;
		LogManager.debug("size: " + data.size());
		for (Object o : data) {
			LogManager.debug(o.toString());
			bytedata[i] = (Byte) o;
			i++;
		}
		
		try {
			outstream.write(bytedata);
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
