package co.com.arquitectura.soa.privado.login;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.ListaConexiones;
import co.com.arquitectura.pojo.basicos.Usuario;
/**
 * esta clase es la encarga a de realizar el login sobre el sistema y obtener la
 * informacion de la persona asociada a ese login
 * @author Alejandro Parra
 * @since 28/07/2017
 *
 */
public class Users extends Usuario{
	private ListaConexiones historial;
	private Conexion actual;
	private Boolean encriptado;
	public Users () {
		encriptado = false;
	}
	
	public ListaConexiones getHistorial() {
		return historial;
	}
	public void setHistorial(ListaConexiones historial) {
		this.historial = historial;
	}
	public Conexion getActual() {
		return actual;
	}
	public void setActual(Conexion actual) {
		this.actual = actual;
	}
	public final void genCodeVerify() {
		actual.setVerificacion(Double.valueOf(Math.round(Math.random()*100000)).intValue());
	}
	public final void encriptar()throws Exception {
		if(StringUtils.isBlank(getUsuario())) {
			encriptado = false;
			throw new Exception("Usuario se encuentra vacio, no se puede encriptar");
		}
		if(StringUtils.isBlank(getPassword())) {
			encriptado = false;
			throw new Exception("Clave se encuentra vacia, no se puede encriptar");
		}
		if(actual.getVerificacion() == null || actual.getVerificacion() <= 0) {
			encriptado = false;
			throw new Exception("El codigo no se ha generado, no se puede encriptar");
		}
		String before = getUsuario()+actual.getVerificacion()+getPassword();
		byte[] message = before.getBytes(Charset.defaultCharset());
		MessageDigest criptografia = MessageDigest.getInstance("MD5");
		byte[] out = criptografia.digest(message);
		setPassword(new String(out,Charset.defaultCharset()));
		encriptado = true;
	}
	public final Boolean clavesIguales(String claveEnviada)throws Exception {
		if(StringUtils.isBlank(getPassword())) {
			throw new Exception("No se ha generado la clave encriptada.");
		}
		if(!encriptado) {
			throw new Exception("No se ha encriptado la clave.");
		}
		System.out.println(claveEnviada+" "+getPassword());
		return getPassword().equals(claveEnviada);
	}
	
}
