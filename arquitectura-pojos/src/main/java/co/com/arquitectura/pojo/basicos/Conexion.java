package co.com.arquitectura.pojo.basicos;

import java.util.Date;

import co.com.arquitectura.librerias.abstracts.ADTO;
import co.com.arquitectura.pojo.basicos.privated.Usuario;
/**
 * tiene la informacion de la conexion actual del usuario
 * @author Alejandro Parra
 * @since 23/03/2017
 * @version 0.0.1
 */
public class Conexion extends ADTO {
	private String ipConexion;
	private String token;
	private String tokenLogin;
	private Date fechaConexion;
	private Date fechaUltimaConexion;
	private Date fechaActual;
	private String estadoConexion;
	private String moduloConexion;
	private String navegador;
	private Usuario usuario;
	private String recordar;
	private String autoLog;
	private Integer verificacion;
	/** Usada para consultar en rango de fechas **/
	private Date fechaIni;
	/** Usada para consultar en rango de fechas **/
	private Date fechaEnd;
	
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaEnd() {
		return fechaEnd;
	}
	public void setFechaEnd(Date fechaEnd) {
		this.fechaEnd = fechaEnd;
	}
	public Integer getVerificacion() {
		return verificacion;
	}
	public String getTokenLogin() {
		return tokenLogin;
	}

	public void setTokenLogin(String tokenLogin) {
		this.tokenLogin = tokenLogin;
	}

	public void setVerificacion(Integer verificacion) {
		this.verificacion = verificacion;
	}
	public String getRecordar() {
		return recordar;
	}
	public void setRecordar(String recordar) {
		this.recordar = recordar;
	}
	public String getAutoLog() {
		return autoLog;
	}
	public void setAutoLog(String autoLog) {
		this.autoLog = autoLog;
	}
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	public String getIpConexion() {
		return ipConexion;
	}
	public void setIpConexion(String ipConexion) {
		this.ipConexion = ipConexion;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
