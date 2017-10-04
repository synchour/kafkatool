package kafkatool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;

import kafkatool.config.KafkaConfig;

import java.io.FileReader;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

@Service
@EnableCircuitBreaker
public class KafkaProducerService {
	private Producer<String, String> producer;
	private KafkaConfig config;
	
	@Autowired
	public KafkaProducerService(KafkaConfig config) {
		this.config = config;
		
		Properties configProperties = new Properties();
	    configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
	    configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
	    configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
	    configProperties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "10485760");
	    
	    if (config.isSslEnabled()) {
	    	configProperties.put("security.protocol", "SSL");	
	    }
	    
	    if (config.getKafkaPropertiesFile() != null && !config.getKafkaPropertiesFile().isEmpty()) {
	    	try {
	    		configProperties.load(new FileReader(config.getKafkaPropertiesFile()));	
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    producer = new KafkaProducer<String, String>(configProperties);
	}
	
	public long send(String content) {
		return new KafkaProducerSendCommand(this.producer, this.config.getTopicName(), content).execute();
	}
}
