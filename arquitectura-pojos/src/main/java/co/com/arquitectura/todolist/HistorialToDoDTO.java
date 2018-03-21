package co.com.arquitectura.todolist;

import java.time.Duration;

import co.com.arquitectura.annotation.linked.LinkKey;
import co.com.arquitectura.constants.generics.ConstantesFormatos;
import co.com.arquitectura.librerias.abstracts.ADTO;

/**
 * Contiene el tiempo transcurrido entre cada cambio de estado, con esto se ve
 * el historial de lo sucedido
 * 
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public class HistorialToDoDTO extends ADTO {
	@LinkKey(classLinked = ToDoDTO.class)
	private String idToDo;
	/** Se guarda el tiempo en segundos**/
	private String tiempoTranscurrido;

	public String getIdToDo() {
		return idToDo;
	}

	public void setIdToDo(String idToDo) {
		this.idToDo = idToDo;
	}

	public String getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}

	public void setTiempoTranscurrido(String tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}

	public void setTime(Duration duration) {
		tiempoTranscurrido = String.format(ConstantesFormatos.FORMAT_INT, duration.getSeconds());
	}
}
