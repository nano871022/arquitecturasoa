package co.com.arquitectura.proccessor.implement;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

import co.com.arquitectura.annotation.proccessor.Constantes;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("co.com.arquitectura.annotation.proccessor.Constantes")
public class ConstantsProccessor extends AbstractProcessor {
	private void message(String msn, String nameClase, String nameField) {
		processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(msn, nameClase, nameField));
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			for (final Element element : roundEnv.getElementsAnnotatedWith(Constantes.class)) {
				if (element instanceof TypeElement) {
					final TypeElement typeElement = (TypeElement) element;
					for (final Element ecloseElement : typeElement.getEnclosedElements()) {
						if (ecloseElement instanceof VariableElement) {
							final VariableElement variableElement = (VariableElement) ecloseElement;
							if (!variableElement.getModifiers().contains(Modifier.STATIC))
								message("La clase '%s' se encuentra anotada como @Constantes, pero el campo '%s' no esta declarado como STATIC",
										ecloseElement.getSimpleName().toString(),
										variableElement.getSimpleName().toString());

							if (!variableElement.getModifiers().contains(Modifier.PUBLIC))
								message("La clase '%s' se encuentra anotada como @Constantes, pero el campo '%s' no esta declarado como PUBLIC",
										ecloseElement.getSimpleName().toString(),
										variableElement.getSimpleName().toString());
							if (!variableElement.getModifiers().contains(Modifier.FINAL))
								message("La clase '%s' se encuentra anotada como @Constantes, pero el campo '%s' no esta declarado como FINAL",
										ecloseElement.getSimpleName().toString(),
										variableElement.getSimpleName().toString());


						}
					}

				}
			}
		} catch (Exception e) {
			processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
		} finally {
		}
		return true;
	}

}
