package co.com.arquitectura.proccessor.implement;

import java.io.IOException;
import java.lang.reflect.Modifier;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import co.com.arquitectura.annotation.proccessor.Services;
import co.com.arquitectura.exceptions.proccess.IdAlreadyUsedException;
import co.com.arquitectura.proccessor.abstracts.AbstractProccessorGeneric;
import co.com.arquitectura.proccessor.verifyAnotation.ServicesGrouped;
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
			info(null,"Load procces");
			for(ServicesGrouped services : groupClass.values()) {
				services.generateSource(processingEnv.getElementUtils(), processingEnv.getFiler());
			}
		}catch(IOException e) {
			error(null,e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	@Override
	protected Boolean verifiedTypeElement(RoundEnvironment roundEnv) {
		info(null,"verified");
		ServicesVerified veryfied = null;
		info(null,annotation.getSimpleName());
		for(Element method : roundEnv.getElementsAnnotatedWith(annotation)) {
			info(null," "+method.getSimpleName());
			if(method.getKind() == ElementKind.METHOD) {
				TypeElement typeElement = (TypeElement) method;
				info(null," "+typeElement.getSimpleName());
				veryfied = proccessAnnotation(typeElement);
			}else {
				error(method," Solo se deben anotar methodos con @"+annotation.getSimpleName());
			}
		}
		return true;
	}

	@Override
	protected ServicesVerified proccessAnnotation(TypeElement typeElement) {
		ServicesVerified services = null;
		try {
			 services = new ServicesVerified(typeElement);
			 if(!isValidClass(services)) {
				 return null;
			 }
			ServicesGrouped group = groupClass.get(services.getCanonicClass());
			if(group == null) {
				String qualifiedGroupName = services.getParent().getCanonicalName();
				group = new ServicesGrouped(qualifiedGroupName);
				groupClass.put(qualifiedGroupName,group);
			}
			group.add(services);
		}	catch (IllegalArgumentException e) {
			error(typeElement, e.getMessage());
		} catch (IdAlreadyUsedException e) {
			error(typeElement, "Conflicto: La clase " + services.getSimpleNameClass() + " tiene el id duplicado "
					+ services.getId());
		} catch (Exception e) {
			error(typeElement, "Problema " + e.getMessage());
		}
	return null;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	protected boolean isValidClass(ServicesVerified annotationClass) {
		TypeElement clase = annotationClass.getClase();
		if(!clase.getModifiers().contains(Modifier.PUBLIC)) {
			error(clase,"El metodo "+annotationClass.getSimpleNameClass()+" no es publico");
			return false;
		}
		if(clase.getModifiers().contains(Modifier.ABSTRACT)) {
			error(clase,"El metodo "+annotationClass.getSimpleNameClass()+" no debe ser abstracto");
			return false;
		}
		return true;
	}


	

}
