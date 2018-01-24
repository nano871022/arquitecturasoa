package co.com.arquitectura.proccessor.verifyAnotation;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import co.com.arquitectura.annotation.proccessor.Services;

public class ServicesVerified extends AbstractVerified<Services> {
	private String descripcion;
	private Services.kind tipo;
	private Services.scope alcance;
	private Class<?> parent;
	private Element method;

	public ServicesVerified(Element method) throws IllegalArgumentException, Exception {
		 super((TypeElement)method.getEnclosingElement(),false);
		 this.method = method;
		 getAnnotation(analized());
	}

	@Override
	protected Services analized() throws Exception {
		Services service = method.getAnnotation(Services.class);
		if (service == null) {
			throw new Exception("La anotacion @Service service no existe en " + clase.getSimpleName() + ".");
		}
		return service;
	}

	@Override
	protected void getAnnotation(Services obj) throws Exception {
		id = obj.alias();
		descripcion = obj.descripcion();
		tipo = obj.tipo();
		alcance = obj.alcance();
		canonicClass = clase.toString();
		simpleNameClass = clase.getSimpleName().toString();
	}
	public Element getMethod() {
		return method;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public Services.kind getTipo() {
		return tipo;
	}

	public Services.scope getAlcance() {
		return alcance;
	}

	public Class<?> getParent() {
		return parent;
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> Class<T>[] getInterface() throws Exception {
		List<Class<T>> lista = superClass(parent);
		return (Class<T>[]) lista.toArray();
	}
}
