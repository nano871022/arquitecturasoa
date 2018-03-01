package co.com.arquitectura.ejb.login.interfaces;

import javax.ejb.Local;

import co.com.arquitectura.exceptions.login.LoginException;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;
/**
 * Encargada de obtener todo lo relacionado con los usuarios para el login
 * @author Alejandro Parra
 * @since 01/03/2018
 *
 */
@Local
public interface IUsuariosLocal {
	/**
	 * Se encarga de obtener la informacion del usuario asociado a ese token
	 * 
	 * @param token
	 *            {@link String}
	 * @param connect
	 *            {@link Conexion}
	 * @return {@link Usuario}
	 * @throws Exception
	 */
	public Usuario getUser(String token, Conexion connect) throws LoginException;

}
