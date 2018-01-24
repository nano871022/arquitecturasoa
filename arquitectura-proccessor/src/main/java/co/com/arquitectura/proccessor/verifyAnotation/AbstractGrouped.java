package co.com.arquitectura.proccessor.verifyAnotation;

import java.util.LinkedHashMap;
import java.util.Map;

import co.com.arquitectura.proccessor.verifyAnotation.declared.IGrouped;
import co.com.arquitectura.proccessor.verifyAnotation.declared.IVerified;

public abstract class AbstractGrouped <T extends IVerified>implements IGrouped<T>{
	protected Map<String,T> items;
	protected String canonicName;
	protected String nameClass;
	protected String packagesSave;
	
	public AbstractGrouped(String canonicName,String packageSave) {
		this.canonicName = canonicName;
		this.packagesSave = packageSave;
		items = new LinkedHashMap<String,T>();
	}
	
	@Override
	public String getCanonicName() {
		return canonicName;
	}

	@Override
	public String getNameClass() {
		return nameClass;
	}

}
