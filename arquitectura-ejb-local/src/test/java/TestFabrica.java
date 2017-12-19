
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import co.com.arquitectura.ejb_local.login.UsarFactory;

public class TestFabrica {
	private UsarFactory userFactory;

	@Test
	public void man() {
		try {
			
			userFactory = new UsarFactory();
			
			Class clase = UsarFactory.class;
			Field[] fields = clase.getDeclaredFields();
			Method[] methods = clase.getDeclaredMethods();
			for(Field field : fields)
				System.out.println(field.getName());
			for(Method method : methods)
				 System.out.println(method.getName());
			
			Object ret = clase.getMethod("getfabrica").invoke(userFactory);
			
			System.out.println("Se obtubo el valor"+ret);
			System.out.println(userFactory.getSaludo());
			
		}catch(Exception e) {
			//System.err.println(e);
		}
	}
}
