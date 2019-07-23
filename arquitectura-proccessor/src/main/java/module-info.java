
module arquitectura.proccessor {
	exports co.com.arquitectura.proccessor.abstracts;
	exports co.com.arquitectura.proccessor.groupedAnotation;
	exports co.com.arquitectura.proccessor.implement;
	exports co.com.arquitectura.proccessor.impresion;
	exports co.com.arquitectura.proccessor.verifyAnotation;
	exports co.com.arquitectura.proccessor.verifyAnotation.declared;

	requires java.compiler;
	requires javawriter;
	requires arquitectura.constants;
	requires arquitectura.exceptions;
	requires org.apache.commons.lang3;
	requires Arquitectura_Anotaciones;
	requires arquitectura.librerias;
}
