package co.com.arquitectura.annotation.linked;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * Esta anotacion es usada para indicar que un campo que tiene un string se enlaza con un objeto con el campo llave
 * por lo cual llamando el metodo indicado en el objeto puedo obtener el objeto asociado a esa llave.
 * @author Alejandro Parra
 * @since 20/03/2018
 */
@Retention(CLASS)
@Target(ElementType.FIELD)
public @interface LinkKey {
	/**
	 * Indica la clase a enlazar, esta clase debe ser extendid de ADTO y dee contener el campo llave
	 * @return {@link Class}
	 */
	public Class<?> classLinked();
}
