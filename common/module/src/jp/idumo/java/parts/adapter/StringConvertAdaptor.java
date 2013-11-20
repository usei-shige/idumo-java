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
package jp.idumo.java.parts.adapter;

import jp.idumo.java.annotation.IDUMOAdaptor;
import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.MultiConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.element.IfStringElement.StringConvertModel;
import jp.idumo.java.model.primitive.IfStringPrimitiveElement;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.IfReceiveValidator;
import jp.idumo.java.validator.ReceiveValidatorSize;
import jp.idumo.java.validator.ReceiveValidatorType;

@IDUMOCommon
@IDUMOAdaptor(receive = { IfStringPrimitiveElement.class, IfStringPrimitiveElement.class }, send = StringConvertModel.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "StringConverter", summary = "2つのStringをデータ構造にまとめる")
public class StringConvertAdaptor implements IfSendable, IfReceivable {
	
	private IfSendable			sender1;
	private IfSendable			sender2;
	
	private IfReceiveValidator	vSize	= new ReceiveValidatorSize(2);
	private IfReceiveValidator	vType1	= new ReceiveValidatorType(1, IfStringPrimitiveElement.class);
	private IfReceiveValidator	vType2	= new ReceiveValidatorType(2, IfStringPrimitiveElement.class);
	
	public StringConvertAdaptor() {}
	
	@Override
	public boolean isReady() {
		boolean flag = true;
		flag = flag && sender1.isReady();
		flag = flag && sender2.isReady();
		return flag;
	}
	
	@Override
	public FlowingData onCall() {
		IfStringPrimitiveElement str1 = (IfStringPrimitiveElement) sender1.onCall().next();
		IfStringPrimitiveElement str2 = (IfStringPrimitiveElement) sender2.onCall().next();
		StringConvertModel strcnvrt = new StringConvertModel(str1.getString(), str2.getString());
		// IDUMOLogManager.debug(gd);
		return new FlowingData(strcnvrt);
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new MultiConnectDataType(IfStringPrimitiveElement.class, IfStringPrimitiveElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(StringConvertModel.class);
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		vType1.validate(senders);
		vType2.validate(senders);
		sender1 = senders[0];
		sender2 = senders[1];
	}
	
}
