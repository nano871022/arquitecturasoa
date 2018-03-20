package co.com.arquitectura.exceptions.query;

import java.util.Arrays;

/**
 * esta excepcion es usada por las consultas para retornar un error especifico
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public class QueryException extends Exception{

	private static final long serialVersionUID = 1L;
	private Class<?> obj;
	private String tipo;
	private String[] name;
	public QueryException(String msn,Class<?> objetoBusqueda,String tipo,String... name) {
		super(msn);
		this.obj = objetoBusqueda;
		this.tipo = tipo;
		this.name = name;
	}
	public QueryException(String msn,Class<?> objetoBusqueda,String tipo,Throwable e,String... name) {
		super(msn,e);
		this.obj = objetoBusqueda;
		this.tipo = tipo;
		this.name = name;
	}
	public Class<?> getObj() {
		return obj;
	}
	public void setObj(Class<?> obj) {
		this.obj = obj;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "QueryException [messange="+getMessage()+",obj=" + obj + ", tipo=" + tipo + ", name=" + Arrays.toString(name) + "]";
	}
}
