package co.com.arquitectura.librerias.objetos;

/**
 * esta clase es usada para indicar las posiciones de alguna informacion, recibe
 * valores X,Y,Z,Width,Height,Size, todos estos valores se accesan desde sus
 * metodos setter y getters, ademas contiene un campo para indicar como
 * descripcion de que se refiere estas posiciones
 * 
 * @author Alejandro Parra
 * @since 15/03/2017
 * @version 0.0.1
 */
public class Posicion {
	private String descripcion;
	private int x;
	private int y;
	private int z;
	private int width;
	private int height;
	private int size;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
