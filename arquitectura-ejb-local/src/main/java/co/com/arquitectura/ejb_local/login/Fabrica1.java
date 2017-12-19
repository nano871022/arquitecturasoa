package co.com.arquitectura.ejb_local.login;

import co.com.arquitectura.annotation.proccessor.Fabrica;

@Fabrica(id = "fabrica1", type = IFabrica.class)
public class Fabrica1 implements IFabrica {
	private String name;

	public Fabrica1() {
		name = "fabrica1";
	}

	@Override
	public String saludo() {
		return "Hola soy " + name;
	}
}
