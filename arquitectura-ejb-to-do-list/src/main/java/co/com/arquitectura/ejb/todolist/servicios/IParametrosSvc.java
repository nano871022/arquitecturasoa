package co.com.arquitectura.ejb.todolist.servicios;

import java.util.List;

import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.todolist.ParametrosToDoDTO;

/**
 * Se encarga de realizar crud sobre la tabla de parametros to-do
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public interface IParametrosSvc {
	/**
	 * Se encarga de crear un parametro
	 * @param paremetro {@link ParametrosToDoDTO}
	 * @throws Exception
	 */
	public void crear(ParametrosToDoDTO paremetro)throws ToDoListException;
	/**
	 * Se encarga de modificar un parametro
	 * @param paremetro {@link ParametrosToDoDTO}
	 * @throws Exception
	 */
	public void modificar(ParametrosToDoDTO paremetro)throws ToDoListException;
	/**
	 * Se encarga de eliminar un parametro siempre y cuando no este usado por otro parametro
	 * @param paremetro {@link ParametrosToDoDTO}
	 * @throws Exception
	 */
	public void eliminar(ParametrosToDoDTO paremetro)throws ToDoListException;
	/**
	 * Se encarga de buscar un parametro segun valores en los campos
	 * @param paremetro {@link ParametrosToDoDTO}
	 * @return {@link List} < {@link ParametrosToDoDTO} >
	 * @throws Exception
	 */
	public List<ParametrosToDoDTO> obtener(ParametrosToDoDTO paremetro)throws ToDoListException;
}
