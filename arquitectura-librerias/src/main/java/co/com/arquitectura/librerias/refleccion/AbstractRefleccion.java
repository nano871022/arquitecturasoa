package co.com.arquitectura.librerias.refleccion;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Esta clase es estatica y se encarga de implementar secciones de codigo para
 * utilizacion de la refleccion en el resto de la aplicacion, el uso de esta es
 * poder usar refleccion en cualquier parte de la aplicacion aplicando los
 * metodos que aqui se indican
 * 
 * @author Jose Alejandro Parra Lasso
 * @since Febrero 17 de 2017
 * @version 0.0.2
 */
public class AbstractRefleccion {
	
	/**
	 * se encarga de obtener el Campo {@link Field} apartir del nombre del campo
	 * @param nombreCampo {@link String} nombre del campo a obtener
	 * @return {@link Field} Campo obtenido
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> Field obtenerCampo(String nombreCampo)throws Exception{
		Class<T> clase = (Class<T>)this.getClass();
		Field campo = clase.getField(nombreCampo);
		return campo;
	}
	
	/**
	 * este metodo se encarga de obtener el valor de un campo, directamete , tomando valores de las instancia y de claseF
	 * @param campo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> T obtenerValor(Field campo)throws Exception{
		Class<T> clase = (Class<T>) this.getClass();
		T instancia = (T)this;
		return obtenerValor(campo, instancia, clase);
	}
	/**
	 * este metodo se encarga de ponerl el valor indicado en el campo, directamente, tomando los valores del a instancia de la clase actual
	 * @param campo {@link Field}
	 * @param valor {@link Object} cualquier tipo de dato, este valor sera castiado para tratar de que sea compatible con el campo de destino
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> void ponerValor(Field campo,Object valor)throws Exception{
		Class<T> clase = (Class<T>)this.getClass();
		T instancia = (T) this;
		ponerValor(campo,instancia,clase,valor);
	}
	/**
	 * este campo se encarga de obtener el valor que tiene almacenado un objeto
	 * el cual es accedido por medio de refleccion
	 * 
	 * @param campo
	 *            {@link Field}
	 * @return <Object> Algun objeto (WildCard)
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> T obtenerValor(Field campo, T instancia, Class<T> clase) throws Exception {
		T valor = null;
		if (campo.isAccessible()) {
			valor = (T) campo.get(instancia);
		} else {
			Method metodo = clase.getMethod(obtenerGet(campo.getName()));
			if (metodo != null) {
				valor = (T) metodo.invoke(instancia);
			}else{
				campo.setAccessible(true);
				valor = obtenerValor(campo, instancia, clase);
			}
		}
		return valor;
	}

	/**
	 * se encarga de agregar un valor directo a la propiedad del objeto o ppor
	 * medio de la funcion set
	 * 
	 * @param campo
	 *            {@link Field}
	 * @param instancia
	 *            {@link Object}
	 * @param clase
	 *            {@link Class}
	 * @return {@link Object}
	 * @throws Exception
	 */
	protected final <T extends Object> void ponerValor(Field campo, T instancia, Class<T> clase, Object valor)
			throws Exception {
		if (campo.isAccessible()) {
			campo.set(instancia, valor);
		} else {
			Method metodo = clase.getMethod(obtenerSet(campo.getName()), valor.getClass());
			if (metodo != null) {
				metodo.invoke(instancia, valor);
			} else {
				campo.setAccessible(true);
				ponerValor(campo, instancia, clase, valor);
			}
		}
	}

	/**
	 * se encarga de poner el prefigo get al nombre del metodo y el nombre del
	 * campo se le agrega la primera letra amayuscula
	 * 
	 * @param nombre
	 *            {@link String} Nombre del campo
	 * @return {@link String} nombre del metodo a obtener
	 * @throws Exception
	 */
	private final String obtenerGet(String nombre) throws Exception {
		return ConstantesLibreria.CONSTANTE_GET + obtenerNombreCamel(nombre);
	}

	/**
	 * se encarga de tomar un nombre y retornar el mismo valor pero con la
	 * primera letra en mayuscula, para ser usado para metodos setter y getters
	 * 
	 * @param nombre
	 *            {@link String} nombnre a poner camel
	 * @return {@link String} nombre con el camel
	 * @throws Exception
	 */
	private final String obtenerNombreCamel(String nombre) throws Exception {
		char[] nombres = nombre.toCharArray();
		nombres[0] = Character.toUpperCase(nombres[0]);
		return nombre.toString();
	}

	/**
	 * se encarga de agregar el prefijo set a el nombre del campo en camel, con
	 * esto para crear un metodo set
	 * 
	 * @param nombre
	 *            {@link String} nombre de la propiedad
	 * @return {@link String} retorna el nopmbre del metodo para ser usado por
	 *         el set
	 * @throws Exception
	 */
	private final String obtenerSet(String nombre) throws Exception {
		return ConstantesLibreria.CONSTANTE_SET + obtenerNombreCamel(nombre);
	}
}
