package co.com.arquitectura.rest.prueba1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {
	@GET
	@Path("/{mensaje}")
	public String getMsg(@PathParam("mensaje") String mensaje){
		return "Mensaje: "+mensaje;
	}

}
