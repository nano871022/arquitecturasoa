package co.com.arquitectura.ejb.todolist.servicios;

import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.todolist.ProjectoDTO;

/**
 * Se encarga de realizar la creación, actualización e eliminación de un
 * projecto
 * 
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public interface ProjectoSvc {
	/**
	 * Se encarga de crear un nuevo projecto
	 * 
	 * @param projecto
	 *            {@link ProjectoDTO}
	 * @throws ToDoListException
	 */
	public void creacion(ProjectoDTO projecto) throws ToDoListException;

	/**
	 * Se encarga de modificar un projecto, siempre y cuando no tenga nada asignado
	 * 
	 * @param projecto
	 *            {@link ProjectoDTO}
	 * @throws ToDoListException
	 */
	public void modificacion(ProjectoDTO projecto) throws ToDoListException;

	/**
	 * Se encarga de eliminar un projecto, siempre y cuando no tenga nada asinado
	 * 
	 * @param projecto
	 *            {@link ProjectoDTO}
	 * @throws ToDoListException
	 */
	public void eliminacion(ProjectoDTO projecto) throws ToDoListException;
}
