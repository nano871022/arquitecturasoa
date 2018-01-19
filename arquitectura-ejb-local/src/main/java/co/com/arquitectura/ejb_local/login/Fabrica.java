package co.com.arquitectura.ejb_local.login;

import co.com.arquitectura.librerias.implement.Factory.IListFactory;
import co.com.arquitectura.librerias.implement.Factory.ObtenerFabrica;

/**
 * Se realiza la implementacion de la Fabrica la cual permite obtener la clase creada
 * @author Alejandro Parra
 * @since 19/01/2018
 */
public class Fabrica extends ObtenerFabrica <ISaludar,IListFactory<ISaludar>> implements IFabrica {
	public Fabrica() {
		namePath = "FactoryPackage";
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
