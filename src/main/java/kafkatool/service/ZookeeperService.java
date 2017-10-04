package kafkatool.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import kafka.utils.ZkUtils;
import kafkatool.config.KafkaConfig;
import kafkatool.data.BrokerDetails;
import kafkatool.data.BrokerInfo;
import kafkatool.data.ControllerInfo;
import kafkatool.data.TopicPartitionDetails;

@Service
public class ZookeeperService {

	//private KafkaConfig config;	
	private ZkClient c;
	
	@Autowired
	public ZookeeperService(KafkaConfig config) {
		//this.config = config;
		System.out.println("Zookeeper url " + config);
		this.c = ZkUtils.createZkClient(config.getZookeeperUrl(), 30000, 30000);
	}
	
	private List<String> getAllBrokers() {
		return c.getChildren("/brokers/ids");
	}
	
	public List<BrokerInfo> getBrokerInfo() {
		List<BrokerInfo> bi = new ArrayList<BrokerInfo>();
		for (String brokerId : getAllBrokers()) {
			BrokerInfo b = new BrokerInfo();
			b.setBrokerId(brokerId);
			b.setDetails(getBrokerDetails(brokerId));
			b.setBd(getBD(brokerId));
			bi.add(b);
		}
		return bi;
	}
	
	public List<String> getAllTopics() {
		return c.getChildren("/brokers/topics");
	}
	
	public List<String> getTopicPartitions(String topicName) {
		if (!getAllTopics().contains(topicName)) {
			return null;
		}
		
		return c.getChildren("/brokers/topics/" + topicName + "/partitions");
	}
	
	private String getTopicPartitionState(String topicName, String partition) {
		
		return tryReadData("/brokers/topics/" + topicName + "/partitions/" + partition + "/state");
	}
	
	private String getBrokerDetails(String brokerId) {
		return tryReadData("/brokers/ids/" + brokerId);
	}
	
	private BrokerDetails getBD(String brokerId) {
		Gson gson = new Gson();
		Type type = new TypeToken<BrokerDetails>(){}.getType();
		return gson.fromJson(getBrokerDetails(brokerId), type);
	}
	
	private String getControllerDetails() {
		String controller = tryReadData("/controller");
		System.out.println(controller);
		return controller;
	}
	
	public ControllerInfo getControllerInfo() {
		Gson gson = new Gson();
		Type type = new TypeToken<ControllerInfo>(){}.getType();
		return gson.fromJson(getControllerDetails(), type);
	}
	
	public TopicPartitionDetails getTopicPartitionDetails(String topicName, String partition) {
		Gson gson = new Gson();
		Type type = new TypeToken<TopicPartitionDetails>(){}.getType();
		return gson.fromJson(getTopicPartitionState(topicName, partition), type);		
	}
	
	public String tryReadData(String path) {
		try {
			return c.readData(path);
		} catch (Exception e) {
			return "{}";
		}
	}
}
