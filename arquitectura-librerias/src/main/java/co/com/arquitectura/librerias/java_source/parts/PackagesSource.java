package co.com.arquitectura.librerias.java_source.parts;

import java.util.ArrayList;
import java.util.List;

import co.com.arquitectura.constantes.java_source.constants.ConstJavaSources;
import co.com.arquitectura.librerias.java_source.interfaces.ISource;

/**
 * Esta clase se encaga de almacenar los paquetes encontrados en el archivo
 * 
 * @author Alejandro Parra
 * @since 24/11/2017
 *
 */
public class PackagesSource implements ISource{
	private List<String> names;

	public PackagesSource() {
		names = new ArrayList<String>();
	}

	public void add(String namePackage) {
		names.add(namePackage);
	}

	public List<String> get() {
		return names;
	}

	public String[] getString() {
		return (String[]) names.toArray();
	}

	public String getName(String name) {
		for (String namess : names) {
			if (namess.toUpperCase().contains(name.toUpperCase())) {
				return namess;
			}
		}
		return "";
	}

	public String getSource() {
		String packages = "";
		for (String name : names) {
			if(name != null)
				packages += "package "+name+ConstJavaSources.PUNTO_COMA+ConstJavaSources.JUMP_LINE;
		}
		return packages;
	}
}
