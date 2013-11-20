package jp.idumo.java.android.component.sensor;

import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.model.AndroidOriginationModel;
import jp.idumo.java.util.LogManager;
import android.app.Activity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AndroidOriginationComponent implements View.OnClickListener{
	
	private AndroidOriginationModel model;
	private boolean isReady;
	private boolean isInit;
	private boolean isResister;
	private boolean isClick;
	
	private Activity activity;
	private Button   button;
	private EditText etext;
	
	public AndroidOriginationComponent(){
		isReady = false;
		isInit  = false;
		isClick = false;
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
	
	public void init(AndroidActivityResource activityResource) {
		LogManager.log();
		isInit = true;
		this.activity = activityResource.getActivity();
	}
	
	public void resister() {
		if(!isResister) {
			LogManager.log();
			isResister = true;
			origination();
		}
	}
	
	public void unregister() {
		if(isResister) {
			isResister = false;
		}
	}
	
	public void origination() {
		etext = new EditText(activity);
		button = new Button(activity);
		button.setText("OK");
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LogManager.log();
				SpannableStringBuilder sb = (SpannableStringBuilder)etext.getText();
				String inputstr = sb.toString();
				model = new AndroidOriginationModel(inputstr);
				isClick = true;
			}
		});
		
		LinearLayout layout = new LinearLayout(activity);
		layout.addView(etext, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		layout.addView(button, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		activity.setContentView(layout);
		LogManager.log();
	}

	@Override
	public void onClick(View v) {
		LogManager.log();
	}
}