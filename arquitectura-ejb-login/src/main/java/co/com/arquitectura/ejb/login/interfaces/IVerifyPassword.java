package co.com.arquitectura.ejb.login.interfaces;

import javax.ejb.Local;

import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

/**
 * esta intefaz se encarga de publicar los servicios que se expondran servicios locales 
 * @author Alejandro Parra
 * @since 28/07/2017
 *
 */
@Local
public interface IVerifyPassword {
	/**
	 * este servicio se encarga de verificar que el password enviado sea igual al password 
	 * almacenado, conencriptacion md5 y codigo de verificacion suministrado con anterioridad.
	 * @param user {@link Usuario}
	 * @param connect {@link Conexion}
	 * @return {@link String} token de conexion asociada al equipo
	 * @throws Exception
	 */
  public String verify(Usuario user,Conexion connect)throws Exception;
  /**
   * se encarga de entregar el código de verificación asociado para la conexión entrante
   * @param connect {@link Conexion}
   * @return {@link Integer} código de verificación generado.
   * @throws Exception
   */
  public Integer getCodeVerify(Conexion connect)throws Exception;
  /**
   * Se encarga de verificacion si en el equipo donde se desea conectar ya se encontraba
   * conectado y no ha cerrado la sesión y está permitido la auto conexión.
   * @param connect {@link Conexion}
   * @return {@link String} retorna un token nuevo de conexión
   * @throws Exception
   */
  public String autoConnect(Conexion connect)throws Exception;
}
