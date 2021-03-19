package aisha.bean;

import java.util.ArrayList;
import java.util.Date;

public class StartupProfile extends BasicBean {
	
	private String field1;//full name
	private String field2;//email
	private String field3;//phone number
	private String field4;   //gender
	private String field5;   //residence
	private String field6;   //education
	private String field7;   //disability
	private String field8;  //if yes
	private String field9;  //facebook
	private String field10; //twitter
	private String field11; //linkedin
	private String field12; //how you heard
	private String field13; //cv
	private String field14; //declaration
	private String field15;
	private Float field16;
	private Float field17;
	private Float field18;
	private Float field19;
	private Float field20;
	private Date field21;//birth date
	private Date field22;
	private Date field23;
	private Date field24;
	private Date field25;



public static ArrayList<String> getAddFields() {
	
	ArrayList<String> startupFields = new ArrayList<String>();
	startupFields.add(0, "field1");
	startupFields.add(1, "field2");
	startupFields.add(2, "field3");
	startupFields.add(3, "field4");
	startupFields.add(4, "field16");
	startupFields.add(5, "field21");
	return startupFields;

}

public static ArrayList<String> getTableFields() {
	
	ArrayList<String> startupFields = new ArrayList<String>();
	startupFields.add(0, "field1");
	startupFields.add(1, "field2");
	startupFields.add(2, "field3");
	startupFields.add(3, "field4");

	return startupFields;

}


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


public String getField11() {
	return field11;
}


public void setField11(String field11) {
	this.field11 = field11;
}


public String getField12() {
	return field12;
}


public void setField12(String field12) {
	this.field12 = field12;
}


public String getField13() {
	return field13;
}


public void setField13(String field13) {
	this.field13 = field13;
}


public String getField14() {
	return field14;
}


public void setField14(String field14) {
	this.field14 = field14;
}


public String getField15() {
	return field15;
}


public void setField15(String field15) {
	this.field15 = field15;
}


public Float getField16() {
	return field16;
}


public void setField16(Float field16) {
	this.field16 = field16;
}


public Float getField17() {
	return field17;
}


public void setField17(Float field17) {
	this.field17 = field17;
}


public Float getField18() {
	return field18;
}


public void setField18(Float field18) {
	this.field18 = field18;
}


public Float getField19() {
	return field19;
}


public void setField19(Float field19) {
	this.field19 = field19;
}


public Float getField20() {
	return field20;
}


public void setField20(Float field20) {
	this.field20 = field20;
}


public Date getField21() {
	return field21;
}


public void setField21(Date field21) {
	this.field21 = field21;
}


public Date getField22() {
	return field22;
}


public void setField22(Date field22) {
	this.field22 = field22;
}


public Date getField23() {
	return field23;
}


public void setField23(Date field23) {
	this.field23 = field23;
}


public Date getField24() {
	return field24;
}


public void setField24(Date field24) {
	this.field24 = field24;
}


public Date getField25() {
	return field25;
}


public void setField25(Date field25) {
	this.field25 = field25;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((field1 == null) ? 0 : field1.hashCode());
	result = prime * result + ((field10 == null) ? 0 : field10.hashCode());
	result = prime * result + ((field11 == null) ? 0 : field11.hashCode());
	result = prime * result + ((field12 == null) ? 0 : field12.hashCode());
	result = prime * result + ((field13 == null) ? 0 : field13.hashCode());
	result = prime * result + ((field14 == null) ? 0 : field14.hashCode());
	result = prime * result + ((field15 == null) ? 0 : field15.hashCode());
	result = prime * result + ((field16 == null) ? 0 : field16.hashCode());
	result = prime * result + ((field17 == null) ? 0 : field17.hashCode());
	result = prime * result + ((field18 == null) ? 0 : field18.hashCode());
	result = prime * result + ((field19 == null) ? 0 : field19.hashCode());
	result = prime * result + ((field2 == null) ? 0 : field2.hashCode());
	result = prime * result + ((field20 == null) ? 0 : field20.hashCode());
	result = prime * result + ((field21 == null) ? 0 : field21.hashCode());
	result = prime * result + ((field22 == null) ? 0 : field22.hashCode());
	result = prime * result + ((field23 == null) ? 0 : field23.hashCode());
	result = prime * result + ((field24 == null) ? 0 : field24.hashCode());
	result = prime * result + ((field25 == null) ? 0 : field25.hashCode());
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
	StartupProfile other = (StartupProfile) obj;
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
	if (field11 == null) {
		if (other.field11 != null)
			return false;
	} else if (!field11.equals(other.field11))
		return false;
	if (field12 == null) {
		if (other.field12 != null)
			return false;
	} else if (!field12.equals(other.field12))
		return false;
	if (field13 == null) {
		if (other.field13 != null)
			return false;
	} else if (!field13.equals(other.field13))
		return false;
	if (field14 == null) {
		if (other.field14 != null)
			return false;
	} else if (!field14.equals(other.field14))
		return false;
	if (field15 == null) {
		if (other.field15 != null)
			return false;
	} else if (!field15.equals(other.field15))
		return false;
	if (field16 == null) {
		if (other.field16 != null)
			return false;
	} else if (!field16.equals(other.field16))
		return false;
	if (field17 == null) {
		if (other.field17 != null)
			return false;
	} else if (!field17.equals(other.field17))
		return false;
	if (field18 == null) {
		if (other.field18 != null)
			return false;
	} else if (!field18.equals(other.field18))
		return false;
	if (field19 == null) {
		if (other.field19 != null)
			return false;
	} else if (!field19.equals(other.field19))
		return false;
	if (field2 == null) {
		if (other.field2 != null)
			return false;
	} else if (!field2.equals(other.field2))
		return false;
	if (field20 == null) {
		if (other.field20 != null)
			return false;
	} else if (!field20.equals(other.field20))
		return false;
	if (field21 == null) {
		if (other.field21 != null)
			return false;
	} else if (!field21.equals(other.field21))
		return false;
	if (field22 == null) {
		if (other.field22 != null)
			return false;
	} else if (!field22.equals(other.field22))
		return false;
	if (field23 == null) {
		if (other.field23 != null)
			return false;
	} else if (!field23.equals(other.field23))
		return false;
	if (field24 == null) {
		if (other.field24 != null)
			return false;
	} else if (!field24.equals(other.field24))
		return false;
	if (field25 == null) {
		if (other.field25 != null)
			return false;
	} else if (!field25.equals(other.field25))
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
	return "StartupProfile [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + ", field4=" + field4 + ", field5="
			+ field5 + ", field6=" + field6 + ", field7=" + field7 + ", field8=" + field8 + ", field9=" + field9
			+ ", field10=" + field10 + ", field11=" + field11 + ", field12=" + field12 + ", field13=" + field13
			+ ", field14=" + field14 + ", field15=" + field15 + ", field16=" + field16 + ", field17=" + field17
			+ ", field18=" + field18 + ", field19=" + field19 + ", field20=" + field20 + ", field21=" + field21
			+ ", field22=" + field22 + ", field23=" + field23 + ", field24=" + field24 + ", field25=" + field25
			+ ", toString()=" + super.toString() + "]";
}

}
