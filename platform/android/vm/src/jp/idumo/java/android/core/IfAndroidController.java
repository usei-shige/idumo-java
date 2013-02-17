package jp.idumo.java.android.core;

import jp.idumo.java.exec.IfCoreController;

/**
 * ApplicationのON/OFF時に必要な処理を書くためのインタフェース
 * 
 * @author Hiroyoshi HOUCHI
 * 
 */
public interface IfAndroidController extends IfCoreController {
	void onIdumoDestroy();
	
	void onIdumoPause();
	
	void onIdumoRestart();
	
	void onIdumoResume();
}
