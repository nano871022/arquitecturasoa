package co.com.arquitectura.cliente.login.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.arquitectura.ejb.local.login.interfaces.ILoginLocal;
import co.com.arquitectura.ejb.local.login.interfaces.IVerifyPassword;
import co.com.arquitectura.pojo.basicos.Conexion;
import co.com.arquitectura.pojo.basicos.privated.Usuario;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {
	@EJB
	private ILoginLocal login;
	@EJB
	private IVerifyPassword verifyPass;
	private String valor;
	private String token;
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
