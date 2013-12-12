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
import jp.idumo.java.component.Zip2AddressComponent;
import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.Zip2AddressModel;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.parts.IfSendable;

/**
 * @author Yusei SHIGENOBU
 *
 * Providerモジュールのテンプレートです．
 * 細かい詳細は既に作成してある他のProvider等を参考に作成してください．
 * このProviderを起点にIDUMOのモジュールは動作し，ComponentやModelと連携します．
 * 
 */

@IDUMOCommon
@IDUMOProvider(send = Zip2AddressModel.class)
@IDUMOInfo(author = "Yusei SHIGENOBU", display = "住所を取得", summary = "指定した郵便番号から住所を取得する")
public class Zip2AddressProvider implements IfSendable {
	
	private Zip2AddressComponent	zip;
	
	@IDUMOConstructor({ "郵便番号(ハイフンなし)" })
	public Zip2AddressProvider(String num) {
		try {
			zip = new Zip2AddressComponent(num);
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
	}
	
	@Override
	public boolean isReady() {
		try {
			return zip.isReady();
		} catch (Exception e) {
			throw new IDUMORuntimeException(e);
		}
	}
	
	@Override
	public FlowingData onCall() {
		FlowingData p = new FlowingData();
		List<Zip2AddressModel> data = zip.getData();
		for(Zip2AddressModel d : data) {
			p.add(d);
		}
		return p;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(Zip2AddressModel.class);
	}
	
}
