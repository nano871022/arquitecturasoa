package co.com.arquitectura.ejb.todolist.servicios;

import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.todolist.ToDoDTO;
/**
 * Esta interface expone los servcios para la creacion, modificacion y eliminacion de un To-Do
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public interface ICreacionSvc {
	/**
	 * Se encarga de la creación del to-do
	 * @param todo {@link ToDoDTO}
	 * @throws ToDoListException
	 */
	public void crear(ToDoDTO todo)throws ToDoListException;
	/**
	 * Se encarga de la actualización del to-do
	 * @param todo {@link ToDoDTO}
	 * @throws ToDoListException
	 */
	public void actualizar(ToDoDTO todo)throws ToDoListException;
	/**
	 * Se encarga de la eliminación del to-do
	 * @param todo {@link ToDoDTO}
	 * @throws ToDoListException
	 */
	public void eliminar(ToDoDTO todo)throws ToDoListException;
	
}
