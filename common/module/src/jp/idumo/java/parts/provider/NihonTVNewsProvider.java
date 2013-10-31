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

import java.util.List;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOConstructor;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOProvider;
import jp.idumo.java.component.NihonTVNewsComponent;
import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.NihonTVNewsModel;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.parts.IfSendable;

/**
 * @author Yusei SHIGENOBU
 *
 * 日テレ系のニュースをフリーワードで検索するモジュール
 * http://appli.ntv.co.jp/appli/api/news/index.html
 */

@IDUMOCommon
@IDUMOProvider(send = NihonTVNewsModel.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "日テレ系のニュースを検索", summary = "日テレ系のニュースをフリーワードで検索するモジュール")
public class NihonTVNewsProvider implements IfSendable {
	
	private NihonTVNewsComponent	nTV;
	
	@IDUMOConstructor({ "検索ワード" })
	public NihonTVNewsProvider(String word) {
		try {
			nTV = new NihonTVNewsComponent(word);
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
	}
	
	@Override
	public boolean isReady() {
		try {
			return nTV.isReady();
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
	}
	
	@Override
	public FlowingData onCall() {
		FlowingData p = new FlowingData();
		List<NihonTVNewsModel> data = nTV.getData();
		for(NihonTVNewsModel d : data) {
			p.add(d);
		}
		return p;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(NihonTVNewsModel.class);
	}
	
}