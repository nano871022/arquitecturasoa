package co.com.arquitectura.todolist;
/**
 * Tabla de parametros exclusivos para To-Do
 * @author Alejandro Parra
 * @since 20/03/2018
 */

import java.math.BigDecimal;
import java.util.Date;

import co.com.arquitectura.annotation.linked.LinkKey;
import co.com.arquitectura.librerias.abstracts.ADTO;
/**
 * Se encarga de usar y almacenar parametros sin referencia especial que se puedan usar en cualquier sitio
 * @author Alejandro Parra
 * @since 20/03/2018
 */
public class ParametrosToDoDTO extends ADTO{
	public String nombre;
	public String descripcion;
	public String valorChar;
	public Integer valorInt;
	public Date valorDate;
	public BigDecimal valorBigDec;
	@LinkKey(classLinked=ParametrosToDoDTO.class)
	public String idDepende;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValorChar() {
		return valorChar;
	}
	public void setValorChar(String valorChar) {
		this.valorChar = valorChar;
	}
	public Integer getValorInt() {
		return valorInt;
	}
	public void setValorInt(Integer valorInt) {
		this.valorInt = valorInt;
	}
	public Date getValorDate() {
		return valorDate;
	}
	public void setValorDate(Date valorDate) {
		this.valorDate = valorDate;
	}
	public BigDecimal getValorBigDec() {
		return valorBigDec;
	}
	public void setValorBigDec(BigDecimal valorBigDec) {
		this.valorBigDec = valorBigDec;
	}
	public String getIdDepende() {
		return idDepende;
	}
	public void setIdDepende(String idDepende) {
		this.idDepende = idDepende;
	}
	
}
