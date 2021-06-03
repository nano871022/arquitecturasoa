package co.com.arquitectura.exceptions.login;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * Clase para manejar las excepciones generadas en el modulo de login de la
 * aplicacion, esta excepcion sera retornada desde este modulo
 * 
 * @author Alejandro Parra
 * @since 28/02/2017
 */
public class LoginException extends Exception {
	private static final long serialVersionUID = -5167002028797307393L;
	private LocalDateTime fecha;
	private Class<?> clase;
	private String message;

	public LoginException(String message, Class<?> clase, Throwable e) {
		super(String.format("Se presento una excepcion en %s con el mensaje %s .", clase.getSimpleName(), message), e);
		fecha = LocalDateTime.now();
		this.clase = clase;
		this.message = message;
	}

	public LoginException(String message, Class<?> clase) {
		super(String.format("Se presento una excepcion en %s con el mensaje %s .", clase.getSimpleName(), message),
				new Throwable());
		fecha = LocalDateTime.now();
		this.clase = clase;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Class<?> getClase() {
		return clase;
	}

	public String stackTrace() {
//		try {
//			StringWriter sw = new StringWriter();
//			PrintWriter pw = new PrintWriter(sw);
//			super.printStackTrace(pw);
//			return sw.toString();
//		} catch (Exception e) { return "";}
		return "sin stack trace";
	}

	@Override
	public String toString() {
		return String.format("LoginException [fecha:%s,clase:%s,message:%s] stackTrace:[%s]", fecha, clase, message,
				stackTrace());
	}
}
