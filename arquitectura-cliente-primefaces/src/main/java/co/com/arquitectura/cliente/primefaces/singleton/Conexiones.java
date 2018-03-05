package co.com.arquitectura.cliente.primefaces.singleton;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.constants.web.WebConstants;
import co.com.arquitectura.pojo.basicos.Conexion;

/**
 * Se encarga de obtener y almacenar la informacion de la conexion actual del
 * usuario
 * 
 * @author Alejandro Parra
 * @since 01/03/2018
 */
public class Conexiones {
	private static Conexiones conexiones;
	private Conexion conexion;
	private FacesContext facesContext;
	private ExternalContext externalContext;
	private HttpSession sessiones;

	private Conexiones() {
	}

	public final static Conexiones instance() throws Exception {
		if (conexiones == null) {
			conexiones = new Conexiones();
			conexiones.init();
		}
		return conexiones;
	}

	private void loadContext() throws Exception {
		if (facesContext == null) {
			facesContext = FacesContext.getCurrentInstance();
		}
		if (externalContext == null) {
			externalContext = facesContext.getExternalContext();
		}
		if (sessiones == null) {
			sessiones = ((HttpServletRequest) externalContext.getRequest()).getSession(true);
		}

	}

	private final void init() throws Exception {
		loadContext();
		conexion = new Conexion();
		conexion.setIpConexion(((HttpServletRequest) externalContext.getRequest()).getLocalAddr());
		conexion.setModuloConexion(externalContext.getContextName());
		conexion.setNavegador(
				((HttpServletRequest) externalContext.getRequest()).getHeader(WebConstants.HEADER_USER_AGENT));
		conexion.setTokenLogin((String) sessiones.getAttribute(WebConstants.TOKEN_LOGIN_SESSION));
	}

	public final void setSessionLogin(String token) {
		if (StringUtils.isNotBlank(token)) {
			sessiones.setAttribute(WebConstants.TOKEN_LOGIN_SESSION, token);
			conexion.setTokenLogin(token);
		}
	}

	public final void endSession() {
		sessiones.removeAttribute(WebConstants.TOKEN_LOGIN_SESSION);
		conexion.setTokenLogin("");
	}
	
	public Conexion getConexion() {
		return conexion;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public ExternalContext getExternalContext() {
		return externalContext;
	}

	public HttpSession getSessiones() {
		return sessiones;
	}
}
