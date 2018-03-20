package co.com.arquitectura.todolist;

import co.com.arquitectura.annotation.linked.LinkKey;
import co.com.arquitectura.librerias.abstracts.ADTO;

public class CargoDTO extends ADTO {
	private String nombre;
	private String descripcion;
	@LinkKey(classLinked=ParametrosToDoDTO.class)
	private String idTipo;
	private String estado;
	public String getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(String tipo) {
		this.idTipo = tipo;
	}
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
}
