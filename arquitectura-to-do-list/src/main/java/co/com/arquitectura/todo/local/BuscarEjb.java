package co.com.arquitectura.todo.local;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.ejb.todolist.servicios.BusquedaSvc;
import co.com.arquitectura.exceptions.query.QueryException;
import co.com.arquitectura.exceptions.todo.ToDoListException;
import co.com.arquitectura.librerias.validacion.Validacion;
import co.com.arquitectura.pojo.basicos.privated.Persona;
import co.com.arquitectura.todolist.CargoDTO;
import co.com.arquitectura.todolist.ParametrosToDoDTO;
import co.com.arquitectura.todolist.ProjectoDTO;
import co.com.arquitectura.todolist.RecursoDTO;
import co.com.arquitectura.todolist.ToDoDTO;

@Local
@Stateless
public class BuscarEjb implements BusquedaSvc {
	@EJB
	private IQuery querySvc;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<ToDoDTO> getToDo(Persona persona, ProjectoDTO projecto) throws ToDoListException {
		try {
			if (!Validacion.isNotEmpty(projecto)) {
				throw new ToDoListException("El objeto projecto se encuentra vacio.");
			}
			if (!Validacion.isNotEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave del projecto se encuentra vacio.");
			}
			if (!Validacion.isNotEmpty(persona)) {
				throw new ToDoListException("El objeto persona se encuentra vacio.");
			}
			if (!Validacion.isNotEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave de persona se encuentra vacio.");
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (Exception e1) {
			throw new ToDoListException("Se presento un error en la validacion de los campos", e1);
		}
		RecursoDTO recurso = new RecursoDTO();
		recurso.setIdProjecto(projecto.getLlave());
		recurso.setIdPersona(persona.getLlave());
		try {
			List<RecursoDTO> lista = querySvc.select(recurso);
			if (lista.size() > 1) {
				throw new ToDoListException("Se encontraron varios registros.");
			}
			recurso = lista.get(0);
		} catch (ToDoListException e) {
			throw e;
		} catch (Exception e) {
			throw new ToDoListException("Se presento error en la consulta del recurso.", e);
		}
		List<ToDoDTO> lista = new ArrayList<ToDoDTO>();
		ToDoDTO dto = new ToDoDTO();
		dto.setIdProjecto(projecto.getLlave());
		dto.setIdResponsable(recurso.getLlave());
		try {
			lista = querySvc.select(dto);
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en la consulta para obtener ToDo's", e);
		}
		return lista;
	}

	@Override
	public List<ProjectoDTO> getProjectos(Persona persona) throws ToDoListException {
		try {
			if (!Validacion.isNotEmpty(persona)) {
				throw new ToDoListException("El objeto persona se encuentra vacio.");
			}
			if (!Validacion.isNotEmpty(persona.getLlave())) {
				throw new ToDoListException("El valor llave de persona se encuentra vacio.");
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en validaciones", e);
		}
		RecursoDTO recurso = new RecursoDTO();
		recurso.setIdPersona(persona.getLlave());
		List<RecursoDTO> lista = new ArrayList<RecursoDTO>();
		try {
			lista = querySvc.select(recurso);
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un erro buscando los recursos.", e);
		}
		List<ProjectoDTO> proyectos = new ArrayList<ProjectoDTO>();
		for (RecursoDTO recursoDto : lista) {
			try {
				proyectos.addAll(querySvc.select(recursoDto.obtenerLinkKey("idProjecto")));
			} catch (Exception e) {
				log.error(e);
			}
		}
		return proyectos;
	}

	@Override
	public List<RecursoDTO> getRecursos(ProjectoDTO projecto) throws ToDoListException {
		try {
			if (!Validacion.isNotEmpty(projecto)) {
				throw new ToDoListException("El objeto proyecto se encuentra vacio.");
			}
			if (!Validacion.isNotEmpty(projecto.getLlave())) {
				throw new ToDoListException("El valor llave de proyecto se encuentra vacio.");
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en validaciones", e);
		}
		RecursoDTO recurso = new RecursoDTO();
		recurso.setIdProjecto(projecto.getLlave());
		List<RecursoDTO> lista = new ArrayList<RecursoDTO>();
		try {
			lista = querySvc.select(recurso);
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en busqueda de Recursos", e);
		}
		return lista;
	}

	@Override
	public List<CargoDTO> getCargos(ParametrosToDoDTO tipo) throws ToDoListException {
		try {
			if (!Validacion.isNotEmpty(tipo)) {
				throw new ToDoListException("El objeto parametro se encuentra vacio.");
			}
			if (!Validacion.isNotEmpty(tipo.getLlave())) {
				throw new ToDoListException("El valor llave del parametro se encuentra vacio.");
			}
		} catch (ToDoListException e) {
			throw e;
		} catch (Exception e) {
			throw new ToDoListException("Se presento un error en validaciones", e);
		}
		CargoDTO dto = new CargoDTO();
		dto.setIdTipo(tipo.getLlave());
		List<CargoDTO> lista = new ArrayList<CargoDTO>();
		try {
			lista = querySvc.select(dto);
		} catch (QueryException e) {
			throw new ToDoListException("Se presento un error en busqueda de Cargos", e);
		}
		return lista;
	}

}
