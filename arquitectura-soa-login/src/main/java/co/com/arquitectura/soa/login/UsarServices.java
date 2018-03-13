package co.com.arquitectura.soa.login;

import java.nio.file.NoSuchFileException;

import org.apache.log4j.Logger;

import co.com.arquitectura.constants.generics.GenericConstants;
import co.com.arquitectura.constants.proccessor.FileNameConstants;
import co.com.arquitectura.constants.proccessor.PackageConstants;
import co.com.arquitectura.librerias.implement.Services.AbstractObtenerServices;
import co.com.arquitectura.librerias.implement.Services.ServicePOJO;
import co.com.arquitectura.librerias.implement.listProccess.IListFromProccess;

/**
 * Clase que usara la fabrica
 * 
 * @author Alejandro Parra
 * @since 20/11/2017
 */
public class UsarServices extends AbstractObtenerServices<IListFromProccess<ServicePOJO>> {
	private Logger log = Logger.getLogger(this.getClass());
	private IListFromProccess<ServicePOJO> lista;

	public UsarServices() {
		namePath = PackageConstants.SERVICE;
	}

	public void catalogo() {
		try {
			lista = getContainer();
			if (lista == null) {
				throw new NoSuchFileException(getFilePathName(), null,
						"No se encontro el archivo generado con los Servicios para este modulo");
			}
			for (ServicePOJO services : lista.getList()) {
				System.out.println(String.format("Servicio : %s , alias : %s ", services.getName(), services.getAlias()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getFilePathName() {
		String canonicalName = this.getClass().getCanonicalName();
		String simpleName = this.getClass().getSimpleName();
		String path = canonicalName.replace(GenericConstants.DOT + simpleName,
				GenericConstants.DOT + namePath + GenericConstants.DOT + FileNameConstants.SERVICE_NAME);
		//path = path.replace("login.", "");
		return path;
	}
}
