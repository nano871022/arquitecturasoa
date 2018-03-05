package co.com.arquitectura.soa.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.arquitectura.ejb.login.interfaces.IVerifyPassword;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;
import co.com.arquitectura.soa.login.privado.interfaz.login.IConexion;

@Stateless
public class verifyPassword implements IVerifyPassword {
	
	@EJB
	private IConexion iConnect;
	public String verify(Usuario user,Conexion connect) throws Exception {
		return iConnect.login(user, connect);
	}

	public Integer getCodeVerify(Conexion connect) throws Exception {
		return iConnect.getCodeVerify(connect);
	}

	public String autoConnect(Conexion connect) throws Exception {
		return iConnect.autoConnect(connect);
	}

}
