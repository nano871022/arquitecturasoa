package co.com.arquitectura.exceptions.proccess;

public class IdAlreadyUsedException extends Exception {
	private static final long serialVersionUID = 4409382275915899612L;

	public IdAlreadyUsedException(String simpleNameClass,String id) {
		super("La clase "+simpleNameClass+" con id "+id+" existe por lo cual no se puede agregar a la lista");
	}
}
