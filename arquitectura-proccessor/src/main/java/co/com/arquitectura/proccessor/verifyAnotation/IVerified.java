package co.com.arquitectura.proccessor.verifyAnotation;

import javax.lang.model.element.TypeElement;
/**
 * interfaz para generaliza el verificador del procesador
 * @author Alejandro Parra
 * @since 19/01/2018
 */
public interface IVerified {
	/**
	 * Obtiene el tipo de elemento de la clase
	 * @return {@link TypeElement}
	 */
	public TypeElement getClase();
	/**
	 * obtiene el id del elemento
	 * @return {@link String}
	 */
	public String getId();
	/**
	 * Obtiene el nombre canonico de la clase
	 * @return {@link String}
	 */
	public String getCanonicClass();
	/**
	 * obtiene el nombre simple de la clase
	 * @return {@link String}
	 */
	public String getSimpleNameClass();
}
