package jp.idumo.java.android.component.sensor;

import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.model.AndroidOriginationModel;
import jp.idumo.java.util.LogManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class AndroidDialogComponent {
	
	private AndroidOriginationModel model;
	
	private Activity activity;
	private EditText etext;
	private View view;
	
	private boolean isReady;
	private boolean isInit;
	private boolean isResister;
	private boolean isClick;
	
	public AndroidDialogComponent() {		
		isReady = false;
		isInit = false;
		isResister = false;
		isClick = false;
	}
	
	public void init(AndroidActivityResource activityResource) {
		LogManager.log();
		isInit = true;
		this.activity = activityResource.getActivity();
		view = new View(activity);
		activity.setContentView(view);
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public boolean isInit() {
		return isInit;
	}
	
	public boolean isClick() {
		return isClick;
	}
	
	public AndroidOriginationModel getData() {
		return model;
	}
	
	public void resister() {
		if(!isResister) {
			LogManager.log();
			
//			view = new View(activity);
//			view.setTextSize(10.0f);
//			view.setText("AndroidOriginationProvider");
//			activity.setContentView(view);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.activity);
			
			etext = new EditText(activity);
			
			alertDialogBuilder.setTitle("OriginationProvider");
			alertDialogBuilder.setMessage("文字列を入力して下さい");
			alertDialogBuilder.setView(etext);
			alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					LogManager.log();
					SpannableStringBuilder sb = (SpannableStringBuilder)etext.getText();
					String inputstr = sb.toString();
					model = new AndroidOriginationModel(inputstr);
					isClick = true;
				}
			});
			alertDialogBuilder.setCancelable(false);
	        AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			isResister = true;
		}
	}
	public void unregister() {
		if(isResister) {
			isResister = false;
		}
	}
}
