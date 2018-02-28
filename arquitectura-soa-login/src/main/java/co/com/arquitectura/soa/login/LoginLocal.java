package co.com.arquitectura.soa.login;

import javax.ejb.Stateless;

import co.com.arquitectura.annotation.proccessor.Services;
import co.com.arquitectura.annotation.proccessor.Services.kind;
import co.com.arquitectura.annotation.proccessor.Services.scope;
import co.com.arquitectura.ejb.login.interfaces.ILoginLocal;
import co.com.arquitectura.librerias.abstracts.AbstractLogger;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;
@Stateless
public class LoginLocal extends AbstractLogger implements ILoginLocal {
	@Services(alcance = scope.EJB, tipo = kind.PUBLIC, alias = "IniciarSesion", descripcion = "Se encarga de iniciar sesion en la aplicacion, retornado las condiciones de coneion para el usuario")
	public String login(Usuario user, Conexion connect) throws Exception {
		String token = "qwertyuiopasdfghjklñzxvbnm";
		logger.info("Creando conexion del usuario con el token " + token);
		return token;
	}

	@Services(alcance = scope.EJB, tipo = kind.PUBLIC, alias = "TerminarSesion", descripcion = "Se encarga de terminar la conexion a la aplicacion para el usuario.")
	public void logout(String token, Conexion connect) throws Exception {
		logger.info("Terminando la conexion del susuario");
	}

}
