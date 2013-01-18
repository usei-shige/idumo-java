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
package jp.idumo.java.parts.provider;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOConstructor;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOProvider;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.model.primitive.IfNumberPrimitiveElement;
import jp.idumo.java.parts.IfSendable;

/**
 * @author Hiroyoshi HOUCHI
 */
@IDUMOCommon
@IDUMOProvider(send = IfNumberPrimitiveElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "指定した数字を送信", summary = "ユーザが指定した数字を送信")
public class NumberProvider implements IfSendable {
	
	private Double	num;
	
	@IDUMOConstructor({ "送る数字" })
	public NumberProvider(double num) {
		this.num = num;
	}
	
	@Override
	public boolean isReady() {
		return true;
	}
	
	@Override
	public FlowingData onCall() {
		// IDUMOLogManager.log();
		FlowingData pipes = new FlowingData();
		pipes.add(new IfNumberPrimitiveElement.NumberPrimitiveModel(num));
		return pipes;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(IfNumberPrimitiveElement.class);
	}
	
}
