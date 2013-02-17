package jp.idumo.java.model.connect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.idumo.java.model.IfDataElement;


public class ArrayConnectDataType implements ConnectDataType {
	
	List<Class<? extends IfDataElement>> data = new ArrayList<Class<? extends IfDataElement>>();
	
	public ArrayConnectDataType(Class<? extends IfDataElement> d) {
		data.add(d);
	}
	
	@Override
	public boolean equals(ConnectDataType connect) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	
	@Override
	public Iterator<Class<? extends IfDataElement>> iterator() {
		return data.iterator();
	}
	
}
