package jp.idumo.java.model.element;

import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.raw.NumberRawDataType;

public interface IfLatLngElement extends IfDataElement {
	public double getLatitude();
	
	public double getLongitude();
	
	public class LatLngModel extends AbstractData implements IfLatLngElement {
		
		private static final String LAT = "lat";
		private static final String LNG = "lng";
		
		public LatLngModel(double lat, double lng) {
			add(new NumberRawDataType(LAT, lat, "latitude"));
			add(new NumberRawDataType(LNG, lng, "longitude"));
		}
		
		@Override
		public double getLatitude() {
			return (Double) getValue(LAT);
		}
		
		@Override
		public double getLongitude() {
			return (Double) getValue(LNG);
		}
		
	}
}
