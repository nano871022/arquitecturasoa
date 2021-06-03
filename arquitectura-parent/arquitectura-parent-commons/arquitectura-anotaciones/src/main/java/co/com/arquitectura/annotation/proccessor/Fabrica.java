package co.com.arquitectura.annotation.proccessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Esta Anotacion es usada para crear el patron de fabrica de fabricas
 * 
 * @author Alejandro Parra
 * @since 2017/11/14
 * @version 1.0
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Fabrica {
	/**
	 * Clase que identifica la fabrica
	 * 
	 * @return {@link Class}
	 */
	public Class type();

	/**
	 * Nombre que identifica la fabrica a escoger
	 * 
	 * @return {@link String}
	 */
	public String id();

}
