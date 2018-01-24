package co.com.arquitectura.ejb_local.login;

import co.com.arquitectura.annotation.proccessor.Fabrica;

@Fabrica(id = "fabrica2", type = ISaludar.class)
public class Saludo2 implements ISaludar {
	private String name;

	public Saludo2() {
		name = "fabrica2";
	}
	public String getName() {
		return name;
	}
	@Override
	public String saludo() {
		return "Hola soy una frabrica de " + name;
	}
}
