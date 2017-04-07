package co.com.arquitectura.cliente.login.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {
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
