package co.com.arquitectura.local.interfaces.Consultas;

import java.util.List;

import javax.sql.DataSource;

import co.com.arquitectura.librerias.abstracts.ADTO;

/**
 * esta interfaz se encarga de generar la interfaz de forma local para realizar
 * consultas en la base de datos
 * 
 * @author Alejandro Parra
 * @version 0.0.1
 */
public interface Consultas {
	/**
	 * Se realiza la consulta segun los campos que se encuentran llenos en el
	 * dto y obtiene todos los registros que tienen la misma coincidencia
	 * 
	 * @param dataSource
	 *            {@link DataSource}
	 * @param dto
	 *            T
	 * @return {@link List} < T >
	 * @throws Exception
	 */
	public <T extends ADTO> List<T> obtenerRegistros(DataSource dataSource, T dto) throws Exception;

	/**
	 * Se realiza la consulta segun los campos que se encuentran llenos en el
	 * dti y obtiene el registro que tenga la misma coincidencia
	 * 
	 * @param dataSource {@link DataSource}
	 * @param dto T
	 * @return {@link List} < T >
	 * @throws Exception
	 */
	public <T extends ADTO> T obtenerRegistro(DataSource dataSource, T dto) throws Exception;
	/**
	 * se encarga de actualizar el registro con la informacion suministrada en el edto
	 * @param dataSource {@link DataSource}
	 * @param dto T
	 * @return T dto con la informacion actualizada
	 * @throws Exception
	 */
	public <T extends ADTO> T actualizar(DataSource dataSource, T dto) throws Exception;
	/**
	 * Se encarga de agregar un registro a la base de datos segun la informacion suministrada en el dto
	 * @param dataSource {@link DataSource}
	 * @param dto T 
	 * @return T registro que fue ingresado en la base de datos
	 * @throws Exception
	 */
	public <T extends ADTO> T agregar(DataSource dataSource, T dto) throws Exception;
	/**
	 * se encarga de llamar un procedimiento que no retorna ninguna informacion
	 * @param dataSource {@link DataSource}
	 * @param nombreProcedimiento {@link String}
	 * @param dto T
	 * @throws Exception
	 */
	public <T extends ADTO> void procedimiento(DataSource dataSource, String nombreProcedimiento, T dto)
			throws Exception;

}
