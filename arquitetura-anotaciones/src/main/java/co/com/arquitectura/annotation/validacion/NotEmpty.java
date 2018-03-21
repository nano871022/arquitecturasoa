package co.com.arquitectura.annotation.validacion;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * Esta anotacion es usada para verificar que el campo indicado 
 * contenga valor y no se encuentre vacio, por vacio se entiende
 * que no sea nulo o que sea un valor valido.
 * @author Alejandro Parra
 * @since 21/03/2018
 */
@Retention(CLASS)
@Target({ FIELD, METHOD })
public @interface NotEmpty {

}
