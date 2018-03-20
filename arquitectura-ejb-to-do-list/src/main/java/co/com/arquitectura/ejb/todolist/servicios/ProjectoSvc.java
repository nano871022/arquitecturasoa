package co.com.arquitectura.ejb.todolist.servicios;

import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.pojo.basicos.privated.Persona;
import co.com.arquitectura.todolist.CargoDTO;
import co.com.arquitectura.todolist.ProjectoDTO;
import co.com.arquitectura.todolist.RecursoDTO;

/**
 * Se encarga de realizar todo lo lo relacionado con un projecto
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
	/**
	 * Se encarga de asignar una persona y cargo de la persona a un projecto
	 * @param projecto {@link ProjectoDTO}
	 * @param persona {@link Persona}
	 * @param cargo {@link CargoDTO}
	 * @throws ToDoListException
	 */
	public void asignarRecursos(ProjectoDTO projecto,Persona persona,CargoDTO cargo)throws ToDoListException;
	/**
	 * Se encarga de modificar el campo de un recurso previamente asignado
	 * @param recurso {@link RecursoDTO} recurso asignado
	 * @param cargo {@link CargoDTO} cargo nuevo
	 * @throws ToDoListException
	 */
	public void modificarCargo(RecursoDTO recurso, CargoDTO cargo)throws ToDoListException;
	/**
	 * Se encarga de eliminar una persona de un projecto siempre y cuando esta no tenga nada asignado
	 * @param projecto {@link ProjectoDTO}
	 * @param persona {@link Persona}
	 * @throws ToDoListException
	 */
	public void eliminarRecurso(ProjectoDTO projecto,Persona persona)throws ToDoListException;
}
