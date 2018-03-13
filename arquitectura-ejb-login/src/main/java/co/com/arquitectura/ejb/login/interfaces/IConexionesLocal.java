package co.com.arquitectura.ejb.login.interfaces;

import java.time.LocalDate;
import java.util.List;

import co.com.arquitectura.exceptions.login.LoginException;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

/**
 * Es encargado de obtener todo lo relacionado con las conexiones para realizar
 * login
 * 
 * @author Alejandro Parra
 * @since 01/03/2018
 */
public interface IConexionesLocal {
	/**
	 * Se encarga de obtener la informacion de la conexion asociada al token
	 * 
	 * @param token
	 *            {@link String}
	 * @param connect
	 *            {@link Conexion}
	 * @return {@link Conexion}
	 * @throws Exception
	 */
	public Conexion getConnect(String token, Conexion connect) throws LoginException;

	/**
	 * Se encarga de ver el historial de conexiones realizadas con el
	 * token,usuario,conexion en un determinado tiempo, debe tener algunas de las
	 * opciones [Usuario,Conexion] Obligatorio el filtro de la fecha
	 * 
	 * @param user
	 *            {@link Usuario} Usuario a buscar {@link Usuario#usuario}
	 * @param connect
	 *            {@link Conexion} conexion
	 *            [{@link Conexion#ipConexion}|{@link Conexion#token}|{@link Conexion#moduloConexion}|{@link Conexion#navegador}]
	 *            a buscar
	 * @param ini
	 *            {@link LocalDate} fecha inicial de busqueda
	 * @param end
	 *            {@link LocalDate} fecha final de busqueda
	 * @return {@link List}< {@link Conexion} > historial
	 * @throws Exception
	 */

	public List<Conexion> getConnect(Usuario user, Conexion connect, LocalDate ini, LocalDate end)
			throws LoginException;

}
