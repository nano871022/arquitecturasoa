package co.com.arquitectura.librerias.implement.Factory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListFactory <T extends Object>implements IListFactory<T> {
	protected List<T> lista;
	public AbstractListFactory() {
		lista = new ArrayList<T>();
		load();
	}
	public abstract void load();
	public final List<T> getList() {
		return lista;
	}

}
