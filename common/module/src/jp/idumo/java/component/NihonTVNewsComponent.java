package jp.idumo.java.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.NihonTVNewsModel;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.util.URL2XMLParser;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

/**
 * 
 * @author Yusei SHIGENOBU
 * 
 * 日テレ系のニュースをフリーワードで検索するモジュール
 * http://appli.ntv.co.jp/appli/api/news/index.html
 * 
 * APIKey：4Uobrbuq5hN04QDdhswwWgeLks6dhwuX8vACa72wDIumf263IHZ6pD1YPLZ7
 * RequestURL例：http://appli.ntv.co.jp/ntv_WebAPI/news/?key=4Uobrbuq5hN04QDdhswwWgeLks6dhwuX8vACa72wDIumf263IHZ6pD1YPLZ7&word=問題&period_start=20080801&period_end=20081001
 * http://appli.ntv.co.jp/ntv_WebAPI/news/?key=4Uobrbuq5hN04QDdhswwWgeLks6dhwuX8vACa72wDIumf263IHZ6pD1YPLZ7&word=Japan
 */

public class NihonTVNewsComponent  {
	private static final String API_KEY = "4Uobrbuq5hN04QDdhswwWgeLks6dhwuX8vACa72wDIumf263IHZ6pD1YPLZ7";
	private static final String REQUEST_URL_SEED = "http://appli.ntv.co.jp/ntv_WebAPI/news/?key=" + API_KEY + "&word=%s";
	
	private String requestURL;
	private ArrayList<NihonTVNewsModel> list;
	private boolean isReady;
	
	private URL2XMLParser parser;
	
	public NihonTVNewsComponent(String word) {
		init(word);
	}
	
	private void init(String word) {
		requestURL = String.format(REQUEST_URL_SEED, word);
		list = new ArrayList<NihonTVNewsModel>();
		try {
			parser = new URL2XMLParser(requestURL);
//			parser.output();
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
		LogManager.debug(requestURL);
		Element root = parser.getRoot();
		List<Element> news = new ArrayList<Element>();
		List<Element> children = root.getChildren();
		for (Element element : children) {
			String name = element.getName();
			if(name.equals("article")) {
				news.add(element);
			}
		}
		Namespace ns = root.getNamespace();
		for(Element element : news) {
			String title   = element.getChildText("title", ns);
			String url     = element.getChildText("url", ns);
			String date    = element.getChildText("date", ns);
			String summary = element.getChildText("summary", ns);
			list.add(new NihonTVNewsModel(title, url, date, summary));
		}
		isReady = true;
	}

	public boolean isReady() throws IOException, JDOMException {
		if (isReady) {
			return true;
		}
		return isReady;
	}

	public List<NihonTVNewsModel> getData() {
		return list;
	}
}

