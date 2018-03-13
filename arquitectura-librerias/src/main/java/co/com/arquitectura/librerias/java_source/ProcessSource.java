package co.com.arquitectura.librerias.java_source;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.arquitectura.constantes.java_source.constants.ConstJavaSources;
import co.com.arquitectura.constantes.java_source.constants.Modifier;
import co.com.arquitectura.librerias.java_source.parts.ArgumentsSource;
import co.com.arquitectura.librerias.java_source.parts.ImportSource;
import co.com.arquitectura.librerias.java_source.parts.PackagesSource;

/**
 * Se encarga de tener los metodos que permiten obtener y analizar las lineas
 * segun expresiones regulares
 * 
 * @author Alejandro Parra
 * @since 28/11/2017
 */
public class ProcessSource {
	protected String comments;
	protected List<String> annotations;

	/**
	 * Se encarga de obtener los argumentos del metodo
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link List} < {@link ArgumentsSource} > lista de argumentos del
	 *         metodo
	 */
	protected List<ArgumentsSource> getArguments(String line) {
		List<ArgumentsSource> lista = new ArrayList<ArgumentsSource>();
		String expReg = "([\\D]|[\\d])*";
		if (Pattern.matches(expReg, line)) {
			Matcher matcher = expReg(line, expReg);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				String result = matcher.group(i);
				result = result.replace(ConstJavaSources.OPEN_PARENT, ConstJavaSources.EMPTY);
				result = result.replace(ConstJavaSources.CLOSE_PARENT, ConstJavaSources.EMPTY);
				String[] arguments = result.split(",");
				for (String argument : arguments) {
					lista.add(getAgument(argument));
				}
			}
		}
		return lista;
	}

	/**
	 * Se encarga de procesar cada argumento y generar el objeto de recurso
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link ArgumentsSource} objeto de retorno
	 */
	protected ArgumentsSource getAgument(String line) {
		ArgumentsSource arg = new ArgumentsSource();
		String[] arguments = line.split(ConstJavaSources.SPACE);
		if (arguments.length == 3) {
			if (arguments[0].contains("@")) {
				arg.add(arguments[0]);
			}
			arg.setKind(arguments[1]);
			arg.setName(arguments[2]);
		} else if (arguments.length == 2) {
			arg.setKind(arguments[0]);
			arg.setName(arguments[1]);
		}
		return arg;
	}

	/**
	 * Se encaga de obtener las interfaces dentro de la clase la cual se debe
	 * implementar y retorna una lista con cada una de las interfases encontradas
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link List} < {@link String} > lista de interfases a implementar
	 */
	protected List<String> findImplements(String line) {
		List<String> lista = new ArrayList<String>();
		String expreg = "(IMPLEMENTS|implements)+([\\D]|[\\d])+[{]";
		if (Pattern.matches(expreg, line)) {
			Matcher match = expReg(line, expreg);
			for (int i = 0; i < getCountMatch(match); i++) {
				String result = match.group(i);
				String cleaned = limpiar(ConstJavaSources.IMPLEMENTS, result, "[" + ConstJavaSources.OPEN_KEY + "]");
				String[] some = cleaned.split(ConstJavaSources.COMA);
				for (int j = 0; j < some.length; j++)
					lista.add(some[j]);
			}
		}
		return lista;
	}

	/**
	 * se encarga de buscar la clase de la cual hereda
	 * 
	 * @param line
	 *            {@link String} linea de analisis de la información.
	 * @return {@link String} nombre de la clase la cual extiende
	 */
	@SuppressWarnings("unused")
	protected String findExtends(String line) {
		String matchs = "(EXTENDS|extends)+\\D+(IMPLEMENTS|implements|[{])";
		Matcher match = expReg(line, matchs);
		String end = "";
		for (int i = 0; i < getCountMatch(match); i++) {
			String result = match.group(i);
			return limpiar(ConstJavaSources.EXTENDS, result, ConstJavaSources.IMPLEMENTS.toLowerCase(),
					"[" + ConstJavaSources.OPEN_KEY + "]");
		}
		return ConstJavaSources.EMPTY;
	}

	/**
	 * Se encarga de obtener las excepciones configuradas en el metodo
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link List} < {@link String} > lista de excepciones
	 */
	protected List<String> getExceptions(String line) {
		List<String> exceptions = new ArrayList<String>();
		String expReg = "throws([\\D]|[\\d])+[{]*";
		if (Pattern.matches(expReg, line)) {
			Matcher matcher = expReg(line, expReg);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				String result = matcher.group(i);
				result = result.replace(ConstJavaSources.THROWS.toLowerCase(), ConstJavaSources.EMPTY);
				result = result.replace(ConstJavaSources.PUNTO_COMA, ConstJavaSources.EMPTY);
				String[] exceptionS = result.split(",");
				for (String exception : exceptionS) {
					exceptions.add(exception);
				}
			}
		}
		return exceptions;
	}

	/**
	 * Se encarga de obtener el tipo de retorno del metodo
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link String} tipo de retorno
	 */
	protected String getReturn(String line) {
		String expReg = "(public|PUBLIC|private|PRIVATE|protected|PROTECTED|abstract|ABSTRACT|static|STATIC|final|FINAL)*([\\D]|[\\d])+[(]*";
		Matcher matcher = expReg(line, expReg);
		for (int i = 0; i < getCountMatch(matcher); i++) {
			String result = matcher.group(i);
			Modifier mod = getModifier(result);
			result = limpiar(mod != null ? mod.toString().toLowerCase() : ConstJavaSources.PUBLIC.toLowerCase(), line,
					ConstJavaSources.STATIC.toLowerCase(), ConstJavaSources.ABSTRACT.toLowerCase(),
					ConstJavaSources.FINAL.toLowerCase(), ConstJavaSources.OPEN_KEY);
			String[] split = result.split(ConstJavaSources.SPACE);
			if (split.length != 0 && split.length >= 2) {
				return split[split.length - 2];
			}
		}
		return ConstJavaSources.EMPTY;
	}

	/**
	 * Se encarga de obtener el nombre del metodo que se encuentra procesando
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link String} nombre del metodo
	 */
	protected String getNameMethod(String line) {
		String expReg = "(public|PUBLIC|private|PRIVATE|protected|PROTECTED|abstract|ABSTRACT|static|STATIC|final|FINAL)*([\\D]|[\\d])+[(]+";
		Matcher matcher = expReg(line, expReg);
		for (int i = 0; i < getCountMatch(matcher); i++) {
			String result = matcher.group(i);
			Modifier mod = getModifier(result);
			result = limpiar(mod != null ? mod.toString().toLowerCase() : ConstJavaSources.PUBLIC.toLowerCase(), result,
					ConstJavaSources.STATIC.toLowerCase(), ConstJavaSources.ABSTRACT.toLowerCase(),
					ConstJavaSources.FINAL.toLowerCase(), ConstJavaSources.OPEN_PARENT, ConstJavaSources.OPEN_KEY);
			String[] split = result.split(ConstJavaSources.SPACE);
			if (split.length != 0 && split.length >= 2) {
				return split[split.length - 1];
			}
		}
		return ConstJavaSources.EMPTY;
	}

	/**
	 * Se encarga de obtener el nombre de la propiedad
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @param kind
	 *            {@link String} tipo de la propiedad encontrado
	 * @return {@link String} nombre de la propiedad.
	 */
	@SuppressWarnings("unused")
	protected String getNamePropertie(String line, String kind) {
		String expReg = "(" + kind + ")+([\\D]|[\\d])+[=|;]+";
		Matcher matcher = expReg(line, expReg);
		for (int i = 0; i < getCountMatch(matcher); i++) {
			String result = matcher.group(i);
			result = limpiar(kind, result, ConstJavaSources.EQUAL, ConstJavaSources.PUNTO_COMA);
			return result;
		}
		return ConstJavaSources.EMPTY;
	}

	/**
	 * Se encarga de obtener el nombre de la clase
	 * 
	 * @param line
	 *            {@link String} linea de reoceesamiento
	 * @param mat
	 *            {@link String} procesando
	 * @return {@link String} nombre obtenido
	 */
	@SuppressWarnings("unused")
	protected String getNameClass(String line) {
		String expreg = "(CLASS|class)+([\\D]|[\\d])+(EXTENDS|IMPLEMENTS|extends|implements|[{])*";
		Matcher match = expReg(line, expreg);
		String end = "";
		for (int i = 0; i < getCountMatch(match); i++) {
			String result = match.group(i);
			return (limpiar(ConstJavaSources.CLASS, result, ConstJavaSources.EXTENDS.toLowerCase(),
					ConstJavaSources.IMPLEMENTS.toLowerCase(), "[" + ConstJavaSources.OPEN_KEY + "]"));
		}
		return null;
	}

	/**
	 * Se encargad e buscar el modificador del registro obtenido
	 * 
	 * @param result
	 *            {@link String} liena de lectura del archivo
	 * @return {@link Modifier}
	 */
	protected Modifier getModifier(String result) {
		if (result != null)
			if (result.toUpperCase().contains(ConstJavaSources.PUBLIC)) {
				return (Modifier.PUBLIC);
			} else if (result.toUpperCase().contains(ConstJavaSources.PROTECTED)) {
				return (Modifier.PROTECTED);
			} else if (result.toUpperCase().contains(ConstJavaSources.PRIVATE)) {
				return (Modifier.PRIVATE);
			}
		return null;
	}

	/**
	 * Se encarga de verificar si la propiedad es final
	 * 
	 * @param result
	 *            {@link String} texto de entrada para verificar
	 * @return {@link Boolean}
	 */
	protected Boolean isFinal(String result) {
		String expReg = "(PRIVATE|private|protected|PROTECTED|public|PUBLIC|static|STATIC)*([\\D]|[\\d])*final([\\D]|[\\d])+";
		if (Pattern.matches(expReg, result)) {
			return true;
		}
		return false;
	}

	protected Boolean isAbstract(String line) {
		String expReg = "([\\D]|[\\d])*(abstract|ABSTRACT)+([\\D]|[\\d])*";
		if (Pattern.matches(expReg, line)) {
			return true;
		}
		return false;
	}

	/**
	 * Se encargad e verificar si la propiedad es static
	 * 
	 * @param result
	 *            {@link String} texto de entrada para verificar
	 * @return {@link Boolean}
	 */
	protected Boolean isStatic(String result) {
		String expReg = "(PRIVATE|private|protected|PROTECTED|public|PUBLIC)*([\\D]|[\\d])*static([\\D]|[\\d])+";
		if (Pattern.matches(expReg, result)) {
			return true;
		}
		return false;
	}

	/**
	 * Se encarga de obtener el tipo de la pripiiedad si es una clase, una
	 * interface...
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link String} nombre del tipo
	 */
	@SuppressWarnings("unused")
	protected String getKind(String line) {
		String expReg = "(PRIVATE|private|protected|PROTECTED|public|PUBLIC|static|STATIC|final|FINAL)*([\\D]|[\\d])*[=;]+";
		if (Pattern.matches(expReg, line)) {
			Matcher matcher = expReg(line, expReg);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				String result = matcher.group(i);
				Modifier modifier = getModifier(result);
				String out = limpiar(modifier != null ? modifier.toString() : ConstJavaSources.EMPTY, result,
						ConstJavaSources.EQUAL, ConstJavaSources.PUNTO_COMA);
				String[] outs = out.split(ConstJavaSources.SPACE);
				if (outs != null && outs.length > 0)
					return outs[0];
				return out;
			}
		}
		return ConstJavaSources.EMPTY;
	}

	/**
	 * Se encarga de obtener el instanciador de la propiedad
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @return {@link String} instanciador de l apropiedad
	 */
	@SuppressWarnings("unused")
	protected String getInstancer(String line) {
		String expReg = "(PRIVATE|private|protected|PROTECTED|public|PUBLIC|static|STATIC|final|FINAL)*([\\D]|[\\d])+[=]+([\\D]|[\\d])+;";
		String expReg2 = "=\\D+;";
		if (Pattern.matches(expReg, line)) {
			Matcher matcher = expReg(line, expReg2);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				String result = matcher.group(i);
				return limpiar(ConstJavaSources.EQUAL, result);
			}
		}
		return null;
	}

	/**
	 * Se encarga de buscar packetes en la linea leida y obtener cada paquete y
	 * agregarlo al archivo
	 * 
	 * @param line
	 *            {@link String}
	 */
	protected boolean findPackage(String line, PackagesSource packages) throws Exception {
		if (packages == null) {
			throw new Exception("El campo package se encuentra vacio.");
		}
		String expreg = "(PACKAGE|package)+\\D+;";
		String result = "";
		if (Pattern.matches(expreg, line)) {
			Matcher matcher = expReg(line, expreg);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				result = matcher.group(i);
				result = limpiar(ConstJavaSources.PACKAGE, result, ConstJavaSources.PUNTO_COMA);
				packages.add(result);
			}
			return true;
		}
		return false;
	}

	/**
	 * Se encarga de configurar y ejecutar la expresion registro
	 * 
	 * @param line
	 *            {@link String} liena de busqueda
	 * @param expReg
	 *            {@link String} expresion regular
	 * @return {@link Matcher}
	 */
	protected Matcher expReg(String line, String expReg) {
		Pattern pattern = Pattern.compile(expReg);
		Matcher matcher = pattern.matcher(line);
		if (matcher.find())
			return matcher;
		return null;
	}

	/**
	 * Se encarga de buscar imports en la linea leida y obtener cada imports y
	 * agregarlo al archivo
	 * 
	 * @param line
	 *            {@link String}
	 * @param imports
	 *            {@link ImportSource}
	 */
	protected boolean findImport(String line, ImportSource imports) throws Exception {
		if (imports == null) {
			throw new Exception("El campo imports se encuentra vacio.");
		}
		String expreg = "(IMPORT|import)+([\\D]|[\\d])+;";
		String result = "";
		if (Pattern.matches(expreg, line)) {
			Matcher matcher = expReg(line, expreg);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				result = matcher.group(i);
				result = limpiar(ConstJavaSources.IMPORT, result, ConstJavaSources.PUNTO_COMA);
				imports.add(result);
			}
			return true;
		}
		return false;
	}

	/**
	 * Se encarga de limpiar el resultado encontrado para obtener el valor
	 * 
	 * @param tipo
	 *            {@link String} tipo proveniente de {@linnk ConstJavaSources}
	 * @param i
	 *            {@link Integer}
	 * @param matcher
	 *            {@link Matcher}
	 * @param end
	 *            {@link String} opcional, codigo de finalización defecto ';'
	 * @return {@link String}
	 */
	protected String limpiar(String tipo, String line, String... end) {
		line = line.trim();
		String last = "";
		for (int j = 0; j < end.length; j++) {
			last += (j > 0 ? "|" : ConstJavaSources.EMPTY);
			last += end[j].length() == 1 ? "[" : ConstJavaSources.EMPTY;
			last += end[j];
			last += end[j].length() == 1 ? "]" : ConstJavaSources.EMPTY;
		}
		last = last.length() == 1 ? "[" + last + "]" : last;
		int p = -1;
		if (tipo.length() > 0 && line.toUpperCase().contains(tipo.toUpperCase())) {
			p = line.toUpperCase().indexOf(tipo.toUpperCase());
			tipo = line.substring(p, tipo.length());
		}
		tipo = tipo.length() == 1 ? "[" + tipo + "]" : tipo;
		tipo = tipo.trim();
		last = last.trim();
		String expReg = "(" + tipo + "|" + last + ")+";
		String result = line.replaceAll(expReg, ConstJavaSources.EMPTY);
		return result.trim();
	}

	/**
	 * se encarga de limpiar la lista de anotaciones y los comentarios en
	 * almacenamiento
	 */
	protected void cleanCommentAnnotations() {
		if (annotations != null) {
			annotations.clear();
		}
		comments = null;
	}

	/**
	 * Usado para obtener el numero de grupos encontrados
	 * 
	 * @param matcher
	 *            {@link Matcher}
	 * @return
	 */
	protected int getCountMatch(Matcher matcher) {
		if (matcher == null)
			return 0;
		int i = matcher.groupCount();
		i = i > 0 ? i : 1;
		return i;
	}
}
