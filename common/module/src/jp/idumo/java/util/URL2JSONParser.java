package jp.idumo.java.util;

import java.io.IOException;
import java.net.URL;

import org.jdom.Document;

public class URL2JSONParser {

	private URL url;
	private Document doc;
	
	public URL2JSONParser(String url) throws IOException {
		this.url = new URL(url);
		init();
	}
	
	public URL2JSONParser(URL url) throws IOException {
		this.url = url;
		init();
	}

	private void init() {
		
	}
}
