package co.com.arquitectura.proccessor.verifyAnotation;

import java.io.Writer;
import java.lang.reflect.Modifier;

import javax.annotation.processing.Filer;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import com.squareup.java.JavaWriter;

import co.com.arquitectura.constants.proccessor.FileNameConstants;
import co.com.arquitectura.constants.proccessor.PackageConstants;
import co.com.arquitectura.exceptions.proccess.IdAlreadyUsedException;

public class ServicesGrouped extends AbstractGrouped<ServicesVerified> {

	public ServicesGrouped(String canonicName) {
		super(canonicName, PackageConstants.SERVICE);
	}

	@Override
	public void add(ServicesVerified verified) throws IdAlreadyUsedException {
		ServicesVerified existente = items.get(verified.getId());
		if(existente != null)
			throw new IdAlreadyUsedException(existente.getSimpleNameClass(),existente.getId());
		items.put(verified.getId(), verified);
	}
	

	@Override
	public void generateSource(Elements elementUtils, Filer filer) throws Exception {
		TypeElement superClassName = elementUtils.getTypeElement(canonicName);
		String factoryClassName = superClassName.getSimpleName() +"" ;
		nameClass = factoryClassName;
		int lastDot = canonicName.lastIndexOf(".");
		String name = canonicName.substring(lastDot);
		name = name.replace(".", "");
		name = canonicName.replace(name, packagesSave+"."+FileNameConstants.SERVICE_NAME);
		JavaFileObject jfo = filer.createSourceFile(name);
		Writer writer = jfo.openWriter();
		JavaWriter jw = new JavaWriter(writer);

		// Write package
		PackageElement pkg = elementUtils.getPackageOf(superClassName);
		if (!pkg.isUnnamed()) {
			jw.emitPackage(pkg.getQualifiedName().toString()+"."+packagesSave);
			jw.emitEmptyLine();
		} else {
			jw.emitPackage("");
		}
		jw.emitImports("co.com.arquitectura.librerias.implement.listProccess.AbstractListFromProccess"
				      ,"co.com.arquitectura.librerias.implement.listProccess.IListFromProccess"
				      ,"co.com.arquitectura.librerias.implement.Services.ServicePOJO");
		jw.beginType(factoryClassName, "class", Modifier.PUBLIC, "AbstractListFactory", "IListFactory");
		jw.emitEmptyLine();
		jw.beginMethod("void", "load", Modifier.PUBLIC);
		for (ServicesVerified item : items.values()) {
			jw.emitStatement("lista.add("
					+ "new ServicePOJO(%s,%s,%s,%s,%s,%s))"
					, item.getClase().getSimpleName()
					, item.getId()
					, item.getDescripcion()
					, item.getTipo()
					, item.getAlcance()
					, item.getClase().getQualifiedName().toString());
			jw.emitEmptyLine();
		}
		jw.endMethod();

		jw.endType();

		jw.close();
	}

}
