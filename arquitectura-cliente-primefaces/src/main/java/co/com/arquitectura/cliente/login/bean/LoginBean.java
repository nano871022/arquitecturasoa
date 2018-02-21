package co.com.arquitectura.cliente.login.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.arquitectura.ejb_local.login.interfaces.ILoginLocal;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {
	@EJB
	private ILoginLocal login;
	private String valor;
	public LoginBean(){
		valor = "texto";
	}
	public void setValor(String v){
		valor = v;
	}
	public String getValor(){
		return valor;
	}
}
