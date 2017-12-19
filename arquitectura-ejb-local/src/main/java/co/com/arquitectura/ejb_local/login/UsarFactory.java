package co.com.arquitectura.ejb_local.login;

import javax.ejb.Local;

import co.com.arquitectura.annotation.proccessor.InjectFabrica;
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
	@InjectFabrica
	private IFabrica fabrica;
	
	/**
	 * Comentatios de pruebas
	 * @return {@link String}
	 */
	public String getSaludo() {
		return "test";
	}
}
