package kafkatool.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfigFactory {
	
	@Bean
	@Profile("dockertoolbox")
	public KafkaConfig getUATConfig() {
		KafkaConfig config = new KafkaConfig();
		config.setZookeeperUrl("192.168.99.100:2181");
		config.setBootstrapServers("192.168.99.100:9092");
		return config;
	}
	
	@Bean
	@ConditionalOnMissingBean(KafkaConfig.class)
    public KafkaConfig getConfig() {
		KafkaConfig config = new KafkaConfig();
		config.setZookeeperUrl("localhost:2181");
		config.setBootstrapServers("localhost:9092");
		config.setTopicName("kafkatool");
		return config;
    }
}
