package co.com.arquitectura.annotation.proccessor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * Esta anotacion sirve solo en tiempo de compilación
 * Sirve para anotar los metodos que indican que son servicios y sus especificaciones
 * El objetivo es ser usado por la aplicacion para tener un catalogo de servicios
 * @author Alejandro Parra
 * @since 19/01/2018
 */
@Retention(CLASS)
@Target(METHOD)
public @interface Services {
	/**
	 * Enumeracion que indica si el servicio es privado o publico
	 * privado entiendase que solo sera mostrado para aplicaciones internas
	 * publico entiendase que se podra acceder desde cualquier consumidor segun tecnologia
	 * @author Alejandro Parra
	 */
   public enum kind {PUBLIC,PRIVATE};
   /**
    * Enumeración que indica el tipo de recurso que se puede consumir
    * @author ALejandro Parra
    */
   public enum scope {ALL,REST,WS,EJB,JSON};
   /**
    * Se indica el nombre del metodo un alias al nombre real
    * @return {@link String}
    */
   public String alias();
   /**
    * Se indica una descripcion del objetivo del servicio
    * @return {@link String}
    */
   public String descripcion();
   /**
    * Recibe la enumeracion de {@link Services.kind}
    * Default Public
    * @return {@link kind}
    */
   public kind tipo() default kind.PUBLIC;
   /**
    *  Indica el alcane del servicio, la tecnologia que lo puede consumir
    *  Default All(Todos)
    * @return {@link scope}
    */
   public scope alcance() default scope.ALL;
   
   public Class<?> parent() ;
}
