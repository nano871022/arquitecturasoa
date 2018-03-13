package co.com.arquitectura.soa.dbconnect;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;

public class File<T extends Object> {
	public List<T> read(T obj) throws Exception {
		List<T> lista = new ArrayList<T>();
		String fileName = obj.getClass().getSimpleName();
		Path path = Paths.get(fileName + ".bd");
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// configurar la lectura para retornar la lista con los valores
		return lista;
	}

	public void write(T obj) throws Exception {
		String fileName = obj.getClass().getSimpleName();
		Path path = Paths.get(fileName + ".bd");
		try (BufferedWriter br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE)) {
			br.write(getLine(obj));
			br.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getLine(T obj) throws Exception{
		if(obj instanceof AbstractRefleccion) {
			return ((AbstractRefleccion)obj).toStrings();
		}else {
			throw new Exception("Error en creacion de linea para guardar");
		}
	}
}
