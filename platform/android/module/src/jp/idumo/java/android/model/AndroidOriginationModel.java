package jp.idumo.java.android.model;

import jp.idumo.java.model.IfDataElement.AbstractData;
import jp.idumo.java.model.raw.StringRawDataType;

public class AndroidOriginationModel extends AbstractData {
	private static final String INPUT = "input";
	
	public AndroidOriginationModel(String inputstr) {
		add(new StringRawDataType(INPUT, inputstr, "input string"));
	}
	
	public String getInput() {
		return (String) getValue(INPUT);
	}
}
