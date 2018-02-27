package co.com.arquitectura.soa.local.login;

import co.com.arquitectura.librerias.implement.listProccess.IListFromProccess;
import co.com.arquitectura.soa.local.login.IFabrica;
import co.com.arquitectura.soa.local.login.ISaludar;
import co.com.arquitectura.librerias.implement.Factory.ObtenerFabrica;

/**
 * Se realiza la implementacion de la Fabrica la cual permite obtener la clase creada
 * @author Alejandro Parra
 * @since 19/01/2018
 */
public class Fabrica extends ObtenerFabrica <ISaludar,IListFromProccess<ISaludar>> implements IFabrica {
	public Fabrica() {
		namePath = "FactoryPackage";
	}
	public ISaludar getSaludo(String nombre) {
		try {
			return getImplements(nombre, getContainer(ISaludar.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> ISaludar getSaludo(T valor, String nombreCampo) {
		try {
			return getImplements(nombreCampo, valor, getContainer(ISaludar.class));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected String getFilePathName(String simpleNameClass, String canonicalName){
		String path = "."+namePath+"." + simpleNameClass;
		path = canonicalName.replace("." + simpleNameClass, path);
		return path;
	}

}
