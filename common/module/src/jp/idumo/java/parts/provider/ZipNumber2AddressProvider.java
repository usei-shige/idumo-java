package jp.idumo.java.parts.provider;

import jp.idumo.java.annotation.IDUMOCommon;
import jp.idumo.java.annotation.IDUMOConstructor;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOProvider;
import jp.idumo.java.component.ZipNumber2AddressComponent;
import jp.idumo.java.exception.IDUMORuntimeException;
import jp.idumo.java.model.ZipNumber2AddressModel;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.parts.IfSendable;



@IDUMOCommon
@IDUMOProvider(send = ZipNumber2AddressModel.class)
//@IDUMOInfo(author = "Yusei SHIGENOBU", display = "住所を取得", summary = "指定した郵便番号から住所を取得する")
public class ZipNumber2AddressProvider implements IfSendable {
	private ZipNumber2AddressComponent	zip;
	
	@IDUMOConstructor({ "郵便番号" })
	public ZipNumber2AddressProvider(int zipNumber) {
		try {
			zip = new ZipNumber2AddressComponent(zipNumber);
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
		p.add(zip.getModel());
		return p;
	}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(ZipNumber2AddressModel.class);
	}
}
