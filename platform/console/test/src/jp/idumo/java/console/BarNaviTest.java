package jp.idumo.java.console;

import jp.idumo.java.console.core.exec.ConsoleComponent;
import jp.idumo.java.console.core.exec.ConsoleWrapper;
import jp.idumo.java.console.core.util.ConsoleLogger;
import jp.idumo.java.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.adapter.Number2GPSAdapter;
import jp.idumo.java.parts.handler.BarNaviHandler;
import jp.idumo.java.parts.provider.NumberProvider;
import jp.idumo.java.util.LogManager;

public class BarNaviTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		BarNaviTest main = new BarNaviTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				
				NumberProvider idumo0 = new NumberProvider(35.659383166725);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(139.69823806542);
				add(idumo1);
				Number2GPSAdapter idumo2 = new Number2GPSAdapter();
				add(idumo2);
				BarNaviHandler idumo3 = new BarNaviHandler();
				add(idumo3);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumo3);
				connect(idumo3, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
