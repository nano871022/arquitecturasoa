package co.com.arquitectura.annotation.proccessor;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Se encarga de realizar la injeccion de valores sobre el el objeto anotado,
 * por logica se obtiene los servicios simulados
 * 
 * @author Alejandro Parra
 * @since 2018-05-19
 * @version 1.1
 */
@Retention(CLASS)
@Target(TYPE)
public @interface Implements {
	
}
