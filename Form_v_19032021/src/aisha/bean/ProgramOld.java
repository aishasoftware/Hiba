package aisha.bean;

import java.util.ArrayList;
import java.util.Date;

public class ProgramOld extends BasicBean{
	
	
	String NAME;
	String CREATED_BY;

    Date CREATION_TIME;
	Date LAST_MODIFICATION_TIME;
	String LAST_MODIFICATION_BY;
	String DESCRIPTION;
	String NO_OF_EVALUATORS;
	String FIRST_EVALUATOR;
	long id;
	String INVESTOR_ID;
	String SECOND_EVALUATOR;
	String THIRD_EVALUATOR;
	String TOTAL_POINTS;
	Investor investor_id;
	
	
	
	
	
	

	public Investor getInvestor_id() {
		return investor_id;
	}

	public void setInvestor_id(Investor investor_id) {
		this.investor_id = investor_id;
	}

	public static ArrayList<String> getTableFields() {
		ArrayList<String> beanFields = new ArrayList<String>();
		beanFields.add(0, "NAME");
		beanFields.add(1, "DESCRIPTION");
		return beanFields;
	}
	
	public static ArrayList<String> getSearchFields() {
		ArrayList<String> beanFields = new ArrayList<String>();
		beanFields.add(0, "NAME");
		beanFields.add(1, "fromDate");
		beanFields.add(2, "toDate");
		return beanFields;
	}
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}
	public Date getCREATION_TIME() {
		return CREATION_TIME;
	}
	public void setCREATION_TIME(Date cREATION_TIME) {
		CREATION_TIME = cREATION_TIME;
	}
	public Date getLAST_MODIFICATION_TIME() {
		return LAST_MODIFICATION_TIME;
	}
	public void setLAST_MODIFICATION_TIME(Date lAST_MODIFICATION_TIME) {
		LAST_MODIFICATION_TIME = lAST_MODIFICATION_TIME;
	}
	public String getLAST_MODIFICATION_BY() {
		return LAST_MODIFICATION_BY;
	}
	public void setLAST_MODIFICATION_BY(String lAST_MODIFICATION_BY) {
		LAST_MODIFICATION_BY = lAST_MODIFICATION_BY;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getNO_OF_EVALUATORS() {
		return NO_OF_EVALUATORS;
	}
	public void setNO_OF_EVALUATORS(String nO_OF_EVALUATORS) {
		NO_OF_EVALUATORS = nO_OF_EVALUATORS;
	}
	public String getFIRST_EVALUATOR() {
		return FIRST_EVALUATOR;
	}
	public void setFIRST_EVALUATOR(String fIRST_EVALUATOR) {
		FIRST_EVALUATOR = fIRST_EVALUATOR;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getINVESTOR_ID() {
		return INVESTOR_ID;
	}
	public void setINVESTOR_ID(String iNVESTOR_ID) {
		INVESTOR_ID = iNVESTOR_ID;
	}
	public String getSECOND_EVALUATOR() {
		return SECOND_EVALUATOR;
	}
	public void setSECOND_EVALUATOR(String sECOND_EVALUATOR) {
		SECOND_EVALUATOR = sECOND_EVALUATOR;
	}
	public String getTHIRD_EVALUATOR() {
		return THIRD_EVALUATOR;
	}
	public void setTHIRD_EVALUATOR(String tHIRD_EVALUATOR) {
		THIRD_EVALUATOR = tHIRD_EVALUATOR;
	}
	public String getTOTAL_POINTS() {
		return TOTAL_POINTS;
	}
	public void setTOTAL_POINTS(String tOTAL_POINTS) {
		TOTAL_POINTS = tOTAL_POINTS;
	}

	@Override
	public String toString() {
		return "Program [NAME=" + NAME + ", CREATED_BY=" + CREATED_BY
				+ ", CREATION_TIME=" + CREATION_TIME
				+ ", LAST_MODIFICATION_TIME=" + LAST_MODIFICATION_TIME
				+ ", LAST_MODIFICATION_BY=" + LAST_MODIFICATION_BY
				+ ", DESCRIPTION=" + DESCRIPTION + ", NO_OF_EVALUATORS="
				+ NO_OF_EVALUATORS + ", FIRST_EVALUATOR=" + FIRST_EVALUATOR
				+ ", id=" + id + ", INVESTOR_ID=" + INVESTOR_ID
				+ ", SECOND_EVALUATOR=" + SECOND_EVALUATOR
				+ ", THIRD_EVALUATOR=" + THIRD_EVALUATOR + ", TOTAL_POINTS="
				+ TOTAL_POINTS + ", investor=" + investor_id + "]";
	}


}
