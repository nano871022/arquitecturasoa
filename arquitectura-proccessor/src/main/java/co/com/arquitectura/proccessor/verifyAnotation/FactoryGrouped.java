package co.com.arquitectura.proccessor.verifyAnotation;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Modifier;

import javax.annotation.processing.Filer;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import com.squareup.java.JavaWriter;

import co.com.arquitectura.annotation.proccessor.Fabrica;
import co.com.arquitectura.proccessor.exception.IdAlreadyUsedException;
import co.com.arquitectura.proccessor.verifyAnotation.declared.IGrouped;

/**
 * Se encarga de agrupar las clases anotadas con @{@link Fabrica}
 * 
 * @author Alejandro Parra
 * @since 2017/11/14
 */
public class FactoryGrouped extends AbstractGrouped<FactoryVerified> implements IGrouped<FactoryVerified>{

	public FactoryGrouped(String canonicName) {
		super(canonicName,"FactoryPackage");
	}

	public void add(FactoryVerified factory) throws IdAlreadyUsedException {
		FactoryVerified existente = items.get(factory.getId());
		if (existente != null) {
			throw new IdAlreadyUsedException(existente);
		}
		items.put(factory.getId(), factory);

	}

	public void generateSource(Elements elementUtils, Filer filer) throws IOException {
		TypeElement superClassName = elementUtils.getTypeElement(canonicName);
		String factoryClassName = superClassName.getSimpleName() +"" ;
		nameClass = factoryClassName;
		int lastDot = canonicName.lastIndexOf(".");
		String name = canonicName.substring(lastDot);
		name = name.replace(".", "");
		name = canonicName.replace(name, packagesSave+"."+name);
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
		jw.emitImports("co.com.arquitectura.librerias.implement.Factory.AbstractListFactory"
				      ,"co.com.arquitectura.librerias.implement.Factory.IListFactory");
		jw.beginType(factoryClassName, "class", Modifier.PUBLIC, "AbstractListFactory", "IListFactory");
		jw.emitEmptyLine();
		jw.beginMethod("void", "load", Modifier.PUBLIC);
		for (FactoryVerified item : items.values()) {
			jw.emitStatement("lista.add(new %s())", item.getClase().getQualifiedName().toString());
			jw.emitEmptyLine();
		}
		jw.endMethod();

		jw.endType();

		jw.close();
	}
}
