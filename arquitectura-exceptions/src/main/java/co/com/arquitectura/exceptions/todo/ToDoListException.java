package co.com.arquitectura.exceptions.todo;

public class ToDoListException extends Exception {
	private static final long serialVersionUID = 4409382275915899612L;

	public ToDoListException(String msn) {
		super(msn);
	}
	public ToDoListException(String msn,Throwable e) {
		super(msn,e);
	}
}
