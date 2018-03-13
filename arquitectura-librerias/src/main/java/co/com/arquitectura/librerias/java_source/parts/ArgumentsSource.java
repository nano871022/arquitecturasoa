package co.com.arquitectura.librerias.java_source.parts;

import java.util.ArrayList;
import java.util.List;

import co.com.arquitectura.constantes.java_source.constants.ConstJavaSources;
import co.com.arquitectura.librerias.java_source.interfaces.ISource;

/**
 * Esta clase se encarga de obtener todos los archmetos de un metodo
 * 
 * @author Alejandro Parra
 * @since 24/11/2017
 *
 */
public class ArgumentsSource implements ISource{
	private String name;
	private String kind;
	private List<String> annotations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public List<String> getAnnotatios() {
		return annotations;
	}

	public void setAnnotatios(List<String> annotation) {
		this.annotations = annotation;
	}
	public void add(String annotation) {
		if(annotations == null) {
			annotations = new ArrayList<String>();
		}
		annotations.add(annotation);
	}

	public String getSource() {
		String source = "";
		if(annotations != null)
		for(String annotation : annotations) {
			source += annotation+ConstJavaSources.SPACE;
		}
		if(kind!=null)
		source += kind+ConstJavaSources.SPACE;
		if(name != null)
		source += name+ConstJavaSources.SPACE;
		return source;
	}

}
