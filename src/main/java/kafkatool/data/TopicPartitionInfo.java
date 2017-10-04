package kafkatool.data;

public class TopicPartitionInfo {
	private String topicName;
	private int partitionNumber;
	private String state;
	private TopicPartitionDetails tp;
	
	@Override
	public int hashCode() {
		return this.getTopicName().hashCode() * 100 + getPartitionNumber();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (this.getClass() != obj.getClass()) return false;
		
		TopicPartitionInfo b = (TopicPartitionInfo) obj ;
		return this.getTopicName().equals(b.getTopicName()) && this.getPartitionNumber() == b.getPartitionNumber();
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public int getPartitionNumber() {
		return partitionNumber;
	}

	public void setPartitionNumber(int partitionNumber) {
		this.partitionNumber = partitionNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TopicPartitionDetails getTp() {
		return tp;
	}

	public void setTp(TopicPartitionDetails tp) {
		this.tp = tp;
	}
}
