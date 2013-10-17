package jp.idumo.java.model;

import jp.idumo.java.model.IfDataElement.AbstractData;
import jp.idumo.java.model.raw.StringRawDataType;

public class NihonTVNewsModel extends AbstractData{
	public static final String TITLE   = "title";
	public static final String URL     = "url";
	public static final String DATE    = "date";
	public static final String SUMMARY = "summary";
	
	public NihonTVNewsModel(String title, String url, String date, String summary) {
		add(new StringRawDataType(TITLE, title, "Article Title"));
		add(new StringRawDataType(URL, url, "Article URL"));
		add(new StringRawDataType(DATE, date, "Article Date"));
		add(new StringRawDataType(SUMMARY, summary, "Article Summary"));
	}

	public String getTitle() {
		return (String) getValue(TITLE);
	}
	public String getUrl() {
		return (String) getValue(URL);
	}
	public String getDate() {
		return (String) getValue(DATE);
	}
	public String getSummary() {
		return (String) getValue(SUMMARY);
	}
	
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTitle());
		sb.append(":");
		sb.append(getDate());
		sb.append(",");
		sb.append(getUrl());
		sb.append(",");
		sb.append(getSummary());
		
		return sb.toString();
	}
	
}
