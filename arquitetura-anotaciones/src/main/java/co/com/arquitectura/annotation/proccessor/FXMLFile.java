package co.com.arquitectura.annotation.proccessor;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Se encarga de obtener el archivo fxml y cargar el recurso indicado, el
 * recurso es el archivo fxml el cual contrala el controller.
 * 
 * @author Alejandro Parra
 * @since 2018-05-19
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface FXMLFile {
	/**
	 * ruta del archivo
	 * 
	 * @return {@link String}
	 */
	public String path();

	/**
	 * nombre del archivo
	 * 
	 * @return {@link String}
	 */
	public String file();

	public String nombreVentana() default "";
	/**
	 * Nombre del fxml file, si no se escribe se deberia tomar por defecto el nombre de la clase
	 * @return {@link String}
	 */
	public String name() default "";
}
