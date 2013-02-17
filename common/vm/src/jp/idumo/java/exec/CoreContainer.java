package jp.idumo.java.exec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.IfConnectable;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;


public class CoreContainer {
	
	private ArrayList<IfConnectable> items = new ArrayList<IfConnectable>();
	
	private ArrayList<IfExecutable> runnables = new ArrayList<IfExecutable>();
	private ArrayList<IfCoreController> controllers = new ArrayList<IfCoreController>();
	
	private HashMap<IfReceivable, Connect> connector = new HashMap<IfReceivable, Connect>();
	
	public void add(IfConnectable item) {
		items.add(item);
		if (item instanceof IfCoreController) {
			controllers.add((IfCoreController) item);
		}
		if (item instanceof IfExecutable) {
			runnables.add((IfExecutable) item);
		}
	}
	
	public void connect(IfSendable sender, IfReceivable receiver) {
		if (!connector.containsKey(receiver)) {
			Connect connect = new Connect();
			connect.add(sender);
			connector.put(receiver, connect);
		} else {
			Connect connect = connector.get(receiver);
			connect.add(sender);
		}
	}
	
	public Collection<IfCoreController> getApplicationControllers() {
		return controllers;
	}
	
	public IfExecutable getRunnable() {
		return runnables.get(0);
	}
	
	public Collection<IfExecutable> getRunnables() {
		return runnables;
	}
	
	public void setup() throws IDUMOException {
		for (Map.Entry<IfReceivable, Connect> entry : connector.entrySet()) {
			IfReceivable receiver = entry.getKey();
			Connect connect = entry.getValue();
			receiver.setSender(connect.getSenders());
		}
		
	}
	
	public class Connect {
		private ArrayList<IfSendable> senders = new ArrayList<IfSendable>();
		
		public void add(IfSendable sender) {
			senders.add(sender);
		}
		
		public IfSendable[] getSenders() {
			return senders.toArray(new IfSendable[0]);
		}
	}
	
}
