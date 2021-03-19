package aisha.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Program extends BasicBean{
	
	
	private String programName;
	private Integer investorId;
	private Date startTime;
	private Date endTime;
	private String description;
	private String appFile1;//application form
	private String appFile2;
	private String appFile3;
	private String appFile4;
	private String eva_1;
	private String eva_2;
	private String eva_3;
	private String eva_4;
	private String eva_5;
	private String interv_1;
	private String interv_2;
	private String interv_3;
	private String interv_4;
	private String interv_5;
	private String noOfEvaluators;
	private String noOfInterviewers;
	private String investorName;
	
	
	
	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getNoOfEvaluators() {
		return noOfEvaluators;
	}

	public void setNoOfEvaluators(String noOfEvaluators) {
		this.noOfEvaluators = noOfEvaluators;
	}

	public String getNoOfInterviewers() {
		return noOfInterviewers;
	}

	public void setNoOfInterviewers(String noOfInterviewers) {
		this.noOfInterviewers = noOfInterviewers;
	}

	public String getEva_1() {
		return eva_1;
	}

	public void setEva_1(String eva_1) {
		this.eva_1 = eva_1;
	}

	public String getEva_2() {
		return eva_2;
	}

	public void setEva_2(String eva_2) {
		this.eva_2 = eva_2;
	}

	public String getEva_3() {
		return eva_3;
	}

	public void setEva_3(String eva_3) {
		this.eva_3 = eva_3;
	}

	public String getEva_4() {
		return eva_4;
	}

	public void setEva_4(String eva_4) {
		this.eva_4 = eva_4;
	}

	public String getEva_5() {
		return eva_5;
	}

	public void setEva_5(String eva_5) {
		this.eva_5 = eva_5;
	}

	public String getInterv_1() {
		return interv_1;
	}

	public void setInterv_1(String interv_1) {
		this.interv_1 = interv_1;
	}

	public String getInterv_2() {
		return interv_2;
	}

	public void setInterv_2(String interv_2) {
		this.interv_2 = interv_2;
	}

	public String getInterv_3() {
		return interv_3;
	}

	public void setInterv_3(String interv_3) {
		this.interv_3 = interv_3;
	}

	public String getInterv_4() {
		return interv_4;
	}

	public void setInterv_4(String interv_4) {
		this.interv_4 = interv_4;
	}

	public String getInterv_5() {
		return interv_5;
	}

	public void setInterv_5(String interv_5) {
		this.interv_5 = interv_5;
	}

	public static ArrayList<String> getPublicFields() {

		ArrayList<String> publicFields = new ArrayList<String>();

		publicFields.add(0, "programName");
		publicFields.add(1, "investorId");
		publicFields.add(2, "startTime");
		publicFields.add(3, "endTime");
		
		return publicFields;

	}
	
	public static ArrayList<String> getSearchFields() {

		ArrayList<String> searchFields = new ArrayList<String>();
		searchFields.add(0, "fromCreate");
		searchFields.add(1, "toCreate");
		searchFields.add(2, "programName");
		searchFields.add(3, "investorId");
		return searchFields;

	}
	
	public static ArrayList<String> getViewFields() {

		ArrayList<String> addFields = new ArrayList<String>();
		addFields.add(0, "programName");
		addFields.add(1, "description");
		addFields.add(2, "startTime");
		addFields.add(3, "endTime");
		addFields.add(4, "appFile1");
		addFields.add(5, "eva_1");
		addFields.add(6, "eva_2");
		addFields.add(7, "eva_3");
		addFields.add(8, "eva_4");
		addFields.add(9, "eva_5");
		
		addFields.add(10, "interv_1");
		addFields.add(11, "interv_2");
		addFields.add(12, "interv_3");
		addFields.add(13, "interv_4");
		addFields.add(14, "interv_5");
		return addFields;

	}

	
	public static ArrayList<String> getAdminFields() {

		ArrayList<String> adminFields = new ArrayList<String>();
	
		adminFields.add(0, "status");
		return adminFields;

	}
	public static ArrayList<String> getPrivateFields() {

		ArrayList<String> privateFields = new ArrayList<String>();

		privateFields.add(0, "eva_1");
		privateFields.add(0, "eva_2");
		privateFields.add(0, "eva_3");
		privateFields.add(0, "eva_4");
		privateFields.add(0, "eva_5");
		privateFields.add(0, "interv_1");
		privateFields.add(0, "interv_2");
		privateFields.add(0, "interv_3");
		privateFields.add(0, "interv_4");
		privateFields.add(0, "interv_5");
		privateFields.add(0, "noOfEvaluators");
		privateFields.add(0, "noOfInterviewers");
		
		return privateFields;

	}
	
	public static ArrayList<String> getProtectedFields() {

		ArrayList<String> privateFields = new ArrayList<String>();
	
		privateFields.add(0, "description");
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
		addFields.add(0, "programName");
		addFields.add(1, "description");
		addFields.add(2, "startTime");
		addFields.add(3, "endTime");
		addFields.add(4, "appFile1");
		addFields.add(5, "eva_1");
		addFields.add(6, "eva_2");
		addFields.add(7, "eva_3");
		addFields.add(8, "eva_4");
		addFields.add(9, "eva_5");
		
		addFields.add(10, "interv_1");
		addFields.add(11, "interv_2");
		addFields.add(12, "interv_3");
		addFields.add(13, "interv_4");
		addFields.add(14, "interv_5");
		addFields.add(15, "noOfEvaluators");
		addFields.add(16, "noOfInterviewers");
		
		return addFields;

	}
	
	
	public static ArrayList<String> getTableFields() {

		ArrayList<String> addFields = new ArrayList<String>();
		addFields.add(0, "programName");
		//addFields.add(1, "investorName");
		return addFields;
	}
	public static Map<String,String> getLinks() {

	Map<String,String> links = new HashMap<String, String>();
	links.put("Manage Users","/Form_v_1.0/SystemUserController/getPlatformUserList");
	links.put("View My Startups","/Form_v_1.0/InvestorController/listStartups");
	links.put("View my Applications","/Form_v_1.0/InvestorController/listApplications");
	links.put("Add Complain","/Form_v_1.0/InvestorController/addComplain");
	links.put("Add Resource","/Form_v_1.0/AssetController/addResource");
	links.put("Add Program","/Form_v_1.0/InvestorController/addProgram");
	return links;
	}
	
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public Integer getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getAppFile1() {
		return appFile1;
	}
	public void setAppFile1(String appFile1) {
		this.appFile1 = appFile1;
	}
	public String getAppFile2() {
		return appFile2;
	}
	public void setAppFile2(String appFile2) {
		this.appFile2 = appFile2;
	}
	public String getAppFile3() {
		return appFile3;
	}
	public void setAppFile3(String appFile3) {
		this.appFile3 = appFile3;
	}
	public String getAppFile4() {
		return appFile4;
	}
	public void setAppFile4(String appFile4) {
		this.appFile4 = appFile4;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Program other = (Program) obj;
		if (appFile1 == null) {
			if (other.appFile1 != null)
				return false;
		} else if (!appFile1.equals(other.appFile1))
			return false;
		if (appFile2 == null) {
			if (other.appFile2 != null)
				return false;
		} else if (!appFile2.equals(other.appFile2))
			return false;
		if (appFile3 == null) {
			if (other.appFile3 != null)
				return false;
		} else if (!appFile3.equals(other.appFile3))
			return false;
		if (appFile4 == null) {
			if (other.appFile4 != null)
				return false;
		} else if (!appFile4.equals(other.appFile4))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (investorId != other.investorId)
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Program [programName=" + programName + ", investorId="
				+ investorId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", description=" + description + ", appFile1="
				+ appFile1 + ", appFile2=" + appFile2 + ", appFile3="
				+ appFile3 + ", appFile4=" + appFile4 + "]";
	}
	
}
