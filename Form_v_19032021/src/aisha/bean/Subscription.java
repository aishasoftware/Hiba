package aisha.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Subscription extends BasicBean {
	public static ArrayList<String> getPublicFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
	
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		return publicFields;

	}
	
	
	public static ArrayList<String> getViewFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
		
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		return publicFields;

	}
	
	public static ArrayList<String> getProtectedFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
		
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		return publicFields;

	}
	public static ArrayList<String> getAddFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
		
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		return publicFields;

	}
	
	public static ArrayList<String> getTableFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
		
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		publicFields.add(2, "status");
		
		return publicFields;

	}
	
	public static ArrayList<String> getPrivateFields() {

		ArrayList<String> privateFields = new ArrayList<String>();
	
		privateFields.add(0, "packUserId");
		return privateFields;

	}
	
	
	public static ArrayList<String> getAdminFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
		
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		return publicFields;

	}
	
	
	public static ArrayList<String> getSearchFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
		
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		return publicFields;

	}
	
	public static ArrayList<String> getConnectionsFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
		
		publicFields.add(0, "packageId");
		publicFields.add(1, "packUserId");
		return publicFields;

	}
	
	private Integer packageId;
	private Integer packUserId;
	private Timestamp startTime;
	private Timestamp endTime;

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	

	public Integer getPackUserId() {
		return packUserId;
	}

	public void setPackUserId(Integer packUserId) {
		this.packUserId = packUserId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Subscription [packageId=" + packageId + ", packUserId="
				+ packUserId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", toString()=" + super.toString() + "]";
	}


}
