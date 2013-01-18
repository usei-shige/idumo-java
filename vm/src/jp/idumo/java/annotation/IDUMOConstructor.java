package jp.idumo.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.CONSTRUCTOR })
public @interface IDUMOConstructor {
	String[] value();
}
