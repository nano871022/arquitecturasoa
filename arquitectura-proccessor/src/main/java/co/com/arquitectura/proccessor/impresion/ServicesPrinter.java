package co.com.arquitectura.proccessor.impresion;

import java.io.Writer;
import java.util.EnumSet;

import javax.lang.model.element.Modifier;

import javax.annotation.processing.Filer;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import com.squareup.javawriter.JavaWriter;

import co.com.arquitectura.constants.generics.GenericConstants;
import co.com.arquitectura.constants.proccessor.FileNameConstants;
import co.com.arquitectura.proccessor.verifyAnotation.ServicesVerified;

public class ServicesPrinter extends AbstractPrinter<ServicesVerified>{
	
	
	public void generateSource(Elements elementUtils, Filer filer) throws Exception {
		TypeElement superClassName = elementUtils.getTypeElement(canonicName);
		String factoryClassName = superClassName.getSimpleName() + "";
		nameClass = factoryClassName;
		int lastDot = canonicName.lastIndexOf(".");
		String name = canonicName.substring(lastDot);
		name = name.replace(".", "");
		name = canonicName.replace(name, packagesSave + "." + FileNameConstants.SERVICE_NAME);
		JavaFileObject jfo = filer.createSourceFile(name);
		Writer writer = jfo.openWriter();
		JavaWriter jw = new JavaWriter(writer);

		// Write package
		PackageElement pkg = elementUtils.getPackageOf(superClassName);
		if (!pkg.isUnnamed()) {
			jw.emitPackage(pkg.getQualifiedName().toString() + "." + packagesSave);
			jw.emitEmptyLine();
		} else {
			jw.emitPackage("");
		}
		jw.emitImports("co.com.arquitectura.librerias.implement.listProccess.AbstractListFromProccess",
				"co.com.arquitectura.librerias.implement.listProccess.IListFromProccess",
				"co.com.arquitectura.librerias.implement.Services.ServicePOJO",
				"co.com.arquitectura.annotation.proccessor.Services");
		jw.beginType(FileNameConstants.SERVICE_NAME, GenericConstants.CLASS, EnumSet.of(Modifier.PUBLIC),
				"AbstractListFromProccess", "IListFromProccess");
		jw.emitEmptyLine();
		jw.beginMethod(GenericConstants.VOID, "load", EnumSet.of(Modifier.PUBLIC));
		for (ServicesVerified item : items.values()) {
			jw.emitStatement("lista.add("
					+ "new ServicePOJO(\"%s\",\"%s\",\"%s,\",Services.kind.%s,Services.scope.%s,Services.Type.%s,%s.class,\"%s\"))",
					item.getMethod().getSimpleName().toString(), item.getId(), item.getDescripcion(), item.getTipo(),
					item.getAlcance(), item.getType(), item.getClase().getQualifiedName().toString(),
					((ExecutableType) item.getMethod().asType()).getParameterTypes());
			jw.emitEmptyLine();
		}
		jw.endMethod();

		jw.endType();

		jw.close();
	}

}
