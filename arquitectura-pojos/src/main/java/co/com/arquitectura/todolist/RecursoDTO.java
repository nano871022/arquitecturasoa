package co.com.arquitectura.todolist;

import co.com.arquitectura.annotation.linked.LinkKey;
import co.com.arquitectura.annotation.validacion.NotEmpty;
import co.com.arquitectura.librerias.abstracts.ADTO;
import co.com.arquitectura.pojo.basicos.privated.Persona;

public class RecursoDTO extends ADTO {
	@NotEmpty
	@LinkKey(classLinked=ProjectoDTO.class)
	private String idProjecto;
	@NotEmpty
	@LinkKey(classLinked=Persona.class)
	private String idPersona;
	@NotEmpty
	@LinkKey(classLinked=CargoDTO.class)
	private String idCargo;
	@NotEmpty
	private String estado;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getIdProjecto() {
		return idProjecto;
	}
	public void setIdProjecto(String idProjecto) {
		this.idProjecto = idProjecto;
	}
	
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String persona) {
		this.idPersona = persona;
	}
	public String getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(String idCargo) {
		this.idCargo = idCargo;
	}
	
	
}
