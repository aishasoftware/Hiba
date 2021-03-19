package aisha.bean;

import java.util.ArrayList;


public class Connection extends BasicBean {

	private int innerId;
	private int outerId;
	private String relationType;
	private String innerName;
	private String outerName;
	
	
	public String getInnerName() {
		return innerName;
	}

	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}

	public String getOuterName() {
		return outerName;
	}

	public void setOuterName(String outerName) {
		this.outerName = outerName;
	}

	public static ArrayList<String> getTableFields() {
		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "innerName");
		tableFields.add(1, "outerName");
		tableFields.add(2, "status");
		tableFields.add(3, "accept");
		return tableFields;
	}
	
	public static ArrayList<String> getSearchFields() {
		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "connectionName");
		tableFields.add(1, "creationTime");
		return tableFields;
	}
	
	public static ArrayList<String> getAddTalentFields() {
		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "connectionName");
		tableFields.add(1, "innerId");
		return tableFields;
	}
	
	public static ArrayList<String> getPublicFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "innerId");
		tableFields.add(1, "outerId");
		tableFields.add(2, "status");
		return tableFields;

	}
	
	public static ArrayList<String> getPrivateFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "innerId");
		tableFields.add(1, "outerId");
		tableFields.add(2, "relationType");
		tableFields.add(3, "status");
		return tableFields;

	}
	
	public static ArrayList<String> getProtectedFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "innerId");
		tableFields.add(1, "outerId");
		tableFields.add(2, "status");
		return tableFields;

	}
	
	public static ArrayList<String> getAdminFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "innerId");
		tableFields.add(1, "outerId");
		tableFields.add(2, "status");
		return tableFields;

	}
	
	public static ArrayList<String> getAddFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "innerId");
		tableFields.add(1, "outerId");
		tableFields.add(2, "status");
		return tableFields;

	}
	
	public static ArrayList<String> getViewFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "innerId");
		tableFields.add(1, "outerId");
		tableFields.add(2, "status");
		return tableFields;

	}
	
	public int getInnerId() {
		return innerId;
	}
	public void setInnerId(int innerId) {
		this.innerId = innerId;
	}
	public int getOuterId() {
		return outerId;
	}
	public void setOuterId(int outerId) {
		this.outerId = outerId;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + innerId;
		result = prime * result
				+ ((innerName == null) ? 0 : innerName.hashCode());
		result = prime * result + outerId;
		result = prime * result
				+ ((outerName == null) ? 0 : outerName.hashCode());
		result = prime * result
				+ ((relationType == null) ? 0 : relationType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (innerId != other.innerId)
			return false;
		if (innerName == null) {
			if (other.innerName != null)
				return false;
		} else if (!innerName.equals(other.innerName))
			return false;
		if (outerId != other.outerId)
			return false;
		if (outerName == null) {
			if (other.outerName != null)
				return false;
		} else if (!outerName.equals(other.outerName))
			return false;
		if (relationType == null) {
			if (other.relationType != null)
				return false;
		} else if (!relationType.equals(other.relationType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Connection [innerId=" + innerId + ", outerId=" + outerId
				+ ", relationType=" + relationType + ", innerName=" + innerName
				+ ", outerName=" + outerName + "]";
	}



}
