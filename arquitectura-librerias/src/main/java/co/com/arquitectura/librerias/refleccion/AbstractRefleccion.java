package co.com.arquitectura.librerias.refleccion;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringEscapeUtils;

import co.com.arquitectura.librerias.constantes.ConstantesLibreria;

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
	 * pone en el nombre del campo suministrado el valor suministrado
	 * 
	 * @param nombreCampo
	 *            {@link String} nombre de la propiedad de lña clase de la cual se
	 *            desa obtener el valor
	 * @param valor
	 *            {@link Object} recibe un objeto del mismo tipo del campo del cual
	 *            se desea poner el nuevo valor
	 * @throws Exception
	 */
	public final <T extends Object> void set(String nombreCampo, T valor) throws Exception {
		ponerValor(obtenerCampo(nombreCampo), valor);
	}

	/**
	 * obtuene el listado de campos ( {@link Field} ) o propiedades del sistema
	 * 
	 * @return {@link Field} array de campos
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public final <T extends Object> Field[] obtenerListaCampos() throws Exception {
		Class<T> clase = (Class<T>) this.getClass();
		return clase.getFields();
	}

	/**
	 * obtiene el valor asociado al nombre del campo
	 * 
	 * @param nombreCampo
	 *            {@link String} nombre de la propiedad de la clase de la cual se
	 *            desea obtener el valor
	 * @return {@link Object} retorna un objeto del mismo tipo del campo del cual se
	 *         obtuvo el valor
	 * @throws Exception
	 */
	public final <T extends Object> T get(String nombreCampo) throws Exception {
		return obtenerValor(obtenerCampo(nombreCampo));
	}

	/**
	 * se encarga de obtener el Campo {@link Field} apartir del nombre del campo
	 * 
	 * @param nombreCampo
	 *            {@link String} nombre del campo a obtener
	 * @return {@link Field} Campo obtenido
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> Field obtenerCampo(String nombreCampo) throws Exception {
		Class<T> clase = (Class<T>) this.getClass();
		Field campo = clase.getField(nombreCampo);
		return campo;
	}

	/**
	 * este metodo se encarga de obtener el valor de un campo, directamete , tomando
	 * valores de las instancia y de claseF
	 * 
	 * @param campo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> T obtenerValor(Field campo) throws Exception {
		Class<T> clase = (Class<T>) this.getClass();
		T instancia = (T) this;
		return obtenerValor(campo, instancia, clase);
	}

	/**
	 * este metodo se encarga de ponerl el valor indicado en el campo, directamente,
	 * tomando los valores del a instancia de la clase actual
	 * 
	 * @param campo
	 *            {@link Field}
	 * @param valor
	 *            {@link Object} cualquier tipo de dato, este valor sera castiado
	 *            para tratar de que sea compatible con el campo de destino
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T extends Object> void ponerValor(Field campo, Object valor) throws Exception {
		Class<T> clase = (Class<T>) this.getClass();
		T instancia = (T) this;
		ponerValor(campo, instancia, clase, valor);
	}

	/**
	 * este campo se encarga de obtener el valor que tiene almacenado un objeto el
	 * cual es accedido por medio de refleccion
	 * 
	 * @param campo
	 *            {@link Field}
	 * @return <Object> Algun objeto (WildCard)
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T, L extends Object> L obtenerValor(Field campo, T instancia, Class<T> clase) throws Exception {
		L valor = null;
		if (campo.isAccessible()) {
			valor = (L) campo.get(instancia);
		} else {
			Method metodo = clase.getMethod(obtenerGet(campo.getName()));
			if (metodo != null) {
				valor = (L) metodo.invoke(instancia);
			} else {
				campo.setAccessible(true);
				valor = obtenerValor(campo, instancia, clase);
			}
		}
		return valor;
	}

	/**
	 * Este caso se usa solo para obtener un valor suministrando el nombre del campo
	 * y la instancia
	 * 
	 * @param nombreCampo
	 *            {@link String}
	 * @param instancia
	 *            {@link Object} extendido
	 * @return {@link Object} extendido, valor obtenido
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected final <T, L extends Object> L obtenerValor(String nombreCampo, T instancia) throws Exception {
		Class<T> clas = (Class<T>) instancia.getClass();
		Field field = clas.getDeclaredField(nombreCampo);
		return obtenerValor(field, instancia, clas);
	}

	/**
	 * se encarga de agregar un valor directo a la propiedad del objeto o ppor medio
	 * de la funcion set
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
	 * se encarga de poner el prefigo get al nombre del metodo y el nombre del campo
	 * se le agrega la primera letra amayuscula
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
	 * se encarga de tomar un nombre y retornar el mismo valor pero con la primera
	 * letra en mayuscula, para ser usado para metodos setter y getters
	 * 
	 * @param nombre
	 *            {@link String} nombnre a poner camel
	 * @return {@link String} nombre con el camel
	 * @throws Exception
	 */
	private final String obtenerNombreCamel(String nombre) throws Exception {
		char[] nombres = nombre.toCharArray();
		nombres[0] = Character.toUpperCase(nombres[0]);
		return String.valueOf(nombres);
	}

	/**
	 * se encarga de agregar el prefijo set a el nombre del campo en camel, con esto
	 * para crear un metodo set
	 * 
	 * @param nombre
	 *            {@link String} nombre de la propiedad
	 * @return {@link String} retorna el nopmbre del metodo para ser usado por el
	 *         set
	 * @throws Exception
	 */
	private final String obtenerSet(String nombre) throws Exception {
		return ConstantesLibreria.CONSTANTE_SET + obtenerNombreCamel(nombre);
	}

	/**
	 * se encarga de modificar el texto dentro de la variable para codificarla en
	 * codigo html y despues este valor sera devuelto a la variable dentro del
	 * objeto
	 * 
	 * @param campo
	 *            {@link Field}
	 * @param instancia
	 *            {@link Object}
	 * @param clase
	 *            {@link Class}
	 * @throws Exception
	 */
	protected final <T, L extends Object> void fieldCleanHtml(Field campo, T instancia, Class<T> clase)
			throws Exception {
		if (campo.getType() == String.class) {
			String valor = obtenerValor(campo, instancia, clase);
			valor = StringEscapeUtils.escapeHtml4(valor);
			ponerValor(campo, instancia, clase, valor);
		}

	}// end limpiar campos html

	/**
	 * se encarga de modificar el texto dentro de la variable para decodificarla en
	 * codigo html y despues este valor sera devuelto a la variable dentro del
	 * objeto
	 * 
	 * @param campo
	 *            {@link Field}
	 * @param instancia
	 *            {@link Object}
	 * @param clase
	 *            {@link Class}
	 * @throws Exception
	 */
	protected final <T, L extends Object> void fieldHtml(Field campo, T instancia, Class<T> clase) throws Exception {
		if (campo.getType() == String.class) {
			String valor = obtenerValor(campo, instancia, clase);
			valor = StringEscapeUtils.unescapeHtml4(valor);
			ponerValor(campo, instancia, clase, valor);
		}

	}// end limpiar campos html

	/**
	 * Se encarga de limpiar los campos de tipo caracter en un objeto, y que cambia
	 * sus valores y los transforma y los codifica en html.
	 * 
	 * @param instance
	 *            {@link Object} puede o no ser suministrada
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public final <T extends Object> void fieldsCleanHtml(T... instancia) throws Exception {
		T instance = null;
		Class<T> clase = null;
		if (instancia == null || instancia.length == 0) {
			instance = (T) this;
		} else {
			instance = instancia[0];
		}
		clase = (Class<T>) instance.getClass();
		Field[] fields = clase.getFields();
		for (Field field : fields) {
			fieldCleanHtml(field, instance, clase);
		}
	}// end limpiar todos los campos html

	/**
	 * Se encarga de limpiar los campos de tipo caracter en un objeto, y que cambia
	 * sus valores y los transforma y los decodifica en html.
	 * 
	 * @param instance
	 *            {@link Object} puede o no ser suministrada
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public final <T extends Object> void fieldsHtml(T... instancia) throws Exception {
		T instance = null;
		Class<T> clase = null;
		if (instancia == null || instancia.length == 0) {
			instance = (T) this;
		} else {
			instance = instancia[0];
		}
		clase = (Class<T>) instance.getClass();
		Field[] fields = clase.getFields();
		for (Field field : fields) {
			fieldHtml(field, instance, clase);
		}
	}// end limpiar todos los campos html

	private final <T extends Object> String toStringRecord(Class<T> clase) throws Exception {
		String out = "";
		Field[] fields = clase.getDeclaredFields();
		for (Field field : fields) {
			if (out.length() > 0) {
				out += ",";
			}
			out += String.format("%s=%s", field.getName(), obtenerValor(field));
		}
		try {
			if (clase.getSuperclass() != null) {
				out += toStringRecord(clase.getSuperclass());
			}
		} catch (Exception e) {
		}
		if (out.length() > 0) {
			out = "[" + out + "]";
		}

		return out;
	}

	@SuppressWarnings("unchecked")
	public final <T extends Object> String toStrings() {
		String out = "";
		try {
			Class<T> clase = (Class<T>) this.getClass();
			out = toStringRecord(clase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
