package co.com.arquitectura.pojo.basicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * se encarga de almacenar el listado de las conexiones activas que tiene el
 * usuario
 * 
 * @author Alejandro Parra
 * @since 23/03/2017
 * @version 0.0.1
 */
public class ListaConexiones {
	private Usuario usuario;
	private List<Conexion> lista;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Conexion> getLista() {
		return lista;
	}

	public void setLista(List<Conexion> lista) {
		this.lista = lista;
	}

	public void add(Conexion conexion) {
		if (lista == null) {
			lista = new ArrayList<Conexion>();
		}
		if (conexion != null && StringUtils.isNotBlank(conexion.getLlave())) {
			lista.add(conexion);
		}
	}

}
