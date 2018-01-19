package co.com.arquitectura.ejb_local.login;

import co.com.arquitectura.proccessor.implement.ObtenerFabrica;
import co.com.arquitectura.proccessor.implement.Factory.IListFactory;

/**
 * Se realiza la implementacion de la Fabrica la cual permite obtener la clase creada
 * @author Alejandro Parra
 * @since 19/01/2018
 */
public class Fabrica extends ObtenerFabrica <ISaludar,IListFactory<ISaludar>> implements IFabrica {
	public Fabrica() {
		namePath = "Fabrica";
	}
	@Override
	public ISaludar getSaludo(String nombre) {
		try {
			return getImplements(nombre, getContainer(ISaludar.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> ISaludar getSaludo(T valor, String nombreCampo) {
		try {
			return getImplements(nombreCampo, valor, getContainer(ISaludar.class));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
