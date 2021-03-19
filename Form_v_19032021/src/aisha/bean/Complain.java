package aisha.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Complain extends BasicBean {
	
	private String email;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String phone;
	private Long platformUserId;
	private String complainMessage;
	private Boolean resolved;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getResolved() {
		return resolved;
	}
	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}
	public String getComplainMessage() {
		return complainMessage;
	}
	public void setComplainMessage(String complainMessage) {
		this.complainMessage = complainMessage;
	}
	/*public boolean isResolved() {
		return resolved;
	}
	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}*/
	public Long getPlatformUserId() {
		return platformUserId;
	}
	public void setPlatformUserId(Long platformUserId) {
		this.platformUserId = platformUserId;
	}
	
	public static ArrayList<String> getPublicFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
	
		publicFields.add(0, "field1");
		return publicFields;

	}
	
	public static ArrayList<String> getViewFields() {

		ArrayList<String> addFields = new ArrayList<String>();
		//addFields.add(0, "title");
		addFields.add(0, "complainMessage");
		addFields.add(1, "email");
		addFields.add(2, "phone");
		addFields.add(3, "status");
		return addFields;
	}

	
	public static ArrayList<String> getAdminFields() {

		ArrayList<String> adminFields = new ArrayList<String>();
	
		adminFields.add(0, "status");
		return adminFields;

	}
	public static ArrayList<String> getPrivateFields() {

		ArrayList<String> privateFields = new ArrayList<String>();
	
		privateFields.add(0, "field2");
		return privateFields;

	}
	
	public static ArrayList<String> getConnectionsFields() {

		ArrayList<String> privateFields = new ArrayList<String>();
	
		privateFields.add(0, "field3");
		return privateFields;

	}
	
		
	
/*	public Set<Program> getPrograms() {
		return programs;
	}


	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}

*/
	public static ArrayList<String> getAddFields() {

		ArrayList<String> addFields = new ArrayList<String>();
	//	addFields.add(0, "title");
		addFields.add(0, "complainMessage");
		addFields.add(1, "email");
		addFields.add(2, "phone");
		return addFields;

	}
	
	
	public static ArrayList<String> getTableFields() {

		ArrayList<String> addFields = new ArrayList<String>();
	//	addFields.add(0, "title");
		addFields.add(0, "email");
		addFields.add(1, "phone");
		return addFields;
	}

	
}
