package jp.idumo.java.model.primitive;

import jp.idumo.java.model.IfDataElement.AbstractData;

public abstract class PrimitiveData extends AbstractData {
	protected static final String NAME = "value";
	
	public Object getValue() {
		return getValue(NAME);
	}
	
}
