package co.com.arquitectura.cliente.login.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.cliente.primefaces.singleton.Conexiones;
import co.com.arquitectura.ejb.login.interfaces.ILoginLocal;
import co.com.arquitectura.ejb.login.interfaces.ITokenLocal;
import co.com.arquitectura.ejb.login.interfaces.IVerifyPassword;
import co.com.arquitectura.exceptions.login.LoginException;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

@RequestScoped
@Named
public class LoginBean {
	@EJB
	private ILoginLocal loginService;
	@EJB
	private IVerifyPassword verifyPass;
	@EJB
	private ITokenLocal tokenService;
	private Conexiones connections;
	private Usuario usuario;
	private String valor;
	private String token;

	public LoginBean() {
		try {
			valor = "texto";
			connections = Conexiones.instance();
			usuario = new Usuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setValor(String v) {
		valor = v;
	}

	public void setToken(String v) {
		token = v;
	}

	public String getValor() {
		return valor;
	}

	public String getToken() {
		try {
			System.out.println(String.format("Con:%s,Mod:%s,Nav:%s", connections.getConexion().getIpConexion(),
					connections.getConexion().getModuloConexion(), connections.getConexion().getNavegador()));
			if (StringUtils.isBlank(connections.getConexion().getToken())) {
				connections.getConexion().setToken(tokenService.token(connections.getConexion()));
			}
			return connections.getConexion().getToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void login() {
		System.out.println(String.format("user:%s ; pass:%s", usuario.getUsuario(), usuario.getPassword()));
		try {
			String token = loginService.login(usuario, connections.getConexion());
			connections.setSessionLogin(token);
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		try {
			loginService.logout(connections.getConexion().getToken(), connections.getConexion());
			connections.endSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean getLoged() {
		return StringUtils.isNotBlank(connections.getConexion().getTokenLogin());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
