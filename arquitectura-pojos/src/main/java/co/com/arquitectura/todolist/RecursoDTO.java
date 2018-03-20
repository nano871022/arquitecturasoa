package co.com.arquitectura.todolist;

import co.com.arquitectura.librerias.abstracts.ADTO;
import co.com.arquitectura.pojo.basicos.privated.Persona;

public class RecursoDTO extends ADTO {
	/**{@link ProjectoDTO}**/
	private String idProjecto;
	private Persona persona;
	private CargoDTO cargo;
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
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public CargoDTO getCargo() {
		return cargo;
	}
	public void setCargo(CargoDTO cargo) {
		this.cargo = cargo;
	}
	
}
