package co.com.arquitectura.proccessor.implement;

import java.io.Writer;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import com.squareup.java.JavaWriter;

import co.com.arquitectura.annotation.proccessor.Implements;
import co.com.arquitectura.proccessor.abstracts.ManagementMessage;

@SupportedSourceVersion(SourceVersion.RELEASE_16)
@SupportedAnnotationTypes("co.com.arquitectura.annotation.proccessor.Implements")
public class ImplementsProccessor extends ManagementMessage<Implements> {

	@Override
	protected boolean isValidClass(Implements annotationClass) {

		try {
			processingEnv.getMessager().printMessage(Kind.ERROR, "Iniciando validacion");
			Path path = Paths.get("text2.txt");

//			var vv = enviroment.getElementsAnnotatedWith(Implements.class).stream()
//				      .map(value->value.toString())
//				      .collect(Collectors.toList());
//			Files.write(path, annotation.stream().map(value->value.toString()).toList());
			Files.write(path, annotationClass.toString().getBytes());
		} catch (Exception e) {
			processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage());
		}
		return false;
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotation, RoundEnvironment enviroment) {
		try {
			var list = enviroment.getRootElements().stream()
					.filter(value -> value.getAnnotation(Implements.class) != null)
					.map(value -> value.toString()+".class")
					.collect(Collectors.toList());
			if (list != null && list.size() > 0) {
				JavaFileObject java = processingEnv.getFiler().createSourceFile("co.com.japl.ea.service.create.ServiceList");
				Writer writer = java.openWriter();
				JavaWriter javaW = new JavaWriter(writer);
				javaW.emitPackage("co.com.japl.ea.service.create");
				javaW.emitEmptyLine();
				javaW.beginType("ServiceList", "class", Modifier.PUBLIC);
				javaW.beginMethod("Class[]", "list", Modifier.PUBLIC);
				javaW.emitStatement("Class<?>[] clazz = %s",list.toString().replace("[", "{").replace("]", "}"));
				javaW.emitStatement("return clazz");
				javaW.endMethod();
				javaW.endType();
				javaW.close();
			}
		} catch (Exception e) {
			processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage());
		}
		return false;
	}

}
