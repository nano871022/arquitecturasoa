package co.com.arquitectura.librerias.validacion;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import co.com.arquitectura.librerias.objetos.Posicion;

/**
 * Esta clase contiene todas los tipos de validaciones que se pueden realizar
 * sobre una variable
 * 
 * @author Alejandro Parra
 * @since 15/03/2017
 * @version 0.0.1
 */
public final class Validacion {
	/**
	 * realiza la validacion de un texto, valida si es obligatorio, y si la
	 * longitud del texto no sumera el valor de maximaLongitud
	 * 
	 * @param valor
	 *            {@link String} valor a validar
	 * @param obligatorio
	 *            {@link Boolean} si el campo es obligatorio
	 * @param maximaLongitud
	 *            {@link Integer} cantidad maxima de caracteres
	 * @return {@link Boolean} <code>true</code>/<code>false</code>
	 * @throws Exception
	 */
	public final static boolean isTexto(String valor, boolean obligatorio, int maximaLongitud) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? StringUtils.isNotBlank(valor) : true;
		valido &= StringUtils.isNotBlank(valor) ? valor.length() <= maximaLongitud : true;
		return valido;
	}

	/**
	 * se realiza la validacion de in texto, valida si es obligatorio
	 * 
	 * @param valor
	 *            {@link String} valor a validar
	 * @param obligatorio
	 *            {@link Boolean} el el campo es obligatorio
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isTexto(String valor, boolean obligatorio) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? StringUtils.isNotBlank(valor) : true;
		return valido;
	}

	/**
	 * busca si en string o caracter esta contenido dentro de un texto
	 * 
	 * @param valor
	 *            {@link String} Texto en el cual se realiaz la busqueda
	 * @param buscar
	 *            {@link String} valor que se desea buscar en el texto
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isBuscar(String valor, String buscar) throws Exception {
		return valor.indexOf(buscar) >= 0;
	}

	/**
	 * busca un patron dentro de un texto suministrado
	 * 
	 * @param valor
	 *            {@link String} valor en el cual se realiza la busqueda
	 * @param patron
	 *            {@link String} patron con el cual se buscaran coincidencias
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isBuscarCoincidencias(String valor, String patron) throws Exception {
		Pattern pattern = Pattern.compile(patron);
		Matcher cruce = pattern.matcher(valor);
		return cruce.matches();
	}

	/**
	 * se encarga de buscar la coincidencia del patron y retornar una lista de
	 * tipo {@link Posicion}, con los valores de X(Inicio), Y(Fin) y
	 * Descripion(que encontro), lo cual indica las posiciones que encontro
	 * informacion y que patron encontro, para posteriormente obtener esos
	 * valores
	 * 
	 * @param valor
	 *            {@link String} valor en el cual se va a realizar la busqueda
	 * @param patron
	 *            {@link String} patron con el cual se realiza la busqueda
	 * @return {@link List} de < {@link Posicion} > listado con las posiciones
	 *         de donde se encontro la informacion dentro del campo valor
	 * @throws Exception
	 */
	public final static List<Posicion> buscarCoincidenciasPos(String valor, String patron) throws Exception {
		List<Posicion> lista = new ArrayList<Posicion>();
		Pattern pattern = Pattern.compile(patron);
		Matcher cruce = pattern.matcher(valor);
		Posicion posicion = null;
		while (cruce.find()) {
			posicion = new Posicion();
			posicion.setX(cruce.start());
			posicion.setY(cruce.end());
			posicion.setDescripcion(cruce.group());
			lista.add(posicion);
		}
		return lista;
	}

	/**
	 * se encarga de realizar la busqueda en el texto suminsitrado y obtener las
	 * coincidencias encontradas y retornarlas en una lista
	 * 
	 * @param valor
	 *            {@link String} valor donde se realiza la busqueda de
	 *            coincidencias
	 * @param patron
	 *            {@link String} patron con el cual se realiza la busqueda
	 * @return {@link List}< {@link String} > lista de coincidencias encontradas
	 * @throws Exception
	 */
	public final static List<String> buscarCoincidencias(String valor, String patron) throws Exception {
		List<String> lista = new ArrayList<String>();
		Pattern pattern = Pattern.compile(patron);
		Matcher cruce = pattern.matcher(valor);
		while (cruce.find()) {
			lista.add(cruce.group());
		}
		return lista;
	}

	/**
	 * se encarga de remplazar pos un texto o un valor dentro de las
	 * coincidencias encontradas del patron
	 * 
	 * @param valor
	 *            {@link String} valor en el cual se realizara busqueda y
	 *            remplazo
	 * @param patron
	 *            {@link String} patron con el cual se realizara la busqueda
	 * @param nuevo
	 *            {@link String} valor por el cual sera remplazado el patron
	 *            encontrado
	 * @param todo
	 *            {@link Boolean} si es <code>true</code> indica que se
	 *            remplazara en todas las coincidencias, de lo contrario solo
	 *            sera en la primera coincidencia enocntrada
	 * @return
	 * @throws Exception
	 */
	public final static String buscarRemplazar(String valor, String patron, String nuevo, boolean todo) throws Exception {
		Pattern pattern = Pattern.compile(patron);
		Matcher cruce = pattern.matcher(valor);
		String salida = null;
		if (todo) {
			salida = cruce.replaceAll(nuevo);
		} else {
			salida = cruce.replaceFirst(nuevo);
		}
		return salida;
	}
	
	/**
	 * Realiza la busqueda en el texto suministrado y apartir de las
	 * conincidencias sirven para dividir el texto en gruppos y retorna el texto
	 * que no es parte de la concidencia en varios registros
	 * 
	 * @param valor
	 *            {@link String} valor en el cual se realiza la busqueda
	 * @param patron
	 *            {@link String} patron con el cual se realiza la bsuqueda
	 * @return {@link String}[] lista de valores que se encontraron entre las
	 *         coincidencias
	 * @throws Exception
	 */
	public final static String[] obtenerGrupos(String valor, String patron) throws Exception {
		Pattern pattern = Pattern.compile(patron);
		String[] grupos = pattern.split(valor);
		return grupos;
	}

	/**
	 * valida que el valor de tipo integer no este vacio esi es obligatorio
	 * 
	 * @param valor
	 *            {@link Integer} valor a validar
	 * @param obligatorio
	 *            {@link Boolean}
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isInteger(Integer valor, boolean obligatorio) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? valor != null : true;
		return valido;
	}

	/**
	 * valida que el valor de tipo big decimal no se encuentre vacio según la
	 * valor de obligatorio
	 * 
	 * @param valor
	 *            {@link BigDecimal} valor a validar
	 * @param obligatorio
	 *            {@link Boolean}
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isBigDecimal(BigDecimal valor, boolean obligatorio) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? valor != null : true;
		return valido;
	}

	/**
	 * valida que el valor de tipo Date no se encuentre vacio según el valor de
	 * obligatorio
	 * 
	 * @param valor
	 *            {@link Date} valor a validar
	 * @param obligatorio
	 *            {@link Boolean}
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isDate(Date valor, boolean obligatorio) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? valor != null : true;
		return valido;
	}

	/**
	 * valida si el valor es obligatorio, y si el valor esta entre el minimo y
	 * el maximo
	 * 
	 * @param valor
	 *            {@link Integer} valor a validar
	 * @param obligatorio
	 *            {@link Boolean}
	 * @param min
	 *            {@link Integer} valor minimo
	 * @param max
	 *            {@link Integer} valor maximo
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isEntre(Integer valor, boolean obligatorio, int min, int max) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? valor != null : true;
		valido &= valor >= min;
		valido &= valor <= max;
		return valido;
	}

	/**
	 * valida si el valor es obligatorio y si el valor esta entre el minimo y el
	 * maximo
	 * 
	 * @param valor
	 *            {@link BigDecimal} valor a validar
	 * @param obligatorio
	 *            {@link Boolean}
	 * @param min
	 *            {@link BigDecimal} valor minimo
	 * @param max
	 *            {@link BigDecimal} valor maximo
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isEntre(BigDecimal valor, boolean obligatorio, BigDecimal min, BigDecimal max) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? valor != null : true;
		valido &= valor.compareTo(min) >= 0;
		valido &= valor.compareTo(max) <= 0;
		return valido;
	}

	/**
	 * valida si el valor es obligatorio y si el valor esta entre la fecha
	 * minima y maxima
	 * 
	 * @param valor
	 *            {@link Date} valor a validar
	 * @param obligatorio
	 *            {@link Boolean}
	 * @param min
	 *            {@link Date} fecha minima
	 * @param max
	 *            {@link Date} fecha maxima
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isEntre(Date valor, boolean obligatorio, Date min, Date max) throws Exception {
		boolean valido = true;
		valido &= obligatorio ? valor != null : true;
		valido &= valor.compareTo(min) >= 0;
		valido &= valor.compareTo(max) <= 0;
		return valido;
	}

	/**
	 * valida si el valor es obligatorio y si el valor esta entre la fecha
	 * minima y maxima, las fechas son suministradas en cualquier formato en
	 * texto y se suministra el formato en el cual se ponene las fehca
	 * 
	 * @param fecha
	 *            {@link String} fecha a validar
	 * @param obligatorio
	 *            {@link Boolean}
	 * @param fechaMin
	 *            {@link String} fecha minima a comparar
	 * @param fechaMax
	 *            {@link String} fecha maxima a comparar
	 * @param formato
	 *            {@link String} formato en el cual se suministraron las fechas
	 *            {@link SimpleDateFormat}
	 * @return {@link Boolean} <code>true</code> / <code>false</code>
	 * @throws Exception
	 */
	public final static boolean isEntre(String fecha, boolean obligatorio, String fechaMin, String fechaMax, String formato)
			throws Exception {
		boolean valido = true;
		SimpleDateFormat formateo = new SimpleDateFormat(formato);
		Date valor = formateo.parse(fecha);
		Date min = formateo.parse(fechaMin);
		Date max = formateo.parse(fechaMax);
		valido &= obligatorio ? valor != null : true;
		valido &= valor.compareTo(min) >= 0;
		valido &= valor.compareTo(max) <= 0;
		return valido;
	}

}
