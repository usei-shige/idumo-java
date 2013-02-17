package jp.idumo.java.android.core.exec;

import java.util.Collection;

import jp.idumo.java.android.core.IfAndroidActivityController;
import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.core.IfAndroidController;
import jp.idumo.java.exec.AbCoreComponent;
import jp.idumo.java.parts.IfConnectable;

public abstract class AndroidComponent extends AbCoreComponent {
	
	private AndroidActivityResource	activity;
	
	public Collection<IfAndroidController> getAndroidControllers() {
		AndroidContainer container = (AndroidContainer) getContainer();
		return container.getAndroidControllers();
	}
	
	@Override
	public void add(IfConnectable item) {
		super.add(item);
		if (item instanceof IfAndroidActivityController) {
			((IfAndroidActivityController) item).registActivity(activity);
		}
	}
	
	public void setActivity(AndroidActivityResource activity) {
		this.activity = activity;
	}
	
}
