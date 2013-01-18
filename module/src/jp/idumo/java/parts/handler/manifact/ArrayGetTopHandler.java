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
package jp.idumo.java.parts.handler.manifact;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOHandler;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.IfThroughElement;
import jp.idumo.java.model.connect.ArrayConnectDataType;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.ReceiveValidatorSize;

/**
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOHandler(receive = IfDataElement.class, send = IfThroughElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "配列の一番上を取得", summary = "配列の一番上の値を取得する")
public class ArrayGetTopHandler implements IfSendable, IfReceivable {
	
	private IfSendable				sender;
	private ReceiveValidatorSize	vSize	= new ReceiveValidatorSize(1);
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		IfDataElement d = sender.onCall().next();
		return new FlowingData(d);
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(IfDataElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return sender.sendableType();
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}
	
}
