package jp.idumo.java.parts.receiptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOConstructor;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.exec.IfCoreController;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.element.IfTextElement;
import jp.idumo.java.model.primitive.IfStringPrimitiveElement;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.validator.IfReceiveValidator;
import jp.idumo.java.validator.ReceiveValidatorSize;
import jp.idumo.java.validator.ReceiveValidatorType;

/**
 * バイト情報をTCP通信を用いて送ることが出来るReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOReceiptor(receive = IfTextElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "指定IPに文字列を送信", summary = "")
public class SendTCPReceiptor implements IfReceivable, IfCoreController, IfExecutable {
	private String				ip;
	private int					port;
	private Socket				socket;
	private PrintWriter			pw;
	private OutputStream		outstream;
	private IfSendable			sender;
	private IfReceiveValidator	vSize	= new ReceiveValidatorSize(1);
	private IfReceiveValidator	vType	= new ReceiveValidatorType(1, IfStringPrimitiveElement.class);
	
	@IDUMOConstructor({ "IPアドレス", "port番号" })
	public SendTCPReceiptor(String ip, int port) {
		LogManager.log();
		this.ip = ip;
		this.port = port;
		socket = new Socket();
	}
	
	@Override
	public boolean isReady() {
		LogManager.log();
		return sender.isReady() && socket.isConnected();
	}
	
	@Override
	public void onIdumoStart() {
		LogManager.log();
		try {
			socket.connect(new InetSocketAddress(ip, port));
			outstream = socket.getOutputStream();
			pw = new PrintWriter(new OutputStreamWriter(outstream));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onIdumoStop() {
		try {
			socket.close();
			outstream.close();
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
		for (Object o : data) {
			LogManager.debug(o.toString());
			pw.println(o.toString());
			pw.flush();
		}
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}
	
}
