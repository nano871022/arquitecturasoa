package co.com.arquitectura.todolist;

import co.com.arquitectura.annotation.linked.LinkKey;
import co.com.arquitectura.librerias.abstracts.ADTO;
/**
 * Se encarga de almacenar los comentario del historial en el orden que se realizan.
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public class ComentariosToDoDto extends ADTO {
	@LinkKey(classLinked=HistorialToDoDTO.class)
	private String idHistorial;
	@LinkKey(classLinked=RecursoDTO.class)
	private String idRecurso;
	private String comentario;
	private Integer orden;
	public String getIdHistorial() {
		return idHistorial;
	}
	public void setIdHistorial(String idHistorial) {
		this.idHistorial = idHistorial;
	}
	public String getIdRecurso() {
		return idRecurso;
	}
	public void setIdRecurso(String idRecurso) {
		this.idRecurso = idRecurso;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
