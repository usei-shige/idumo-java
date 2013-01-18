package jp.idumo.java.model;

import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.raw.StringRawDataType;

public interface IfTextElement extends IfDataElement {
	public String getText();

	public class TextModel extends AbstractData implements IfTextElement {
		private static final String	TEXT	= "text";

		public TextModel(String text) {
			add(new StringRawDataType(TEXT, text, "text"));
		}

		@Override
		public String getText() {
			return (String) getValue(TEXT);
		}

	}
}
