package co.com.arquitectura.cliente.login.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import co.com.arquitectura.ejb.local.login.interfaces.ILoginLocal;
import co.com.arquitectura.ejb.local.login.interfaces.IVerifyPassword;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

@RequestScoped
@Named
public class LoginBean {
	@EJB
	private ILoginLocal login;
	@EJB
	private IVerifyPassword verifyPass;
	private String valor;
	private String token;
	
	public ILoginLocal getLogin() {
		return login;
	}
	public void setLogin(ILoginLocal login) {
		this.login = login;
	}
	public IVerifyPassword getVerifyPass() {
		return verifyPass;
	}
	public void setVerifyPass(IVerifyPassword verifyPass) {
		this.verifyPass = verifyPass;
	}
	public LoginBean(){
		valor = "texto";
	}
	public void setValor(String v){
		valor = v;
	}
	public void setToken(String v){
		token = v;
	}
	public String getValor(){
		return valor;
	}
	
	public String getToken() {
		try {
			return login.login(new Usuario(), new Conexion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void logout() {
		try {
			login.logout(getToken(), new Conexion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
