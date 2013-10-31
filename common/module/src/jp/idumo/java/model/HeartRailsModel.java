package jp.idumo.java.model;

import jp.idumo.java.model.IfDataElement.AbstractData;
import jp.idumo.java.model.element.IfLatLngElement;
import jp.idumo.java.model.raw.NumberRawDataType;
import jp.idumo.java.model.raw.StringRawDataType;

public class HeartRailsModel extends AbstractData implements IfLatLngElement{
	public static final String NAME = "name";
	public static final String LINE = "line";
	public static final String NEXT = "next";
	public static final String PREV = "prev";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	
	public HeartRailsModel(String name, String line, String next, String prev, double glat, double glng) {
		add(new StringRawDataType(NAME, name, "Station Name"));
		add(new StringRawDataType(LINE, line, "Train Line Name"));
		add(new StringRawDataType(NEXT, next, "Next Station"));
		add(new StringRawDataType(PREV, prev, "Prev Station"));
		add(new NumberRawDataType(LATITUDE, glat, "Station Latitude"));
		add(new NumberRawDataType(LONGITUDE, glng, "Station Longitude"));
	}

	public String getName() {
		return (String) getValue(NAME);
	}
	public String getLine() {
		return (String) getValue(LINE);
	}
	public String getNext() {
		return (String) getValue(NEXT);
	}
	public String getPrev() {
		return (String) getValue(PREV);
	}

	@Override
	public double getLatitude() {
		return (Double) getValue(LATITUDE);
	}
	@Override
	public double getLongitude() {
		return (Double) getValue(LONGITUDE);
	}
	
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append("最寄り駅：");
		sb.append(getName());
		sb.append(",");
		sb.append(getLine());
		return sb.toString();
	}
}
