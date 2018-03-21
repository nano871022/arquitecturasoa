package co.com.arquitectura.todolist;
/**
 * Esta clase es utilizada como dto para el manejo de to do
 * @author Alejandro Parra
 * @since 20/03/2018
 */

import java.time.LocalDateTime;
import java.time.LocalTime;
import co.com.arquitectura.annotation.linked.LinkKey;
import co.com.arquitectura.annotation.validacion.NotEmpty;
import co.com.arquitectura.librerias.abstracts.ADTO;

public class ToDoDTO extends ADTO{
	@NotEmpty
	private LocatedType ubicacion;
	@NotEmpty
	private LocalDateTime desde;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	@LinkKey(classLinked=RecursoDTO.class)
	private String idResponsable;
	@LinkKey(classLinked=RecursoDTO.class)
	private String idAuditor;
	@LinkKey(classLinked=RecursoDTO.class)
	private String idDepende;
	private LocalTime tiempoEstimado;
	@LinkKey(classLinked=ProjectoDTO.class)
	@NotEmpty
	private String idProjecto;
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
	
	public LocalTime getTiempoEstimado() {
		return tiempoEstimado;
	}
	public void setTiempoEstimado(LocalTime tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public String getIdDepende() {
		return idDepende;
	}
	public void setIdDepende(String idDepende) {
		this.idDepende = idDepende;
	}
	public String getIdProjecto() {
		return idProjecto;
	}
	public void setIdProjecto(String idProjecto) {
		this.idProjecto = idProjecto;
	}
	
	
}
