package jp.idumo.java.console;

import jp.idumo.java.console.core.exec.ConsoleComponent;
import jp.idumo.java.console.core.exec.ConsoleWrapper;
import jp.idumo.java.console.core.util.ConsoleLogger;
import jp.idumo.java.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.handler.raw.NumberGetValueHandler;
import jp.idumo.java.parts.handler.raw.NumberMoreThanHandler;
import jp.idumo.java.parts.provider.LivedoorWeatherProvider;
import jp.idumo.java.util.LogManager;

public class NumberMoreThanTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		NumberMoreThanTest main = new NumberMoreThanTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63);
				add(idumo0);
				NumberGetValueHandler idumo1 = new NumberGetValueHandler("max_temp");
				add(idumo1);
				NumberMoreThanHandler idumo2 = new NumberMoreThanHandler(10);
				add(idumo2);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo1);
				connect(idumo1, idumo2);
				connect(idumo2, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
