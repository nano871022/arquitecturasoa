package co.com.arquitectura.ejb_local.login.interfaces;

import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

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
	public String login(Usuario user, Conexion connect) throws Exception;

	/**
	 * Se encarga de terminar la sesion con el token suministrado
	 * 
	 * @param token
	 *            {@link String} pre - generado
	 * @param connect
	 *            {@link Conexion}
	 * @throws Exception
	 */
	public void logout(String token, Conexion connect) throws Exception;
}
