package aisha.util.bean;


/**
 * This class is the base class of any field in html file
 * 
 * @author Aisha
 * 
 *
 */
public class Field {
	private String fID;	
	private String fType;
	private String fMaxlength;	
	private String fContentEnglish;	
	private String fRequired;
	private String fBoxSze;
	private String saveInField;
	private String fContentArabic;
	private String fRegExpr;
	private String fEnabledFor;
	private String fAccessedBy;
	
	public String getfID() {
		return fID;
	}
	public void setfID(String fID) {
		this.fID = fID;
	}
	public String getfType() {
		return fType;
	}
	public void setfType(String fType) {
		this.fType = fType;
	}
	public String getfMaxlength() {
		return fMaxlength;
	}
	public void setfMaxlength(String fMaxlength) {
		this.fMaxlength = fMaxlength;
	}
	public String getfContentEnglish() {
		return fContentEnglish;
	}
	public void setfContentEnglish(String fContentEnglish) {
		this.fContentEnglish = fContentEnglish;
	}

	public String getfRequired() {
		return fRequired;
	}
	public void setfRequired(String fRequired) {
		this.fRequired = fRequired;
	}
	public String getfBoxSze() {
		return fBoxSze;
	}
	public void setfBoxSze(String fBoxSze) {
		this.fBoxSze = fBoxSze;
	}
	public String getSaveInField() {
		return saveInField;
	}
	public void setSaveInField(String saveInField) {
		this.saveInField = saveInField;
	}
	public String getfContentArabic() {
		return fContentArabic;
	}
	public void setfContentArabic(String fContentArabic) {
		this.fContentArabic = fContentArabic;
	}
	public String getfRegExpr() {
		return fRegExpr;
	}
	public void setfRegExpr(String fRegExpr) {
		this.fRegExpr = fRegExpr;
	}
	public String getfEnabledFor() {
		return fEnabledFor;
	}
	public void setfEnabledFor(String fEnabledFor) {
		this.fEnabledFor = fEnabledFor;
	}
	public String getfAccessedBy() {
		return fAccessedBy;
	}
	public void setfAccessedBy(String fAccessedBy) {
		this.fAccessedBy = fAccessedBy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fAccessedBy == null) ? 0 : fAccessedBy.hashCode());
		result = prime * result + ((fBoxSze == null) ? 0 : fBoxSze.hashCode());
		result = prime * result + ((fContentArabic == null) ? 0 : fContentArabic.hashCode());
		result = prime * result + ((fContentEnglish == null) ? 0 : fContentEnglish.hashCode());
		result = prime * result + ((fEnabledFor == null) ? 0 : fEnabledFor.hashCode());
		result = prime * result + ((fID == null) ? 0 : fID.hashCode());
		result = prime * result + ((fMaxlength == null) ? 0 : fMaxlength.hashCode());
		result = prime * result + ((fRegExpr == null) ? 0 : fRegExpr.hashCode());
		result = prime * result + ((fRequired == null) ? 0 : fRequired.hashCode());
		result = prime * result + ((fType == null) ? 0 : fType.hashCode());
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
		Field other = (Field) obj;
		if (fAccessedBy == null) {
			if (other.fAccessedBy != null)
				return false;
		} else if (!fAccessedBy.equals(other.fAccessedBy))
			return false;
		if (fBoxSze == null) {
			if (other.fBoxSze != null)
				return false;
		} else if (!fBoxSze.equals(other.fBoxSze))
			return false;
		if (fContentArabic == null) {
			if (other.fContentArabic != null)
				return false;
		} else if (!fContentArabic.equals(other.fContentArabic))
			return false;
		if (fContentEnglish == null) {
			if (other.fContentEnglish != null)
				return false;
		} else if (!fContentEnglish.equals(other.fContentEnglish))
			return false;
		if (fEnabledFor == null) {
			if (other.fEnabledFor != null)
				return false;
		} else if (!fEnabledFor.equals(other.fEnabledFor))
			return false;
		if (fID == null) {
			if (other.fID != null)
				return false;
		} else if (!fID.equals(other.fID))
			return false;
		if (fMaxlength == null) {
			if (other.fMaxlength != null)
				return false;
		} else if (!fMaxlength.equals(other.fMaxlength))
			return false;
		if (fRegExpr == null) {
			if (other.fRegExpr != null)
				return false;
		} else if (!fRegExpr.equals(other.fRegExpr))
			return false;
		if (fRequired == null) {
			if (other.fRequired != null)
				return false;
		} else if (!fRequired.equals(other.fRequired))
			return false;
		if (fType == null) {
			if (other.fType != null)
				return false;
		} else if (!fType.equals(other.fType))
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
		return "Field [fID=" + fID + ", fType=" + fType + ", fMaxlength=" + fMaxlength + ", fContentEnglish="
				+ fContentEnglish + ", fRequired=" + fRequired + ", fBoxSze=" + fBoxSze + ", saveInField=" + saveInField
				+ ", fContentArabic=" + fContentArabic + ", fRegExpr=" + fRegExpr + ", fEnabledFor=" + fEnabledFor
				+ ", fAccessedBy=" + fAccessedBy + "]";
	}
	}
