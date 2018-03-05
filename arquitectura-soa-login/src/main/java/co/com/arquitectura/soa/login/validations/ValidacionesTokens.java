package co.com.arquitectura.soa.login.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.exceptions.login.LoginException;

/**
 * Se encarga de generar validaciones con todo lo relacionado para generar,
 * verificar, modificar tokens
 * 
 * @author Alejandro Parra
 * @since 28/02/2018
 */
public class ValidacionesTokens {
	/**
	 * Se encarga de validar la ip sea valida en Ipv4 o Ipv6
	 * 
	 * @param ip
	 *            {@link String}
	 * @return {@link Boolean}
	 * @throws LoginException
	 */
	public static final boolean validarIp(String ip) throws LoginException {
		ip = ip.trim();
		Matcher find = null;
		Pattern patron = null;
		if (StringUtils.isBlank(ip))
			throw new LoginException("No se suministro ninguna ip.", ValidacionesTokens.class, new Throwable());
		if (ip.contains(".")) {
			String ipV4 = "^([01].\\d?\\d?|2[0-4]\\d|25[0-4])\\."+
					      "([01]?\\d\\d?|2[0-4]\\d|25[0-4])\\."+
					      "([01]?\\d\\d?|2[0-4]\\d|25[0-4])\\."+
					      "([01]?\\d\\d?|2[0-4]\\d|25[0-4])$";
			patron = Pattern.compile(ipV4);
			find = patron.matcher(ip);
		} else if (ip.contains(":")) {
			String ipV6 = "^([0-9a-fA-F]{0,4}):"+
						  "([0-9a-fA-F]{0,4}):"+
						  "([0-9a-fA-F]{0,4}):"+
						  "([0-9a-fA-F]{0,4}):"+
						  "([0-9a-fA-F]{0,4}):"+
						  "([0-9a-fA-F]{0,4}):"+
						  "([0-9a-fA-F]{0,4}):"+
						  "([0-9a-fA-F]{0,4})$";
			patron = Pattern.compile(ipV6);
			find = patron.matcher(ip);
		} else {
			throw new LoginException("La ip suministrada no es valida en ipV4 o ipV6", ValidacionesTokens.class,
					new Throwable());
		}
		return find.find();
	}
}
