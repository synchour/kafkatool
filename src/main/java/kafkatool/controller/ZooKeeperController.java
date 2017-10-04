package kafkatool.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kafkatool.data.BrokerInfo;
import kafkatool.data.ClusterInfo;
import kafkatool.data.ControllerInfo;
import kafkatool.data.TopicPartitionInfo;
import kafkatool.service.ZookeeperService;

@RestController
@CrossOrigin
@RequestMapping("zk")
public class ZooKeeperController {
	
	@Autowired
	ZookeeperService zk;
	
	private Log log = LogFactory.getLog(ZooKeeperController.class);
	
	@GetMapping("/all")
	public Object getSummaryInfo() {
		Map<String, Object> objects = new HashMap<String, Object>();
		
		// controller
		ControllerInfo controllerInfo = zk.getControllerInfo();
		String controllerId = controllerInfo.getBrokerid();

		// brokers
		Map<String, BrokerInfo> bi = zk.getBrokerInfo().stream()
				.collect(Collectors.toMap(BrokerInfo::getBrokerId, x -> x));
		
		// set controller flag
		bi.values().stream().filter(b -> b.getBrokerId().equals(controllerId))
			.findFirst().get().setController(true);

		// cluster
		ClusterInfo ci = new ClusterInfo();
		
		ci.setBrokers(bi);
		
		objects.put("clusterInfo", ci);
		objects.put("controller", controllerInfo);		
		// Topics and partitions
		
		List<TopicPartitionInfo> topicDetails = new ArrayList<TopicPartitionInfo>();
		for (String topicName : zk.getAllTopics()) {
			//
			//if (!topicName.startsWith("_")) {				
				for (String partition : zk.getTopicPartitions(topicName)) {
					TopicPartitionInfo topic = new TopicPartitionInfo();
					topic.setTopicName(topicName);
					topic.setPartitionNumber(Integer.parseInt(partition));
					//topic.setState(zk.getTopicPartitionState(topicName, partition));					
					topic.setTp(zk.getTopicPartitionDetails(topicName, partition));
					// TODO: isr
					// TODO: leader
					topicDetails.add(topic);
				}
			//}
		}
		
		topicDetails.sort(new Comparator<TopicPartitionInfo>() {

			@Override
			public int compare(TopicPartitionInfo o1, TopicPartitionInfo o2) {
				return o1.getTopicName().compareTo(o2.getTopicName()) * 100 + o1.getPartitionNumber() - o2.getPartitionNumber();
			}
		});
		objects.put("topic", topicDetails);
		
		return objects;
	}
}
