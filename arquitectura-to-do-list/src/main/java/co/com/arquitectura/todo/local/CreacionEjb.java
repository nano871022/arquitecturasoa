package co.com.arquitectura.todo.local;

import javax.ejb.EJB;

import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.ejb.todolist.servicios.ICreacionSvc;
import co.com.arquitectura.exceptions.query.QueryException;
import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.librerias.validacion.Validacion;
import co.com.arquitectura.todolist.ToDoDTO;

public class CreacionEjb implements ICreacionSvc {

	@EJB
	private IQuery querySvc;

	@Override
	public void crear(ToDoDTO todo) throws ToDoListException {
		try {
			if (Validacion.isEmpty(todo)) {
				throw new ToDoListException("El objeto To-Do se encuentra vacio.");
			}
			if (Validacion.isNotEmpty(todo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto to-Do se encuentra llena.");
			}
			Validacion.validarObject(todo);
			querySvc.insert(todo);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta.", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error.", e);
		}

	}

	@Override
	public void actualizar(ToDoDTO todo) throws ToDoListException {
		try {
			if (Validacion.isEmpty(todo)) {
				throw new ToDoListException("El objeto To-Do se encuentra vacio.");
			}
			if (Validacion.isEmpty(todo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto to-Do se encuentra vacio.");
			}
			Validacion.validarObject(todo);
			querySvc.update(todo);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta.", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error.", e);
		}
	}

	@Override
	public void eliminar(ToDoDTO todo) throws ToDoListException {
		try {
			if (Validacion.isEmpty(todo)) {
				throw new ToDoListException("El objeto To-Do se encuentra vacio.");
			}
			if (Validacion.isEmpty(todo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto to-Do se encuentra vacio.");
			}
			querySvc.delete(todo);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta.", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error.", e);
		}
	}

}
