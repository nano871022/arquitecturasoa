package co.com.arquitectura.librerias.utilidades;

import java.lang.reflect.Field;

import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;

/**
 * esta clase se encarga de realizar la clonacion de un objeto, se recibe un
 * objeto se copia todos los valores del objeto y se enctrega otro valor
 * 
 * @author Alejandro Parra
 * @since 24/03/2017
 * @version 0.0.1
 *
 */
public final class Clonar<T extends Object> extends AbstractRefleccion {
	private T original;
	/**
	 * se encarga de realizar la copia de un objeto desde metodo estatico
	 * @param original T instancia(objeto) que desea copiar
	 * @return T instancia(objeto) resultante de la copia
	 * @throws Exception
	 */
	public static final <T extends Object> T get(T original) throws Exception {
		Clonar<T> copiar = new Clonar<T>(original);
		return copiar.clonar();
	}
	/**
	 * constructor
	 * @param objeto Instancia(Objeto) el cual se desea copiar
	 */
	public Clonar(T objeto) {
		original = objeto;
	}

	/**
	 * se encarga de realizar la copia de el objeto
	 * 
	 * @return T instancia(Objeto) resultante de la copia
	 * @throws Exception
	 */
	protected final T clonar() throws Exception {
		Class<T> clase = getClase();
		Field[] campos = clase.getFields();
		T instancia = getInstancia();
		copiarCampos(campos, instancia);
		return instancia;
	}

	/**
	 * se encarga de recorrer cada campo y realizar la copia del valor de la
	 * instancia(objeto) original hacia el campo de la instancia(objeto) de
	 * destino
	 * 
	 * @param campos
	 *            {@link Field} lista de campos
	 * @param destino
	 *            T instancia en donde se almacenara los valores copiados
	 * @throws Exception
	 */
	private final void copiarCampos(Field[] campos, T destino) throws Exception {
		for (Field campo : campos) {
			ponerValor(campo, destino, getClase(), obtenerValor(campo, original, getClase()));
		}
	}

	/**
	 * se encargca de obtener la clase apartir de la clase origen
	 * 
	 * @return {@link Class} de tipo T
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private final Class<T> getClase() throws Exception {
		if (original != null) {
			return (Class<T>) original.getClass();
		} else {
			throw new Exception("No puede obtner el class ya que no tiene una clase que clonar");
		}
	}

	/**
	 * se encarga de obtener un nuevo objeto apartir de la clase a clonar
	 * 
	 * @return T instancia o objeto igual al objeto a copiar
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private final T getInstancia() throws Exception {
		return (T) getClass().newInstance();
	}
}
