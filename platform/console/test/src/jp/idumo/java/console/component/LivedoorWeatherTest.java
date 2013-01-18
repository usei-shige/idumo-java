package jp.idumo.java.console.component;

import java.io.IOException;

import jp.idumo.java.component.LivedoorWeatherComponent;

import org.jdom.JDOMException;

public class LivedoorWeatherTest {
	public static void main(String args[]) throws IOException, JDOMException {
		LivedoorWeatherComponent weather = new LivedoorWeatherComponent(63);
		System.out.println(weather.getData());
		
	}
	
}
