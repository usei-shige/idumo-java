/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jp.idumo.java.component;

import java.util.ArrayList;
import java.util.List;

import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.BarNaviModel;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.util.URL2XMLParser;

import org.jdom.Element;
import org.jdom.Namespace;


/**
 * webapi.suntory.co.jp/barnavi/v2/shops?key=3f0f69e7cffb6195eee5bafd9f227e0481fa322b71856181796ba4b6bee04061
 * &pattern=1&lat=35.659383166725&lng=139.69823806542&url=http://idumo.comp.is.uec.ac.jp/builder/index.html
 * 
 * @author Yusei SHIGENOBU
 * @version 2.0
 */
public class BarNaviComponent {
	private static final String API_KEY = "3f0f69e7cffb6195eee5bafd9f227e0481fa322b71856181796ba4b6bee04061";
	private static final String URL     = "http://idumo.comp.is.uec.ac.jp/builder/index.html";
	private static final String REQUEST_URL_SEED = "http://webapi.suntory.co.jp/barnavi/v2/shops?key=" + API_KEY + "&pattern=1&lat=%f&lng=%f&url=" + URL;
	
	private String requestURL;
	
	private ArrayList<BarNaviModel> list;
	private boolean isReady;
	
	private URL2XMLParser parser;
	
	public BarNaviComponent() {}
	
	public BarNaviComponent(double lat, double lon) {
		init(lat, lon);
	}
	
	public ArrayList<BarNaviModel> getData() {
		return list;
	}
	
	public void init(double lat, double lon) {
		requestURL = String.format(REQUEST_URL_SEED, lat, lon);
		LogManager.debug(requestURL);
		list = new ArrayList<BarNaviModel>();
		try {
			parser = new URL2XMLParser(requestURL);
			// parser.output();
		}
		catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
		Element root = parser.getRoot();
		List<Element> shops = new ArrayList<Element>();
		List<Element> children = root.getChildren();
		for (Element element : children) {
			String name = element.getName();
			if (name.equals("shop")) {
				shops.add(element);
			}
		}
		Namespace ns = root.getNamespace();
		for (Element element : shops) {
			String name = element.getChildText("name", ns);
			String kana = element.getChildText("name_kana", ns);
			Double glat = Double.parseDouble(element.getChildText("lat_world", ns));
			Double glng = Double.parseDouble(element.getChildText("lng_world", ns));
			String address = element.getChildText("address", ns);
			String type = element.getChildText("type", ns);
			String open = element.getChildText("open", ns);
			String budget = element.getChildText("budget", ns);
			list.add(new BarNaviModel(name, kana, glat, glng, address, type, open, budget));
		}
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public void setLatLon(double lat, double lon) {
		init(lat, lon);
	}
	
}
