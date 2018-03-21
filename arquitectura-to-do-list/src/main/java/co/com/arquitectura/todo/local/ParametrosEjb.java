package co.com.arquitectura.todo.local;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.ejb.todolist.servicios.IParametrosSvc;
import co.com.arquitectura.exceptions.query.QueryException;
import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.librerias.validacion.Validacion;
import co.com.arquitectura.todolist.ParametrosToDoDTO;

public class ParametrosEjb implements IParametrosSvc {
	@EJB
	private IQuery querySvc;

	@Override
	public void crear(ParametrosToDoDTO parametro) throws ToDoListException {
		try {
			if (Validacion.isEmpty(parametro)) {
				throw new ToDoListException("El objeto parametro se encuentra vacio");
			}
			if (Validacion.isNotEmpty(parametro.getLlave())) {
				throw new ToDoListException("EL valor llave del parametro se encuentra lleno.");
			}
			Validacion.validarObject(parametro);
			querySvc.insert(parametro);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en creaci�n de registro.", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en creaci�n del parametro", e);
		}
	}

	@Override
	public void modificar(ParametrosToDoDTO parametro) throws ToDoListException {
		try {
			if (Validacion.isEmpty(parametro)) {
				throw new ToDoListException("El objeto parametro se encuentra vacio.");
			}
			if (Validacion.isEmpty(parametro.getLlave())) {
				throw new ToDoListException("EL valor llave del parametro se encuentra vacio.");
			}
			Validacion.validarObject(parametro);
			querySvc.update(parametro);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en modificaci�n de registro.", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en modificaci�n del parametro", e);
		}
	}

	@Override
	public void eliminar(ParametrosToDoDTO parametro) throws ToDoListException {
		try {
			if (Validacion.isEmpty(parametro)) {
				throw new ToDoListException("El objeto parametro se encuentra vacio.");
			}
			if (Validacion.isEmpty(parametro.getLlave())) {
				throw new ToDoListException("EL valor llave del parametro se encuentra vacio.");
			}
			querySvc.delete(parametro);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la eliminaci�n del registro.", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en la eliminaci�n del parametro", e);
		}
	}

	@Override
	public List<ParametrosToDoDTO> obtener(ParametrosToDoDTO parametro) throws ToDoListException {
		List<ParametrosToDoDTO> lista = new ArrayList<ParametrosToDoDTO>();
		try {
			if (Validacion.isEmpty(parametro)) {
				throw new ToDoListException("El objeto parametro se encuentra vacio.");
			}
			lista = querySvc.select(parametro);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la eliminaci�n del registro.", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en la eliminaci�n del parametro", e);
		}
		return lista;
	}

}
