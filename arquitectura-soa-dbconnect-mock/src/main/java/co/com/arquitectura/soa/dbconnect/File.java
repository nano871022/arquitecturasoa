package co.com.arquitectura.soa.dbconnect;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import co.com.arquitectura.librerias.refleccion.AbstractRefleccion;

public class File<T extends Object> {
	@SuppressWarnings("unchecked")
	public List<T> read(T obj) throws Exception {
		List<T> lista = new ArrayList<T>();
		String fileName = obj.getClass().getSimpleName();
		Path path = Paths.get(fileName + ".bd");
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach((s) -> {
				try {
					T newObj = (T) obj.getClass().newInstance();
					String clean = s;
					clean = clean.replace("[[", "");
					clean = clean.replace("]]", "");
					String[] splits = clean.split("\\|\\|");
					for (String split : splits) {
						String[] valuepair = split.split("::");
						if (valuepair.length == 2)
							try {
								((AbstractRefleccion) newObj).set(valuepair[0], valuepair[1]);
							} catch (NoSuchMethodException e) {
								System.out.println("No se encontro metodo::"+e.getMessage());
							}
					}//end for
					System.out.println("loads::" + ((AbstractRefleccion) newObj).toStrings());
					lista.add(newObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			stream.close();
		} catch (NoSuchFileException e) {
			throw new Exception("No se encontro el archivo " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void write(List<T> lobj) throws Exception {
		T objt = lobj.get(0);
		String fileName = objt.getClass().getSimpleName();
		Path path = Paths.get(fileName + ".bd");
		System.out.println(path.getFileName() + " - " + path.getFileSystem());
		try (BufferedWriter br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE)) {
			for (T obj : lobj) {
				br.write(getLine(obj));
				br.newLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getLine(T obj) throws Exception {
		if (obj instanceof AbstractRefleccion) {
			return ((AbstractRefleccion) obj).toStrings();
		} else {
			throw new Exception("Error en creacion de linea para guardar");
		}
	}
}
