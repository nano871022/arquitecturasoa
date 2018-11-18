package co.com.arquitectura.proccessor.impresion;

import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;

import co.com.arquitectura.proccessor.verifyAnotation.declared.IVerified;

public interface IPrinter <T extends IVerified>{

	public void generateSource(Elements elementUtils, Filer filer) throws Exception;
	public void setCanonicName(String canonicName);
	public void setNameClass(String nameClass);
	public void setPackagesSave(String packagesSave);
	public void addAllItems(Map<String,T> items);
}
