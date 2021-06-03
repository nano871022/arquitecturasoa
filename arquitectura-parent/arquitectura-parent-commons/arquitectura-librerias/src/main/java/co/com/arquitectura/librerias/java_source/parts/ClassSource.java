package co.com.arquitectura.librerias.java_source.parts;

import java.util.ArrayList;
import java.util.List;

import co.com.arquitectura.librerias.java_source.constants.ConstJavaSources;
import co.com.arquitectura.librerias.java_source.constants.Modifier;
import co.com.arquitectura.librerias.java_source.interfaces.ISource;
import co.com.arquitectura.librerias.validacion.Validacion;

/**
 * Esta clase se encarga de leer todas las clases en el archivo y las carga
 * 
 * @author Alejandro Parra
 * @since 24/11/2017
 *
 */
public class ClassSource implements ISource {
	private String name;
	private Modifier modifier;
	private Boolean abstracts;
	private List<String> annotations;
	private String extendss;
	private List<String> implementss;
	private List<PropertiesSource> properties;
	private List<MethodsSource> methods;
	private String comments;

	public ClassSource() {
		implementss = new ArrayList<String>();
		annotations = new ArrayList<String>();
		properties = new ArrayList<PropertiesSource>();
		methods = new ArrayList<MethodsSource>();
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void addMethods(MethodsSource method) {
		if (methods == null) {
			methods = new ArrayList<MethodsSource>();
		}
		methods.add(method);
	}

	public void addPropertie(PropertiesSource propertie) {
		if (properties == null) {
			properties = new ArrayList<PropertiesSource>();
		}
		properties.add(propertie);
	}

	public void addImplement(String implement) {
		if (implementss == null) {
			implementss = new ArrayList<String>();
		}
		implementss.add(implement);
	}

	public void addAnnotatios(String annotation) {
		if (annotations == null) {
			annotations = new ArrayList<String>();
		}
		annotations.add(annotation);
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

	public List<String> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<String> annotations) {
		this.annotations = annotations;
	}

	public String getExtendss() {
		return extendss;
	}

	public void setExtendss(String extendss) {
		this.extendss = extendss;
	}

	public List<String> getImplementss() {
		return implementss;
	}

	public void setImplementss(List<String> implementss) {
		this.implementss = implementss;
	}

	public List<PropertiesSource> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertiesSource> properties) {
		this.properties = properties;
	}

	public List<MethodsSource> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodsSource> methods) {
		this.methods = methods;
	}

	public String getSource() {
		if (name == null)
			return ConstJavaSources.EMPTY;
		String clasS = "";
		if (comments != null)
			clasS += comments;
		if (annotations != null)
			for (String annotation : annotations) {
				clasS += annotation + ConstJavaSources.JUMP_LINE;
			}
		clasS += modifier.toString().toLowerCase() + ConstJavaSources.SPACE;
		clasS += abstracts ? "abstract" + ConstJavaSources.SPACE : "";
		clasS += ConstJavaSources.CLASS.toLowerCase() + ConstJavaSources.SPACE;
		clasS += name + ConstJavaSources.SPACE;
		clasS += extendss != null && extendss.length() > 1 ? "extends " + extendss + ConstJavaSources.SPACE : "";
		clasS += implementss.size() > 0 ? "implements" + ConstJavaSources.SPACE : "";
		for (int i = 0; i < implementss.size(); i++) {
			clasS += implementss.get(i);
			clasS += i > 0 && i < implementss.size() - 1 ? ConstJavaSources.COMA : "";
		}
		clasS += ConstJavaSources.OPEN_KEY + ConstJavaSources.JUMP_LINE;
		for (PropertiesSource propertie : properties) {
			clasS += propertie.getSource();
		}
		for (MethodsSource method : methods) {
			clasS += method.getSource();
		}
		try {
			if (Validacion.contarTexto(clasS, ConstJavaSources.OPEN_KEY) > Validacion.contarTexto(clasS,
					ConstJavaSources.CLOSE_KEY))
				clasS += ConstJavaSources.CLOSE_KEY;
		} catch (Exception e) {
			clasS += ConstJavaSources.CLOSE_KEY+"//"+e.getMessage();
		}
		return clasS;
	}

}
