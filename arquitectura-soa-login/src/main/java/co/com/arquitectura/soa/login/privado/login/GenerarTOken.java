package co.com.arquitectura.soa.login.privado.login;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import co.com.arquitectura.constants.generics.GenericConstants;
import co.com.arquitectura.exceptions.login.LoginException;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

/**
 * Esta clase se encarga de generar el token para la conexion
 * 
 * @author Alejandro Parra
 * @since 28/02/2018
 */
public class GenerarTOken {
	/**
	 * Se encarga de geenrar el token para poder realizar el login
	 * 
	 * @param ip
	 *            {@link String} ip de conexion
	 * @return {@link String}
	 * @throws LoginException
	 */
	public static final String tokenLogin(String ip) throws LoginException {
		String head = UUID.randomUUID().toString().toUpperCase();
		LocalDateTime fecha = LocalDateTime.now();
		String encode = String.format("%s:%s:%s", head, ip, fecha.toString());
		try {
			return Base64.getEncoder().encodeToString(encode.getBytes(GenericConstants.ENCODE_UTF8));
		} catch (UnsupportedEncodingException e) {
			throw new LoginException("Se encontro problema en la generacion del token para login", GenerarTOken.class,
					e);
		}
	}

	/**
	 * Se encarga de generar el token para mantener la sesion
	 * 
	 * @param user
	 *            {@link String}
	 * @param ip
	 *            {@link String}
	 * @param modulo
	 *            {@link String}
	 * @param navegador
	 *            {@link String}
	 * @return {@link String}
	 * @throws LoginException
	 */
	public static final String tokenSession(String user, String ip, String modulo, String navegador)
			throws LoginException {
		LocalDateTime fecha = LocalDateTime.now();
		String random = UUID.randomUUID().toString().toUpperCase();
		String encode = String.format("%s:%s:%s:%s:%s:%s", random, user, ip, modulo, navegador, fecha.toString());
		try {
			return Base64.getEncoder().encodeToString(encode.getBytes(GenericConstants.ENCODE_UTF8));
		} catch (UnsupportedEncodingException e) {
			throw new LoginException("Se encontro problema en la generacion del token para login", GenerarTOken.class,
					e);
		}
	}

	public static final Conexion tokenSessionValidate(String token) throws LoginException {
		Conexion connect = new Conexion();
		String decode = new String(Base64.getDecoder().decode(token));
		String[] split = decode.split(":");
		if (split.length != 6)
			throw new LoginException("El token es invalido", GenerarTOken.class);
		Usuario user = new Usuario();
		user.setUsuario(split[1]);
		connect.setIpConexion(split[2]);
		connect.setModuloConexion(split[3]);
		connect.setNavegador(split[4]);
		connect.setFechaConexion(Date.from(LocalDateTime.parse(split[5]).atZone(ZoneId.systemDefault()).toInstant()));
		connect.setUsuario(user);
		connect.setTokenLogin(token);
		connect.setToken(split[0]);
		return connect;
	}
}
