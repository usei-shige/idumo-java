package jp.idumo.java.model.element;

import jp.idumo.java.model.IfDataElement;
import jp.idumo.java.model.raw.NumberRawDataType;

public interface IfNumberElement extends IfDataElement {
	public double getNumber1();
	
	public double getNumber2();
	
	public class NumberConvertModel extends AbstractData implements IfNumberElement {
		
		private static final String NUMBER1 = "number1";
		private static final String NUMBER2 = "number2";
		
		public NumberConvertModel(double num1, double num2) {
			add(new NumberRawDataType(NUMBER1, num1, "number1"));
			add(new NumberRawDataType(NUMBER2, num2, "number2"));
		}
		
		@Override
		public double getNumber1() {
			return (Double) getValue(NUMBER1);
		}
		
		@Override
		public double getNumber2() {
			return (Double) getValue(NUMBER2);
		}
		
	}
}
