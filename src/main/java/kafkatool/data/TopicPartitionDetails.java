package kafkatool.data;

import java.util.List;

public class TopicPartitionDetails {
	private int controller_epoch;
	private String leader;
	private int version;
	private List<String> isr;
	public int getController_epoch() {
		return controller_epoch;
	}
	public void setController_epoch(int controller_epoch) {
		this.controller_epoch = controller_epoch;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<String> getIsr() {
		return isr;
	}
	public void setIsr(List<String> isr) {
		this.isr = isr;
	}
}
