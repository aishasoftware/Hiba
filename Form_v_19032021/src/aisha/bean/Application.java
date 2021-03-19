package aisha.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application extends BasicBean{
	private String progName;
	
	
	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	private String eva_1;
	private String eva_2;
	private String eva_3;
	private String eva_4;
	private String eva_5;
	
	private String intv_1;
	private String intv_2;
	private String intv_3;
	private String intv_4;
	private String intv_5;
	
	private String createdBy;
	private String lastModifiedBy;
	private Date creationTime;
	private Date lastModificationTime;
	private int version;
	private String applicantType;
	private List<Application> results = new ArrayList<>();
	private int firstPage;
	private int maxResult;
	private int totalResult;
	private long id;
	private Long app_Id;
	private Long applicantId;
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
private String field11;
private String field12;
private String field13;
private String field14;
private String field15;
private String field16;
private String field17;
private String field18;
private String field19;
private String field20;
private String field21;
private String field22;
private String field23;
private String field24;
private String field25;
private String field26;
private String field27;
private String field28;
private String field29;
private String field30;

private String appStatus;
private String interviewComment;
private String reviewComment;
private String ev_1_comment;
private String ev_2_comment;
private String ev_3_comment;
private String ev_4_comment;
private String ev_5_comment;

private Float ev_1_score = 0.00f;
private Float ev_2_score = 0.00f;
private Float ev_3_score = 0.00f;
private Float ev_4_score = 0.00f;
private Float ev_5_score = 0.00f;

private String intv_1_comment;
private String intv_2_comment;
private String intv_3_comment;
private String intv_4_comment;
private String intv_5_comment;

private Float intv_1_score = 0.00f;
private Float intv_2_score = 0.00f;
private Float intv_3_score = 0.00f;
private Float intv_4_score = 0.00f;
private Float intv_5_score = 0.00f;

private Float ev_avg_score = 0.00f;
private Float intv_avg_score = 0.00f;
private Float final_score;
private Float interview_score;
private Date submTime;
private Integer noOfEvaluators = 0;
private Integer noOfInterviewers  = 0;
private String status;
private boolean orderAsc;
private String orderBy = "id";	


public String getIntv_1() {
	return intv_1;
}

public void setIntv_1(String intv_1) {
	this.intv_1 = intv_1;
}

public String getIntv_2() {
	return intv_2;
}

public void setIntv_2(String intv_2) {
	this.intv_2 = intv_2;
}

public String getIntv_3() {
	return intv_3;
}

public void setIntv_3(String intv_3) {
	this.intv_3 = intv_3;
}

public String getIntv_4() {
	return intv_4;
}

public void setIntv_4(String intv_4) {
	this.intv_4 = intv_4;
}

public String getIntv_5() {
	return intv_5;
}

public void setIntv_5(String intv_5) {
	this.intv_5 = intv_5;
}

public String getIntv_1_comment() {
	return intv_1_comment;
}

public void setIntv_1_comment(String intv_1_comment) {
	this.intv_1_comment = intv_1_comment;
}

public String getIntv_2_comment() {
	return intv_2_comment;
}

public void setIntv_2_comment(String intv_2_comment) {
	this.intv_2_comment = intv_2_comment;
}

public String getIntv_3_comment() {
	return intv_3_comment;
}

public void setIntv_3_comment(String intv_3_comment) {
	this.intv_3_comment = intv_3_comment;
}

public String getIntv_4_comment() {
	return intv_4_comment;
}

public void setIntv_4_comment(String intv_4_comment) {
	this.intv_4_comment = intv_4_comment;
}

public String getIntv_5_comment() {
	return intv_5_comment;
}

public void setIntv_5_comment(String intv_5_comment) {
	this.intv_5_comment = intv_5_comment;
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

public Float getIntv_1_score() {
	return intv_1_score;
}

public void setIntv_1_score(Float intv_1_score) {
	this.intv_1_score = intv_1_score;
}

public Float getIntv_2_score() {
	return intv_2_score;
}

public void setIntv_2_score(Float intv_2_score) {
	this.intv_2_score = intv_2_score;
}

public Float getIntv_3_score() {
	return intv_3_score;
}

public void setIntv_3_score(Float intv_3_score) {
	this.intv_3_score = intv_3_score;
}

public Float getIntv_4_score() {
	return intv_4_score;
}

public void setIntv_4_score(Float intv_4_score) {
	this.intv_4_score = intv_4_score;
}

public Float getIntv_5_score() {
	return intv_5_score;
}

public void setIntv_5_score(Float intv_5_score) {
	this.intv_5_score = intv_5_score;
}

public Float getIntv_avg_score() {
	return intv_avg_score;
}

public void setIntv_avg_score(Float intv_avg_score) {
	this.intv_avg_score = intv_avg_score;
}

public Integer getNoOfInterviewers() {
	return noOfInterviewers;
}

public void setNoOfInterviewers(Integer noOfInterviewers) {
	this.noOfInterviewers = noOfInterviewers;
}

public String getApplicantType() {
	return applicantType;
}

public void setApplicantType(String applicantType) {
	this.applicantType = applicantType;
}

public static ArrayList<String> getPublicFields() {

	ArrayList<String> publicFields = new ArrayList<String>();

	publicFields.add(0, "field1");
	return publicFields;

}

public static ArrayList<String> getEvaluationFields() {

	ArrayList<String> evaluationFields = new ArrayList<String>();

	evaluationFields.add(0, "eva_score");
	evaluationFields.add(1, "eva_comment");
	evaluationFields.add(2, "intv_score");
	evaluationFields.add(3, "intv_comment");
	//evaluationFields.add(4, "appStatus");
	return evaluationFields;

}

public static ArrayList<String> getViewFields() {

	ArrayList<String> addFields = new ArrayList<String>();
	addFields.add(0, "field1");
	addFields.add(1, "field2");
	addFields.add(2, "eva_score11");
	addFields.add(3, "eva_score11");
	addFields.add(4, "field5");
	addFields.add(5, "field6");
	addFields.add(6, "field7");
	addFields.add(7, "field8");
	addFields.add(8, "field9");
	addFields.add(9, "field10");
	addFields.add(10, "field11");
	addFields.add(11, "field12");
	addFields.add(12, "field13");
	addFields.add(13, "field14");
	addFields.add(14, "field15");
	addFields.add(15, "field16");
	addFields.add(16, "field17");
	addFields.add(17, "field18");
	addFields.add(18, "field19");
	addFields.add(19, "field20");
	addFields.add(20, "field21");
	addFields.add(21, "field22");
	addFields.add(22, "field23");
	addFields.add(23, "field24");
	addFields.add(24, "field25");
	addFields.add(25, "field26");
	addFields.add(26, "field27");
	addFields.add(27, "field28");
	addFields.add(28, "field29");
	addFields.add(29, "field30");
//	addFields.add(30, "status");
	
	return addFields;
}

public static ArrayList<String> getSearchFields() {

	ArrayList<String> searchFields = new ArrayList<String>();
	searchFields.add(0, "fromCreate");
	searchFields.add(1, "toCreate");
	searchFields.add(2, "applicantId");
	//searchFields.add(3, "field2");
	return searchFields;

}

public static ArrayList<String> getAdminFields() {

	ArrayList<String> adminFields = new ArrayList<String>();
	adminFields.add(0, "status");
	adminFields.add(1, "ev_1_score");
	adminFields.add(2, "ev_2_score");
	adminFields.add(3, "ev_3_score");
	adminFields.add(4, "ev_4_score");
	adminFields.add(5, "ev_5_score");
	adminFields.add(6, "intv_1_score");
	adminFields.add(7, "intv_2_score");
	adminFields.add(8, "intv_3_score");
	adminFields.add(9, "intv_4_score");
	adminFields.add(10, "intv_5_score");
	adminFields.add(11, "ev_avg_score");
	adminFields.add(12, "intv_avg_score");
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
	addFields.add(0, "field1");
	addFields.add(1, "field2");
	addFields.add(2, "field3");
	addFields.add(3, "field13");
	addFields.add(4, "field14");
	addFields.add(5, "field15");
	addFields.add(6, "field10");
	addFields.add(7, "field11");
	addFields.add(8, "field12");
	addFields.add(9, "status");
	addFields.add(10, "noOfInterviewers");
	addFields.add(11, "noOfEvaluators");
/*		addFields.add(6, "field7");
	addFields.add(7, "field8");
	addFields.add(8, "field9");
	addFields.add(9, "field10");
	addFields.add(10, "talents");*/
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

public String getReviewComment() {
	return reviewComment;
}
public void setReviewComment(String reviewComment) {
	this.reviewComment = reviewComment;
}

public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}
public boolean isOrderAsc() {
	return orderAsc;
}
public void setOrderAsc(boolean orderAsc) {
	this.orderAsc = orderAsc;
}
public String getOrderBy() {
	return orderBy;
}
public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
}
public Float getEvaluation(int evaluator)
{
if(evaluator == 1)
	return ev_1_score;
if(evaluator == 2)
	return ev_2_score;
if(evaluator == 3)
	return ev_3_score;
if(evaluator == 4)
	return ev_4_score;
if(evaluator == 5)
	return ev_5_score;
else return null;
}
public int getFirstPage() {
	return firstPage;
}

public void setFirstPage(int firstPage) {
	this.firstPage = firstPage;
}

public int getMaxResult() {
	return maxResult;
}

public void setMaxResult(int maxResult) {
	this.maxResult = maxResult;
}

public int getTotalResult() {
	return totalResult;
}

public void setTotalResult(int totalResult) {
	this.totalResult = totalResult;
}



public Integer getNoOfEvaluators() {
	return noOfEvaluators;
}

public void setNoOfEvaluators(Integer noOfEvaluators) {
	this.noOfEvaluators = noOfEvaluators;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

private HashMap<String,Object> criteria =  new HashMap<>();

public HashMap<String, Object> getCriteria() {
	return criteria;
}

public void setCriteria(HashMap<String, Object> criteria) {
	this.criteria = criteria;
}

public Long getApp_Id() {
	return app_Id;
}

public void setApp_Id(Long app_Id) {
	this.app_Id = app_Id;
}

public Long getApplicantId() {
	return applicantId;
}

public void setApplicantId(Long applicantId) {
	this.applicantId = applicantId;
}

public Float getInterview_score() {
	return interview_score;
}

public void setInterview_score(Float interview_score) {
	this.interview_score = interview_score;
}

public String getField16() {
	return field16;
}

public void setField16(String field16) {
	this.field16 = field16;
}

public String getField17() {
	return field17;
}

public void setField17(String field17) {
	this.field17 = field17;
}

public String getField18() {
	return field18;
}

public void setField18(String field18) {
	this.field18 = field18;
}

public String getField19() {
	return field19;
}

public void setField19(String field19) {
	this.field19 = field19;
}

public String getField20() {
	return field20;
}

public void setField20(String field20) {
	this.field20 = field20;
}

public String getField21() {
	return field21;
}

public void setField21(String field21) {
	this.field21 = field21;
}

public String getField22() {
	return field22;
}

public void setField22(String field22) {
	this.field22 = field22;
}

public String getField23() {
	return field23;
}

public void setField23(String field23) {
	this.field23 = field23;
}

public String getField24() {
	return field24;
}

public void setField24(String field24) {
	this.field24 = field24;
}

public String getField25() {
	return field25;
}

public void setField25(String field25) {
	this.field25 = field25;
}

public String getField26() {
	return field26;
}

public void setField26(String field26) {
	this.field26 = field26;
}

public String getField27() {
	return field27;
}

public void setField27(String field27) {
	this.field27 = field27;
}

public String getField28() {
	return field28;
}

public void setField28(String field28) {
	this.field28 = field28;
}

public String getField29() {
	return field29;
}

public void setField29(String field29) {
	this.field29 = field29;
}

public String getField30() {
	return field30;
}

public void setField30(String field30) {
	this.field30 = field30;
}

public String getAppStatus() {
	return appStatus;
}

public void setAppStatus(String appStatus) {
	this.appStatus = appStatus;
}

public String getInterviewComment() {
	return interviewComment;
}

public void setInterviewComment(String interviewComment) {
	this.interviewComment = interviewComment;
}

public String getEv_1_comment() {
	return ev_1_comment;
}

public void setEv_1_comment(String ev_1_comment) {
	this.ev_1_comment = ev_1_comment;
}

public String getEv_2_comment() {
	return ev_2_comment;
}

public void setEv_2_comment(String ev_2_comment) {
	this.ev_2_comment = ev_2_comment;
}

public String getEv_3_comment() {
	return ev_3_comment;
}

public void setEv_3_comment(String ev_3_comment) {
	this.ev_3_comment = ev_3_comment;
}

public String getEv_4_comment() {
	return ev_4_comment;
}

public void setEv_4_comment(String ev_4_comment) {
	this.ev_4_comment = ev_4_comment;
}

public String getEv_5_comment() {
	return ev_5_comment;
}

public void setEv_5_comment(String ev_5_comment) {
	this.ev_5_comment = ev_5_comment;
}

public Float getEv_1_score() {
	return ev_1_score;
}

public void setEv_1_score(Float ev_1_score) {
	this.ev_1_score = ev_1_score;
}

public Float getEv_2_score() {
	return ev_2_score;
}

public void setEv_2_score(Float ev_2_score) {
	this.ev_2_score = ev_2_score;
}

public Float getEv_3_score() {
	return ev_3_score;
}

public void setEv_3_score(Float ev_3_score) {
	this.ev_3_score = ev_3_score;
}

public Float getEv_4_score() {
	return ev_4_score;
}

public void setEv_4_score(Float ev_4_score) {
	this.ev_4_score = ev_4_score;
}

public Float getEv_5_score() {
	return ev_5_score;
}

public void setEv_5_score(Float ev_5_score) {
	this.ev_5_score = ev_5_score;
}

public Float getEv_avg_score() {
	return ev_avg_score;
}

public void setEv_avg_score(Float ev_avg_score) {
	this.ev_avg_score = ev_avg_score;
}

public Float getFinal_score() {
	return final_score;
}

public void setFinal_score(Float final_score) {
	this.final_score = final_score;
}

public Date getSubmTime() {
	return submTime;
}

public void setSubmTime(Date submTime) {
	this.submTime = submTime;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public static ArrayList<String> getTableFields() {
	ArrayList<String> beanFields = new ArrayList<String>();
	beanFields.add(0, "id");
	beanFields.add(1, "applicantId");
	beanFields.add(2, "appStatus");
	beanFields.add(3, "submTime");
	
    return beanFields;
}

public static ArrayList<String> getInterviewerFields(int interviewer) {
	ArrayList<String> beanFields = new ArrayList<String>();
	
	if (interviewer == 1)
	{
	beanFields.add(0, "ev_1_score");
	beanFields.add(1, "ev_1_comment");
	
	}
	
	if (interviewer == 2)	
	{
	beanFields.add(0, "ev_2_score");
	beanFields.add(1, "ev_2_comment");
	}
	
	if (interviewer == 3)
	{
	beanFields.add(0, "ev_3_score");
	beanFields.add(1, "ev_3_comment");
	}
	if (interviewer == 4)
	{
	beanFields.add(0, "ev_4_score");
	beanFields.add(1, "ev_4_comment");
	}
	if (interviewer == 5)
	{
	beanFields.add(0, "ev_5_score");
	beanFields.add(1, "ev_5_comment");
	}
	
	return beanFields;
}


public static ArrayList<String> getAllFields() {
	ArrayList<String> beanFields = new ArrayList<String>();
	
	beanFields.add(0, "ev_1_score");
	beanFields.add(1, "ev_1_comment");

	beanFields.add(2, "ev_2_score");
	beanFields.add(3, "ev_2_comment");

	beanFields.add(4, "ev_3_score");
	beanFields.add(5, "ev_3_comment");

/*	beanFields.add(6, "ev_4_score");
	beanFields.add(7, "ev_4_comment");

	beanFields.add(8, "ev_5_score");
	beanFields.add(9, "ev_5_comment");*/

	beanFields.add(6, "interview_score");
	beanFields.add(7, "interviewComment");
	beanFields.add(8, "ev_avg_score");
	beanFields.add(8, "appStatus");
	return beanFields;
}


public static ArrayList<String> getEvaluatorFields(int evaluator) {
	ArrayList<String> beanFields = new ArrayList<String>();
	if (evaluator == 1)
	{
	beanFields.add(0, "ev_1_score");
	beanFields.add(1, "ev_1_comment");
	
	}
	
	if (evaluator == 2)	
	{
	beanFields.add(0, "ev_2_score");
	beanFields.add(1, "ev_2_comment");
	}
	
	if (evaluator == 3)
	{
	beanFields.add(0, "ev_3_score");
	beanFields.add(1, "ev_3_comment");
	}
	if (evaluator == 4)
	{
	beanFields.add(0, "ev_4_score");
	beanFields.add(1, "ev_4_comment");
	}
	if (evaluator == 5)
	{
	beanFields.add(0, "ev_5_score");
	beanFields.add(1, "ev_5_comment");
	}
	

	
return beanFields;
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

@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((appStatus == null) ? 0 : appStatus.hashCode());
	result = prime * result + ((app_Id == null) ? 0 : app_Id.hashCode());
	result = prime * result
			+ ((applicantId == null) ? 0 : applicantId.hashCode());
	result = prime * result
			+ ((applicantType == null) ? 0 : applicantType.hashCode());
	result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
	result = prime * result
			+ ((creationTime == null) ? 0 : creationTime.hashCode());
	result = prime * result + ((criteria == null) ? 0 : criteria.hashCode());
	result = prime * result
			+ ((ev_1_comment == null) ? 0 : ev_1_comment.hashCode());
	result = prime * result
			+ ((ev_1_score == null) ? 0 : ev_1_score.hashCode());
	result = prime * result
			+ ((ev_2_comment == null) ? 0 : ev_2_comment.hashCode());
	result = prime * result
			+ ((ev_2_score == null) ? 0 : ev_2_score.hashCode());
	result = prime * result
			+ ((ev_3_comment == null) ? 0 : ev_3_comment.hashCode());
	result = prime * result
			+ ((ev_3_score == null) ? 0 : ev_3_score.hashCode());
	result = prime * result
			+ ((ev_4_comment == null) ? 0 : ev_4_comment.hashCode());
	result = prime * result
			+ ((ev_4_score == null) ? 0 : ev_4_score.hashCode());
	result = prime * result
			+ ((ev_5_comment == null) ? 0 : ev_5_comment.hashCode());
	result = prime * result
			+ ((ev_5_score == null) ? 0 : ev_5_score.hashCode());
	result = prime * result
			+ ((ev_avg_score == null) ? 0 : ev_avg_score.hashCode());
	result = prime * result + ((eva_1 == null) ? 0 : eva_1.hashCode());
	result = prime * result + ((eva_2 == null) ? 0 : eva_2.hashCode());
	result = prime * result + ((eva_3 == null) ? 0 : eva_3.hashCode());
	result = prime * result + ((eva_4 == null) ? 0 : eva_4.hashCode());
	result = prime * result + ((eva_5 == null) ? 0 : eva_5.hashCode());
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
			+ ((final_score == null) ? 0 : final_score.hashCode());
	result = prime * result + firstPage;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result
			+ ((interviewComment == null) ? 0 : interviewComment.hashCode());
	result = prime * result
			+ ((interview_score == null) ? 0 : interview_score.hashCode());
	result = prime * result + ((intv_1 == null) ? 0 : intv_1.hashCode());
	result = prime * result
			+ ((intv_1_comment == null) ? 0 : intv_1_comment.hashCode());
	result = prime * result
			+ ((intv_1_score == null) ? 0 : intv_1_score.hashCode());
	result = prime * result + ((intv_2 == null) ? 0 : intv_2.hashCode());
	result = prime * result
			+ ((intv_2_comment == null) ? 0 : intv_2_comment.hashCode());
	result = prime * result
			+ ((intv_2_score == null) ? 0 : intv_2_score.hashCode());
	result = prime * result + ((intv_3 == null) ? 0 : intv_3.hashCode());
	result = prime * result
			+ ((intv_3_comment == null) ? 0 : intv_3_comment.hashCode());
	result = prime * result
			+ ((intv_3_score == null) ? 0 : intv_3_score.hashCode());
	result = prime * result + ((intv_4 == null) ? 0 : intv_4.hashCode());
	result = prime * result
			+ ((intv_4_comment == null) ? 0 : intv_4_comment.hashCode());
	result = prime * result
			+ ((intv_4_score == null) ? 0 : intv_4_score.hashCode());
	result = prime * result + ((intv_5 == null) ? 0 : intv_5.hashCode());
	result = prime * result
			+ ((intv_5_comment == null) ? 0 : intv_5_comment.hashCode());
	result = prime * result
			+ ((intv_5_score == null) ? 0 : intv_5_score.hashCode());
	result = prime * result
			+ ((intv_avg_score == null) ? 0 : intv_avg_score.hashCode());
	result = prime
			* result
			+ ((lastModificationTime == null) ? 0 : lastModificationTime
					.hashCode());
	result = prime * result
			+ ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
	result = prime * result + maxResult;
	result = prime * result
			+ ((noOfEvaluators == null) ? 0 : noOfEvaluators.hashCode());
	result = prime * result
			+ ((noOfInterviewers == null) ? 0 : noOfInterviewers.hashCode());
	result = prime * result + (orderAsc ? 1231 : 1237);
	result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
	result = prime * result + ((progName == null) ? 0 : progName.hashCode());
	result = prime * result + ((results == null) ? 0 : results.hashCode());
	result = prime * result
			+ ((reviewComment == null) ? 0 : reviewComment.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((submTime == null) ? 0 : submTime.hashCode());
	result = prime * result + totalResult;
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
	Application other = (Application) obj;
	if (appStatus == null) {
		if (other.appStatus != null)
			return false;
	} else if (!appStatus.equals(other.appStatus))
		return false;
	if (app_Id == null) {
		if (other.app_Id != null)
			return false;
	} else if (!app_Id.equals(other.app_Id))
		return false;
	if (applicantId == null) {
		if (other.applicantId != null)
			return false;
	} else if (!applicantId.equals(other.applicantId))
		return false;
	if (applicantType == null) {
		if (other.applicantType != null)
			return false;
	} else if (!applicantType.equals(other.applicantType))
		return false;
	if (createdBy == null) {
		if (other.createdBy != null)
			return false;
	} else if (!createdBy.equals(other.createdBy))
		return false;
	if (creationTime == null) {
		if (other.creationTime != null)
			return false;
	} else if (!creationTime.equals(other.creationTime))
		return false;
	if (criteria == null) {
		if (other.criteria != null)
			return false;
	} else if (!criteria.equals(other.criteria))
		return false;
	if (ev_1_comment == null) {
		if (other.ev_1_comment != null)
			return false;
	} else if (!ev_1_comment.equals(other.ev_1_comment))
		return false;
	if (ev_1_score == null) {
		if (other.ev_1_score != null)
			return false;
	} else if (!ev_1_score.equals(other.ev_1_score))
		return false;
	if (ev_2_comment == null) {
		if (other.ev_2_comment != null)
			return false;
	} else if (!ev_2_comment.equals(other.ev_2_comment))
		return false;
	if (ev_2_score == null) {
		if (other.ev_2_score != null)
			return false;
	} else if (!ev_2_score.equals(other.ev_2_score))
		return false;
	if (ev_3_comment == null) {
		if (other.ev_3_comment != null)
			return false;
	} else if (!ev_3_comment.equals(other.ev_3_comment))
		return false;
	if (ev_3_score == null) {
		if (other.ev_3_score != null)
			return false;
	} else if (!ev_3_score.equals(other.ev_3_score))
		return false;
	if (ev_4_comment == null) {
		if (other.ev_4_comment != null)
			return false;
	} else if (!ev_4_comment.equals(other.ev_4_comment))
		return false;
	if (ev_4_score == null) {
		if (other.ev_4_score != null)
			return false;
	} else if (!ev_4_score.equals(other.ev_4_score))
		return false;
	if (ev_5_comment == null) {
		if (other.ev_5_comment != null)
			return false;
	} else if (!ev_5_comment.equals(other.ev_5_comment))
		return false;
	if (ev_5_score == null) {
		if (other.ev_5_score != null)
			return false;
	} else if (!ev_5_score.equals(other.ev_5_score))
		return false;
	if (ev_avg_score == null) {
		if (other.ev_avg_score != null)
			return false;
	} else if (!ev_avg_score.equals(other.ev_avg_score))
		return false;
	if (eva_1 == null) {
		if (other.eva_1 != null)
			return false;
	} else if (!eva_1.equals(other.eva_1))
		return false;
	if (eva_2 == null) {
		if (other.eva_2 != null)
			return false;
	} else if (!eva_2.equals(other.eva_2))
		return false;
	if (eva_3 == null) {
		if (other.eva_3 != null)
			return false;
	} else if (!eva_3.equals(other.eva_3))
		return false;
	if (eva_4 == null) {
		if (other.eva_4 != null)
			return false;
	} else if (!eva_4.equals(other.eva_4))
		return false;
	if (eva_5 == null) {
		if (other.eva_5 != null)
			return false;
	} else if (!eva_5.equals(other.eva_5))
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
	if (final_score == null) {
		if (other.final_score != null)
			return false;
	} else if (!final_score.equals(other.final_score))
		return false;
	if (firstPage != other.firstPage)
		return false;
	if (id != other.id)
		return false;
	if (interviewComment == null) {
		if (other.interviewComment != null)
			return false;
	} else if (!interviewComment.equals(other.interviewComment))
		return false;
	if (interview_score == null) {
		if (other.interview_score != null)
			return false;
	} else if (!interview_score.equals(other.interview_score))
		return false;
	if (intv_1 == null) {
		if (other.intv_1 != null)
			return false;
	} else if (!intv_1.equals(other.intv_1))
		return false;
	if (intv_1_comment == null) {
		if (other.intv_1_comment != null)
			return false;
	} else if (!intv_1_comment.equals(other.intv_1_comment))
		return false;
	if (intv_1_score == null) {
		if (other.intv_1_score != null)
			return false;
	} else if (!intv_1_score.equals(other.intv_1_score))
		return false;
	if (intv_2 == null) {
		if (other.intv_2 != null)
			return false;
	} else if (!intv_2.equals(other.intv_2))
		return false;
	if (intv_2_comment == null) {
		if (other.intv_2_comment != null)
			return false;
	} else if (!intv_2_comment.equals(other.intv_2_comment))
		return false;
	if (intv_2_score == null) {
		if (other.intv_2_score != null)
			return false;
	} else if (!intv_2_score.equals(other.intv_2_score))
		return false;
	if (intv_3 == null) {
		if (other.intv_3 != null)
			return false;
	} else if (!intv_3.equals(other.intv_3))
		return false;
	if (intv_3_comment == null) {
		if (other.intv_3_comment != null)
			return false;
	} else if (!intv_3_comment.equals(other.intv_3_comment))
		return false;
	if (intv_3_score == null) {
		if (other.intv_3_score != null)
			return false;
	} else if (!intv_3_score.equals(other.intv_3_score))
		return false;
	if (intv_4 == null) {
		if (other.intv_4 != null)
			return false;
	} else if (!intv_4.equals(other.intv_4))
		return false;
	if (intv_4_comment == null) {
		if (other.intv_4_comment != null)
			return false;
	} else if (!intv_4_comment.equals(other.intv_4_comment))
		return false;
	if (intv_4_score == null) {
		if (other.intv_4_score != null)
			return false;
	} else if (!intv_4_score.equals(other.intv_4_score))
		return false;
	if (intv_5 == null) {
		if (other.intv_5 != null)
			return false;
	} else if (!intv_5.equals(other.intv_5))
		return false;
	if (intv_5_comment == null) {
		if (other.intv_5_comment != null)
			return false;
	} else if (!intv_5_comment.equals(other.intv_5_comment))
		return false;
	if (intv_5_score == null) {
		if (other.intv_5_score != null)
			return false;
	} else if (!intv_5_score.equals(other.intv_5_score))
		return false;
	if (intv_avg_score == null) {
		if (other.intv_avg_score != null)
			return false;
	} else if (!intv_avg_score.equals(other.intv_avg_score))
		return false;
	if (lastModificationTime == null) {
		if (other.lastModificationTime != null)
			return false;
	} else if (!lastModificationTime.equals(other.lastModificationTime))
		return false;
	if (lastModifiedBy == null) {
		if (other.lastModifiedBy != null)
			return false;
	} else if (!lastModifiedBy.equals(other.lastModifiedBy))
		return false;
	if (maxResult != other.maxResult)
		return false;
	if (noOfEvaluators == null) {
		if (other.noOfEvaluators != null)
			return false;
	} else if (!noOfEvaluators.equals(other.noOfEvaluators))
		return false;
	if (noOfInterviewers == null) {
		if (other.noOfInterviewers != null)
			return false;
	} else if (!noOfInterviewers.equals(other.noOfInterviewers))
		return false;
	if (orderAsc != other.orderAsc)
		return false;
	if (orderBy == null) {
		if (other.orderBy != null)
			return false;
	} else if (!orderBy.equals(other.orderBy))
		return false;
	if (progName == null) {
		if (other.progName != null)
			return false;
	} else if (!progName.equals(other.progName))
		return false;
	if (results == null) {
		if (other.results != null)
			return false;
	} else if (!results.equals(other.results))
		return false;
	if (reviewComment == null) {
		if (other.reviewComment != null)
			return false;
	} else if (!reviewComment.equals(other.reviewComment))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	if (submTime == null) {
		if (other.submTime != null)
			return false;
	} else if (!submTime.equals(other.submTime))
		return false;
	if (totalResult != other.totalResult)
		return false;
	if (version != other.version)
		return false;
	return true;
}

@Override
public String toString() {
	return "Application [progName=" + progName + ", eva_1=" + eva_1
			+ ", eva_2=" + eva_2 + ", eva_3=" + eva_3 + ", eva_4=" + eva_4
			+ ", eva_5=" + eva_5 + ", intv_1=" + intv_1 + ", intv_2=" + intv_2
			+ ", intv_3=" + intv_3 + ", intv_4=" + intv_4 + ", intv_5="
			+ intv_5 + ", createdBy=" + createdBy + ", lastModifiedBy="
			+ lastModifiedBy + ", creationTime=" + creationTime
			+ ", lastModificationTime=" + lastModificationTime + ", version="
			+ version + ", applicantType=" + applicantType + ", results="
			+ results + ", firstPage=" + firstPage + ", maxResult=" + maxResult
			+ ", totalResult=" + totalResult + ", id=" + id + ", app_Id="
			+ app_Id + ", applicantId=" + applicantId + ", field1=" + field1
			+ ", field2=" + field2 + ", field3=" + field3 + ", field4="
			+ field4 + ", field5=" + field5 + ", field6=" + field6
			+ ", field7=" + field7 + ", field8=" + field8 + ", field9="
			+ field9 + ", field10=" + field10 + ", field11=" + field11
			+ ", field12=" + field12 + ", field13=" + field13 + ", field14="
			+ field14 + ", field15=" + field15 + ", field16=" + field16
			+ ", field17=" + field17 + ", field18=" + field18 + ", field19="
			+ field19 + ", field20=" + field20 + ", field21=" + field21
			+ ", field22=" + field22 + ", field23=" + field23 + ", field24="
			+ field24 + ", field25=" + field25 + ", field26=" + field26
			+ ", field27=" + field27 + ", field28=" + field28 + ", field29="
			+ field29 + ", field30=" + field30 + ", appStatus=" + appStatus
			+ ", interviewComment=" + interviewComment + ", reviewComment="
			+ reviewComment + ", ev_1_comment=" + ev_1_comment
			+ ", ev_2_comment=" + ev_2_comment + ", ev_3_comment="
			+ ev_3_comment + ", ev_4_comment=" + ev_4_comment
			+ ", ev_5_comment=" + ev_5_comment + ", ev_1_score=" + ev_1_score
			+ ", ev_2_score=" + ev_2_score + ", ev_3_score=" + ev_3_score
			+ ", ev_4_score=" + ev_4_score + ", ev_5_score=" + ev_5_score
			+ ", intv_1_comment=" + intv_1_comment + ", intv_2_comment="
			+ intv_2_comment + ", intv_3_comment=" + intv_3_comment
			+ ", intv_4_comment=" + intv_4_comment + ", intv_5_comment="
			+ intv_5_comment + ", intv_1_score=" + intv_1_score
			+ ", intv_2_score=" + intv_2_score + ", intv_3_score="
			+ intv_3_score + ", intv_4_score=" + intv_4_score
			+ ", intv_5_score=" + intv_5_score + ", ev_avg_score="
			+ ev_avg_score + ", intv_avg_score=" + intv_avg_score
			+ ", final_score=" + final_score + ", interview_score="
			+ interview_score + ", submTime=" + submTime + ", noOfEvaluators="
			+ noOfEvaluators + ", noOfInterviewers=" + noOfInterviewers
			+ ", status=" + status + ", orderAsc=" + orderAsc + ", orderBy="
			+ orderBy + ", criteria=" + criteria + "]";
}



}
