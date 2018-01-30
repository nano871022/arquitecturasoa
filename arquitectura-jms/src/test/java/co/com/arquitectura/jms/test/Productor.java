package co.com.arquitectura.jms.test;

import java.util.Properties;

import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Test;

public class Productor {
	private String MESSAGE = "Hello, world";
	private String CONNECTION_FACTORY = "jms/remoteCF";
	private String QUEUE = "java:jboss/exported/jms/queue/ExampleQueue";
	private String TOPIC = "jms/Topic";
	private String USUARIO = "invitado";
	private String PASSWORD = "1nv1t4d0";
	
	@Test
	public void prueba() throws Exception{
		Context namingContext;
		JMSContext context;
		Queue queue;
		try {
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			prop.put(Context.PROVIDER_URL, "http-remoting://localhost:5445");
			prop.put("jboss.naming.client.ejb.context", true);
			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			prop.put(Context.SECURITY_PRINCIPAL, USUARIO);
			prop.put(Context.SECURITY_CREDENTIALS, PASSWORD);
			
			InitialContext ic = new InitialContext(prop);
			QueueConnectionFactory factory = (QueueConnectionFactory) ic.lookup(CONNECTION_FACTORY);
			queue = (Queue)ic.lookup(QUEUE);
			QueueConnection conexion = factory.createQueueConnection();
			QueueSession sesion = conexion.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver reciverCola = sesion.createReceiver(queue);
					
			
			
//			InitialContext ic = new InitialContext(prop);
//			QueueConnectionFactory connectionFactory = (QueueConnectionFactory)ic.lookup(QUEUE);
//			QueueConnection conexion = connectionFactory.createQueueConnection();
//			QueueSession sesion = conexion.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			//TopicConnectionFactory connectionFactory = (TopicConnectionFactory)ic.lookup(CONNECTION_FACTORY);
			//TopicConnection conection = connectionFactory.createTopicConnection(USUARIO, PASSWORD);
			//Topic topic = (Topic)ic.lookup(QUEUE);
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
