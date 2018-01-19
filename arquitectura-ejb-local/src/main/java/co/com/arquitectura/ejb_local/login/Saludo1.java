package co.com.arquitectura.ejb_local.login;

import co.com.arquitectura.annotation.proccessor.Fabrica;

@Fabrica(id = "fabrica1", type = ISaludar.class)
public class Saludo1 implements ISaludar {
	private String name;

	public Saludo1() {
		name = "fabrica1";
	}

	@Override
	public String saludo() {
		return "Hola soy " + name;
	}
}
