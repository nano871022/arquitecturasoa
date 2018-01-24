import org.junit.Test;

import co.com.arquitectura.ejb_local.login.UsarServices;

public class TestServices {
	@Test
	public void man() {
		UsarServices usar = new UsarServices();
		usar.catalogo();
	}
}
