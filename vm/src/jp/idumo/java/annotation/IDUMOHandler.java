package jp.idumo.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import jp.idumo.java.model.IfDataElement;


@Target({ ElementType.TYPE })
public @interface IDUMOHandler {
	Class<? extends IfDataElement> send();
	Class<? extends IfDataElement> receive();
}
