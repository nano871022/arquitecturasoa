package co.com.arquitectura.ejb_local.login;

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
