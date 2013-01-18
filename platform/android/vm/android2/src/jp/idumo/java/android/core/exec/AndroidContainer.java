package jp.idumo.java.android.core.exec;

import java.util.ArrayList;
import java.util.Collection;

import jp.idumo.java.android.core.IfAndroidController;
import jp.idumo.java.exec.CoreContainer;
import jp.idumo.java.parts.IfConnectable;


public class AndroidContainer extends CoreContainer {
	private ArrayList<IfAndroidController>	androidControllers	= new ArrayList<IfAndroidController>();
	
	@Override
	public void add(IfConnectable item) {
		super.add(item);
		if (item instanceof IfAndroidController) {
			androidControllers.add((IfAndroidController) item);
		}
	}
	
	public Collection<IfAndroidController> getAndroidControllers() {
		return androidControllers;
	}
	
}
