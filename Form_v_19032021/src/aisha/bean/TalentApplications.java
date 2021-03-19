package aisha.bean;

import java.util.ArrayList;

public class TalentApplications extends BasicBean {
	
private String field1;
private String field2;
private String field3;
private String field4;
private String field5;
private String field6;
private String field7;
private String field8;
private String field9;
private String field10;
public String getField1() {
	return field1;
}
public void setField1(String field1) {
	this.field1 = field1;
}
public String getField2() {
	return field2;
}
public void setField2(String field2) {
	this.field2 = field2;
}
public String getField3() {
	return field3;
}
public void setField3(String field3) {
	this.field3 = field3;
}
public String getField4() {
	return field4;
}
public void setField4(String field4) {
	this.field4 = field4;
}
public String getField5() {
	return field5;
}
public void setField5(String field5) {
	this.field5 = field5;
}
public String getField6() {
	return field6;
}
public void setField6(String field6) {
	this.field6 = field6;
}
public String getField7() {
	return field7;
}
public void setField7(String field7) {
	this.field7 = field7;
}
public String getField8() {
	return field8;
}
public void setField8(String field8) {
	this.field8 = field8;
}
public String getField9() {
	return field9;
}
public void setField9(String field9) {
	this.field9 = field9;
}
public String getField10() {
	return field10;
}
public void setField10(String field10) {
	this.field10 = field10;
}


public static ArrayList<String> getTableFields() {
	
	ArrayList<String> talentFields = new ArrayList<String>();
	talentFields.add(0, "field1");
	talentFields.add(1, "field2");
	talentFields.add(2, "field3");
	talentFields.add(3, "field4");
	talentFields.add(4, "field5");
	return talentFields;

}


public static ArrayList<String> getSearchFields() {
	
	ArrayList<String> talentFields = new ArrayList<String>();
	talentFields.add(0, "field1");
	talentFields.add(1, "field2");
	talentFields.add(2, "field3");
	talentFields.add(3, "field4");
	talentFields.add(4, "field5");
	return talentFields;

}

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((field1 == null) ? 0 : field1.hashCode());
	result = prime * result + ((field10 == null) ? 0 : field10.hashCode());
	result = prime * result + ((field2 == null) ? 0 : field2.hashCode());
	result = prime * result + ((field3 == null) ? 0 : field3.hashCode());
	result = prime * result + ((field4 == null) ? 0 : field4.hashCode());
	result = prime * result + ((field5 == null) ? 0 : field5.hashCode());
	result = prime * result + ((field6 == null) ? 0 : field6.hashCode());
	result = prime * result + ((field7 == null) ? 0 : field7.hashCode());
	result = prime * result + ((field8 == null) ? 0 : field8.hashCode());
	result = prime * result + ((field9 == null) ? 0 : field9.hashCode());
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
	TalentApplications other = (TalentApplications) obj;
	if (field1 == null) {
		if (other.field1 != null)
			return false;
	} else if (!field1.equals(other.field1))
		return false;
	if (field10 == null) {
		if (other.field10 != null)
			return false;
	} else if (!field10.equals(other.field10))
		return false;
	if (field2 == null) {
		if (other.field2 != null)
			return false;
	} else if (!field2.equals(other.field2))
		return false;
	if (field3 == null) {
		if (other.field3 != null)
			return false;
	} else if (!field3.equals(other.field3))
		return false;
	if (field4 == null) {
		if (other.field4 != null)
			return false;
	} else if (!field4.equals(other.field4))
		return false;
	if (field5 == null) {
		if (other.field5 != null)
			return false;
	} else if (!field5.equals(other.field5))
		return false;
	if (field6 == null) {
		if (other.field6 != null)
			return false;
	} else if (!field6.equals(other.field6))
		return false;
	if (field7 == null) {
		if (other.field7 != null)
			return false;
	} else if (!field7.equals(other.field7))
		return false;
	if (field8 == null) {
		if (other.field8 != null)
			return false;
	} else if (!field8.equals(other.field8))
		return false;
	if (field9 == null) {
		if (other.field9 != null)
			return false;
	} else if (!field9.equals(other.field9))
		return false;
	return true;
}
@Override
public String toString() {
	return "TalentApplications [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + ", field4=" + field4
			+ ", field5=" + field5 + ", field6=" + field6 + ", field7=" + field7 + ", field8=" + field8 + ", field9="
			+ field9 + ", field10=" + field10 + ", getFirstPage()=" + getFirstPage() + ", getMaxResult()="
			+ getMaxResult() + ", getTotalResult()=" + getTotalResult() + ", isOrderAsc()=" + isOrderAsc()
			+ ", getOrderBy()=" + getOrderBy() + ", getId()=" + getId() + ", getStatus()=" + getStatus()
			+ ", getCreationTime()=" + getCreationTime() + ", getLastUpdateTime()=" + getLastUpdateTime()
			+ ", getCreatedBy()=" + getCreatedBy() + ", getLastUpdatedBy()=" + getLastUpdatedBy()
			+ ", getSearchCriteria()=" + getSearchCriteria() + ", getVersion()=" + getVersion() + ", getResults()="
			+ getResults() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
}

}
