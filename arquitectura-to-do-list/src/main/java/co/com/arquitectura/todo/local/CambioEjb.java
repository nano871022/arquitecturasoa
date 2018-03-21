package co.com.arquitectura.todo.local;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.ejb.todolist.servicios.ICambiosSvc;
import co.com.arquitectura.exceptions.query.QueryException;
import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.librerias.validacion.Validacion;
import co.com.arquitectura.pojo.basicos.privated.Persona;
import co.com.arquitectura.todolist.ComentariosToDoDto;
import co.com.arquitectura.todolist.HistorialToDoDTO;
import co.com.arquitectura.todolist.LocatedType;
import co.com.arquitectura.todolist.RecursoDTO;
import co.com.arquitectura.todolist.ToDoDTO;

public class CambioEjb implements ICambiosSvc {
	@EJB
	private IQuery querySvc;

	@Override
	public void asignar(Persona persona, ToDoDTO todo, String comentario) throws ToDoListException {
		try {
			if (Validacion.isEmpty(persona)) {
				throw new ToDoListException("El objeto persona se encuentra vacio");
			}
			if (Validacion.isEmpty(persona.getLlave())) {
				throw new ToDoListException("El valor llave del objeto persona se encuentra vacio");
			}
			if (Validacion.isEmpty(todo)) {
				throw new ToDoListException("El objeto To-Do se encuentra vacio");
			}
			if (Validacion.isEmpty(todo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto To-Do se encuentra vacio");
			}
			todo.setUbicacion(LocatedType.PorHacer);
			todo.setIdDepende(persona.getLlave());
			querySvc.update(todo);
			HistorialToDoDTO hist = new HistorialToDoDTO();
			hist.setIdToDo(todo.getLlave());
			Duration duration = Duration.between(todo.getDesde(), LocalDateTime.now());
			hist.setTime(duration);
			querySvc.insert(hist);
			if (Validacion.isNotEmpty(hist.getLlave()) && StringUtils.isNotEmpty(comentario)) {
				RecursoDTO recurso = new RecursoDTO();
				recurso.setIdPersona(persona.getLlave());
				recurso.setIdProjecto(todo.getIdProjecto());
				List<RecursoDTO> lista = querySvc.select(recurso);
				if (Validacion.isNotEmpty(lista) && lista.size() == 1) {
					ComentariosToDoDto comment = new ComentariosToDoDto();
					comment.setComentario(comentario);
					comment.setIdRecurso(lista.get(0).getLlave());
					comment.setIdHistorial(hist.getLlave());
					Validacion.validarObject(comment);
					querySvc.insert(comment);
				}
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la query", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error.", e);
		}
	}

	@Override
	public void ejecutando(ToDoDTO todo, String comentario) throws ToDoListException {
		try {
			if (Validacion.isEmpty(todo)) {
				throw new ToDoListException("El objeto To-Do se encuentra vacio");
			}
			if (Validacion.isEmpty(todo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto To-Do se encuentra vacio");
			}
			todo.setUbicacion(LocatedType.Ejecutando);
			HistorialToDoDTO hist = new HistorialToDoDTO();
			hist.setIdToDo(todo.getLlave());
			Duration duration = Duration.between(todo.getFechaUltimaModificacion().toInstant(), LocalDateTime.now());
			querySvc.update(todo);
			hist.setTime(duration);
			querySvc.insert(hist);
			if (Validacion.isNotEmpty(hist.getLlave()) && StringUtils.isNotEmpty(comentario)) {
				RecursoDTO recurso = new RecursoDTO();
				recurso.setIdPersona(todo.getIdResponsable());
				recurso.setIdProjecto(todo.getIdProjecto());
				List<RecursoDTO> lista = querySvc.select(recurso);
				if (Validacion.isNotEmpty(lista) && lista.size() == 1) {
					ComentariosToDoDto comment = new ComentariosToDoDto();
					comment.setComentario(comentario);
					comment.setIdRecurso(lista.get(0).getLlave());
					comment.setIdHistorial(hist.getLlave());
					Validacion.validarObject(comment);
					querySvc.insert(comment);
				}
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la query", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error.", e);
		}
	}

	@Override
	public void realizado(ToDoDTO todo, String comentario) throws ToDoListException {
		try {
			if (Validacion.isEmpty(todo)) {
				throw new ToDoListException("El objeto To-Do se encuentra vacio");
			}
			if (Validacion.isEmpty(todo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto To-Do se encuentra vacio");
			}
			todo.setUbicacion(LocatedType.Realizado);
			HistorialToDoDTO hist = new HistorialToDoDTO();
			hist.setIdToDo(todo.getLlave());
			Duration duration = Duration.between(todo.getFechaUltimaModificacion().toInstant(), LocalDateTime.now());
			querySvc.update(todo);
			hist.setTime(duration);
			querySvc.insert(hist);
			if (Validacion.isNotEmpty(hist.getLlave()) && StringUtils.isNotEmpty(comentario)) {
				RecursoDTO recurso = new RecursoDTO();
				recurso.setIdPersona(todo.getIdResponsable());
				recurso.setIdProjecto(todo.getIdProjecto());
				List<RecursoDTO> lista = querySvc.select(recurso);
				if (Validacion.isNotEmpty(lista) && lista.size() == 1) {
					ComentariosToDoDto comment = new ComentariosToDoDto();
					comment.setComentario(comentario);
					comment.setIdRecurso(lista.get(0).getLlave());
					comment.setIdHistorial(hist.getLlave());
					Validacion.validarObject(comment);
					querySvc.insert(comment);
				}
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la query", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error.", e);
		}
	}

	@Override
	public void aprobado(ToDoDTO todo, String comentario) throws ToDoListException {
		try {
			if (Validacion.isEmpty(todo)) {
				throw new ToDoListException("El objeto To-Do se encuentra vacio");
			}
			if (Validacion.isEmpty(todo.getLlave())) {
				throw new ToDoListException("El valor llave del objeto To-Do se encuentra vacio");
			}
			todo.setUbicacion(LocatedType.Aprobado);
			HistorialToDoDTO hist = new HistorialToDoDTO();
			hist.setIdToDo(todo.getLlave());
			Duration duration = Duration.between(todo.getFechaUltimaModificacion().toInstant(), LocalDateTime.now());
			querySvc.update(todo);
			hist.setTime(duration);
			querySvc.insert(hist);
			if (Validacion.isNotEmpty(hist.getLlave()) && StringUtils.isNotEmpty(comentario)) {
				RecursoDTO recurso = new RecursoDTO();
				recurso.setIdPersona(todo.getIdResponsable());
				recurso.setIdProjecto(todo.getIdProjecto());
				List<RecursoDTO> lista = querySvc.select(recurso);
				if (Validacion.isNotEmpty(lista) && lista.size() == 1) {
					ComentariosToDoDto comment = new ComentariosToDoDto();
					comment.setComentario(comentario);
					comment.setIdRecurso(lista.get(0).getLlave());
					comment.setIdHistorial(hist.getLlave());
					Validacion.validarObject(comment);
					querySvc.insert(comment);
				}
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en la query", e);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error.", e);
		}
	}

}
