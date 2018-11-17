package co.com.arquitectura.proccessor.implement;

import java.io.IOException;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import co.com.arquitectura.annotation.proccessor.Services;
import co.com.arquitectura.exceptions.proccess.IdAlreadyUsedException;
import co.com.arquitectura.proccessor.abstracts.AbstractProccessorGeneric;
import co.com.arquitectura.proccessor.groupedAnotation.ServicesGrouped;
import co.com.arquitectura.proccessor.verifyAnotation.ServicesVerified;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("co.com.arquitectura.annotation.proccessor.Services")
public class ServiceProccessor extends AbstractProccessorGeneric<ServicesVerified, ServicesGrouped, Services> {

	public ServiceProccessor() {
		super(Services.class);
	}

	@Override
	protected void proccess(RoundEnvironment roundEnv) throws Exception {
		try {
			if (groupClass != null && groupClass.values() != null) {
				for (ServicesGrouped services : groupClass.values()) {
					services.generateSource(processingEnv.getElementUtils(), processingEnv.getFiler());
				}
			}
		} catch (IOException e) {
			error(null, e.getMessage());
		}
		info(null,"fin proccess");
	}

	@SuppressWarnings("unused")
	@Override
	protected Boolean verifiedTypeElement(RoundEnvironment roundEnv) {
		ServicesVerified veryfied = null;
		for (Element method : roundEnv.getElementsAnnotatedWith(annotation)) {
			if (method.getKind() == ElementKind.METHOD) {
				veryfied = proccessAnnotation(method);
			} else {
				error(method, " Solo se deben anotar methodos con @" + annotation.getSimpleName());
			}
		}
		return true;
	}

	@Override
	protected ServicesVerified proccessAnnotation(Element method) {
		info(method, "proccessAnotation "+method.getEnclosingElement().toString());
		ServicesVerified services = null;
		try {
			services = new ServicesVerified(method);
				if (!isValidClass(services)) {
					return null;
				}
			ServicesGrouped group = groupClass.get(services.getCanonicClass());
			if (group == null) {
				String qualifiedGroupName = services.getCanonicClass();
				group = new ServicesGrouped(qualifiedGroupName);
				groupClass.put(qualifiedGroupName, group);
			}
			group.add(services);
		} catch (IllegalArgumentException e) {
			error(method, e.getMessage());
		} catch (IdAlreadyUsedException e) {
			error(method, "Conflicto: La clase " + services.getSimpleNameClass() + " tiene el id duplicado "
					+ services.getId());
		} catch (Exception e) {
			error(method, "Problema:: " + e.getMessage());
		}
		return null;
	}

	@Override
	protected boolean isValidClass(ServicesVerified annotationClass) {
		Element method = annotationClass.getMethod();
		if (!method.getModifiers().contains(Modifier.PUBLIC)) {
			error(method, "El metodo " + annotationClass.getMethod().getSimpleName().toString() + " no es publico");
			return false;
		}
		if (method.getModifiers().contains(Modifier.ABSTRACT)) {
			error(method,
					"El metodo " + annotationClass.getMethod().getSimpleName().toString() + " no debe ser abstracto");
			return false;
		}
		return true;
	}

}
