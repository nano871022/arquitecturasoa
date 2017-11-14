package co.com.arquitectura.rest.prueba1;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/hello")

public class HelloWorldService {
	private List<String> lista;
	@GET
	@Path("/msn/{mensaje}")
	public String getMsg(@PathParam("mensaje") String mensaje){
		return "Mensaje: "+mensaje;
	}
	private void loadList() {
		lista = new ArrayList<String>();
		lista.add("registro 1");
		lista.add("registro 2");
		lista.add("registro 3");
		lista.add("registro 4");
	}
	@GET
	@Path("/lista")
	@Produces("application/json")
	public List<String> getList(){
		loadList();
		return lista;
	}

}
