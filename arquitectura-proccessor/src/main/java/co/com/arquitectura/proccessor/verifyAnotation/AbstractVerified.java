package co.com.arquitectura.proccessor.verifyAnotation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import co.com.arquitectura.proccessor.verifyAnotation.declared.IVerified;

/**
 * Se encarga de implmentar la interfaz de verificacion de clases para procesor
 * Esta clase es de tipo abstract y sera que ser implementado el codigo final en
 * otra clase
 * 
 * @author Alejandro Parra
 * @param <T>
 */
public abstract class AbstractVerified<T extends Annotation> implements IVerified {

	protected TypeElement clase;
	protected String id;
	protected String canonicClass;
	protected String simpleNameClass;

	/** constructor **/
	public AbstractVerified(TypeElement clase,boolean... noAuto) throws IllegalArgumentException, Exception {
		this.clase = clase;
		if(noAuto.length > 0 && noAuto.length == 1 && noAuto[0])
			getAnnotation(analized());
	}

	/**
	 * metodo que se encarga de obtener la anotacion principal de la clase
	 * suministrada ademas de almacenar el campo id en el objeto
	 * 
	 * @return {@link Annotation}
	 * @throws Exception
	 */
	protected abstract T analized() throws Exception;

	/**
	 * Se encarga de obtener el nombre canonico de la clase se encarga de obtener el
	 * nombre simple de la calse
	 * 
	 * @param obj
	 * @throws Exception
	 */
	protected abstract void getAnnotation(T obj) throws Exception;

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

	@SuppressWarnings("unchecked")
	protected <I extends Object> List<Class<I>> superClass(Class<?> parent) throws Exception {
		List<Class<I>> lista = new ArrayList<Class<I>>();
		if (parent != null) {
			Class<I>[] classs = (Class<I>[]) (parent.getInterfaces());
			for (Class<I> clas : classs)
				lista.add(clas);
		}
		lista.addAll(superClass(parent.getSuperclass()));
		lista.remove(null);
		return lista;
	}

}
