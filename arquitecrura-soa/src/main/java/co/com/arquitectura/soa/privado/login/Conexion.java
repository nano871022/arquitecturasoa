package co.com.arquitectura.soa.privado.login;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.pojo.basicos.privated.Usuario;
import co.com.arquitectura.soa.privado.interfaz.login.IConexion;

/**
 * se encarga de implentar todas las acciones pertinentes para realizar login 
 * en el sistema
 * @author Alejandro Parra
 * @since 31/07/2017
 */
@Stateless
public class Conexion implements IConexion {
	@EJB
	private IQuery query;
	@Override
	public co.com.arquitectura.pojo.basicos.Conexion getConnect(co.com.arquitectura.pojo.basicos.Conexion connect)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validToken(String token, co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(Usuario user, co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(String token, co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getCodeVerify(co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		if(StringUtils.isBlank(connect.getIpConexion() )) {
			throw new Exception("La conexión no contiene la direccón IP.");
		}
		if(StringUtils.isBlank(connect.getNavegador())) {
			throw new Exception("La conexión no contiene el navegador o aplicación de conexión.");
		}
		if(StringUtils.isBlank(connect.getModuloConexion())) {
			throw new Exception("La conexión no contiene la modulo donde se encuentra realizando la conexión.");
		}
		if(query.selectCount(connect)>0) {
			throw new Exception("La conexión ya se encuentra abierta.");
		}
		Users users = new Users();
		users.setActual(connect);
		users.genCodeVerify();
		users.getActual().setFechaActual(new Date());
		query.insert(users.getActual());
		return users.getActual().getVerificacion();
	}

	@Override
	public String autoConnect(co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeAutoConnect(String token, co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeAutoRemember(String token, co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getUsuarios(co.com.arquitectura.pojo.basicos.Conexion connect) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
