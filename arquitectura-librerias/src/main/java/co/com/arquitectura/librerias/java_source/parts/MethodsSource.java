package co.com.arquitectura.librerias.java_source.parts;

import java.util.ArrayList;
import java.util.List;

import co.com.arquitectura.constantes.java_source.constants.ConstJavaSources;
import co.com.arquitectura.constantes.java_source.constants.Modifier;
import co.com.arquitectura.librerias.java_source.interfaces.ISource;
import co.com.arquitectura.librerias.validacion.Validacion;

/**
 * Esta clase se encarga de carga la informacion de los metodos encontrados en
 * el archivo java
 * 
 * @author Alejandro Parra
 * @since 24/11/2017
 */
public class MethodsSource implements ISource {
	private String name;
	private Modifier modifier;
	private Boolean abstracts;
	private List<ArgumentsSource> arguments;
	private String comments;
	private List<String> annotations;
	private String statements;
	private String returns;
	private List<String> exceptions;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<String> exceptions) {
		this.exceptions = exceptions;
	}

	public void addException(String exception) {
		if (exceptions == null) {
			exceptions = new ArrayList<String>();
		}
		exceptions.add(exception);
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

	public String getStatements() {
		return statements;
	}

	public void setStatements(String statements) {
		this.statements = statements;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	public Boolean getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(Boolean abstracts) {
		this.abstracts = abstracts;
	}

	public List<ArgumentsSource> getArguments() {
		return arguments;
	}

	public void add(ArgumentsSource argument) {
		if (arguments == null) {
			arguments = new ArrayList<ArgumentsSource>();
		}
		arguments.add(argument);
	}

	public void setArguments(List<ArgumentsSource> arguments) {
		this.arguments = arguments;
	}

	public List<String> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}

	public String getSource() {
		if (name == null && returns == null)
			return ConstJavaSources.EMPTY;
		String source = "";
		if (comments != null)
			source += comments;
		if (annotations != null)
			for (String annotation : annotations) {
				source += annotation + ConstJavaSources.JUMP_LINE;
			}
		source += (modifier != null ? modifier.toString().toLowerCase() : Modifier.PUBLIC.toString().toLowerCase())
				+ ConstJavaSources.SPACE;
		source += abstracts ? ConstJavaSources.ABSTRACT.toLowerCase() + ConstJavaSources.SPACE : ConstJavaSources.EMPTY;
		source += returns + ConstJavaSources.SPACE;
		source += name + ConstJavaSources.SPACE;
		source += ConstJavaSources.OPEN_PARENT;
		if (arguments != null)
			for (int i = 0; i < arguments.size(); i++) {
				source += arguments.get(i).getSource();
				source += i > 0 && i < arguments.size() - 1 ? ConstJavaSources.COMA : ConstJavaSources.EMPTY;
			}
		source += ConstJavaSources.CLOSE_PARENT;
		source += ConstJavaSources.OPEN_KEY + ConstJavaSources.JUMP_LINE;
		if (statements != null)
			source += statements + ConstJavaSources.JUMP_LINE;
		try {
			if (Validacion.contarTexto(source, ConstJavaSources.OPEN_KEY) > Validacion.contarTexto(source,
					ConstJavaSources.CLOSE_KEY))
				source += ConstJavaSources.CLOSE_KEY + "//";
		} catch (Exception e) {
			source += ConstJavaSources.CLOSE_KEY + " //" + e.getMessage();
		}
		source += ConstJavaSources.JUMP_LINE;
		return source;
	}
}
