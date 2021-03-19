package aisha.test;


/**
 * This class is the base class of any field in html file
 * 
 * @author mohamed.osman
 * 
 *
 */
public class Question {
	String qID;	
	String qType;
	int qMaxlength;	
	String qContent;	
	boolean required;
	int qBoxSze;
	String saveInField;
	String qContentArabic;
	
	
	
	public String getqContentArabic() {
		return qContentArabic;
	}
	public void setqContentArabic(String qContentArabic) {
		this.qContentArabic = qContentArabic;
	}
	public String getSaveInField() {
		return saveInField;
	}
	public void setSaveInField(String saveInField) {
		this.saveInField = saveInField;
	}
	public String getqID() {
		return qID;
	}
	public void setqID(String qID) {
		this.qID = qID;
	}
	public String getqType() {
		return qType;
	}
	public void setqType(String qType) {
		this.qType = qType;
	}
	public int getqMaxlength() {
		return qMaxlength;
	}
	public void setqMaxlength(int qMaxlength) {
		this.qMaxlength = qMaxlength;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public int getqBoxSze() {
		return qBoxSze;
	}
	public void setqBoxSze(int qBoxSze) {
		this.qBoxSze = qBoxSze;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + qBoxSze;
		result = prime * result + ((qContent == null) ? 0 : qContent.hashCode());
		result = prime * result + ((qContentArabic == null) ? 0 : qContentArabic.hashCode());
		result = prime * result + ((qID == null) ? 0 : qID.hashCode());
		result = prime * result + qMaxlength;
		result = prime * result + ((qType == null) ? 0 : qType.hashCode());
		result = prime * result + (required ? 1231 : 1237);
		result = prime * result + ((saveInField == null) ? 0 : saveInField.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (qBoxSze != other.qBoxSze)
			return false;
		if (qContent == null) {
			if (other.qContent != null)
				return false;
		} else if (!qContent.equals(other.qContent))
			return false;
		if (qContentArabic == null) {
			if (other.qContentArabic != null)
				return false;
		} else if (!qContentArabic.equals(other.qContentArabic))
			return false;
		if (qID == null) {
			if (other.qID != null)
				return false;
		} else if (!qID.equals(other.qID))
			return false;
		if (qMaxlength != other.qMaxlength)
			return false;
		if (qType == null) {
			if (other.qType != null)
				return false;
		} else if (!qType.equals(other.qType))
			return false;
		if (required != other.required)
			return false;
		if (saveInField == null) {
			if (other.saveInField != null)
				return false;
		} else if (!saveInField.equals(other.saveInField))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Question [qID=" + qID + ", qType=" + qType + ", qMaxlength=" + qMaxlength + ", qContent=" + qContent
				+ ", required=" + required + ", qBoxSze=" + qBoxSze + ", saveInField=" + saveInField
				+ ", qContentArabic=" + qContentArabic + "]";
	}
	

}
