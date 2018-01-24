package co.com.arquitectura.librerias.implement.listProccess;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;

public abstract class AbstractObtenerListFromProccess <M extends Object, T extends IListFromProccess<M>> extends AbstractRefleccion {
	protected String namePath;
	/**
	 * Se encarga de obtener la clase que tiene las clases unidas de la fabrica
	 * @param interfaz {@link Class}
	 * @return {@link IListFromProccess}
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public final T getContainer(Class<M> interfaz) throws Exception {
		if (!interfaz.isInterface()) {
			throw new Exception("La clase suministrada no es de una interface");
		}
		if(StringUtils.isBlank(namePath)) {
			throw new Exception("Debe suministrar el namePath al construir la clase");
		}
		String path = getFilePathName(interfaz.getSimpleName(), interfaz.getCanonicalName());
		Class<T> clase = (Class<T>) Class.forName(path);
		Class<?>[] clases = clase.getInterfaces();
		for(Class<?> cla : clases) {
			if(cla.getSimpleName().contains("IListFromProccess"))
			 return clase.newInstance();
		}
		if (clase.isInstance(IListFromProccess.class)) {
			return clase.newInstance();
		}
		return null;
	}
	/**
	 * Se encarga de generar el nombre del archivo a obtener
	 * @param simpleNameClass {@link String}
	 * @param canonicalName {@link String}
	 * @return {@link String} nombre con ruta completa
	 */
	protected abstract String getFilePathName(String simpleNameClass,String canonicalName) ;
	
	/**
	 * obtuene la implementacion segun nombre de clase buscada en la lista de clases
	 * @param nameClass {@link String}
	 * @param list {@link IListFromProccess}
	 * @return {@link Object}
	 * @throws Exception
	 */
	protected final M getImplements(String nameClass, T list) throws Exception {
		if(StringUtils.isBlank(nameClass)) {
			throw new Exception ("No se suministro el nombre de la clase");
		}
		if(list == null) {
			throw new Exception ("No se suministro el objeto IListFromProccess");
		}
		List<M> lista = list.getList();
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
	protected final <N extends Object> M getImplements(String fieldName,N value, T listFacory) throws Exception {
		if(StringUtils.isBlank(fieldName)) {
			throw new Exception ("No se suministro el nombre de la calse");
		}
		if(value == null) {
			throw new Exception ("No se suministro el valor a buscar");
		}
		if(listFacory == null) {
			throw new Exception ("No se suministro el objeto IListFromProccess");
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
	protected final <N extends Object> M validateValue(String fieldName, N value, M obj) throws Exception {
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
	protected abstract M validateName(String name, M obj) throws Exception ;
	
}
