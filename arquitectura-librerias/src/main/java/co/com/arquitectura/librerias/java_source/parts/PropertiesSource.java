package co.com.arquitectura.librerias.java_source.parts;

import java.util.ArrayList;
import java.util.List;

import co.com.arquitectura.constantes.java_source.constants.ConstJavaSources;
import co.com.arquitectura.constantes.java_source.constants.Modifier;
import co.com.arquitectura.librerias.java_source.interfaces.ISource;

/**
 * Esta clase es usada para almacenar cada propiedades de la clase
 * 
 * @author Alejandro Parra
 * @since 24/11/2017
 */
public class PropertiesSource implements ISource {
	private String name;
	private String kind;
	private List<String> annotations;
	private String comments;
	private Modifier modifier;
	private Boolean finals;
	private Boolean statics;
	private String instancer;

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<String> annotation) {
		this.annotations = annotation;
	}

	public void addAnnotation(String annotation) {
		if (annotations == null)
			annotations = new ArrayList<String>();
		annotations.add(annotation);
	}

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	public String getInstancer() {
		return instancer;
	}

	public void setInstancer(String instancer) {
		this.instancer = instancer;
	}

	public Boolean getFinals() {
		return finals;
	}

	public void setFinals(Boolean finals) {
		this.finals = finals;
	}

	public Boolean getStatics() {
		return statics;
	}

	public void setStatics(Boolean statics) {
		this.statics = statics;
	}

	public String getSource() {
		String source = "";
		if (comments != null)
			source += comments;
		if (annotations != null)
			for (String annotation : annotations) {
				source += annotation + ConstJavaSources.JUMP_LINE;
			}

		source += (modifier != null ? modifier.toString().toLowerCase() : ConstJavaSources.PUBLIC.toLowerCase())
				+ ConstJavaSources.SPACE;
		source += statics ? ConstJavaSources.STATIC.toLowerCase() + ConstJavaSources.SPACE : ConstJavaSources.EMPTY;
		source += finals ? ConstJavaSources.FINAL.toLowerCase() + ConstJavaSources.SPACE : ConstJavaSources.EMPTY;
		source += kind + ConstJavaSources.SPACE;
		source += name + ConstJavaSources.SPACE;
		if (instancer != null)
			source += instancer.length() > 1 ? ConstJavaSources.EQUAL + instancer : ConstJavaSources.EMPTY;
		source += ConstJavaSources.PUNTO_COMA + ConstJavaSources.JUMP_LINE;
		return source;
	}

}
