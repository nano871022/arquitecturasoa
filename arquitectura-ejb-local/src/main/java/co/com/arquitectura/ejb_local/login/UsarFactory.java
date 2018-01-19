package co.com.arquitectura.ejb_local.login;

import javax.ejb.Local;
/**
 * Clase que usara la fabrica
 * @author Ing-0-0013
// * @since 20/11/2017
 */
@Local
public class UsarFactory  {
	/**
	 * Inteface que tendra la fabrica
	 */
	private IFabrica fabrica = new Fabrica();
	
	/**
	 * Comentatios de pruebas
	 * @return {@link String}
	 */
	public String getSaludo() {
		ISaludar saludar = fabrica.getSaludo("Saludo1");
		return saludar.saludo();
	}
	
	public static void main(String...strings) {
		UsarFactory usar = new UsarFactory();
		usar.getSaludo();
	}
}
