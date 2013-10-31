package jp.idumo.java.component;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.Namespace;

import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.HeartRailsModel;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.util.URL2XMLParser;


/**
 * 
 * @author Yusei SHIGENOBU
 * 
 * GPSより受け取った緯度経度データから最寄りの駅と路線を検索するAPI
 * HeartRails Express
 * http://express.heartrails.com/api.html
 * 
 * RequestURL例：電通大の緯度経度より最寄り駅を表示
 * http://express.heartrails.com/api/xml?method=getStations&y=35.656092&x=139.544074
 *
 */

public class HeartRailsComponent  {
	private static final String REQUEST_URL_SEED = "http://express.heartrails.com/api/xml?method=getStations&y=%f&x=%f";

	private String requestURL;
	private ArrayList<HeartRailsModel> list;
	private boolean isReady;

	private URL2XMLParser parser;
	
	public HeartRailsComponent(double lat, double lng) {
		requestURL = String.format(REQUEST_URL_SEED, lat, lng);
	}
	
	public HeartRailsComponent() {
	}

	public ArrayList<HeartRailsModel> getData() {
		return list;
	}

	public void init(double lat, double lng) {
		requestURL = String.format(REQUEST_URL_SEED, lat, lng);
		LogManager.debug(requestURL);
		list = new ArrayList<HeartRailsModel>();
		try {
			parser = new URL2XMLParser(requestURL);
			parser.output();
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
		Element root = parser.getRoot();
		List<Element> stations = new ArrayList<Element>();
		List<Element> children = root.getChildren();
		for (Element element : children) {
			String name = element.getName();
			if(name.equals("station")) {
				stations.add(element);
			}
		}
		Namespace ns = root.getNamespace();
		for(Element element : stations) {
			String name = element.getChildText("name", ns);
			String line = element.getChildText("line", ns);
			String next = element.getChildText("next", ns);
			String prev = element.getChildText("prev", ns);
			Double glat = Double.parseDouble(element.getChildText("y", ns));
			Double glng = Double.parseDouble(element.getChildText("x", ns));
			list.add(new HeartRailsModel(name, line, next, prev, glat, glng));
		}
	}

	public boolean isReady() {
		return isReady;
	}

	public void setLatLon(double lat, double lon) {
		init(lat, lon);
	}
}

