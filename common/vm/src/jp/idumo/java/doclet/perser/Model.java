package jp.idumo.java.doclet.perser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.javadoc.ClassDoc;

import jp.idumo.java.doclet.json.IJSONValue;
import jp.idumo.java.doclet.json.StringArrayValue;
import jp.idumo.java.model.IfDataElement;

public class Model implements IAnnotation {

	private static final String TAG            = "contains";
	private static final String I_DATA_ELEMENT = IfDataElement.class.getName();
	
	private Set<String> classList =new HashSet<String>();
	
	public Model(ClassDoc classDoc) {
		super();
		analyze(classDoc);
	}
	
	public void analyze(ClassDoc doc) {
		if(doc == null) return;
		classList.add(doc.toString());
		analyze(doc.superclass());
		ClassDoc[] interfaceDocs = doc.interfaces();
		for (ClassDoc classDoc : interfaceDocs) {
			analyze(classDoc);
		}
	}
	
	public boolean isContainDataElement() {
		return classList.contains(I_DATA_ELEMENT);
	}
	
	@Override
	public Map<String, IJSONValue> getKVMap() {
		Map<String, IJSONValue> items = new HashMap<String, IJSONValue>();
		classList.remove(Object.class.getName());
		items.put(TAG, new StringArrayValue(new ArrayList<String>(classList)));
		return items;
	}
	
}
