package co.com.arquitectura.librerias.implement.Services;

/**
 * Almacena la informacion de los serivicios
 * 
 * @author Alejandro Parra
 * @since 22/01/2018
 */
public class ServicePOJO {
	private String name;
	private String alias;
	private String description;
	private Object kind;
	private Object scope;
	private Class<?> classs;
	private String parameters;

	public <M extends Object> ServicePOJO(String name, String alias, String description, Object kind, Object scope,
			Class<M> classs, String parameters) {
		this.name = name;
		this.alias = alias;
		this.description = description;
		this.kind = kind;
		this.scope = scope;
		this.classs = classs;
		this.parameters = parameters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getKind() {
		return kind;
	}

	public void setKind(Object kind) {
		this.kind = kind;
	}

	public Object getScope() {
		return scope;
	}

	public void setScope(Object scope) {
		this.scope = scope;
	}

	public Class<?> getClasss() {
		return classs;
	}

	public void setClasss(Class<?> classs) {
		this.classs = classs;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String[] getParameter() throws ClassNotFoundException {
		return parameters.split(",");
	}

}
