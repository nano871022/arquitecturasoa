package co.com.arquitectura.annotation.linked;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(CLASS)
@Target(ElementType.FIELD)
public @interface LinkKey {
	public Class<?> classLinked();
}
