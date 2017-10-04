package kafkatool.data;

public class ControllerInfo {
	private int version;
	private String brokerid;
	private String timestamp;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getBrokerid() {
		return brokerid;
	}
	public void setBrokerid(String brokerid) {
		this.brokerid = brokerid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
