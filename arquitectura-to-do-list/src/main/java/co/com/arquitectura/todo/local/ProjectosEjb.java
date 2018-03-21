package co.com.arquitectura.todo.local;

import javax.ejb.EJB;

import co.com.arquitectura.constants.generics.ConstantesValores;
import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.ejb.todolist.servicios.IProjectoSvc;
import co.com.arquitectura.exceptions.query.QueryException;
import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.librerias.validacion.Validacion;
import co.com.arquitectura.pojo.basicos.privated.Persona;
import co.com.arquitectura.todolist.CargoDTO;
import co.com.arquitectura.todolist.ProjectoDTO;
import co.com.arquitectura.todolist.RecursoDTO;

public class ProjectosEjb implements IProjectoSvc {
	@EJB
	private IQuery querySvc;

	@Override
	public void creacion(ProjectoDTO projecto) throws ToDoListException {
		try {
			if (Validacion.isEmpty(projecto)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isNotEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra lleno.");
			}
			Validacion.validarObject(projecto);
			querySvc.insert(projecto);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error", e);
		}
	}

	@Override
	public void modificacion(ProjectoDTO projecto) throws ToDoListException {
		try {
			if (Validacion.isEmpty(projecto)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra vacio.");
			}
			Validacion.validarObject(projecto);
			querySvc.update(projecto);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error", e);
		}
	}

	@Override
	public void eliminacion(ProjectoDTO projecto) throws ToDoListException {
		try {
			if (Validacion.isEmpty(projecto)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra vacio.");
			}
			querySvc.delete(projecto);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error", e);
		}
	}

	@Override
	public void asignarRecursos(ProjectoDTO projecto, Persona persona, CargoDTO cargo) throws ToDoListException {
		try {
			if (Validacion.isEmpty(projecto)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra vacio.");
			}
			if (Validacion.isEmpty(persona)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isEmpty(persona.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra vacio.");
			}
			if (Validacion.isEmpty(cargo)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isEmpty(cargo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra vacio.");
			}
			RecursoDTO dto = new RecursoDTO();
			dto.setEstado(ConstantesValores.CONST_ACTIVADO);
			dto.setIdPersona(persona.getLlave());
			dto.setIdCargo(cargo.getLlave());
			dto.setIdProjecto(projecto.getLlave());
			Validacion.validarObject(dto);
			querySvc.insert(dto);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error", e);
		}
	}

	@Override
	public void modificarCargo(RecursoDTO recurso, CargoDTO cargo) throws ToDoListException {
		try {
			if (Validacion.isEmpty(recurso)) {
				throw new ToDoListException("El objeto recurso se encuentra vacio.");
			}
			if (Validacion.isEmpty(recurso.getLlave())) {
				throw new ToDoListException("El valor llave del recurso se encuentra vacio.");
			}
			if (Validacion.isEmpty(cargo)) {
				throw new ToDoListException("El objeto cargo se encuentra vacio.");
			}
			if (Validacion.isEmpty(cargo.getLlave())) {
				throw new ToDoListException("El valor llave del cargo se encuentra vacio.");
			}
			recurso.setIdCargo(cargo.getLlave());
			Validacion.validarObject(recurso);
			querySvc.update(recurso);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error", e);
		}

	}

	@Override
	public void eliminarRecurso(ProjectoDTO projecto, Persona persona) throws ToDoListException {
		try {
			if (Validacion.isEmpty(projecto)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra vacio.");
			}
			if (Validacion.isEmpty(persona)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (Validacion.isEmpty(persona.getLlave())) {
				throw new ToDoListException("El valor llave del objeto se encuentra vacio.");
			}
			RecursoDTO dto = new RecursoDTO();
			dto.setIdPersona(persona.getLlave());
			dto.setIdProjecto(projecto.getLlave());
			querySvc.delete(dto);
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la consulta", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error", e);
		}
	}

}
