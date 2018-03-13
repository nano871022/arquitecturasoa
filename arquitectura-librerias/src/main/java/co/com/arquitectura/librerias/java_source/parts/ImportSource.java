package co.com.arquitectura.librerias.java_source.parts;

import java.util.ArrayList;
import java.util.List;

import co.com.arquitectura.constantes.java_source.constants.ConstJavaSources;
import co.com.arquitectura.librerias.java_source.interfaces.ISource;

/**
 * Esta clase se encarga de obtener todos los imports encontrados en el archivo
 * java
 * 
 * @author Alejandro Parra
 * @since 24/11/2017
 *
 */
public class ImportSource implements ISource {
	private List<String> imports;

	public ImportSource() {
		imports = new ArrayList<String>();
	}

	public void add(String nameImport) {
		imports.add(nameImport);
	}

	public List<String> get() {
		return imports;
	}

	public String[] getString() {
		return (String[]) imports.toArray();
	}

	public String getName(String name) {
		for (String names : imports) {
			if (names.toUpperCase().contains(name.toUpperCase())) {
				return names;
			}
		}
		return "";
	}

	public String getSource() {
		String importS = "";
		for (String imporT : imports) {
			if (imporT != null && imporT.length() >0 )
				importS += "import " + imporT + ConstJavaSources.PUNTO_COMA + ConstJavaSources.JUMP_LINE;
		}
		return importS;
	}
}
