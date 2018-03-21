package co.com.arquitectura.todolist;

import java.time.LocalDate;

import co.com.arquitectura.annotation.validacion.NotEmpty;
import co.com.arquitectura.librerias.abstracts.ADTO;

public class ProjectoDTO extends ADTO{
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	private LocalDate desde;
	private LocalDate hasta;
	@NotEmpty
	private String estado;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public LocalDate getHasta() {
		return hasta;
	}
	public void setHasta(LocalDate hasta) {
		this.hasta = hasta;
	}
	
	

}