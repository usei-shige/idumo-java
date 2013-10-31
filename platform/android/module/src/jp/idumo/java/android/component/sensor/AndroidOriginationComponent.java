package jp.idumo.java.android.component.sensor;

import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.model.AndroidOriginationModel;
import android.app.Activity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AndroidOriginationComponent {
	
	private AndroidOriginationModel model;
	private boolean isReady;
	private boolean isInit;
	private Activity activity;
	
	private Button   button;
	private EditText etext;
	
	public AndroidOriginationComponent(){
		isReady = false;
		isInit  = false;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public boolean isInit() {
		return isInit;
	}
	
	public AndroidOriginationModel getData() {
		return model;
	}
	
	public void init(AndroidActivityResource activity) {
		isInit = true;
		
		etext = new EditText(activity.getActivity());
		button = new Button(activity.getActivity());
		button.setText("OK");
		
		activity.getActivity().setContentView(etext);
		activity.getActivity().setContentView(button);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SpannableStringBuilder sb = (SpannableStringBuilder)etext.getText();
				String inputstr = sb.toString();
				model = new AndroidOriginationModel(inputstr);
			}
		});
	}
}