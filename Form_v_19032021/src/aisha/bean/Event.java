package aisha.bean;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event extends BasicBean {
	
	private String eventName;
	private String description;
	private String category;
	private String location;
	private String eventStatus;
	private Integer noOfAttendants;
	private Date eventDate;
	private Time startTime;
	private Time endTime;
	
	
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getNoOfAttendants() {
		return noOfAttendants;
	}
	public void setNoOfAttendants(Integer noOfAttendants) {
		this.noOfAttendants = noOfAttendants;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result
				+ ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result
				+ ((eventStatus == null) ? 0 : eventStatus.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((noOfAttendants == null) ? 0 : noOfAttendants.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
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
		Event other = (Event) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
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
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventStatus == null) {
			if (other.eventStatus != null)
				return false;
		} else if (!eventStatus.equals(other.eventStatus))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (noOfAttendants == null) {
			if (other.noOfAttendants != null)
				return false;
		} else if (!noOfAttendants.equals(other.noOfAttendants))
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
		return "Event [eventName=" + eventName + ", description=" + description
				+ ", category=" + category + ", location=" + location
				+ ", eventStatus=" + eventStatus + ", noOfAttendants="
				+ noOfAttendants + ", eventDate=" + eventDate + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
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


		ArrayList<String> addFields = new ArrayList<String>();
		addFields.add(0, "eventName");
		//addFields.add(1, "location");
		addFields.add(1, "eventDate");
		//addFields.add(3, "noOfAttendants");
		addFields.add(2, "eventStatus");
		return addFields;

	}
	
	public static ArrayList<String> getAddFields() {
			
		ArrayList<String> addFields = new ArrayList<String>();
		addFields.add(0, "eventName");
		addFields.add(1, "description");
		addFields.add(2, "category");
		addFields.add(3, "noOfAttendants");
		addFields.add(4, "eventDate");
		addFields.add(5, "startTime");
		addFields.add(6, "endTime");
		addFields.add(7, "location");
		
		return addFields;

	}
	
	public static ArrayList<String> getViewFields() {

		ArrayList<String> addFields = new ArrayList<String>();
		addFields.add(0, "eventName");
		addFields.add(1, "decsription");
		addFields.add(2, "location");
		addFields.add(3, "eventDate");
		addFields.add(4, "noOfAttendants");
		addFields.add(5, "eventStatus");
		addFields.add(6, "category");
		//addFields.add(7, "status");
		return addFields;

	}
	
	public static ArrayList<String> getPublicFields() {

		ArrayList<String> publicFields = new ArrayList<String>();
	
		publicFields.add(0, "field1");
		return publicFields;

	}
	
	public static ArrayList<String> getAdminFields() {

		ArrayList<String> adminFields = new ArrayList<String>();
	
		adminFields.add(0, "status");
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
		
	public static Map<String,String> getLinks() {

		Map<String,String> links = new HashMap<String, String>();
		links.put("My Profile","/Form_v_1.0/TalentController/viewMyProfile");
		links.put("My Applications","/Form_v_1.0/TalentController/viewMyApplications");
		links.put("My Startups","/Form_v_1.0/TalentController/viewMyStartups");
		return links;
		}
	
}
