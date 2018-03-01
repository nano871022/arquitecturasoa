package co.com.arquitectura.librerias.abstracts;

 import java.util.Date;

import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;

/**
 * Esta clase indica que el objeto es un abstact para manejo de dto, los cuales
 * tendran algunas propiedades por defecto ademas de extender de el abtract de refleccion
 * 
 * @author Alejandro Parra
 * @since 15/03/2017
 * @version 0.0.1
 */
public class ADTO extends AbstractRefleccion{
	private String llave;
	private String usarioCreacion;
	private Date fechaCreacion;
	private Date fechaUltimaModificacion; 
	
	public String getLlave() {
		return llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getUsarioCreacion() {
		return usarioCreacion;
	}

	public void setUsarioCreacion(String usarioCreacion) {
		this.usarioCreacion = usarioCreacion;
	}
}
