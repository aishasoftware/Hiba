package aisha.util.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aisha.bean.BasicBean;


/**
 * This class is the base class of any field in html file
 * 
 * @author Aisha
 * 
 *
 */
public class FieldAttributes {
	private String name;
	private String nameEng;	
	private String nameArb;
	private String regExpr;	
	private String type;	
	private String required;
	private String boxSize;
	private String maxLength;
	private String enabledFor;
	private String accessedBy;
	private String useAs;
	private String saveInField;
	private String list;
	private String multiple;
	private List<BasicBean> options;// = new ArrayList<BasicBean>();
	private Set<String> items;// = new ArrayList<BasicBean>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUseAs() {
		return useAs;
	}
	public void setUseAs(String useAs) {
		this.useAs = useAs;
	}
	public String getSaveInField() {
		return saveInField;
	}
	public void setSaveInField(String saveInField) {
		this.saveInField = saveInField;
	}
	public List<BasicBean> getOptions() {
		return options;
	}
	public void setOptions(List<BasicBean> options) {
		this.options = options;
	}
	public String getNameEng() {
		return nameEng;
	}
	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}
	public String getNameArb() {
		return nameArb;
	}
	public void setNameArb(String nameArb) {
		this.nameArb = nameArb;
	}
	public String getRegExpr() {
		return regExpr;
	}
	public void setRegExpr(String regExpr) {
		this.regExpr = regExpr;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getBoxSize() {
		return boxSize;
	}
	public void setBoxSize(String boxSize) {
		this.boxSize = boxSize;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	public String getEnabledFor() {
		return enabledFor;
	}
	public void setEnabledFor(String enabledFor) {
		this.enabledFor = enabledFor;
	}
	public String getAccessedBy() {
		return accessedBy;
	}
	public void setAccessedBy(String accessedBy) {
		this.accessedBy = accessedBy;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	
	
	public String getMultiple() {
		return multiple;
	}
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
	public Set<String> getItems() {
		return items;
	}
	public void setItems(Set<String> items) {
		this.items = items;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessedBy == null) ? 0 : accessedBy.hashCode());
		result = prime * result + ((boxSize == null) ? 0 : boxSize.hashCode());
		result = prime * result
				+ ((enabledFor == null) ? 0 : enabledFor.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result
				+ ((maxLength == null) ? 0 : maxLength.hashCode());
		result = prime * result
				+ ((multiple == null) ? 0 : multiple.hashCode());
		result = prime * result + ((nameArb == null) ? 0 : nameArb.hashCode());
		result = prime * result + ((nameEng == null) ? 0 : nameEng.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((regExpr == null) ? 0 : regExpr.hashCode());
		result = prime * result
				+ ((required == null) ? 0 : required.hashCode());
		result = prime * result
				+ ((saveInField == null) ? 0 : saveInField.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((useAs == null) ? 0 : useAs.hashCode());
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
		FieldAttributes other = (FieldAttributes) obj;
		if (accessedBy == null) {
			if (other.accessedBy != null)
				return false;
		} else if (!accessedBy.equals(other.accessedBy))
			return false;
		if (boxSize == null) {
			if (other.boxSize != null)
				return false;
		} else if (!boxSize.equals(other.boxSize))
			return false;
		if (enabledFor == null) {
			if (other.enabledFor != null)
				return false;
		} else if (!enabledFor.equals(other.enabledFor))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (maxLength == null) {
			if (other.maxLength != null)
				return false;
		} else if (!maxLength.equals(other.maxLength))
			return false;
		if (multiple == null) {
			if (other.multiple != null)
				return false;
		} else if (!multiple.equals(other.multiple))
			return false;
		if (nameArb == null) {
			if (other.nameArb != null)
				return false;
		} else if (!nameArb.equals(other.nameArb))
			return false;
		if (nameEng == null) {
			if (other.nameEng != null)
				return false;
		} else if (!nameEng.equals(other.nameEng))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (regExpr == null) {
			if (other.regExpr != null)
				return false;
		} else if (!regExpr.equals(other.regExpr))
			return false;
		if (required == null) {
			if (other.required != null)
				return false;
		} else if (!required.equals(other.required))
			return false;
		if (saveInField == null) {
			if (other.saveInField != null)
				return false;
		} else if (!saveInField.equals(other.saveInField))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (useAs == null) {
			if (other.useAs != null)
				return false;
		} else if (!useAs.equals(other.useAs))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FieldAttributes [name=" + name + ", nameEng=" + nameEng
				+ ", nameArb=" + nameArb + ", regExpr=" + regExpr + ", type="
				+ type + ", required=" + required + ", boxSize=" + boxSize
				+ ", maxLength=" + maxLength + ", enabledFor=" + enabledFor
				+ ", accessedBy=" + accessedBy + ", useAs=" + useAs
				+ ", saveInField=" + saveInField + ", list=" + list
				+ ", multiple=" + multiple + ", options=" + options
				+ ", items=" + items + "]";
	}


	}
