package co.com.arquitectura.librerias.java_source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import co.com.arquitectura.librerias.java_source.interfaces.ISource;
import co.com.arquitectura.librerias.java_source.parts.ClassSource;
import co.com.arquitectura.librerias.java_source.parts.ImportSource;
import co.com.arquitectura.librerias.java_source.parts.PackagesSource;

/**
 * Se encarga de leer un archivo de java y procesarlo y retornar el codigo
 * organizado
 * 
 * @author Alejandro Parra
 * @since 24/11/2017
 */
public class JavaSources extends AnalysedSource implements ISource {
	private PackagesSource packages;
	private ImportSource imports;
	private List<ClassSource> classs;

	public JavaSources() {
		packages = new PackagesSource();
		imports = new ImportSource();
		classs = new ArrayList<ClassSource>();
	}

	/**
	 * Se encarga de llere el archivo indicado y los almacena correctamente en las
	 * diferentes partes de la calse
	 * 
	 * @param sourceFile
	 *            {@link String} ruta y nombre del archivo
	 * @throws Exception
	 */
	public void readFile(String sourceFile) throws Exception {
		File file = new File(sourceFile);
		if (file.exists()) {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				processLine(line);
			}
			br.close();
			fr.close();
		} else {
			throw new Exception("El archivo (" + sourceFile + ")no existe ");
		}
	}

	/**
	 * Se encarga de realizar todas las llamadas para procesar la linea agregada
	 * 
	 * @param line
	 *            {@link String} texto obtenido del archivo
	 * @throws Exception
	 */
	private void processLine(String line) throws Exception {
		if (line.trim().length() <= 0) {
			return;
		}
		if (!findComments(line))
			if (!findAnnotation(line))
				if (!findPackage(line, packages))
					if (!findImport(line, imports))
						if (!findClass(line, classs))
							if (!findProperties(line, classs))
								if (!findActions(line, classs))
									findStatement(line, classs);
	}

	public PackagesSource getPackages() {
		return packages;
	}

	public ImportSource getImports() {
		return imports;
	}

	public List<ClassSource> getClasss() {
		return classs;
	}

	public String getSource() {
		String source = "";
		source += packages.getSource();
		source += imports.getSource();
		for (ClassSource clasS : classs) {
			source += clasS.getSource();
		}
		return source;
	}
}
