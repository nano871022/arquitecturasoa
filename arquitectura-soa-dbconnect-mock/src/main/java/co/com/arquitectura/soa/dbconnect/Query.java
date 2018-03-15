package co.com.arquitectura.soa.dbconnect;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import co.com.arquitectura.ejb.query.IQuery;
import co.com.arquitectura.librerias.abstracts.ADTO;
import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;

/**
 * se encarga de relizar el mock para simular el uso de base de datos, es una
 * prueba de conexion
 * 
 * @author Alejandro Parra
 * @since 30/07/2017
 *
 */
@Stateless
public class Query implements IQuery {
	private Logger log = Logger.getLogger(this.getClass());
	public <T extends ADTO> List<T> select(T object, String... name) throws Exception {
		List<T> listaout = null;
		File<T> file = new File<T>();
		List<T> lista = file.read(object);
		for (T obj : lista) {
			if (((AbstractRefleccion) obj).isEqualFillField(object, obj)) {
				if (listaout == null) {
					listaout = new ArrayList<T>();
				}
				listaout.add(obj);
			}
		}
		return listaout;
	}

	public <T extends ADTO> Integer selectCount(T object, String... name) throws Exception {
		return select(object).size();
	}

	public <T extends ADTO> void update(T object, String... name) throws Exception {
		File<T> file = new File<T>();
		boolean bl = false; 
		List<T> lista = file.read(object);
		for (int i = 0 ; i < lista.size();i++) {
			T obj = lista.get(i);
			log.info(((ADTO) obj).getLlave() +"=="+ ((ADTO)object).getLlave());
			if (((ADTO) obj).getLlave() == ((ADTO)object).getLlave()) {
				lista.set(i,object);
				bl = true;
			}
		}
		if (bl)
			file.write(lista);
		else
			throw new Exception("No se logro actualizar el registro.");
	}

	public <T extends ADTO> void delete(T object, String... name) throws Exception {
		File<T> file = new File<T>();
		boolean bl = false;
		List<T> lista = file.read(object);
		for (T obj : lista) {
			if (((AbstractRefleccion) obj).isEqualFillField(object, obj)) {
				lista.remove(obj);
				bl = true;
			}
		}
		if (bl)
			file.write(lista);
		else
			throw new Exception("No se logro eliminar el registro.");
	}

	public <T extends ADTO> void insert(T object, String... name) throws Exception {
		File<T> file = new File<T>();
		List<T> lista = file.read(object);
		((ADTO)object).setLlave(new DecimalFormat("000000000").format(lista.size()));
		lista.add(object);
		file.write(lista);
	}

	public <T> T procedure(String name, Map<String, Object> mapaInOut, String... packageName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
