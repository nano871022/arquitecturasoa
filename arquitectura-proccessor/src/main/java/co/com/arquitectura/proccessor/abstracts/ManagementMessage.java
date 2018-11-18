package co.com.arquitectura.proccessor.abstracts;

import javax.annotation.processing.AbstractProcessor;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

/**
 * Esta clase abstracta se encarga de manejar los mensajes para el processor y
 * no tener que generar un error en compilacion
 * 
 * @author Alejandro Parra
 * @Since 2017/11/14
 *
 */
public abstract class ManagementMessage<T extends Object> extends AbstractProcessor {
	protected final void error(Element e, String msn) {
		if (processingEnv != null && processingEnv.getMessager() != null && e != null)
			processingEnv.getMessager().printMessage(Kind.ERROR, msn, e);
	}

	protected final void error(String msn) {
		processingEnv.getMessager().printMessage(Kind.ERROR, msn);
	}

	protected final void info(Element e, String msg) {
		processingEnv.getMessager().printMessage(Kind.NOTE, msg, e);
	}

	protected final void info(String msg) {
		processingEnv.getMessager().printMessage(Kind.NOTE, msg, null);
	}

	protected abstract boolean isValidClass(T annotationClass);
}
