package co.com.arquitectura.proccessor.implement.Factory;

import java.util.List;
/**
 * Interfaz en la cual es implementada para almacenar las lista de fabrica
 * @author Alejandro Parra
 * @param {@link Object} 
 */
public interface IListFactory <T extends Object> {
	/**
	 * Obtiene una lista de objetos configurados
	 * @return {@link List} listas
	 */
	public List<T> getList();
}
