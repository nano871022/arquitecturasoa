package co.com.arquitectura.soa.local.login;

import co.com.arquitectura.soa.local.login.ISaludar;

public interface IFabrica  {
	/**
	 * Obtener Saludo Apartir del nombre
	 * 
	 * @param nombre {@link String}
	 */
	public ISaludar getSaludo(String nombre);

	/**
	 * Obtengo un Saludo apartir del valor comparado con el valor dentro de una
	 * propiedad de la clase
	 * @param valor {@link Object}
	 * @param nombreCampo {@link String}
	 */
	public <T extends Object> ISaludar getSaludo(T valor, String nombreCampo);
}
