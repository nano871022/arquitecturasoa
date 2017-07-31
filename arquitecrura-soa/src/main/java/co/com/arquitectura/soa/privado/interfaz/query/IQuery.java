package co.com.arquitectura.soa.privado.interfaz.query;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import co.com.arquitectura.librerias.abstracts.ADTO;
/**
 * se encarga de manejar las consultas a la base de datos, se maneja por interfaz, para realizar conexion a las
 * diferentes librerias que se pueden usaro como busquedas directas o por jpa o eclipse link ...
 * @author Alejandro Parra
 * @since 31/07/2017
 */
@Local
public interface IQuery {
	/**
	 * se encarga de seleccionar uno o varios registros
	 * @param object {@link ADTO} extends
	 * @param name {@link String} nombre de conexion (opcional)
	 * @return {@link List} {@link ADTO} extends
	 * @throws Exception
	 */
	public <T extends ADTO> List<T> select(T object,String... name)throws Exception;
	/**
	 * se encarga de buscar segun el objeto suministrado y entrega un numero (usualmente usado pa|ra select count(1) from XXX)
	 * @param object {@link ADTO} extends
     * @param name {@link String} nombre de conexion (opcional)	 * 
	 * @return {@link Integer}
	 * @throws Exception
	 */
	public <T extends ADTO> Integer selectCount(T object,String... name) throws Exception;
	/**
	 * se encarga de actualziar un registro en la base de datos
	 * @param object {@link ADTO} extends
	 * @param name {@link String} nombre de conexion (opcional)
	 * @throws Exception
	 */
	public <T extends ADTO> void update(T object,String... name) throws Exception;
	/**
	 * se encarga de eliminar un registro de la base de datos
	 * @param object {@link ADTO} extends
	 * @param name {@link String} nombre de conexion (opcional)
	 * @throws Exception
	 */
	public <T extends ADTO> void delete(T object,String... name) throws Exception;
	/**
	 * se encarga de ingresar un registro a la base de datos
	 * @param object {@link ADTO} extends
	 * @param name {@link String} nombre de conexion (opcional)
	 * @throws Exception
	 */
	public <T extends ADTO> void insert(T object,String... name) throws Exception;
	/**
	 * Se encarga de llamar a los procedimientos almacenados y retornar un valor de definido
	 * @param name {@link String} nombre del procedimieto(sin paquete)
	 * @param mapaInOut {@link Map} < {@link String} , {@link Object} > mapa con los nombres ordenados de los paramentros de entrada y salida del procedimiento
	 * @param packageName {@link String} nombre del paquete donde se encuentra el procedimiento, opcional.
	 * @return {@link Object} objeto no definido de retornos
	 * @throws Exception
	 */
	public <T extends Object> T procedure(String name, Map<String,Object> mapaInOut,String... packageName)throws Exception; 
}
