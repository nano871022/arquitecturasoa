package co.com.arquitectura.soa.login;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
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
@Local
public class LoginLocal extends AbstractLogger implements ILoginLocal, IConexionesLocal, IUsuariosLocal, ITokenLocal {
	@EJB
	private IQuery queryEjb;

	@Services(alcance = scope.EJB, tipo = kind.PUBLIC, alias = "IniciarSesion", descripcion = "Se encarga de iniciar sesion en la aplicacion, retornado las condiciones de coneion para el usuario")
	public String login(Usuario user, Conexion connect) throws LoginException {
		String token = "";
		try {
			List<Conexion> lista = queryEjb.select(connect);
			if (lista != null)
				if (lista.size() != 1) {
					throw new LoginException("No se encontro la conección", this.getClass());
				}
			List<Usuario> listU = queryEjb.select(user);
			if (listU != null)
				if (listU.size() != 1) {
					throw new LoginException("NO se encontro el usuario", this.getClass());
				}
			token = GenerarTOken.tokenSession(user.getUsuario(), connect.getIpConexion(), connect.getModuloConexion(),
					connect.getNavegador());
			connect.setToken(token);
			connect.setEstadoConexion("A");
			connect.setFechaConexion((new Date()));
			connect.setUsuario(user);
			connect.setUsarioCreacion(user.getUsuario());
			if (StringUtils.isNotBlank(connect.getLlave())) {
				queryEjb.update(connect);
			} else {
				queryEjb.insert(connect);
			}
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
		if (StringUtils.isBlank(connect.getIpConexion()))
			throw new LoginException("No se suministro la ip del usuario", this.getClass(), new Throwable());
		if (StringUtils.isBlank(connect.getModuloConexion()))
			throw new LoginException("No se suministro el modulo que solicita el servicio de obtener token",
					this.getClass(), new Throwable());
		if (StringUtils.isBlank(token))
			throw new LoginException("No se suministro el token.", getClass());
		connect.setToken(token);
		try {
			List<Conexion> lista = queryEjb.select(connect);
			if (lista.size() > 0)
				return lista.get(0).getUsuario();
		} catch (Exception e) {
			throw new LoginException("No se pudo obtener la conexion.", getClass(), e);
		}
		return null;
	}

	@Override
	public Conexion getConnect(String token, Conexion connect) throws LoginException {
		if (StringUtils.isBlank(connect.getIpConexion())) {
			throw new LoginException("Ip de conexion se encuentra vacia.", this.getClass());
		}
		if (StringUtils.isNotBlank(connect.getModuloConexion())) {
			throw new LoginException("Modulo de conexion se encuentra vacio.", this.getClass());
		}
		connect.setToken(token);
		try {
			List<Conexion> lista = queryEjb.select(connect);
			if (lista.size() > 0)
				return lista.get(0);
		} catch (Exception e) {
			throw new LoginException("Se presento error en busqueda de la conexion", this.getClass(), e);
		}
		return null;
	}

	@Override
	public List<Conexion> getConnect(Usuario user, Conexion connect, LocalDate ini, LocalDate end)
			throws LoginException {
		if (StringUtils.isBlank(connect.getIpConexion())) {
			throw new LoginException("Ip de conexion se encuentra vacia.", this.getClass());
		}
		if (StringUtils.isNotBlank(connect.getModuloConexion())) {
			throw new LoginException("Modulo de conexion se encuentra vacio.", this.getClass());
		}
		if (StringUtils.isNotBlank(user.getUsuario())) {
			throw new LoginException("El usuario no se encuentra registrado.", this.getClass());
		}
		if (ini == null) {
			throw new LoginException("la fecha inicial de filtro se encuentra vacia", this.getClass());
		}
		if (end == null) {
			throw new LoginException("la fecha final de filtro se encuentra vacia", this.getClass());
		}
		Duration duration = Duration.between(ini, end);
		if (duration.toDays() > 365) {
			throw new LoginException("El rango entre fechas no puede superar los 12 meses", this.getClass());
		}
		List<Conexion> lista = null;
		connect.setUsuario(user);
		connect.setFechaIni(Date.from(ini.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		connect.setFechaEnd(Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		try {
			lista = queryEjb.select(connect);
		} catch (Exception e) {
			throw new LoginException("Se presento un error buscando las conexiones del usuario", this.getClass(), e);
		}
		return lista;
	}

	@Override
	public List<Usuario> getUsers(String token, Conexion connect) throws LoginException {
		// TODO Auto-generated method stub
		return null;
	}

}
