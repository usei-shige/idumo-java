package jp.idumo.java.console.core.exec;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.exec.IfCoreActivity;
import jp.idumo.java.exec.IfCoreController;
import jp.idumo.java.parts.IfExecutable;

public class ConsoleActivity implements IfCoreActivity {
	private ConsoleComponent	component;
	
	public ConsoleActivity(ConsoleComponent component) {
		this.component = component;
		this.component.setContainer(new ConsoleContainer());
	}
	
	@Override
	public void onIdumoCreated() throws IDUMOException {
		component.onIdumoMakeFlowChart();
		component.setup();
		component.onIdumoPrepare();
	}
	
	@Override
	public void onIdumoExec() throws IDUMORuntimeException {
		while (!component.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		IfExecutable runnable = component.getRunnable();
		while (!runnable.isReady()) {
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		int count = component.getLoopCount();
		if (count == -1) {
			while (true) {
				if (runnable.isReady()) {
					runnable.run();
				}
				try {
					Thread.sleep(component.getSleepTime());
				} catch (InterruptedException e) {}
			}
		}
		for (int i = 0; i < count;) {
			if (runnable.isReady()) {
				runnable.run();
				i++;
			}
			try {
				Thread.sleep(component.getSleepTime());
			} catch (InterruptedException e) {}
		}
		
	}
	
	@Override
	public void onIdumoStart() {
		component.onIdumoPrepare();
		for (IfCoreController controller : component.getApplicationControllers()) {
			controller.onIdumoStart();
		}
		component.setReady(true);
	}
	
	@Override
	public void onIdumoStop() {
		for (IfCoreController controller : component.getApplicationControllers()) {
			controller.onIdumoStop();
		}
		component.setReady(false);
	}
}
