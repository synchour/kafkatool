package kafkatool.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClusterInfo {
	private Map<String, BrokerInfo> brokers;

	public Map<String, BrokerInfo> getBrokers() {
		return brokers;
	}

	public void setBrokers(Map<String, BrokerInfo> brokers) {
		this.brokers = brokers;
	}

	
	/*
	public void sort() {
		this.brokers.sort(new Comparator<BrokerInfo>() {

			@Override
			public int compare(BrokerInfo o1, BrokerInfo o2) {
				return o1.getBrokerId().compareTo(o2.getBrokerId());
			}
		});
	}*/
}
