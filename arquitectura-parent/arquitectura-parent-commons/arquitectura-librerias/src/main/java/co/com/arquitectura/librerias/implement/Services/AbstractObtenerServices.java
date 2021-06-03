package co.com.arquitectura.librerias.implement.Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.exceptions.lib.FieldValueException;
import co.com.arquitectura.librerias.implement.listProccess.IListFromProccess;
import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;
/**
 * Se encarga de traer cual quier clase que extienda {@link IListFromProccess}
 * @author Alejandro Parra
 * @param <T> extiende {@link IListFromProccess}
 */
public abstract class AbstractObtenerServices<T extends IListFromProccess<ServicePOJO>>
		extends AbstractRefleccion {
	protected String namePath;

	@SuppressWarnings("unchecked")
	public final T getContainer() throws Exception {
		if (StringUtils.isBlank(namePath)) {
			throw new FieldValueException("namePath");
		}
		String path = getFilePathName();
		Class<T> clase = (Class<T>) Class.forName(path);
		if (clase.isInstance(IListFromProccess.class)) {
			return clase.newInstance();
		}
		System.out.println(clase);
		List<Class<?>> clases = getInterfaces(clase);
		for (Class<?> clas : clases) {
			if (clas.getSimpleName().contains("IListFromProccess")) {
				return clase.newInstance();
			}
		}
		return null;
	}

	protected abstract String getFilePathName();

	/**
	 * obtiene todas las interfaces dentro de la clase y sus padres
	 * 
	 * @param clase
	 *            {@link Class}
	 * @return {@link List} < {@link Class} >
	 */
	private List<Class<?>> getInterfaces(Class<?> clase) {
		List<Class<?>> lista = new ArrayList<Class<?>>();
		if (clase == null) {
			return lista;
		}
		Class<?>[] clases = clase.getInterfaces();
		for (Class<?> clas : clases) {
			lista.add(clas);
		} // end for
		List<Class<?>> lista2 = getInterfaces((Class<?>) clase.getSuperclass());
		if (lista2.size() > 0)
			lista.addAll(lista2);
		return lista;
	}
}
