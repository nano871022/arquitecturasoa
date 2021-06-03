package co.com.arquitectura.annotation.proccessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * verifica que las propiedades de la clase esten con valores staticos
 * @author Alejandro Parra
 * @since 2017-11-09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Immutable {

}
