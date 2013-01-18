package jp.idumo.java.console;

import jp.idumo.java.console.core.exec.ConsoleComponent;
import jp.idumo.java.console.core.exec.ConsoleWrapper;
import jp.idumo.java.console.core.util.ConsoleLogger;
import jp.idumo.java.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.adapter.Number2GPSAdapter;
import jp.idumo.java.parts.provider.NumberProvider;
import jp.idumo.java.util.LogManager;

public class Number2GPSTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		Number2GPSTest main = new Number2GPSTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				NumberProvider idumo0 = new NumberProvider(40.0);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(132.0);
				add(idumo1);
				Number2GPSAdapter idumo2 = new Number2GPSAdapter();
				add(idumo2);
				
				ConsoleViewReceiptor idumor = new ConsoleViewReceiptor();
				add(idumor);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumor);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
