package co.com.arquitectura.ejb_local.login;

import co.com.arquitectura.annotation.proccessor.Services;
import co.com.arquitectura.annotation.proccessor.Services.kind;
import co.com.arquitectura.annotation.proccessor.Services.scope;
import co.com.arquitectura.ejb_local.login.interfaces.ILoginLocal;
import co.com.arquitectura.librerias.abstracts.AbstractLogger;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.Usuario;

public class LoginLocal extends AbstractLogger implements ILoginLocal {
	@Services(alcance = scope.EJB, tipo = kind.PUBLIC, alias = "IniciarSesion", descripcion = "Se encarga de iniciar sesion en la aplicacion, retornado las condiciones de coneion para el usuario", parent = LoginLocal.class)
	@Override
	public String login(Usuario user, Conexion connect) throws Exception {
		String token = "qwertyuiopasdfghjklñzxvbnm";
		logger.info("Creando conexion del usuario con el token " + token);
		return token;
	}

	@Override
	@Services(alcance = scope.EJB, tipo = kind.PUBLIC, alias = "TerminarSesion", descripcion = "Se encarga de terminar la conexion a la aplicacion para el usuario.", parent = LoginLocal.class)
	public void logout(String token, Conexion connect) throws Exception {
		logger.info("Terminando la conexion del susuario");
	}

}
