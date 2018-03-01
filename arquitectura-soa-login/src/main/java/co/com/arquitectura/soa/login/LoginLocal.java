package co.com.arquitectura.soa.login;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.annotation.proccessor.Services;
import co.com.arquitectura.annotation.proccessor.Services.kind;
import co.com.arquitectura.annotation.proccessor.Services.scope;
import co.com.arquitectura.ejb.login.interfaces.IConexionesLocal;
import co.com.arquitectura.ejb.login.interfaces.ILoginLocal;
import co.com.arquitectura.ejb.login.interfaces.ITokenLocal;
import co.com.arquitectura.ejb.login.interfaces.IUsuariosLocal;
import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.exceptions.login.LoginException;
import co.com.arquitectura.librerias.abstracts.AbstractLogger;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;
import co.com.arquitectura.soa.login.privado.login.GenerarTOken;
import co.com.arquitectura.soa.login.validations.ValidacionesTokens;

@Stateless
public class LoginLocal extends AbstractLogger implements ILoginLocal, IConexionesLocal, IUsuariosLocal, ITokenLocal {
	@EJB
	private IQuery queryEjb;

	@Services(alcance = scope.EJB, tipo = kind.PUBLIC, alias = "IniciarSesion", descripcion = "Se encarga de iniciar sesion en la aplicacion, retornado las condiciones de coneion para el usuario")
	public String login(Usuario user, Conexion connect) throws LoginException {
		String token = "";
		try {
			List<Conexion> lista = queryEjb.select(connect);
			List<Usuario> listU = queryEjb.select(user);
			token = GenerarTOken.tokenSession(user.getUsuario(), connect.getIpConexion(), connect.getModuloConexion(),
					connect.getNavegador());

			logger.info("Creando conexion del usuario con el token " + token);

			return token;
		} catch (Exception e) {
			throw new LoginException("Se genero error en el login.", this.getClass(), e);
		}
	}

	@Services(alcance = scope.EJB, tipo = kind.PUBLIC, alias = "TerminarSesion", descripcion = "Se encarga de terminar la conexion a la aplicacion para el usuario.")
	public void logout(String token, Conexion connect) throws LoginException {
		try {
			queryEjb.update(connect);
			logger.info("Sesion terminada.");
		} catch (Exception e) {
			throw new LoginException("Se genero error en el logout", this.getClass(), e);
		}
	}

	@Override
	public String token(Conexion connect) throws LoginException {
		String token = ConstantesLogin.VACIO;
		if (StringUtils.isBlank(connect.getIpConexion()))
			throw new LoginException("No se suministro la ip del usuario", this.getClass(), new Throwable());
		if (StringUtils.isBlank(connect.getModuloConexion()))
			throw new LoginException("No se suministro el modulo que solicita el servicio de obtener token",
					this.getClass(), new Throwable());
		if (ValidacionesTokens.validarIp(connect.getIpConexion())) {
			Conexion dto = new Conexion();
			dto.setIpConexion(connect.getIpConexion());
			dto.setModuloConexion(connect.getModuloConexion());
			dto.setNavegador(connect.getNavegador());
			try {
				List<Conexion> lista = queryEjb.select(dto);
				if (lista != null) {
					if (lista.size() > 0) {
						return lista.get(0).getToken();
					}
				}
				token = GenerarTOken.tokenLogin(connect.getIpConexion());
				dto.setToken(token);
				queryEjb.insert(dto);
			} catch (Exception e) {
				throw new LoginException("Se presento un error en token", this.getClass(), e);
			}
		} else {
			throw new LoginException("Ip no tiene un formato valido.", this.getClass());
		}
		return token;
	}

	@Override
	public Usuario getUser(String token, Conexion connect) throws LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conexion getConnect(String token, Conexion connect) throws LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conexion> getConnect(Usuario user, Conexion connect, LocalDate ini, LocalDate end)
			throws LoginException {
		// TODO Auto-generated method stub
		return null;
	}

}
