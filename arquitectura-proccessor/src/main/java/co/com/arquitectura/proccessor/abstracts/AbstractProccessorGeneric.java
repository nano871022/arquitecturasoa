package co.com.arquitectura.proccessor.abstracts;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import co.com.arquitectura.proccessor.verifyAnotation.declared.IGrouped;
import co.com.arquitectura.proccessor.verifyAnotation.declared.IVerified;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public abstract class AbstractProccessorGeneric<S extends IVerified, T extends IGrouped<S>, N extends Annotation>
		extends ManagementMessage<S> {
	protected Class<N> annotation;
	protected Map<String, T> groupClass = new LinkedHashMap<String, T>();

	public AbstractProccessorGeneric(Class<N> annotation) {
		this.annotation = annotation;
	}
	/**
	 * Se ejecuta automatico por el procesador 
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (!verifiedTypeElement(roundEnv))
			return false;
		try {
			proccess(roundEnv);
		} catch (Exception e) {
			error(null, e.getMessage());
		}
		groupClass.clear();
		return false;
	}

	/**
	 * Se encarga de procesar cada anotacion inscrita
	 * 
	 * @param roundEnv
	 *            {@link RoundEnvironment}
	 * @throws Exception
	 */
	protected abstract void proccess(RoundEnvironment roundEnv) throws Exception;

	/**
	 * se encarga de verificar el tipo de elemento de la anotacion y si es aceptada
	 * o no
	 * 
	 * @param roundEnv
	 *            {@link RoundEnvironment}
	 */
	protected abstract Boolean verifiedTypeElement(RoundEnvironment roundEnv);

	/**
	 * Es el encargado de realizar la validacion de la clase recibida
	 * 
	 * @param typeElement
	 * @return
	 */
	protected S proccessAnnotation(TypeElement typeElement) {return null;};
	protected S proccessAnnotation(Element typeElement) {return null;};

	protected abstract boolean isValidClass(S annotationClass);
}
