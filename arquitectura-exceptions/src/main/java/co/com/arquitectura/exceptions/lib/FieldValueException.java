package co.com.arquitectura.exceptions.lib;

/**
 * clase de excepcion que indica que un campo de las propuedades no tiene valor
 * 
 * @author Alejandro Parra
 * @since 23/01/2017
 */
public class FieldValueException extends Exception {
	private static final long serialVersionUID = -5167002028797307393L;

	public FieldValueException(String nameField) {
		super(String.format("El campo %s no tiene ningun valor.", nameField));
	}
}
