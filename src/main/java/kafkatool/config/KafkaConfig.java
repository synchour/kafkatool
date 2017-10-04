package kafkatool.config;

public class KafkaConfig {
	private String bootstrapServers;
	
	private String zookeeperUrl;
	
	private String topicName;
	
	private String kafkaPropertiesFile;
	
	private String consumerGroup;
	
	private boolean sslEnabled;

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	public String getZookeeperUrl() {
		return zookeeperUrl;
	}

	public void setZookeeperUrl(String zookeeperUrl) {
		this.zookeeperUrl = zookeeperUrl;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getKafkaPropertiesFile() {
		return kafkaPropertiesFile;
	}

	public void setKafkaPropertiesFile(String kafkaPropertiesFile) {
		this.kafkaPropertiesFile = kafkaPropertiesFile;
	}

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

	public boolean isSslEnabled() {
		return sslEnabled;
	}

	public void setSslEnabled(boolean sslEnabled) {
		this.sslEnabled = sslEnabled;
	}
}
