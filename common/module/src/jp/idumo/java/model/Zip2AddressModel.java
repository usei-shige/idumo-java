package jp.idumo.java.model;

import jp.idumo.java.model.IfDataElement.AbstractData;
import jp.idumo.java.model.element.IfLatLngElement;
import jp.idumo.java.model.raw.NumberRawDataType;
import jp.idumo.java.model.raw.StringRawDataType;

/**
 * 
 * @author Yusei SHIGENOBU
 *
 */
public class Zip2AddressModel extends AbstractData implements IfLatLngElement{

	public static final String POSTAL = "postal";
	public static final String PREFECTURE = "prefecture";
	public static final String CITY = "city";
	public static final String CITY_KANA = "city_kana";
	public static final String TOWN = "town";
	public static final String TOWN_KANA = "town_kana";
	public static final String GLAT = "glat";
	public static final String GLNG = "glng";

	public Zip2AddressModel(String postal, String prefecture, String city, String city_kana,
			String town, String town_kana, double glat, double glng) {
		add(new StringRawDataType(POSTAL, postal, "postal number"));
		add(new StringRawDataType(PREFECTURE, prefecture, "prefecture"));
		add(new StringRawDataType(CITY, city, "city"));
		add(new StringRawDataType(CITY_KANA, city_kana, "city_kana"));
		add(new StringRawDataType(TOWN, town, "town"));
		add(new StringRawDataType(TOWN_KANA, town_kana, "town_kana"));
		add(new NumberRawDataType(GLAT, glat, "latitude"));
		add(new NumberRawDataType(GLNG, glng, "longitude"));
	}
	
	public String getPostal() {
		return (String) getValue(POSTAL);
	}
	
	public String getPrefecture() {
		return (String) getValue(PREFECTURE);
	}
	
	public String getCity() {
		return (String) getValue(CITY);
	}
	
	public String getCity_kana() {
		return (String) getValue(CITY_KANA);
	}
	
	public String getTown() {
		return (String) getValue(TOWN);
	}
	
	public String getTown_kana() {
		return (String) getValue(TOWN_KANA);
	}

	@Override
	public double getLatitude() {
		return (Double) getValue(GLAT);
	}

	@Override
	public double getLongitude() {
		return (Double) getValue(GLNG);
	}
	
	public String getText() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPostal());
		sb.append(":");
		sb.append(getPrefecture());
		sb.append(getCity());
		sb.append(getTown());
		sb.append("/");
		sb.append(getLatitude());
		sb.append(",");
		sb.append(getLongitude());
		
		return sb.toString();
	}
}
