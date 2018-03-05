package co.com.arquitectura.ejb.login.interfaces;

import javax.ejb.Local;

import co.com.arquitectura.exceptions.login.LoginException;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

/**
 * Es el encargado de obtener todo lo relacionado con el login
 * 
 * @author Alejandro Parra
 * @since 01/03/2018
 */
@Local
public interface ILoginLocal {
	/**
	 * Suministra el usuario y el password(encriptado) para ser validado y envia la
	 * información de conexión para realizar el respectivo login y generar el token.
	 * 
	 * @param user
	 *            {@link Usuario}
	 * @param connect
	 *            {@link Conexion}
	 * @return {@link String} token generado
	 * @throws Exception
	 */
	public String login(Usuario user, Conexion connect) throws LoginException;

	/**
	 * Se encarga de terminar la sesion con el token suministrado
	 * 
	 * @param token
	 *            {@link String} pre - generado
	 * @param connect
	 *            {@link Conexion}
	 * @throws Exception
	 */
	public void logout(String token, Conexion connect) throws LoginException;
}
