package co.com.arquitectura.librerias.abstracts;

import org.apache.log4j.Logger;

public abstract class AbstractLogger {
 protected Logger logger = Logger.getLogger(this.getClass());
}
