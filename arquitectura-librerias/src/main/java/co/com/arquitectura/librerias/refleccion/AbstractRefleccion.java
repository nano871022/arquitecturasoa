package co.com.arquitectura.librerias.refleccion;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Esta clase es estatica y se encarga de implementar secciones de codigo para
 * utilizacion de la refleccion en el resto de la aplicacion, el uso de esta es
 * poder usar refleccion en cualquier parte de la aplicacion aplicando los
 * metodos que aqui se indican
 * 
 * @author Jose Alejandro Parra Lasso
 * @since Febrero 17 de 2017
 * @version 0.0.1
 */
public class AbstractRefleccion {
	/**
	 * este campo se encarga de obtener el valor que tiene almacenado un objeto
	 * el cual es accedido por medio de refleccion
	 * 
	 * @param campo
	 *            {@link Field}
	 * @return <Object> Algun objeto (WildCard)
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> T obtenerValor(Field campo, T instancia, Class<T> clase) throws Exception {
		T valor = null;
		if (campo.isAccessible()) {
			valor = (T) campo.get(instancia);
		} else {
			Method metodo = clase.getMethod(obtenerGet(campo.getName()));
			valor = (T) metodo.invoke(instancia);
		}
		return valor;
	}

	/**
	 * se encarga de poner el prefigo get al nombre del metodo y el nombre del
	 * campo se le agrega la primera letra amayuscula
	 * 
	 * @param nombre {@link String} Nombre del campo
	 * @return {@link String} nombre del metodo a obtener
	 * @throws Exception
	 */
	private final String obtenerGet(String nombre) throws Exception {
		char[] nombres = nombre.toCharArray();
		nombres[0] = Character.toUpperCase(nombres[0]);
		return ConstantesLibreria.CONSTANTE_GET+nombre.toString();
	}
}
