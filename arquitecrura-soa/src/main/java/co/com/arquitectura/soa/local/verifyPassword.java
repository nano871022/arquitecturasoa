package co.com.arquitectura.soa.local;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;
import co.com.arquitectura.soa.privado.interfaz.login.IConexion;
@Stateless
public class verifyPassword implements co.com.arquitectura.ejb_local.login.verifyPassword {
	
	@EJB
	private IConexion iConnect;
	@Override
	public String verify(Usuario user,Conexion connect) throws Exception {
		return iConnect.login(user, connect);
	}

	@Override
	public Integer getCodeVerify(Conexion connect) throws Exception {
		return iConnect.getCodeVerify(connect);
	}

	@Override
	public String autoConnect(Conexion connect) throws Exception {
		return iConnect.autoConnect(connect);
	}

}
