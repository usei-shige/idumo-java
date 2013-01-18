package jp.idumo.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import jp.idumo.java.model.IfDataElement;


@Target({ ElementType.TYPE })
public @interface IDUMOAdaptor {
	Class<? extends IfDataElement> send();

	Class<? extends IfDataElement>[] receive();
}
