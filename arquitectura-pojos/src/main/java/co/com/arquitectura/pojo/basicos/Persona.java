package co.com.arquitectura.pojo.basicos;

import java.util.Date;

/**
 * El objeto es de tipo persona con el cual permite tener informacion basica de
 * una persona
 * 
 * @author Alejandro Parra
 * @since 23/03/2017
 * @version 0.0.1
 */
public class Persona {
	private String nombres;
	private String apellidos;
	private String tipoDocumento;
	private String documento;
	private String genero;
	private Date fechaNacimiento;

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}
