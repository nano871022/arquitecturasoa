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

import co.com.arquitectura.annotation.proccessor.Immutable;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("co.com.arquitectura.annotation.proccessor.Immutable")
public class ImmutableProccessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		try {
			for (final Element element : roundEnv.getElementsAnnotatedWith(Immutable.class)) {
				if (element instanceof TypeElement) {
					final TypeElement typeElement = (TypeElement) element;
					for (final Element ecloseElement : typeElement.getEnclosedElements()) {
						if (ecloseElement instanceof VariableElement) {
							final VariableElement variableElement = (VariableElement) ecloseElement;
							if (!variableElement.getModifiers().contains(Modifier.FINAL)) {
								processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(
										"La clase '%s' se encuentra anotada como @Immutable, pero el campo '%s' no esta declarado como final",
										typeElement.getSimpleName(), variableElement.getSimpleName()));
							}
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
