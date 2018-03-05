package co.com.arquitectura.soa.local.login;

import co.com.arquitectura.annotation.proccessor.Fabrica;
import co.com.arquitectura.soa.local.login.ISaludar;

@Fabrica(id = "fabrica2", type = ISaludar.class)
public class Saludo2 implements ISaludar {
	private String name;

	public Saludo2() {
		name = "fabrica2";
	}
	public String getName() {
		return name;
	}
	public String saludo() {
		return "Hola soy una frabrica de " + name;
	}
}
