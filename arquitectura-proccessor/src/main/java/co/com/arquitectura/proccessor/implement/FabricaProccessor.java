package co.com.arquitectura.proccessor.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import co.com.arquitectura.annotation.proccessor.Fabrica;
import co.com.arquitectura.annotation.proccessor.InjectFabrica;
import co.com.arquitectura.exceptions.proccess.IdAlreadyUsedException;
import co.com.arquitectura.librerias.java_source.JavaSources;
import co.com.arquitectura.librerias.java_source.constants.ConstJavaSources;
import co.com.arquitectura.proccessor.abstracts.AbstractProccessorGeneric;
import co.com.arquitectura.proccessor.verifyAnotation.FactoryGrouped;
import co.com.arquitectura.proccessor.verifyAnotation.FactoryVerified;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ "co.com.arquitectura.annotation.proccessor.Fabrica",
		"co.com.arquitectura.annotation.proccessor.InjectFabrica" })
public class FabricaProccessor extends AbstractProccessorGeneric<FactoryVerified,FactoryGrouped,Fabrica> {

	public FabricaProccessor() {
		super(Fabrica.class);
	}
	@SuppressWarnings("unused")
	protected Boolean verifiedTypeElement(RoundEnvironment roundEnv) {
		FactoryVerified veryfied = null;
		for (Element clase : roundEnv.getElementsAnnotatedWith(annotation)) {
			info(" "+clase.getSimpleName());
			if (clase.getKind() == ElementKind.CLASS) {
				TypeElement typeElement = (TypeElement) clase;
				veryfied = proccessAnnotation(typeElement);
			} else {
				error(clase, "Solo se deben anotar clases con @" + annotation.getSimpleName());
			}
		}
		return true;
	}
	protected void proccess(RoundEnvironment roundEnv) throws Exception{
		try {
			for (FactoryGrouped factoryClass : groupClass.values()) {
				factoryClass.generateSource(processingEnv.getElementUtils(), processingEnv.getFiler());
			}
		} catch (IOException e) {
			error(null, e.getMessage());
		}

		for (Element elemento : roundEnv.getElementsAnnotatedWith(InjectFabrica.class)) {
			if (elemento.getKind() == ElementKind.FIELD) {
				VariableElement typeElement = (VariableElement) elemento;
				addValue(typeElement);
				TypeElement type = (TypeElement) elemento.getEnclosingElement();
				openClass(type, typeElement, getNameClaseGenerated(typeElement.getSimpleName().toString()));

			}
		}
	}
	private String getNameClaseGenerated(String name) {
		info(name);
		for (FactoryGrouped factory : groupClass.values()) {
			info(name + " -- " + factory.getCanonicName());
			if (factory.getCanonicName().toUpperCase().contains(name.toUpperCase())) {
				return factory.getNameClass();
			}
		}
		return null;
	}

	protected FactoryVerified proccessAnnotation(TypeElement typeElement) {
		FactoryVerified fabrica = null;
		try {
			info(typeElement, "antes validando clase");
			fabrica = new FactoryVerified(typeElement);
			if (!isValidClass(fabrica)) {
				return null;
			}
			info(null, "validando clase");
			FactoryGrouped factoryClass = groupClass.get(fabrica.getCanonicClass());
			if (factoryClass == null) {
				String qualifiedGroupName = fabrica.getCanonicClass();
				factoryClass = new FactoryGrouped(qualifiedGroupName);
				groupClass.put(qualifiedGroupName, factoryClass);
			}
			factoryClass.add(fabrica);
		} catch (IllegalArgumentException e) {
			error(typeElement, e.getMessage());
		} catch (IdAlreadyUsedException e) {
			error(typeElement, "Conflicto: La clase " + fabrica.getSimpleNameClass() + " tiene el id duplicado "
					+ fabrica.getId());
		} catch (Exception e) {
			error(typeElement, "Problema " + e.getMessage());
		}
		return fabrica;
	}

	protected void openClass(TypeElement typeElement, VariableElement typeElement2, String ruta) {
		String file = "src.main.java." + typeElement.getQualifiedName();
		String separador = "";
		if (SystemUtils.IS_OS_WINDOWS)
			separador = "\\";
		else
			separador = "/";
		file = file.replace(".", separador);
		file += ".java";
		info("Nombre: " + file);
		JavaSources javaS = new JavaSources();
		try {
			javaS.readFile(file);
			String[] name = file.split(separador + separador);
			writeFile(javaS.getSource(), typeElement.getQualifiedName().toString(), name[name.length - 1]);
			info(javaS.getSource());
		} catch (Exception e) {
			e.printStackTrace();
			error(e.getMessage());
		}
		// readFile(file,ruta,typeElement2);
	}

