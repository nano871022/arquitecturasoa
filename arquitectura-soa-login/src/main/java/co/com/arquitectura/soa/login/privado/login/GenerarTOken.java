package co.com.arquitectura.soa.login.privado.login;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

import co.com.arquitectura.constants.generics.GenericConstants;
import co.com.arquitectura.exceptions.login.LoginException;

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
}
