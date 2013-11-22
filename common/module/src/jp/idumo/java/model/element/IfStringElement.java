package jp.idumo.java.model.element;

import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.raw.StringRawDataType;

public interface IfStringElement extends IfDataElement {
	public String getString1();
	
	public String getString2();
	
	public class StringConvertModel extends AbstractData implements IfStringElement {
		
		private static final String STRING1 = "string1";
		private static final String STRING2 = "string2";
		
		public StringConvertModel(String str1, String str2) {
			add(new StringRawDataType(STRING1, str1, "latitude"));
			add(new StringRawDataType(STRING2, str2, "longitude"));
		}

		@Override
		public String getString1() {
			return (String) getValue(STRING1);
		}

		@Override
		public String getString2() {
			return (String) getValue(STRING2);
		}
		
	}
}
