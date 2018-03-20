package co.com.arquitectura.todolist;
/**
 * Esta clase es utilizada como dto para el manejo de to do
 * @author Alejandro Parra
 * @since 20/03/2018
 */

import java.time.LocalDateTime;
import java.time.LocalTime;
import co.com.arquitectura.annotation.linked.LinkKey;

import co.com.arquitectura.librerias.abstracts.ADTO;

public class ToDoDTO extends ADTO{
	private LocatedType ubicacion;
	private LocalDateTime desde;
	private String descripcion;
	/**recursoDTO**/
	private String idResponsable;
	/**recursoDTO**/
	@LinkKey(classLinked=RecursoDTO.class)
	private String idAuditor;
	private ToDoDTO depende;
	private LocalTime tiempoEstimado;
	private ProjectoDTO projecto;
	public LocatedType getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(LocatedType ubicacion) {
		this.ubicacion = ubicacion;
	}
	public LocalDateTime getDesde() {
		return desde;
	}
	public void setDesde(LocalDateTime desde) {
		this.desde = desde;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getIdResponsable() {
		return idResponsable;
	}
	public void setIdResponsable(String idResponsable) {
		this.idResponsable = idResponsable;
	}
	public String getIdAuditor() {
		return idAuditor;
	}
	public void setIdAuditor(String idAuditor) {
		this.idAuditor = idAuditor;
	}
	public ToDoDTO getDepende() {
		return depende;
	}
	public void setDepende(ToDoDTO depende) {
		this.depende = depende;
	}
	public LocalTime getTiempoEstimado() {
		return tiempoEstimado;
	}
	public void setTiempoEstimado(LocalTime tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public ProjectoDTO getProjecto() {
		return projecto;
	}
	public void setProjecto(ProjectoDTO projecto) {
		this.projecto = projecto;
	}
	
}