	private void writeFile(String text, String paquete, String name) throws Exception {
		paquete = paquete.replaceAll("."+name.replace(".java", ConstJavaSources.EMPTY), ConstJavaSources.EMPTY);
		FileObject filerO = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, paquete, name);
		Writer writer = filerO.openWriter();
		writer.write(text);
		writer.flush();
		writer.close();
	}

	private void readFile(String file, String ruta, VariableElement typeElement2) {
		File archivo = new File(file);
		info("encontrado: " + archivo.exists() + " " + ((new File("/")).getAbsolutePath()) + " "
				+ archivo.getAbsolutePath());
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String reader = "";
			String linea = "";
			while ((linea = br.readLine()) != null) {
				if (linea.contains(" " + typeElement2.getSimpleName() + ";") && StringUtils.isNotBlank(ruta)) {
					linea = linea.replace(" " + typeElement2.getSimpleName() + ";",
							" " + typeElement2.getSimpleName() + "= new " + ruta + "();");
					reader += linea + "\n";
					info(linea + " ");
				} else {
					reader += linea + "\n";
				}
			}
			if (reader.length() > 0) {
				info(reader);
				fw = new FileWriter(archivo);
				bw = new BufferedWriter(fw);
				bw.write(reader);
			}
		} catch (FileNotFoundException e) {
			error(e.getMessage());
		} catch (IOException e) {
			error(e.getMessage());
		} finally {
			try {
				bw.close();
				br.close();
				fw.close();
				fr.close();
			} catch (IOException e) {
				error(e.getMessage());
			}
		}

	}
	@Override
	protected boolean isValidClass(FactoryVerified annotationClass) {
		info(null, "validando clase");
		TypeElement clase = annotationClass.getClase();
		if (!clase.getModifiers().contains(Modifier.PUBLIC)) {
			error(clase, "La clase " + annotationClass.getSimpleNameClass() + " no es público.");
			return false;
		}
		if (clase.getModifiers().contains(Modifier.ABSTRACT)) {
			error(clase, "La clase " + annotationClass.getSimpleNameClass() + " es abstracta y no puede ser anotada.");
			return false;
		}
		TypeElement superClass = processingEnv.getElementUtils().getTypeElement(annotationClass.getCanonicClass());
		if (superClass.getKind() == ElementKind.INTERFACE) {
			if (!clase.getInterfaces().contains(superClass.asType())) {
				error(clase, "La clase " + clase.getSimpleName()
						+ " anotada con @Fabrica debe implementar la interface " + annotationClass.getCanonicClass());
				return false;
			}
		} else {
			TypeElement claseActual = clase;
			while (true) {
				info(clase, "Obteniendo super clase " + claseActual.toString());
				TypeMirror superClase = claseActual.getSuperclass();
				if (superClase.getKind() == TypeKind.NONE) {
					error(clase,
							"La clase " + clase.getQualifiedName().toString() + " anotada con "
									+ FactoryVerified.class.getSimpleName() + " debe heredar de "
									+ annotationClass.getCanonicClass());
					return false;
				}
				if (superClase.toString().equals(annotationClass.getCanonicClass())) {
					break;
				}
				claseActual = (TypeElement) processingEnv.getTypeUtils().asElement(superClase);
			}
		}
		for (Element enclosed : clase.getEnclosedElements()) {
			if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
				ExecutableElement contructor = (ExecutableElement) enclosed;
				if (contructor.getParameters().size() == 0 && contructor.getModifiers().contains(Modifier.PUBLIC)) {
					return true;
				}
			}
		}

		error(clase,
				"La clase " + clase.getQualifiedName().toString() + " debe contener un constructor vacio y publico");
		return false;
	}

	private void addValue(VariableElement clase) {
		String nameSimple = ((Element) clase).getSimpleName().toString();
		TypeElement type = (TypeElement) clase.getEnclosingElement();
		try {
			Class clases = Class.forName(type.getQualifiedName().toString());
		} catch (Exception e) {
			error(clase, e.getMessage());
		}

		// processingEnv.getFiler().getResource(location, clase, relativeName);
		// info(null, clase.getClass().getCanonicalName());
		// JavaFile javaFile;
		// for (String id : factoryClases.keySet()) {
		//
		// if (clase.getEnclosingElement().getKind() == ElementKind.CLASS) {
		// TypeElement type = (TypeElement) clase.getEnclosingElement();
		// try {
		// Class claz = Class.forName(type.getQualifiedName().toString());
		// info(null, claz.getSimpleName());
		// ClassName nameFabric = ClassName.get(
		// Class.forName(factoryClases.get(id).getCanonicaName()) );
		// info(null, nameFabric.toString());
		// /*MethodSpec metodo = MethodSpec.methodBuilder("getFactory")
		// .addStatement("fabrica = new $T()", nameFabric)
		// .build();
		// */
		//// info(null, metodo.toString());
		// Class clasess = Class.forName(id);
		// FieldSpec field = FieldSpec.builder(clasess, "fabricas")
		// .addModifiers(Modifier.PRIVATE,Modifier.FINAL)
		// .build();
		//
		// info(null, field.toString());
		// TypeSpec nameClas = TypeSpec.classBuilder(claz.getSimpleName()+"Factor")
		// .addField(field)
		//// .addMethod(metodo)
		// .build();
		//
		// info(null, nameClas.toString());
		// Builder build = JavaFile.builder(claz.getPackage().getName(), nameClas);
		//
		// javaFile = build.build();
		// File sourcePath = new File("src/main/java");
		// javaFile.writeTo(sourcePath);
		// info(clase, "clase name"+claz.getPackage().toString()+" "+ nameClas);
		// } catch (Exception e) {
		// error(clase, e.getMessage());
		// }
		// }
		// }
	}

}
