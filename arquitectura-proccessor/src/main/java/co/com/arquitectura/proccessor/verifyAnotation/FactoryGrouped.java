package co.com.arquitectura.proccessor.verifyAnotation;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import com.squareup.java.JavaWriter;

import co.com.arquitectura.annotation.proccessor.Fabrica;
import co.com.arquitectura.proccessor.exception.IdAlreadyUsedException;

/**
 * Se encarga de agrupar las clases anotadas con @{@link Fabrica}
 * 
 * @author Alejandro Parra
 * @since 2017/11/14
 */
public class FactoryGrouped {
	private String canonicName;
	private Map<String, FactoryVerified> items;
	private static final String SUFFIX = "Factory";
	private String nameClase;

	public FactoryGrouped(String canonicName) {
		this.canonicName = canonicName;
		items = new LinkedHashMap<String, FactoryVerified>();
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
		String factoryClassName = superClassName.getSimpleName() + SUFFIX;
		nameClase = factoryClassName;
		JavaFileObject jfo = filer.createSourceFile(canonicName + SUFFIX);
		Writer writer = jfo.openWriter();
		JavaWriter jw = new JavaWriter(writer);

		// Write package
		PackageElement pkg = elementUtils.getPackageOf(superClassName);
		if (!pkg.isUnnamed()) {
			jw.emitPackage(pkg.getQualifiedName().toString());
			jw.emitEmptyLine();
		} else {
			jw.emitPackage("");
		}

		jw.beginType(factoryClassName, "class", Modifier.PUBLIC);
		jw.emitEmptyLine();
		jw.beginMethod(canonicName, "create", Modifier.PUBLIC, "String", "id");

		jw.beginControlFlow("if (id == null)");
		jw.emitStatement("throw new IllegalArgumentException(\"id is null!\")");
		jw.endControlFlow();

		for (FactoryVerified item : items.values()) {
			jw.beginControlFlow("if (\"" + item.getId() + "\".equals(id))");
			jw.emitStatement("return new %s()", item.getClase().getQualifiedName().toString());
			jw.endControlFlow();
			jw.emitEmptyLine();
		}

		jw.emitStatement("throw new IllegalArgumentException(\"Unknown id = \" + id)");
		jw.endMethod();

		jw.endType();

		jw.close();
	}

	public String getCanonicaName() {
		return canonicName + SUFFIX;
	}
	public String getNameClass() {
		return nameClase;
	}
}
