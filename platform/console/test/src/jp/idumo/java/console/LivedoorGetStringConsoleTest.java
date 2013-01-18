package jp.idumo.java.console;

import jp.idumo.java.console.core.exec.ConsoleComponent;
import jp.idumo.java.console.core.exec.ConsoleWrapper;
import jp.idumo.java.console.core.util.ConsoleLogger;
import jp.idumo.java.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.handler.raw.StringGetValueHandler;
import jp.idumo.java.parts.provider.LivedoorWeatherProvider;
import jp.idumo.java.util.LogManager;

public class LivedoorGetStringConsoleTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		LivedoorGetStringConsoleTest main = new LivedoorGetStringConsoleTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new LivedoorWeatherConsoleComponent());
	}
}

class LivedoorWeatherConsoleComponent extends ConsoleComponent {
	@Override
	public void onIdumoMakeFlowChart() throws IDUMOException {
		LivedoorWeatherProvider idumo0 = new LivedoorWeatherProvider(63);
		add(idumo0);
		StringGetValueHandler idumo1 = new StringGetValueHandler("min_temp");
		add(idumo1);
		ConsoleViewReceiptor idumo2 = new ConsoleViewReceiptor();
		add(idumo2);
		
		connect(idumo0, idumo1);
		connect(idumo1, idumo2);
		
	}
	
	@Override
	public void onIdumoPrepare() {
		setLoopCount(1);
		setSleepTime(1000);
	}
}
