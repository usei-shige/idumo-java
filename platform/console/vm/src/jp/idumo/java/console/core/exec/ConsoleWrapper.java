package jp.idumo.java.console.core.exec;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.exec.IfCoreWrapper;

public abstract class ConsoleWrapper implements IfCoreWrapper {
	
	private ConsoleActivity	execution;
	
	public void exec() {
		init();
		try {
			execution.onIdumoCreated();
		} catch (IDUMOException e) {
			e.printStackTrace();
		}
		execution.onIdumoStart();
		execution.onIdumoExec();
		execution.onIdumoStop();
	}
	
	public void setExecution(ConsoleActivity execution) {
		this.execution = execution;
	}
	
	public void setExecutionWithComponent(ConsoleComponent component) {
		execution = new ConsoleActivity(component);
	}
}
