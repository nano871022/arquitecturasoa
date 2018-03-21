package co.com.arquitectura.ejb.todolist.servicios;

import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.pojo.basicos.privated.Persona;
import co.com.arquitectura.todolist.ToDoDTO;

public interface ICambiosSvc {
	/**
	 * Se encarga de cambiar el estado de to-do a por hacer, se le asigna la persona
	 * quien va a resolverlo.
	 * 
	 * @param persona
	 *            {@link Persona}
	 * @param todo
	 *            {@link ToDoDTO}
	 * @param comentario
	 *            {@link String}
	 * @throws ToDoListException
	 */
	public void asignar(Persona persona, ToDoDTO todo, String comentario) throws ToDoListException;

	/**
	 * Se encarga de cambiar el estado de to-do a ejecutando.
	 * 
	 * @param todo
	 *            {@link ToDoDTO}
	 * @param comentario
	 *            {@link String}
	 * @throws ToDoListException
	 */
	public void ejecutando(ToDoDTO todo, String comentario) throws ToDoListException;

	/**
	 * Se encarga de cambiar el estado de to-do a realizado.
	 * 
	 * @param todo
	 *            {@link ToDoDTO}
	 * @param comentario
	 *            {@link String}
	 * @throws ToDoListException
	 */
	public void realizado(ToDoDTO todo, String comentario) throws ToDoListException;

	/**
	 * Se encarga de cambiar el estado de to-do a aprobado.
	 * 
	 * @param todo
	 *            {@link ToDoDTO}
	 * @param comentario
	 *            {@link String}
	 * @throws ToDoListException
	 */
	public void aprobado(ToDoDTO todo, String comentario) throws ToDoListException;
}
