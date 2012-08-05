package support.stubs;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StubJmsBrokerConfiguration {
	
	private final static String BROKER_URL = "tcp://hasitha-Inspiron-N5110:61615";
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public BrokerService jmsEmbededBroker(){
		BrokerService broker = new BrokerService();

		TransportConnector connector = new TransportConnector();
		try {
			connector.setUri(new URI(BROKER_URL));
			broker.addConnector(connector);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return broker;
	}
}
