package jp.idumo.java.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.Zip2AddressModel;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.util.URL2XMLParser;

/**
 * 
 * @author Yusei SHIGENOBU
 * 
 */

public class Zip2AddressComponent  {

	private static final String REQUEST_URL = "http://geoapi.heartrails.com/api/xml?method=searchByPostal&postal=%s";
	
	private ArrayList<Zip2AddressModel> list;
	private String request_Url;
	private boolean isReady;
	private URL2XMLParser parser;
	
	public Zip2AddressComponent(String num) throws IOException, JDOMException {
		isReady = false;
		request_Url = String.format(REQUEST_URL, num);
		init();
	}

	public ArrayList<Zip2AddressModel> getData() {
		return list;
	}

	public boolean isReady() throws IOException, JDOMException {
		if (isReady) {
			return true;
		}
		init();
		return isReady;
	}

	public void init() throws IOException, JDOMException {
		LogManager.debug(request_Url);
		list = new ArrayList<Zip2AddressModel>();
		try {
			parser = new URL2XMLParser(request_Url);
			parser.output();
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
		Element root = parser.getRoot();
		// parser.output();
		List<Element> location = new ArrayList<Element>();
		List<Element> children = root.getChildren();
		for (Element element : children) {
			String name = element.getName();
			if(name.equals("location")) {
				location.add(element);
			}
		}
		Namespace ns = root.getNamespace();
		for(Element element : location) {
			String city = element.getChildText("city", ns);
			String city_kana = element.getChildText("city-kana", ns);
			String town = element.getChildText("town", ns);
			String town_kana = element.getChildText("town-kana", ns);
			Double glat = Double.parseDouble(element.getChildText("y", ns));
			Double glng = Double.parseDouble(element.getChildText("x", ns));
			String prefecture = element.getChildText("prefecture", ns);
			String postal = element.getChildText("postal", ns);
			
			list.add(new Zip2AddressModel(postal, prefecture, city, city_kana, town, town_kana, glat, glng));
		}
		isReady = true;
	}
}

