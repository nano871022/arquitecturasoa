package co.com.arquitectura.pojo.abstracts;

import co.com.arquitectura.librerias.abstracts.ADTO;

/**
 * clase para abstraer el uso de parametros, se toman los valores basicos pero
 * para el uso de estos parametros se tendran extenderan apartir de este para
 * que se pueda especializar, ademas extiende del abstract dto con el cual
 * tambien se extiende de el manejo de reflection
 * 
 * @author Alejandro Parra
 * @since 23/03/2017
 * @version 0.0.1
 *
 */
public abstract class AParametros extends ADTO {
	private String descripcion;
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
