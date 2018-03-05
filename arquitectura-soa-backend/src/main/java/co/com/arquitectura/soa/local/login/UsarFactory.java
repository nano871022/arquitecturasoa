package co.com.arquitectura.soa.local.login;

import javax.ejb.Local;

import org.apache.log4j.Logger;

/**
 * Clase que usara la fabrica
 * @author Alejandro Parra
 * @since 20/11/2017
 */
@Local
public class UsarFactory  {
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * Inteface que tendra la fabrica
	 */
	private IFabrica fabrica = new Fabrica();
	
	/**
	 * Comentatios de pruebas
	 * @return {@link String}
	 */
	public String getSaludo() throws Exception{
		ISaludar saludar = fabrica.getSaludo("Saludo1");
		if(saludar == null)
				throw new Exception("No se encontro implementacion buscada");
		return saludar.saludo();
	}
	public String getSaludo(String name) throws Exception{
		ISaludar saludar = fabrica.getSaludo(name,"name");
		if(saludar == null)
				throw new Exception("No se encontro implementacion buscada");
		return saludar.saludo();
	}
}
