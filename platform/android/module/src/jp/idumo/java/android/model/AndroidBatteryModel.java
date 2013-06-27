package jp.idumo.java.android.model;

import jp.idumo.java.model.IfDataElement.AbstractData;
import jp.idumo.java.model.raw.NumberRawDataType;
import jp.idumo.java.model.raw.StringRawDataType;

public class AndroidBatteryModel extends AbstractData{
	public static final String STATUS      = "status";
	public static final String PLUG        = "plug";
	public static final String LEVEL       = "level";
	public static final String SCALE       = "scale";
	public static final String TEMPERATURE = "temperature";
	
	public AndroidBatteryModel(String status, String plug, int level, int scale, int temperature) {
		add(new StringRawDataType(STATUS, status, "Battery status"));
		add(new StringRawDataType(PLUG, plug, "Battery plugged"));
		add(new NumberRawDataType(LEVEL, level, "Battery level"));
		add(new NumberRawDataType(SCALE, scale, "Battery scale"));
		add(new NumberRawDataType(TEMPERATURE, temperature, "Battery temperature"));
	}
	
	public String getStatus() {
		return (String) getValue(STATUS);
	}
	
	public String getPlug() {
		return (String) getValue(PLUG);
	}
	
	public int getLevel() {
		return (Integer) getValue(LEVEL);
	}
	
	public int getScale() {
		return (Integer) getValue(SCALE);
	}
	
	public int getTemperature() {
		return (Integer) getValue(TEMPERATURE);
	}
}