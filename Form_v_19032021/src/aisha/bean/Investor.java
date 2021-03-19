package aisha.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class Investor extends BasicBean {

	private int version;
	private String field1; // name
	private String field2; // sector
	private String field3; // about us
	private String field4; // phone
	private String field5; // email
	private String field6; 
	private String field7; 
	private String field8; 
	private String field9; 
	private String field10; // linked-in
	private String field11; // twitter
	private String field12; // facebook
	private String field13; // user name
	private String field14; // password
	private String field15; // confirmed password

	private Float field16;
	private Float field17;
	private Float field18;
	private Float field19;
	private Float field20;

	private int field21;
	private int field22;
	private int field23;
	private int field24;
	private int field25;

	private Date field26;// foundation date
	private Date field27;
	private Date field28;
	private Date field29;
	private Date field30;

	private Set<Startup> startups = new HashSet<Startup>();
	private Set<Connection> connections = new HashSet<Connection>();
	private Set<Program> programs = new HashSet<Program>();

	
/*	public static ArrayList<String> getViewFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
//general information
publicFields.add(0, "field1");//name
publicFields.add(1, "field2");//sector
publicFields.add(2, "field3");//about Us
publicFields.add(3, "field4");//email
publicFields.add(4, "field5");//phone
//links
publicFields.add(5, "field9");//website
publicFields.add(6, "field10");//linked in
publicFields.add(7, "field11");//twitter
publicFields.add(8, "field12");//facebook
publicFields.add(9, "field26");//foundation date
		return publicFields;
	}*/
	
public static ArrayList<String> getPublicFields() {

ArrayList<String> publicFields = new ArrayList<String>();
//general information
publicFields.add(0, "field1");//name
publicFields.add(1, "field2");//sector
publicFields.add(2, "field3");//about Us
publicFields.add(3, "field4");//email
publicFields.add(4, "field5");//phone
publicFields.add(5, "field26");//foundation date
//links
publicFields.add(6, "field9");//website
publicFields.add(7, "field10");//linked in
publicFields.add(8, "field11");//twitter
publicFields.add(9, "field12");//facebook

return publicFields;
		
	}


	public static ArrayList<String> getProtectedFields() {

		ArrayList<String> protectedFields = new ArrayList<String>();
		//contacts
		protectedFields.add(0, "field4");
		protectedFields.add(1, "field5");
		return protectedFields;

	}
	
	public static ArrayList<String> getPrivateFields() {

		ArrayList<String> privateFields = new ArrayList<String>();
		//credentials
	//	privateFields.add(0, "field13");
		return privateFields;

	}
	
	public static ArrayList<String> getSearchFields() {

		ArrayList<String> searchFields = new ArrayList<String>();
		searchFields.add(0, "fromCreate");
		searchFields.add(1, "toCreate");
		searchFields.add(2, "field1");
		searchFields.add(3, "field2");
		return searchFields;

	}



	public static ArrayList<String> getAddFields() {

		ArrayList<String> addFields = new ArrayList<String>();

		addFields.add(0, "field1");
		addFields.add(1, "field2");
		addFields.add(2, "field3");
		addFields.add(3, "field4");
		addFields.add(4, "field5");

		addFields.add(5, "field9");
		addFields.add(6, "field10");
		addFields.add(7, "field11");
		addFields.add(8, "field12");

		addFields.add(9, "field13");
		addFields.add(10, "field14");
		addFields.add(11, "field15");

		addFields.add(12, "field26");

		return addFields;
	}
	
	public static ArrayList<String> getViewFields() {

		ArrayList<String> addFields = new ArrayList<String>();

		addFields.add(0, "field1");
		addFields.add(1, "field2");
		addFields.add(2, "field3");
		addFields.add(3, "field4");
		addFields.add(4, "field5");

		addFields.add(5, "field9");
		addFields.add(6, "field10");
		addFields.add(7, "field11");
		addFields.add(8, "field12");

		addFields.add(9, "field13");
		addFields.add(10, "field14");
		addFields.add(11, "field15");
		addFields.add(12, "field26");

		return addFields;
	}

	public static ArrayList<String> getTableFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "field1");
		tableFields.add(1, "field2");
		return tableFields;
	}
	
	public static ArrayList<String> getAdminTableFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "field1");
		tableFields.add(1, "field2");
		return tableFields;
	}

	public static Map<String, String> getLinks() {

		Map<String, String> links = new LinkedHashMap<String, String>();

		links.put("My Connections",
				"/Form_v_1.0/InvestorController/getConnectionList");
		
		links.put("My Programs",
				"/Form_v_1.0/InvestorController/getMyProgramList");
		
		links.put("Add Program", "/Form_v_1.0/InvestorController/addProgram");
		
		links.put("Manage Users",
				"/Form_v_1.0/PlatformUserController/getPlatformUserList");

		return links;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public int getField21() {
		return field21;
	}

	public void setField21(int field21) {
		this.field21 = field21;
	}

	public int getField22() {
		return field22;
	}

	public void setField22(int field22) {
		this.field22 = field22;
	}

	public int getField23() {
		return field23;
	}

	public void setField23(int field23) {
		this.field23 = field23;
	}

	public int getField24() {
		return field24;
	}

	public void setField24(int field24) {
		this.field24 = field24;
	}

	public int getField25() {
		return field25;
	}

	public void setField25(int field25) {
		this.field25 = field25;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getField26() {
		return field26;
	}

	public void setField26(Date field26) {
		this.field26 = field26;
	}

	public Date getField27() {
		return field27;
	}

	public void setField27(Date field27) {
		this.field27 = field27;
	}

	public Date getField28() {
		return field28;
	}

	public void setField28(Date field28) {
		this.field28 = field28;
	}

	public Date getField29() {
		return field29;
	}

	public void setField29(Date field29) {
		this.field29 = field29;
	}

	public Date getField30() {
		return field30;
	}

	public void setField30(Date field30) {
		this.field30 = field30;
	}

	public Set<Startup> getStartups() {
		return startups;
	}

	public void setStartups(Set<Startup> startups) {
		this.startups = startups;
	}

	public Set<Connection> getConnections() {
		return connections;
	}

	public void setConnections(Set<Connection> connections) {
		this.connections = connections;
	}

	public Set<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((connections == null) ? 0 : connections.hashCode());
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
		result = prime * result + field21;
		result = prime * result + field22;
		result = prime * result + field23;
		result = prime * result + field24;
		result = prime * result + field25;
		result = prime * result + ((field26 == null) ? 0 : field26.hashCode());
		result = prime * result + ((field27 == null) ? 0 : field27.hashCode());
		result = prime * result + ((field28 == null) ? 0 : field28.hashCode());
		result = prime * result + ((field29 == null) ? 0 : field29.hashCode());
		result = prime * result + ((field3 == null) ? 0 : field3.hashCode());
		result = prime * result + ((field30 == null) ? 0 : field30.hashCode());
		result = prime * result + ((field4 == null) ? 0 : field4.hashCode());
		result = prime * result + ((field5 == null) ? 0 : field5.hashCode());
		result = prime * result + ((field6 == null) ? 0 : field6.hashCode());
		result = prime * result + ((field7 == null) ? 0 : field7.hashCode());
		result = prime * result + ((field8 == null) ? 0 : field8.hashCode());
		result = prime * result + ((field9 == null) ? 0 : field9.hashCode());
		result = prime * result
				+ ((programs == null) ? 0 : programs.hashCode());
		result = prime * result
				+ ((startups == null) ? 0 : startups.hashCode());
		result = prime * result + version;
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
		Investor other = (Investor) obj;
		if (connections == null) {
			if (other.connections != null)
				return false;
		} else if (!connections.equals(other.connections))
			return false;
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
		if (field21 != other.field21)
			return false;
		if (field22 != other.field22)
			return false;
		if (field23 != other.field23)
			return false;
		if (field24 != other.field24)
			return false;
		if (field25 != other.field25)
			return false;
		if (field26 == null) {
			if (other.field26 != null)
				return false;
		} else if (!field26.equals(other.field26))
			return false;
		if (field27 == null) {
			if (other.field27 != null)
				return false;
		} else if (!field27.equals(other.field27))
			return false;
		if (field28 == null) {
			if (other.field28 != null)
				return false;
		} else if (!field28.equals(other.field28))
			return false;
		if (field29 == null) {
			if (other.field29 != null)
				return false;
		} else if (!field29.equals(other.field29))
			return false;
		if (field3 == null) {
			if (other.field3 != null)
				return false;
		} else if (!field3.equals(other.field3))
			return false;
		if (field30 == null) {
			if (other.field30 != null)
				return false;
		} else if (!field30.equals(other.field30))
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
		if (programs == null) {
			if (other.programs != null)
				return false;
		} else if (!programs.equals(other.programs))
			return false;
		if (startups == null) {
			if (other.startups != null)
				return false;
		} else if (!startups.equals(other.startups))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Investor [version=" + version + ", field1=" + field1
				+ ", field2=" + field2 + ", field3=" + field3 + ", field4="
				+ field4 + ", field5=" + field5 + ", field6=" + field6
				+ ", field7=" + field7 + ", field8=" + field8 + ", field9="
				+ field9 + ", field10=" + field10 + ", field11=" + field11
				+ ", field12=" + field12 + ", field13=" + field13
				+ ", field14=" + field14 + ", field15=" + field15
				+ ", field16=" + field16 + ", field17=" + field17
				+ ", field18=" + field18 + ", field19=" + field19
				+ ", field20=" + field20 + ", field21=" + field21
				+ ", field22=" + field22 + ", field23=" + field23
				+ ", field24=" + field24 + ", field25=" + field25
				+ ", field26=" + field26 + ", field27=" + field27
				+ ", field28=" + field28 + ", field29=" + field29
				+ ", field30=" + field30 + ", startups=" + startups
				+ ", connections=" + connections + ", programs=" + programs
				+ "]";
	}

}