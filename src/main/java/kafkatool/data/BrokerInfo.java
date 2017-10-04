package kafkatool.data;

public class BrokerInfo {
	private String brokerId;
	private String endPoint;
	private boolean isController;
	private String details;
	private BrokerDetails bd;
	
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public boolean isController() {
		return isController;
	}
	public void setController(boolean isController) {
		this.isController = isController;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Override
	public int hashCode() {
		return this.brokerId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (this.getClass() != obj.getClass()) return false;
		
		BrokerInfo b = (BrokerInfo) obj ;
		return this.brokerId.equals(b.getBrokerId());
	}
	public BrokerDetails getBd() {
		return bd;
	}
	public void setBd(BrokerDetails bd) {
		this.bd = bd;
	}
	
}
