package co.com.arquitectura.librerias.abstracts;

 import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;

/**
 * Esta clase indica que el objeto es un abstact para manejo de dto, los cuales
 * tendran algunas propiedades por defecto ademas de extender de el abtract de refleccion
 * 
 * @author Alejandro Parra
 * @since 15/03/2017
 * @version 0.0.1
 */
public abstract class ADTO extends AbstractRefleccion{
	private String llave;

	public String getLlave() {
		return llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}
	
}
