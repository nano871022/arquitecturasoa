package co.com.arquitectura.soa.local.login;

import co.com.arquitectura.annotation.proccessor.Fabrica;
import co.com.arquitectura.soa.local.login.ISaludar;

@Fabrica(id = "fabrica1", type = ISaludar.class)
public class Saludo1 implements ISaludar {
	private String name;

	public Saludo1() {
		name = "fabrica1";
	}

	public String saludo() {
		return "Hola soy " + name;
	}
}
