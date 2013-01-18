package jp.idumo.java.console;

import jp.idumo.java.console.core.exec.ConsoleComponent;
import jp.idumo.java.console.core.exec.ConsoleWrapper;
import jp.idumo.java.console.core.util.ConsoleLogger;
import jp.idumo.java.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.provider.NumberProvider;
import jp.idumo.java.util.LogManager;

public class NumberProviderTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		NumberProviderTest main = new NumberProviderTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				NumberProvider idumo0 = new NumberProvider(10.0);
				add(idumo0);
				ConsoleViewReceiptor idumo1 = new ConsoleViewReceiptor();
				add(idumo1);
				
				connect(idumo0, idumo1);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
