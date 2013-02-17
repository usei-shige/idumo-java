package jp.idumo.java.exec;

import java.util.Collection;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.IfConnectable;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;


public abstract class AbCoreComponent {
	
	private CoreContainer container = null;
	private CoreSetting setting = new CoreSetting();
	private boolean isReady;
	
	/**
	 * @param item
	 * @see jp.idumo.java.exec.CoreContainer#add(jp.idumo.java.parts.IfConnectable)
	 */
	public void add(IfConnectable item) {
		container.add(item);
	}
	
	/**
	 * @param sender
	 * @param receiver
	 * @see jp.idumo.java.exec.CoreContainer#connect(jp.idumo.java.parts.IfSendable,
	 *      jp.idumo.java.parts.IfReceivable)
	 */
	public void connect(IfSendable sender, IfReceivable receiver) {
		container.connect(sender, receiver);
	}
	
	/**
	 * @return
	 * @see jp.idumo.java.exec.CoreContainer#getApplicationControllers()
	 */
	public Collection<IfCoreController> getApplicationControllers() {
		return container.getApplicationControllers();
	}
	
	/**
	 * @return container
	 */
	public CoreContainer getContainer() {
		return container;
	}
	
	/**
	 * @return
	 * @see jp.idumo.java.exec.CoreSetting#getLoopCount()
	 */
	public int getLoopCount() {
		return setting.getLoopCount();
	}
	
	/**
	 * @return
	 * @see jp.idumo.java.exec.CoreContainer#getRunnable()
	 */
	public IfExecutable getRunnable() {
		return container.getRunnable();
	}
	
	/**
	 * @return
	 * @see jp.idumo.java.exec.CoreSetting#getSleepTime()
	 */
	public int getSleepTime() {
		return setting.getSleepTime();
	}
	
	/**
	 * @return isReady
	 */
	public boolean isReady() {
		return isReady;
	}
	
	abstract public void onIdumoMakeFlowChart() throws IDUMOException;
	
	abstract public void onIdumoPrepare();
	
	/**
	 * @param container
	 *            セットする container
	 */
	public void setContainer(CoreContainer container) {
		this.container = container;
	}
	
	/**
	 * @param loopCount
	 * @see jp.idumo.java.exec.CoreSetting#setLoopCount(int)
	 */
	public void setLoopCount(int loopCount) {
		setting.setLoopCount(loopCount);
	}
	
	/**
	 * @param isReady
	 *            セットする isReady
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}
	
	/**
	 * @param sleepTime
	 * @see jp.idumo.java.exec.CoreSetting#setSleepTime(int)
	 */
	public void setSleepTime(int sleepTime) {
		setting.setSleepTime(sleepTime);
	}
	
	/**
	 * @throws IDUMOException
	 * @see jp.idumo.java.exec.CoreContainer#setup()
	 */
	public void setup() throws IDUMOException {
		container.setup();
	}
	
}
