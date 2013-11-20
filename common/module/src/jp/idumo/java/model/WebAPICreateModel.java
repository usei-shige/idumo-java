package jp.idumo.java.model;

import jp.idumo.java.model.IfDataElement.AbstractData;
import jp.idumo.java.model.raw.StringRawDataType;

public class WebAPICreateModel extends AbstractData{
	public static final String TITLE   = "title";
	public static final String WEEK    = "week";
	public static final String TIME    = "time";
	public static final String STATION = "station";
	public static final String NEXT    = "next";
	
	public WebAPICreateModel(String title, String week, String time, String station, String next) {
		add(new StringRawDataType(TITLE, title, "Anime Title"));
		add(new StringRawDataType(WEEK, week, "On Air Week"));
		add(new StringRawDataType(TIME, time, "On Air Time"));
		add(new StringRawDataType(STATION, station, "On Air Station"));
		add(new StringRawDataType(NEXT, next, "On Air Episode"));
	}
	
	public String getTitle() {
		return (String) getValue(TITLE);
	}
	
	public String getWeek() {
		return (String) getValue(WEEK);
	}
	
	public String getTime() {
		return (String) getValue(TIME);
	}
	
	public String getStation() {
		return (String) getValue(STATION);
	}
	
	public String getNext() {
		return (String) getValue(NEXT);
	}
	
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTitle());
		sb.append(":");
		sb.append(getWeek());
		sb.append(",");
		sb.append(getTime());
		sb.append(",");
		sb.append(getStation());
		sb.append(",");
		sb.append(getNext());
		
		return sb.toString();
	}
	
}
