package aisha.bean;


public class Assistant extends BasicBean {
	
	private Long investorId;
	private Long platformUserId;
	
	public Long getPlatformUserId() {
		return platformUserId;
	}
	public void setPlatformUserId(Long platformUserId) {
		this.platformUserId = platformUserId;
	}
	public Long getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Long investorId) {
		this.investorId = investorId;
	}


}
