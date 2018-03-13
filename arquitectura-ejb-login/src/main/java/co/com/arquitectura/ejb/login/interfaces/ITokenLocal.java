package co.com.arquitectura.ejb.login.interfaces;

import co.com.arquitectura.exceptions.login.LoginException;
import co.com.arquitectura.pojo.basicos.Conexion;

/**
 * Es el encargado de obtenener todo lo relacionado con la generacion y
 * verificacion de tokens
 * 
 * @author Alejandro Parra
 * @since 01/03/2018
 *
 */
public interface ITokenLocal {
	/**
	 * Se encarga de suministrar la informacion de la conexion y al validar esta
	 * informacion, se genera el token para esta conexion o se retorna el token de
	 * conexion segun configuracion conexion permanete establecida para esa ip
	 * 
	 * @param connect
	 *            {@link Conexion}
	 * @return {@link String} token generado
	 * @throws Exception
	 */
	public String token(Conexion connect) throws LoginException;
}
