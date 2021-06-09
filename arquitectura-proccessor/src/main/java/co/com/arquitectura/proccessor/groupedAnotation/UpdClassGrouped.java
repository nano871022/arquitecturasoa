package co.com.arquitectura.proccessor.groupedAnotation;

import java.io.Writer;
import javax.lang.model.element.Modifier;
import java.util.EnumSet;

import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

import com.squareup.javawriter.JavaWriter;

import co.com.arquitectura.constants.generics.GenericConstants;
import co.com.arquitectura.exceptions.proccess.IdAlreadyUsedException;
import co.com.arquitectura.proccessor.verifyAnotation.UpdClassVerified;

public class UpdClassGrouped extends AbstractGrouped<UpdClassVerified> {
	public final static String PATH = "upds";
	public final static String PACKAGE = "co.com.japl.ea.dto.dto."+PATH;
	public final static String LAST_NAME = "UpdDTO";
	public UpdClassGrouped(String canonicName) {
		super(canonicName, PATH);
	}

	@Override
	public void add(UpdClassVerified verified) throws IdAlreadyUsedException {
		UpdClassVerified existente = items.get(verified.getId());
		if (existente != null)
			throw new IdAlreadyUsedException(existente.getSimpleNameClass(), existente.getId());
		items.put(verified.getId(), verified);
	}

	@Override
	public void generateSource(Elements elementUtils, Filer filer) throws Exception {
		TypeElement superClassName = elementUtils.getTypeElement(canonicName);
		String factoryClassName = superClassName.getSimpleName() + "";
		nameClass = factoryClassName;
		int lastDot = canonicName.lastIndexOf(".");
		String name = PACKAGE+canonicName.substring(lastDot).replaceAll("DTO", "")+LAST_NAME;
		createFile(name, filer, elementUtils, superClassName);
	}

	private final void createFile(String name, Filer filer, Elements elementUtils, TypeElement superClassName)
			throws Exception {
		JavaFileObject jfo = filer.createSourceFile(name);
		Writer writer = jfo.openWriter();
		JavaWriter jw = new JavaWriter(writer);
		loadHeader(jw);
		jw.close();
	}

	private final <T extends Object> void loadHeader(JavaWriter jw) throws Exception {
		jw.emitPackage(PACKAGE);
		jw.emitEmptyLine();
		jw.emitImports(
				"java.time.LocalDateTime"
				,"co.com.japl.ea.common.interfaces.IUpdClass"
				,canonicName
				);
		String[] split = canonicName.split("\\.");
		String nombre = split[split.length-1];
		jw.beginType(nombre.replaceAll("DTO", "") + LAST_NAME, GenericConstants.CLASS, EnumSet.of(Modifier.PUBLIC),
				nombre ,"IUpdClass");
		jw.emitEmptyLine();
		jw.emitField("LocalDateTime", "fechaActualizado", EnumSet.of(Modifier.PRIVATE));
		jw.emitField("String", "usuarioActualiza", EnumSet.of(Modifier.PRIVATE));
		jw.beginMethod("LocalDateTime", "getFechaActualizado", EnumSet.of(Modifier.PUBLIC));
		jw.emitStatement("return fechaActualizado");
		jw.emitEmptyLine();
		jw.endMethod();
		jw.beginMethod("String", "getUsuarioActualizo", EnumSet.of(Modifier.PUBLIC));
		jw.emitStatement("return usuarioActualiza");
		jw.emitEmptyLine();
		jw.endMethod();
		jw.beginMethod(GenericConstants.VOID, "setUsuarioActualizo", EnumSet.of(Modifier.PUBLIC),"String","usuario");
		jw.emitStatement("usuarioActualiza = usuario");
		jw.emitEmptyLine();
		jw.endMethod();
		jw.beginMethod(GenericConstants.VOID, "setFechaActualizado", EnumSet.of(Modifier.PUBLIC),"LocalDateTime","fechaActualizado");
		jw.emitStatement("this.fechaActualizado = fechaActualizado");
		jw.emitEmptyLine();
		jw.endMethod();
		jw.endType();
	}

}
