package co.com.arquitectura.todolist;

import java.time.LocalTime;

import co.com.arquitectura.librerias.abstracts.ADTO;

/**
 * Contiene el tiempo transcurrido entre cada cambio de estado, con esto se ve
 * el historial de lo sucedido
 * @author Alejandro Parra 
 * @since 20/03/2018
 */
public class HistorialToDoDTO extends ADTO{
	private String idToDo;
	private LocalTime tiempoTranscurrido;
	public String getIdToDo() {
		return idToDo;
	}
	public void setIdToDo(String idToDo) {
		this.idToDo = idToDo;
	}
	public LocalTime getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	public void setTiempoTranscurrido(LocalTime tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}
	
}
