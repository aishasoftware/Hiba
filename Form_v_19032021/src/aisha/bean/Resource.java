package aisha.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Resource extends BasicBean {

	private String resourceName;
	private Double price;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	private Set<Package> packages = new HashSet<Package>();
	
	public static ArrayList<String> getPublicFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
	
		publicFields.add(0, "resourceName");
		return publicFields;

	}
	
	public static ArrayList<String> getPrivateFields() {

		ArrayList<String> privateFields = new ArrayList<String>();
	
		privateFields.add(0, "price");
		return privateFields;

	}
	
	public static ArrayList<String> getViewFields() {

		ArrayList<String> addFields = new ArrayList<String>();
		addFields.add(0, "field1");
		addFields.add(1, "field2");
		addFields.add(2, "field3");
		addFields.add(3, "field4");
		addFields.add(4, "field5");
		addFields.add(5, "field6");
		addFields.add(6, "field7");
		addFields.add(7, "field8");
		addFields.add(8, "field9");
		addFields.add(9, "field10");
		addFields.add(10, "talents");
		addFields.add(11, "investors");
		return addFields;

	}

	
	public static ArrayList<String> getAdminFields() {

		ArrayList<String> adminFields = new ArrayList<String>();
	
		adminFields.add(0, "status");
		return adminFields;

	}
	
	public static ArrayList<String> getConnectionsFields() {

		ArrayList<String> privateFields = new ArrayList<String>();
	
		privateFields.add(0, "field3");
		return privateFields;

	}
	
	public static Map<String,String> getLinks() {

		Map<String,String> links = new HashMap<String, String>();
		links.put("My Profile","/Form_v_1.0/StartupController/viewMyProfile");
		//links.put("My Applications","/Form_v_1.0/StartupController/getApplicationsList");
		//links.put("My Co-founders","/Form_v_1.0/StartupController/getTalentsList");
		return links;
		}
		
	public static ArrayList<String> getSearchFields() {

		ArrayList<String> searchFields = new ArrayList<String>();
		searchFields.add(0, "fromCreate");
		searchFields.add(1, "toCreate");
		searchFields.add(2, "field1");
		searchFields.add(3, "field2");
		return searchFields;

	}
	
	
	public static ArrayList<String> getTableFields() {

		ArrayList<String> tableFields = new ArrayList<String>();
		tableFields.add(0, "resourceName");
		tableFields.add(1, "price");
		tableFields.add(2, "status");
		return tableFields;

	}
	
	public static ArrayList<String> getAddFields() {
		ArrayList<String> addFields = new ArrayList<String>();
		addFields.add(0, "resourceName");
		addFields.add(1, "price");
		return addFields;
	}
	
	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Set<Package> getPackages() {
		return packages;
	}
	public void setPackages(Set<Package> packages) {
		this.packages = packages;
	}
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((packages == null) ? 0 : packages.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((resourceName == null) ? 0 : resourceName.hashCode());
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
		Resource other = (Resource) obj;
		if (packages == null) {
			if (other.packages != null)
				return false;
		} else if (!packages.equals(other.packages))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (resourceName == null) {
			if (other.resourceName != null)
				return false;
		} else if (!resourceName.equals(other.resourceName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Resource [resourceName=" + resourceName + ", price=" + price
				+ ", packages=" + packages + "]";
	}*/

}
