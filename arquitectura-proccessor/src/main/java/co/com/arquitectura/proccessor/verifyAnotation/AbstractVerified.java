package co.com.arquitectura.proccessor.verifyAnotation;

import java.lang.annotation.Annotation;

import javax.lang.model.element.TypeElement;

import co.com.arquitectura.proccessor.verifyAnotation.declared.IVerified;
/**
 * Se encarga de implmentar la interfaz de verificacion de clases para procesor
 * Esta clase es de tipo abstract y sera que ser implementado el codigo final en otra clase 
 * @author Alejandro Parra
 * @param <T>
 */
public abstract class AbstractVerified <T extends Annotation> implements IVerified {
	
	protected TypeElement clase;
	protected String id;
	protected String canonicClass;
	protected String simpleNameClass;
	/** constructor **/
	public AbstractVerified(TypeElement clase)throws IllegalArgumentException, Exception  {
		this.clase = clase;
		getAnnotation(analized());
	}
	/** 
	 * metodo que se encarga de obtener la anotacion principal de la clase suministrada
	 * ademas de almacenar el campo id en el objeto
	 * @return {@link Annotation}
	 * @throws Exception
	 */
	protected abstract T analized () throws Exception;
	/**
	 * Se encarga de obtener el nombre canonico de la clase
	 * se encarga de obtener el nombre simple de la calse
	 * @param obj
	 * @throws Exception
	 */
	protected abstract void getAnnotation(T obj)throws Exception;
	@Override
	public TypeElement getClase() {
		return clase;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getCanonicClass() {
		return canonicClass;
	}

	@Override
	public String getSimpleNameClass() {
		return simpleNameClass;
	}

}
