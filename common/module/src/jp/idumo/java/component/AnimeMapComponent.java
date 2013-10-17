package jp.idumo.java.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.AnimeMapModel;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.util.URL2XMLParser;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

/**
 * @author Yusei SHIGENOBU
 * AnimeMap
 * 
 */


public class AnimeMapComponent  {
		
		private static final String REQUEST_URL_SEED = "http://animemap.net/api/table/%s";
		
		private String requestURL;
		
		private ArrayList<AnimeMapModel> list;
		
		private boolean isReady;
		private URL2XMLParser parser;
		
		public AnimeMapComponent(String ken) throws IOException, JDOMException {
			String ken_name = KenNameDecide(ken);
			requestURL = String.format(REQUEST_URL_SEED, ken_name);
			init();
		}
		
		public ArrayList<AnimeMapModel> getData() {
			return list;
		}
		
		public void init() throws IOException, JDOMException {
			list = new ArrayList<AnimeMapModel>();
			try {
				parser = new URL2XMLParser(requestURL);
//				parser.output();
			}
			catch (Exception e) {
				throw new IDUMORuntimeException(e);
			}
			LogManager.debug(requestURL);
			Element root = parser.getRoot();
			List<Element> animes = new ArrayList<Element>();
			List<Element> children = root.getChild("response").getChildren();
			for (Element element : children) {
				String name = element.getName();
				if (name.equals("item")) {
					animes.add(element);
				}
			}
			Namespace ns = root.getNamespace();
			for (Element element : animes) {
				String title   = element.getChildText("title", ns);
				String time    = element.getChildText("time", ns);
				String station = element.getChildText("station", ns);
				String next    = element.getChildText("next", ns);
//				String today   = element.getChildText("today");
				String week    = element.getChildText("week");
				list.add(new AnimeMapModel(title, week, time, station, next));	
			}
			isReady = true;
		}
		
		public boolean isReady() throws IOException, JDOMException {
			if (isReady) {
				return true;
			}
			init();
			return isReady;
		}
		
		public String KenNameDecide(String ken) {
			String ken_name = "tokyo.xml";
			if(ken == "北海道") {
				ken_name = "hokkaidou.xml";
			} else if(ken == "青森県" || ken == "青森") {
				ken_name = "aomori.xml";
			} else if(ken == "岩手県" || ken == "岩手") {
				ken_name = "iwate.xml";
			} else if(ken == "宮城県" || ken == "宮城") {
				ken_name = "miyagi.xml";
			} else if(ken == "秋田県" || ken == "秋田") {
				ken_name = "akita.xml";
			} else if(ken == "山形県" || ken == "山形") {
				ken_name = "yamagata.xml";
			} else if(ken == "福島県" || ken == "福島") {
				ken_name = "fukushima.xml";
			} else if(ken == "茨城県" || ken == "茨城") {
				ken_name = "ibaraki.xml";
			} else if(ken == "栃木県" || ken == "栃木") {
				ken_name = "tochigi.xml";
			} else if(ken == "群馬県" || ken == "群馬") {
				ken_name = "gunma.xml";
			} else if(ken == "埼玉県" || ken == "埼玉") {
				ken_name = "saitama.xml";
			} else if(ken == "千葉県" || ken == "千葉") {
				ken_name = "chiba.xml";
			} else if(ken == "東京都" || ken == "東京") {
				ken_name = "tokyo.xml";
			} else if(ken == "神奈川県" || ken == "神奈川") {
				ken_name = "kanagawa.xml";
			} else if(ken == "新潟県" || ken == "新潟") {
				ken_name = "niigata.xml";
			} else if(ken == "富山県" || ken == "富山") {
				ken_name = "toyama.xml";
			} else if(ken == "石川県" || ken == "石川") {
				ken_name = "ishikawa.xml";
			} else if(ken == "福井県" || ken == "福井") {
				ken_name = "fukui.xml";
			} else if(ken == "山梨県" || ken == "山梨") {
				ken_name = "yamanashi.xml";
			} else if(ken == "長野県" || ken == "長野") {
				ken_name = "nagano.xml";
			} else if(ken == "岐阜県" || ken == "岐阜") {
				ken_name = "gifu.xml";
			} else if(ken == "静岡県" || ken == "静岡") {
				ken_name = "shizuoka.xml";
			} else if(ken == "愛知県" || ken == "愛知") {
				ken_name = "aichi.xml";
			} else if(ken == "三重県" || ken == "三重") {
				ken_name = "mie.xml";
			} else if(ken == "滋賀県" || ken == "滋賀") {
				ken_name = "shiga.xml";
			} else if(ken == "京都府" || ken == "京都") {
				ken_name = "kyoto.xml";
			} else if(ken == "大阪府" || ken == "大阪") {
				ken_name = "osaka.xml";
			} else if(ken == "兵庫県" || ken == "兵庫") {
				ken_name = "hyogo.xml";
			} else if(ken == "奈良県" || ken == "奈良") {
				ken_name = "nara.xml";
			} else if(ken == "和歌山県" || ken == "和歌山") {
				ken_name = "wakayama.xml";
			} else if(ken == "鳥取県" || ken == "鳥取") {
				ken_name = "tottori.xml";
			} else if(ken == "島根県" || ken == "島根") {
				ken_name = "shimane.xml";
			} else if(ken == "岡山県" || ken == "岡山") {
				ken_name = "okayama.xml";
			} else if(ken == "広島県" || ken == "広島") {
				ken_name = "hiroshima.xml";
			} else if(ken == "山口県" || ken == "山口") {
				ken_name = "yamaguchi.xml";
			} else if(ken == "徳島県" || ken == "徳島") {
				ken_name = "tokushima.xml";
			} else if(ken == "香川県" || ken == "香川") {
				ken_name = "kagawa.xml";
			} else if(ken == "愛媛県" || ken == "愛媛") {
				ken_name = "ehime.xml";
			} else if(ken == "高知県" || ken == "高知") {
				ken_name = "kochi.xml";
			} else if(ken == "福岡県" || ken == "福岡") {
				ken_name = "fukuoka.xml";
			} else if(ken == "佐賀県" || ken == "佐賀") {
				ken_name = "saga.xml";
			} else if(ken == "長崎県" || ken == "長崎") {
				ken_name = "nagasaki.xml";
			} else if(ken == "熊本県" || ken == "熊本") {
				ken_name = "kumamoto.xml";
			} else if(ken == "大分県" || ken == "大分") {
				ken_name = "oita.xml";
			} else if(ken == "宮崎県" || ken == "宮崎") {
				ken_name = "miyazaki.xml";
			} else if(ken == "鹿児島県" || ken == "鹿児島") {
				ken_name = "kagoshima.xml";
			} else if(ken == "沖縄県" || ken == "沖縄") {
				ken_name = "okinawa.xml";
			}
			
			return ken_name;
		}
}

