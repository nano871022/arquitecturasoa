package co.com.arquitectura.DConnect.query;

import java.util.List;

import java.util.Map;

import javax.ejb.Stateless;

import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.librerias.abstracts.ADTO;
/**
 * se encarga de relizar el mock para simular el uso de base de datos, es una prueba de conexion
 * @author Alejandro Parra
 * @since 30/07/2017
 *
 */
@Stateless
public class queryMock implements IQuery {

	@Override
	public <T extends ADTO> List<T> select(T object, String... name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ADTO> Integer selectCount(T object, String... name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ADTO> void update(T object, String... name) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends ADTO> void delete(T object, String... name) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends ADTO> void insert(T object, String... name) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T procedure(String name, Map<String, Object> mapaInOut, String... packageName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
