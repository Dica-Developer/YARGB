package com.yarpg.restresources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GsonSerializable {
	String value() default "";
	String validator() default "";
	boolean encodeHtml() default true;
}
