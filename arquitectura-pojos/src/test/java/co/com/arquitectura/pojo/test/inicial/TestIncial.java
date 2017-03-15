package co.com.arquitectura.pojo.test.inicial;

import co.com.arquitectura.librerias.validacion.Validacion;

public class TestIncial {

	public static void main(String[] args) {
		try {
			
			String texto = "pepito";
			String buscar = "a";
			System.out.println("Resultado de busqueda: " + (Validacion.isBuscar(texto, buscar) ? "Encontrado" : "Fallo"));
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
