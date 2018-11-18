package co.com.arquitectura.proccessor.verifyAnotation.declared;

import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;

import co.com.arquitectura.exceptions.proccess.IdAlreadyUsedException;

public interface IGrouped<T extends IVerified> {
	/**
	 * Se encarga de agregar un objeto a la lista del objeto
	 * 
	 * @param verified {@link Object}
	 */
	public void add(T verified) throws IdAlreadyUsedException;
	/**
	 * Se obtiene el canonical name del objeto
	 * @return {@link String}
	 */
	public String getCanonicName();
	/**
	 * Obtiene el nombre de la clase
	 * @return {@link String}
	 */
	public String getNameClass();
	/**
	 * Se encarga de almacenar los iterms
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T extends IVerified>Map<String,T> getItems();
	/**
	 * usado para retorna el package donde se almacena
	 * @return {@link String}
	 */
	public String getPackageSave();
	/**
	 * Se encarga de crear el archivo con javafuleobjet
	 * @param element 
	 * @param filer
	 * @throws Exception
	 */
	public void generateSource(Elements element,Filer filer)throws Exception;
}
