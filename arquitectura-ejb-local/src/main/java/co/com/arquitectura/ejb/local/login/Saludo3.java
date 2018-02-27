package co.com.arquitectura.ejb.local.login;

import co.com.arquitectura.annotation.proccessor.Fabrica;

@Fabrica(id = "fabrica3", type = ISaludar.class)
public class Saludo3 implements ISaludar {
	private String name;

	public Saludo3() {
		name = "fabrica3";
	}
	public String getName() {
		return name;
	}
	public String saludo() {
		return "Hola soy una frabrica de " + name;
	}
}
