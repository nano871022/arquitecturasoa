package co.com.arquitectura.librerias.implement.Factory;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;
/**
 * Clase abstracta que se encarga de obtener la clase creada por medio del processor
 * @author Alejandro Parra
 *
 * @param <M>
 * @param <T>
 */
public abstract class ObtenerFabrica<M extends Object, T extends IListFactory<M>> extends AbstractRefleccion {
	protected String namePath;
	/**
	 * Se encarga de obtener la clase que tiene las clases unidas de la fabrica
	 * @param interfaz {@link Class}
	 * @return {@link IListFactory}
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T getContainer(Class<M> interfaz) throws Exception {
		if (!interfaz.isInterface()) {
			throw new Exception("La clase suministrada no es de una interface");
		}
		if(StringUtils.isBlank(namePath)) {
			throw new Exception("Debe suministrar el namePath al construir la clase");
		}
		String canonicalName = interfaz.getCanonicalName();
		String path = "."+namePath+"." + interfaz.getSimpleName();
		path = canonicalName.replace("." + interfaz.getSimpleName(), path);
		Class<T> clase = (Class<T>) Class.forName(path);
		Class<?>[] clases = clase.getInterfaces();
		for(Class<?> cla : clases) {
			if(cla.getSimpleName().contains("IListFactory"))
			 return clase.newInstance();
		}
		if (clase.isInstance(IListFactory.class)) {
			return clase.newInstance();
		}
		return null;
	}
	/**
	 * obtuene la implementacion segun nombre de clase buscada en la lista de clases
	 * @param nameClass {@link String}
	 * @param listFacory {@link IListFactory}
	 * @return {@link Object}
	 * @throws Exception
	 */
	public M getImplements(String nameClass, T listFacory) throws Exception {
		if(StringUtils.isBlank(nameClass)) {
			throw new Exception ("No se suministro el nombre de la clase");
		}
		if(listFacory == null) {
			throw new Exception ("No se suministro el objeto IListFactory");
		}
		List<M> lista = listFacory.getList();
		M obj = null;
		for (M impl : lista) {
			try {
				obj = validateName(nameClass, impl);
				if (obj != null) {
					return obj;
				}
			} catch (NoSuchFieldException e) {
				continue;
			}
		}
		return null;
	}
	
	/**
	 * Se encarga de obtener la implementacion segun valor en un campo dentro de {@link IListFactory}
	 * @param fieldName {@link String}
	 * @param value {@link Object}
	 * @param listFacory {@link IListFactory}
	 * @return {@link Object} implentacion
	 * @throws Exception
	 */
	public <N extends Object> M getImplements(String fieldName,N value, T listFacory) throws Exception {
		if(StringUtils.isBlank(fieldName)) {
			throw new Exception ("No se suministro el nombre de la calse");
		}
		if(value == null) {
			throw new Exception ("No se suministro el valor a buscar");
		}
		if(listFacory == null) {
			throw new Exception ("No se suministro el objeto IListFactory");
		}
		List<M> lista = listFacory.getList();
		M obj = null;
		for (M impl : lista) {
			try {
				obj = validateValue(fieldName,value, impl);
				if (obj != null) {
					return obj;
				}
			} catch (NoSuchFieldException e) {
				continue;
			}catch (NoSuchMethodException e) {
				continue;
			}
		}
		return null;
	}
	
	/**
	 * Se encarga de validar la implementacion si cumple con el valor dentro del campo indicado
	 * @param fieldName {@link String}
	 * @param value {@link Object}
	 * @param obj {@link Object} implementacion
	 * @return {@link Object} implementacion
	 * @throws Exception
	 */
	private <N extends Object> M validateValue(String fieldName, N value, M obj) throws Exception {
		if (obj == null) {
			throw new Exception("No se suministro el objeto.");
		}
		if (value == null) {
			throw new Exception("No se suministro el valor a buscar.");
		}
		if (StringUtils.isBlank(fieldName.trim())) {
			throw new Exception("No se suministro el nombre del campo a buscar");
		}
		Object v = obtenerValor(fieldName, obj);
		if (v == value) {
			return obj;
		}
		return null;
	}
	/**
	 * Se encarga de validar si cumple la implementacion con el nombre de la clase suministrado 
	 * @param name {@link String}
	 * @param obj {@link Object} implementacion
	 * @return {@link Object} implementacion
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private M validateName(String name, M obj) throws Exception {
		if (obj == null) {
			throw new Exception("No se suministro el objeto.");
		}
		if (StringUtils.isBlank(name.trim())) {
			throw new Exception("No se suministro el nombre del objeto");
		}
		Class<M> clas = (Class<M>) obj.getClass();
		if (clas.getName().contains(name)) {
			return obj;
		}
		return null;
	}
}
