package jp.idumo.java.android.core.exec;

import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.exec.IfCoreWrapper;
import android.os.Bundle;

import com.google.android.maps.MapActivity;

public abstract class AndroidWrapper extends MapActivity implements IfCoreWrapper {
	
	private AndroidActivity	execution;
	
	public AndroidWrapper(){}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		execution.setActivity(new AndroidActivityResource(this));
		try {
			execution.onIdumoCreated();
		} catch (IDUMOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onPause() {
		execution.onIdumoStop();
		super.onPause();
	}
	
	@Override
	public void onRestart() {
		execution.onIdumoStop();
		execution.onIdumoStart();
		super.onRestart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		execution.onIdumoStart();
		Thread thread = new Thread(execution);
		thread.start();
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
	public void setExecution(AndroidActivity execution) {
		this.execution = execution;
	}
	
	public void setExecutionWithComponent(AndroidComponent component) {
		execution = new AndroidActivity(component);
	}
}
