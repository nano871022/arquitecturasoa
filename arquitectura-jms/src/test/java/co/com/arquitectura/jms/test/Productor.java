package co.com.arquitectura.jms.test;

import java.util.Properties;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.jms.Connection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class Productor {
	private String MESSAGE = "Hello, world";
	private String CONNECTION_FACTORY = "java:/jms/RemoteConnectionFactory";
	private String QUEUE = "java:/myJmsTest/MyQueue";
	private String TOPIC = "jms/Topic";
	private String USUARIO = "myJmsUser";
	private String PASSWORD = "myJmsPassword";
	private Integer port = 4447;
	private String server = "localhost";
	@Test
	public void activeMq() {
		ActiveMQConnectionFactory connectionF = new ActiveMQConnectionFactory("tcp://localhost:4447");
		try {
			Connection connection = connectionF.createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		} 
	}
	
//	@Test
	public void prueba() throws Exception{
		Context namingContext;
		JMSContext context;
		Queue queue;
		try {
			
			Properties prop = new Properties();
			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			//prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			prop.put(Context.PROVIDER_URL, "remote://"+server+":"+port);
//			prop.put("jboss.naming.client.ejb.context", true);
//			prop.put("jboss.naming.client.connect.options.org.xnio.Options.SSL_STARTTLS", false);
//			prop.put("jboss.naming.client.remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLE", true);
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
