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

import java.util.Map;
import java.util.TreeMap;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOConstructor;
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
import jp.idumo.java.util.LogManager;
import jp.idumo.java.validator.IfReceiveValidator;
import jp.idumo.java.validator.ReceiveValidatorSize;

/**
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 */
@IDUMOCommon
@IDUMOHandler(receive = IfDataElement.class, send = IfThroughElement.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "指定した項目でソート", summary = "入力データを指定した項目でソートする")
public class SortHandler implements IfSendable, IfReceivable {
	private String				name;
	private IfSendable			sender;
	private IfReceiveValidator	vSize	= new ReceiveValidatorSize(1);
	
	@IDUMOConstructor({ "ソートする要素" })
	public SortHandler(String name) {
		this.name = name;
	}
	
	@Override
	public boolean isReady() {
		return sender.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		TreeMap<Object, IfDataElement> map = new TreeMap<Object, IfDataElement>();
		for (IfDataElement d : sender.onCall()) {
			map.put(d.get(name).getValue(), d);
			LogManager.debug(d.get(name).getValue());
		}
		FlowingData idf = new FlowingData();
		for (Map.Entry<Object, IfDataElement> e : map.entrySet()) {
			idf.add(e.getValue());
		}
		return idf;
	}
	
	@Override
	public ConnectDataType receivableType() {
		return new ArrayConnectDataType(IfDataElement.class);
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new ArrayConnectDataType(IfDataElement.class);
	}
	
	@Override
	public void setSender(IfSendable... senders) throws IDUMOException {
		vSize.validate(senders);
		sender = senders[0];
	}
	
}
