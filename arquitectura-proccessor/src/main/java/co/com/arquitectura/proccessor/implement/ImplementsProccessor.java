package co.com.arquitectura.proccessor.implement;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import com.squareup.javawriter.JavaWriter;

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
				JavaFileObject java = processingEnv.getFiler().createSourceFile("co.com.japl.ea.implement.inject.create.ServiceList");
				Writer writer = java.openWriter();
				JavaWriter javaW = new JavaWriter(writer);
				javaW.emitPackage("co.com.japl.ea.implement.inject.create");
				javaW.emitImports("org.pyt.common.common.Log");
				javaW.emitEmptyLine();
				javaW.beginType("ServiceList", "class", EnumSet.of(Modifier.PUBLIC));
				javaW.emitField("Log", "logger",EnumSet.of(Modifier.PRIVATE),"Log.Log(this.getClass())");
				javaW.emitField("ServiceList", "instance", EnumSet.of(Modifier.PRIVATE,Modifier.STATIC), "new ServiceList()");
				javaW.beginConstructor(EnumSet.of(Modifier.PRIVATE));
				javaW.emitStatement("try{ \nvar clazz = org.pyt.common.common.InjectUtil.class");
				javaW.emitStatement("var instance = clazz.getDeclaredMethod(\"instance\").invoke(null)");
				javaW.emitStatement("clazz.getDeclaredMethod(\"add\",Class.class).invoke(instance,ServiceList.class)");
				javaW.emitStatement("}catch(Exception e){logger.logger(e);}");
				javaW.endMethod();
				javaW.beginMethod("Class[]", "list", EnumSet.of(Modifier.PUBLIC));
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
