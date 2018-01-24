package co.com.arquitectura.librerias.implement.listProccess;

import java.util.List;

/**
 * Interfaz en la cual es implementada para almacenar las lista creadas desde e
 * proccess y son procesos genericos que consiste con una determinada anotacion
 * obtiene una lista de los objetos que la usan
 * 
 * @author Alejandro Parra
 * @param {@link
 * 			Object}
 */
public interface IListFromProccess<T extends Object> {
	/**
	 * Obtiene una lista de objetos configurados
	 * 
	 * @return {@link List} listas
	 */
	public List<T> getList();
}
