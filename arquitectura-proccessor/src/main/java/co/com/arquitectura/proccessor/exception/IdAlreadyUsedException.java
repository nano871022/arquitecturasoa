package co.com.arquitectura.proccessor.exception;

import co.com.arquitectura.proccessor.verifyAnotation.FactoryVerified;

public class IdAlreadyUsedException extends Exception {
	private static final long serialVersionUID = 4409382275915899612L;

	public IdAlreadyUsedException(FactoryVerified exist) {
		super("La clase "+exist.getSimpleNameClass()+" con id "+exist.getId()+" existe por lo cual no se puede agregar a la lista");
	}

}
