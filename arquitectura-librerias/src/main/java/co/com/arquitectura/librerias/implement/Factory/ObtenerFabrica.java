package co.com.arquitectura.librerias.implement.Factory;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.librerias.implement.listProccess.AbstractObtenerListFromProccess;
import co.com.arquitectura.librerias.implement.listProccess.IListFromProccess;
/**
 * Clase abstracta que se encarga de obtener la clase creada por medio del processor
 * @author Alejandro Parra
 *
 * @param <M>
 * @param <T>
 */
public abstract class ObtenerFabrica<M extends Object, T extends IListFromProccess<M>> extends AbstractObtenerListFromProccess<M, T> {
	/**
	 * Se encarga de validar si cumple la implementacion con el nombre de la clase suministrado 
	 * @param name {@link String}
	 * @param obj {@link Object} implementacion
	 * @return {@link Object} implementacion
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected M validateName(String name, M obj) throws Exception {
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
