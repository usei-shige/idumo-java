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
package jp.idumo.java.parts.handler.raw;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOConstructor;
import jp.idumo.java.annotation.IDUMOHandler;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfBoolPrimitiveElement;
import jp.idumo.java.model.primitive.IfNumberPrimitiveElement;
import jp.idumo.java.model.primitive.IfBoolPrimitiveElement.BoolPrimitiveModel;
import jp.idumo.java.parts.IfReceivable;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.validator.ReceiveValidatorSize;
import jp.idumo.java.validator.ReceiveValidatorType;

/**
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOHandler(receive = IfNumberPrimitiveElement.class, send = BoolPrimitiveModel.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "入力値＞指定値", summary = "入力値が指定値より大きい場合")
public class NumberMoreThanHandler implements IfSendable, IfReceivable {
	
	private IfSendable				sender;
	private double					condition;
	private ReceiveValidatorSize	validator	= new ReceiveValidatorSize(1);
	private ReceiveValidatorType	vType		= new ReceiveValidatorType(1, IfNumberPrimitiveElement.class);
	
	@IDUMOConstructor({ "比較する数値" })
	public NumberMoreThanHandler(double condition) {
		this.condition = condition;
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		IfNumberPrimitiveElement number = (IfNumberPrimitiveElement) sender.onCall().next();
		double d = number.getNumber();
		// IDUMOLogManager.debug(d);
		// IDUMOLogManager.debug(String.format("raw:%.0f,con:%.0f",d,condition));
		if (condition < d) {
			return new FlowingData(new IfBoolPrimitiveElement.BoolPrimitiveModel(true));
		}
		return new FlowingData(new IfBoolPrimitiveElement.BoolPrimitiveModel(false));
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new SingleConnectDataType(IfNumberPrimitiveElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfBoolPrimitiveElement.class);
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		validator.validate(senders);
		vType.validate(senders);
		sender = senders[0];
	}
}
