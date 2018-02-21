package co.com.arquitectura.pojo.basicos.publics;


import java.util.Date;

import co.com.arquitectura.librerias.abstracts.ADTO;

/**
 * se encarga del manejo de el login, conexiones del usuario con la aplicacion
 * 
 * @author Alejandro Parra
 * @since 23/03/2017
 * @version 0.0.1
 */
public class Usuario extends ADTO {
	private String usuario;
	private String password;
	private String password2;
	private int numeroIntentos;
	private String estado;
	private Date fechaBloqueo;
	private Persona asociado;
	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public int getNumeroIntentos() {
		return numeroIntentos;
	}

	public void setNumeroIntentos(int numeroIntentos) {
		this.numeroIntentos = numeroIntentos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaBloqueo() {
		return fechaBloqueo;
	}

	public void setFechaBloqueo(Date fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	public final Persona getAsociado() {
		return asociado;
	}

	public final void setAsociado(Persona asociado) {
		this.asociado = asociado;
	}
}
