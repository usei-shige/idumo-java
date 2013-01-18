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
package jp.idumo.java.console.receiptor;

import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.IfTextElement;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.parts.IfExecutable;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.ReceiveValidatorSize;

/**
 * Systemoutに出力するReceiptor
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
public class ConsoleViewReceiptor implements IfReceivable, IfExecutable {
	
	private IfSendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(IfDataElement.class);
	}
	
	@Override
	public void run() {
		FlowingData flowdata = sender.onCall();
		// IDUMODataPrimitive data = (IDUMODataPrimitive) flowdata.next();
		// System.out.println(data.getValue());
		for (IfDataElement idumoData : flowdata) {
			System.out.println(((IfTextElement) idumoData).getText());
		}
		// IDUMOData data = (IDUMOData) flowdata.next();
		// System.out.println(data);
	}
	
	@Override
	public void setSender(IfSendable... handler) throws IDUMOException {
		vSize.validate(handler);
		sender = handler[0];
	}
	
}
