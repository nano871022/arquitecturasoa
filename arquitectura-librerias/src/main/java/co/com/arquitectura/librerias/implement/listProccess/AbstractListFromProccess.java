package co.com.arquitectura.librerias.implement.listProccess;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListFromProccess<T extends Object>implements IListFromProccess<T> {
	protected List<T> lista;
	public AbstractListFromProccess() {
		lista = new ArrayList<T>();
		load();
	}
	public abstract void load();
	public final List<T> getList() {
		return lista;
	}

}
