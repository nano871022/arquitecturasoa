package co.com.arquitectura.soa.login.privado.interfaz.login;

import java.util.List;

import javax.ejb.Local;

import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

/**
 * se encarga de implentar todas las acciones pertinentes para realizar login 
 * en el sistema
 * @author Alejandro Parra
 * @since 31/07/2017
 */
@Local
public interface IConexion {
	/**
	 * con la informacion basica de usuario y ip optiene la información de la conexion asociada
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @return {@link co.com.arquitectura.pojo.basicos.Conexion}
	 */
	public Conexion getConnect(Conexion connect) throws Exception;
	/**
	 * Se encarga de validar que el token suministrado si sea valido para la conexion suministrada
	 * @param token {@link String} codigo token pre generado
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public Boolean validToken(String token,Conexion connect)throws Exception;
	/**
	 * Suministra el usuario y el password(encriptado) para ser validado y envia la información de conexión
	 * para realizar el respectivo login y generar el token.
	 * @param user {@link Usuario}
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @return {@link String} token generado
	 * @throws Exception
	 */
	public String login(Usuario user,Conexion connect)throws Exception;
	/**
	 * Se encarga de terminar la sesion con el token suministrado
	 * @param token {@link String} pre - generado
	 * @param connect {@link IConexion}
	 * @throws Exception
	 */
	public void logout(String token,Conexion connect)throws Exception;
	/**
	 * Genera el codigo de verificación para realizar el login
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @return {@link Integer} código de verificación generado
	 * @throws Exception
	 */
	public Integer getCodeVerify(Conexion connect)throws Exception;
	/**
	 * Se encarga de verificar si se realiza la auto conexion o no, si se encuentra
	 * activado el auto conección cuando inicio sesion y la session no se ha cerrado aun
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @return {@link String} se genera un nuevo token para esta conexion 
	 * @throws Exception
	 */
	public String autoConnect(Conexion connect) throws Exception;
	/**
	 * Cuando desea cambiar la configuración de auto conexion cuando se encuentra dentro de la session
	 * @param token {@link String} pre-generado
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @throws Exception
	 */
	public void changeAutoConnect(String token,Conexion connect)throws Exception;
	/**
	 * Se encagra de cambiar la configuracion de auto recordar cuando se encuentra dentro de la session
	 * @param token {@link String} pre - generado
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @throws Exception
	 */
	public void changeAutoRemember(String token,Conexion connect)throws Exception;
	
	/**
	 * retorna el listado de los usuarios conectados sobre esa conexión en el equipo
	 * @param connect {@link co.com.arquitectura.pojo.basicos.Conexion}
	 * @return {@link List} < {@link String} > usuarios
	 * @throws Exception
	 */
	public List<String> getUsuarios(Conexion connect)throws Exception;
}
