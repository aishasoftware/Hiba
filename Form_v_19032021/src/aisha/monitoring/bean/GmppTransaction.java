package aisha.monitoring.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class GmppTransaction {
private String tranType;
private String tranErrorCode;
private String tranErrorMessage;
private String tranErrorDescription;
private String trnGmppResponse;
private String tranCSHResponse;
private String tranBPGResponse;
private String tranTWOResponse;
private String tranFeeEngineResponse;
private String tranCount;
private Date creationTime;

private HashMap<String, Object> searchCriteria;

public static ArrayList<String> getSearchFields() {
	ArrayList<String> beanFields = new ArrayList<String>();
	beanFields.add(0, "tranType");
	beanFields.add(1, "tranErrorCode");
	beanFields.add(2, "trnGmppResponse");
	beanFields.add(3, "fromDate");
	beanFields.add(4, "toDate");
	return beanFields;
}


public static ArrayList<String> getTableFields() {
	ArrayList<String> beanFields = new ArrayList<String>();
	beanFields.add(0, "tranType");
	beanFields.add(1, "tranErrorCode");
	beanFields.add(2, "trnGmppResponse");
	beanFields.add(3, "tranCount");
	beanFields.add(4, "creationTime");
	return beanFields;
}

public String getTranType() {
	return tranType;
}
public void setTranType(String tranType) {
	this.tranType = tranType;
}
public String getTranErrorCode() {
	return tranErrorCode;
}
public void setTranErrorCode(String tranErrorCode) {
	this.tranErrorCode = tranErrorCode;
}
public String getTranErrorMessage() {
	return tranErrorMessage;
}
public void setTranErrorMessage(String tranErrorMessage) {
	this.tranErrorMessage = tranErrorMessage;
}
public String getTranErrorDescription() {
	return tranErrorDescription;
}
public void setTranErrorDescription(String tranErrorDescription) {
	this.tranErrorDescription = tranErrorDescription;
}
public String getTrnGmppResponse() {
	return trnGmppResponse;
}
public void setTrnGmppResponse(String trnGmppResponse) {
	this.trnGmppResponse = trnGmppResponse;
}
public String getTranCSHResponse() {
	return tranCSHResponse;
}
public void setTranCSHResponse(String tranCSHResponse) {
	this.tranCSHResponse = tranCSHResponse;
}
public String getTranBPGResponse() {
	return tranBPGResponse;
}
public void setTranBPGResponse(String tranBPGResponse) {
	this.tranBPGResponse = tranBPGResponse;
}
public String getTranTWOResponse() {
	return tranTWOResponse;
}
public void setTranTWOResponse(String tranTWOResponse) {
	this.tranTWOResponse = tranTWOResponse;
}
public String getTranFeeEngineResponse() {
	return tranFeeEngineResponse;
}
public void setTranFeeEngineResponse(String tranFeeEngineResponse) {
	this.tranFeeEngineResponse = tranFeeEngineResponse;
}
public String getTranCount() {
	return tranCount;
}
public void setTranCount(String tranCount) {
	this.tranCount = tranCount;
}

public Date getCreationTime() {
	return creationTime;
}

public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
}

public HashMap<String, Object> getSearchCriteria() {
	return searchCriteria;
}

public void setSearchCriteria(HashMap<String, Object> searchCriteria) {
	this.searchCriteria = searchCriteria;
}

@Override
public String toString() {
	return "GmppTransaction [tranType=" + tranType + ", tranErrorCode=" + tranErrorCode + ", tranErrorMessage="
			+ tranErrorMessage + ", tranErrorDescription=" + tranErrorDescription + ", trnGmppResponse="
			+ trnGmppResponse + ", tranCSHResponse=" + tranCSHResponse + ", tranBPGResponse=" + tranBPGResponse
			+ ", tranTWOResponse=" + tranTWOResponse + ", tranFeeEngineResponse=" + tranFeeEngineResponse
			+ ", tranCount=" + tranCount + ", creationTime=" + creationTime + ", searchCriteria=" + searchCriteria
			+ "]";
}


}
