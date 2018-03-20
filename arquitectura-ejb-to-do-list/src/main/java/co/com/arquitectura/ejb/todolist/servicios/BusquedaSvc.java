package co.com.arquitectura.ejb.todolist.servicios;

import java.util.List;

import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.pojo.basicos.privated.Persona;
import co.com.arquitectura.todolist.ProjectoDTO;
import co.com.arquitectura.todolist.ToDoDTO;

/**
 * Esta interfaz se encarga de publicar los servicios necesarios para usar la
 * lista to do
 * 
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public interface BusquedaSvc {
	/**
	 * Se encarga de obtner todos los todo asignados a la personas, segun projecto
	 * @param persona {@link Persona}
	 * @param projecto {@link ProjectoDTO}
	 * @return {@link List} < {@link ToDoDTO} >
	 * @throws ToDoListException
	 */
	public List<ToDoDTO> getToDo(Persona persona, ProjectoDTO projecto) throws ToDoListException;
	/**
	 * Se encarga de obtener todos los projectos en los cuales se encuentra asignado
	 * @param persona {@link Persona}
	 * @return {@link List} < {@link ProjectoDTO} >
	 * @throws ToDoListException
	 */
	public List<ProjectoDTO> getProjectos(Persona persona)throws ToDoListException;

}
