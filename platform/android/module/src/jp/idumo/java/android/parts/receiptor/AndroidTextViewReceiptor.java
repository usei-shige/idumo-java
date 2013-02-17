/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jp.idumo.java.android.parts.receiptor;

import jp.idumo.java.android.annotation.IDUMOAndroid;
import jp.idumo.java.android.core.IfAndroidActivityController;
import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ArrayConnectDataType;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;
import jp.idumo.java.validator.ReceiveValidatorSize;
import android.widget.TextView;

/**
 * Android上にテキスト情報を出力するReceiptorです
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
@IDUMOAndroid
@IDUMOReceiptor(receive = IfDataElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "テキストの表示", summary = "Androidのテキスト表示")
public class AndroidTextViewReceiptor implements IfReceivable, IfExecutable, IfAndroidActivityController {
	
	private TextView				view;
	private IfSendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(IfDataElement.class);
	}
	
	@Override
	public void run() {
		LogManager.log();
		FlowingData idf = sender.onCall();
		StringBuilder sb = new StringBuilder();
		for (IfDataElement d : idf) {
			sb.append(d.getText());
		}
		
		LogManager.debug(sb.toString());
		
		view.setText(sb.toString());
	}
	
	@Override
	public void setSender(IfSendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
	@Override
	public void registActivity(AndroidActivityResource activity) {
		view = new TextView(activity.getActivity());
		activity.getActivity().setContentView(view);
		view.setTextSize(30.0f);
	}
	
}
