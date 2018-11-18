package co.com.arquitectura.proccessor.impresion;

import java.util.HashMap;
import java.util.Map;

import co.com.arquitectura.proccessor.verifyAnotation.declared.IVerified;

@SuppressWarnings("rawtypes")
public abstract class AbstractPrinter <T extends IVerified> implements IPrinter{
	protected String canonicName;
	protected String nameClass;
	protected String packagesSave;
	protected Map<String,T> items;
	@Override
	public void setCanonicName(String canonicName) {
		this.canonicName = canonicName;
	}
	@Override
	public void setNameClass(String nameClass) {
		this.nameClass = nameClass;
	}
	@Override
	public void setPackagesSave(String packagesSave) {
		this.packagesSave = packagesSave;
	}
	@SuppressWarnings({ "unchecked" })
	@Override
	public void addAllItems(Map items) {
		if(this.items == null) {
			this.items = new HashMap<String,T>();
		}
		this.items.putAll(items);
	}
	
}
