package co.com.arquitectura.proccessor.verifyAnotation;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.annotation.proccessor.Fabrica;

/**
 * Se encarga de verificar los campos anotados y los puede retornar con los
 * metodos indicados
 * 
 * @author Alejandro Parra
 * @since 2017/11/14
 *
 */
public class FactoryVerified {
	private TypeElement clase;
	private String id;
	private String canonicClass;
	private String simpleNameClass;

	/**
	 * Constructor encargado de cargar la información apartir del
	 * {@link TypeElement}, el cual es un elemento de clase
	 * 
	 * @param clase
	 *            {@link TypeElement}
	 * @throws IllegalArgumentException
	 */
	public FactoryVerified(TypeElement clase) throws IllegalArgumentException, Exception {
		this.clase = clase;
		getAnnotation(analized());
	}

	/**
	 * obtiene el id de la anotación y verifica que tenga información
	 * 
	 * @return {@link Fabrica}
	 */
	private Fabrica analized()throws Exception {
		Fabrica fabrica = clase.getAnnotation(Fabrica.class);
		id = fabrica.id();
		if (StringUtils.isBlank(id)) {
			throw new IllegalArgumentException(
					"El valor de la anotación ID se encuentra vacio en la clase " + clase.getSimpleName());
		}
		return fabrica;
	}

	/**
	 * almacena los parametros de la anotacion para poder ser consultados despues
	 * 
	 * @param fabrica
	 *            {@link Fabrica}
	 * @throws Exception
	 */
	private void getAnnotation(Fabrica fabrica) throws Exception {
		try {
			Class<?> claz = fabrica.type();
			canonicClass = claz.getCanonicalName();
			simpleNameClass = claz.getSimpleName();
		} catch (MirroredTypeException e) {
			try {
				DeclaredType classTypeMirror = (DeclaredType) e.getTypeMirror();
				TypeElement classType = (TypeElement) classTypeMirror.asElement();
				canonicClass = classType.getQualifiedName().toString();
				simpleNameClass = classType.getSimpleName().toString();
			} catch (Exception e2) {
				throw new Exception(e2.getMessage() + " factoryVerified:76 " + fabrica.id());
			}
		} catch (Exception e2) {
			throw new Exception(e2.getMessage() + " factoryVerified:80 " + fabrica.id());
		}
	}

	/**
	 * Clase obtenida de la anotación
	 * 
	 * @return {@link TypeElement}
	 */
	public TypeElement getClase() {
		return clase;
	}

	/**
	 * Id obtenida de la anotacion
	 * 
	 * @return {@link String}
	 */
	public String getId() {
		return id;
	}

	/**
	 * Nombre canonico de la clase obtenido apartir de la clase
	 * 
	 * @return {@link String}
	 */
	public String getCanonicClass() {
		return canonicClass;
	}

	/**
	 * Nombre simple de la clase
	 * 
	 * @return {@link String}
	 */
	public String getSimpleNameClass() {
		return simpleNameClass;
	}

}
