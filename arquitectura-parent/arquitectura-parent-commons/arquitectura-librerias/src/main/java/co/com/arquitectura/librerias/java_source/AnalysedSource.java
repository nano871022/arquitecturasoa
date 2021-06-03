package co.com.arquitectura.librerias.java_source;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.arquitectura.librerias.java_source.constants.ConstJavaSources;
import co.com.arquitectura.librerias.java_source.parts.ClassSource;
import co.com.arquitectura.librerias.java_source.parts.MethodsSource;
import co.com.arquitectura.librerias.java_source.parts.PropertiesSource;

/**
 * Esta clase se encargad de analizar cada linea que lee el archivo y la procesa
 * para poder crear cada parte de la clase y almacenarla para depues poder
 * usarla
 * 
 * @author Alejandro Parra
 * @since 28/11/2017
 */
public class AnalysedSource extends ProcessSource {
	/**
	 * se encarga de encontrar los comentarios y almacenarlos en temporal para
	 * despues ponerlos en los diferentes tipos o hijos de la clase
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @throws Exception
	 */
	protected boolean findComments(String line) throws Exception {
		String expReg = "([*]|[/*]|[/**]|[//])+([\\D]|[\\d])*";
		line = line.trim();
		if (Pattern.matches(expReg, line)) {
			if (comments == null) {
				comments = ConstJavaSources.EMPTY;
			}
			line += ConstJavaSources.JUMP_LINE;
			Matcher matcher = expReg(line, expReg);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				String result = matcher.group(i);
				comments += result;
				break;
			}
			return true;
		}
		return false;
	}

	/**
	 * Se encarga de encontrar las anotaciones y almacenarlos en temporal para
	 * despues ponerlos en los diferentes tipos o hijos de la clase
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @throws Exception
	 */
	protected boolean findAnnotation(String line) throws Exception {
		line = line.trim();
		String expReg = "[@]+\\D+[(]*\\D*[)]*";
		Matcher matcher = expReg(line, expReg);
		if (annotations == null) {
			annotations = new ArrayList<String>();
		}
		int j = 0;
		for (int i = 0; i < getCountMatch(matcher); i++) {
			String result = matcher.group(i);
			annotations.add(result);
			j++;
		}
		return j > 0;
	}

	/**
	 * Agrega la linea directamente al statement del metodo
	 * 
	 * @param line
	 *            {@link String} linea que se cree statement
	 * @param classS
	 *            {@link List} < {@link ClassSource} > lista de clases encontrads
	 * @throws Exception
	 */
	protected void findStatement(String line, List<ClassSource> classS) throws Exception {
		if ((classS == null || classS.size() <= 0) && line.trim().length() > 0) {
			throw new Exception("El campo lista classS se encuentra vacio.");
		}
		ClassSource clase = classS.get(0);
		if (clase.getMethods().size() > 0) {
			MethodsSource method = clase.getMethods().get(clase.getMethods().size() - 1);
			String old = method.getStatements() != null ? method.getStatements() : ConstJavaSources.EMPTY;
			method.setStatements(old + line.trim() + ConstJavaSources.JUMP_LINE);
		}
	}

	/**
	 * Se encarga de obtener las cabeceras de los metodos
	 * 
	 * @param line
	 *            {@link String} texto de busquedda
	 * @param classS
	 *            {@link List} < {@link ClassSource} > lista de clases encontradas
	 * @throws Exception
	 */
	protected boolean findActions(String line, List<ClassSource> classS) throws Exception {
		String expReg = "(PUBLIC|public|STATIC|static|final|FINAL|private|PRIVATE|protected|PROTECTED)*\\D+[(]+\\D*[)]+\\D*[{]*";
		if (Pattern.matches(expReg, line)) {
			if ((classS == null || classS.size() <= 0) && line.trim().length() > 0) {
				throw new Exception("El campo lista classS se encuentra vacio." + line);
			}
			ClassSource classs = classS.get(0);
			MethodsSource methods = null;
			Matcher matcher = expReg(line, expReg);
			for (int i = 0; i < getCountMatch(matcher); i++) {
				String result = matcher.group(i);
				if (result == null)
					continue;
				methods = new MethodsSource();
				methods.setArguments(getArguments(line));
				methods.setAbstracts(isAbstract(line));
				methods.setModifier(getModifier(result));
				methods.setName(getNameMethod(line));
				methods.setReturns(getReturn(line));
				methods.setExceptions(getExceptions(line));
				methods.setAnnotations(annotations);
				methods.setComments(comments);
				cleanCommentAnnotations();
				classs.addMethods(methods);
			}
			return true;
		}
		return false;
	}

	/**
	 * Se encarga de buscar todas las propiedades de la clase y agregarlas a la
	 * lista de propiedades
	 * 
	 * @param line
	 *            {@link String} texto de busqueda
	 * @param classS
	 *            {@link List} < {@link ClassSource} listado de clases encontradas
	 *            en el archivo procesado
	 * @throws Exception
	 */
	protected boolean findProperties(String line, List<ClassSource> classS) throws Exception {
		String expreg = "(PUBLIC|public|STATIC|static|final|FINAL|private|PRIVATE|protected|PROTECTED])*\\D+[=]*\\D*[;]+";
		if (Pattern.matches(expreg, line)) {
			if ((classS == null || classS.size() <= 0) && line.trim().length() > 0) {
				throw new Exception("findProperties:El campo lista classS se encuentra vacio." + line);
			}
			ClassSource classs = classS.get(0);
			Matcher matcher = expReg(line, expreg);
			PropertiesSource propertie = null;
			boolean add = false;
			for (int i = 0; i < getCountMatch(matcher); i++) {
				String result = matcher.group(i);
				result = result.trim();
				propertie = new PropertiesSource();
				propertie.setKind(getKind(result));
				propertie.setFinals(isFinal(result));
				propertie.setStatics(isStatic(result));
				propertie.setModifier(getModifier(result));
				propertie.setInstancer(getInstancer(result));
				propertie.setName(getNamePropertie(line, propertie.getKind()));
				for (String annotate : annotations)
					propertie.addAnnotation(annotate);
				propertie.setComments(comments);
				String expr = "(return|[\"'=\\t{}()])+";
				if (propertie.getName() == null)
					continue;
				if (propertie.getKind() == null)
					continue;
				if (Pattern.matches(expr, propertie.getName()))
					continue;
				if (Pattern.matches(expr, propertie.getKind()))
					continue;
				classs.addPropertie(propertie);
				cleanCommentAnnotations();
				add = true;
			}
			return add;
		}
		return false;
	}

	/**
	 * Se encarga de validar el nombre de la clase, herencia, implementación y
	 * realizar la configuracion de esta informacion dentro del objeto de clase
	 * 
	 * @param line
	 *            {@link String} linea a analizar
	 * @param classS
	 *            {@link List}< {@link ClassSource} > lista de recursos de clases
	 * @throws Exception
	 */
	protected boolean findClass(String line, List<ClassSource> classS) throws Exception {
		if (line.toUpperCase().contains(ConstJavaSources.CLASS.toUpperCase())) {
			String expreg = "(PUBLIC|public|abstract|ABSTRACT)*\\D*(CLASS|class)+\\D+(EXTENDS|IMPLEMENTS|extends|implements)*\\D*[{]*";
			String result = "";
			ClassSource cs = null;
			if (Pattern.matches(expreg, line)) {
				if (classS == null && line.trim().length() > 0) {
					throw new Exception("El campo lista classS se encuentra vacio.");
				}
				Matcher matcher = expReg(line, expreg);
				for (int i = 0; i < getCountMatch(matcher); i++) {
					result = matcher.group(i);
					cs = new ClassSource();
					// si es abstracta la clase
					cs.setAbstracts(result.toUpperCase().contains(ConstJavaSources.ABSTRACT));
					// el tipo de modificador
					cs.setModifier(getModifier(result));
					// el nombre del a clase
					cs.setName(getNameClass(result));
					// si estiende la casle
					cs.setExtendss(findExtends(result));
					// las implementaciones de la calse
					cs.setImplementss(findImplements(result));
					if (annotations != null)
						for (String annotation : annotations)
							cs.addAnnotatios(annotation);
					cs.setComments(comments);
					cleanCommentAnnotations();
					if (cs.getName() == null)
						break;
					classS.add(cs);
				}
				return true;
			}
		}
		return false;
	}

}
