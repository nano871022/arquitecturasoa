package co.com.arquitectura.pojo.basicos;

import java.util.Date;

import co.com.arquitectura.librerias.abstracts.ADTO;
/**
 * tiene la informacion de la conexion actual del usuario
 * @author Alejandro Parra
 * @since 23/03/2017
 * @version 0.0.1
 */
public class Conexion extends ADTO {
	private String ipConexion;
	private String referenciaConexion;
	private Date fechaConexion;
	private Date fechaUltimaConexion;
	private String estadoConexion;
	private String moduloConexion;
	private String navegador;
	private Usuario usuario;
	
	public String getIpConexion() {
		return ipConexion;
	}
	public void setIpConexion(String ipConexion) {
		this.ipConexion = ipConexion;
	}
	public String getReferenciaConexion() {
		return referenciaConexion;
	}
	public void setReferenciaConexion(String referenciaConexion) {
		this.referenciaConexion = referenciaConexion;
	}
	public Date getFechaConexion() {
		return fechaConexion;
	}
	public void setFechaConexion(Date fechaConexion) {
		this.fechaConexion = fechaConexion;
	}
	public Date getFechaUltimaConexion() {
		return fechaUltimaConexion;
	}
	public void setFechaUltimaConexion(Date fechaUltimaConexion) {
		this.fechaUltimaConexion = fechaUltimaConexion;
	}
	public String getEstadoConexion() {
		return estadoConexion;
	}
	public void setEstadoConexion(String estadoConexion) {
		this.estadoConexion = estadoConexion;
	}
	public String getModuloConexion() {
		return moduloConexion;
	}
	public void setModuloConexion(String moduloConexion) {
		this.moduloConexion = moduloConexion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNavegador() {
		return navegador;
	}
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}
}
