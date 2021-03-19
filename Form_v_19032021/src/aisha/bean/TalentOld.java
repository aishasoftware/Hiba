package aisha.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TalentOld {

	// talent fields
	private long id;
	private String filePath;
	//@NotNull
	private String fullName;
	
	//@DateTimeFormat(pattern="MM-dd-yyyy")
	private Date dateOfBirth;
	//@NotNull
	private String residence;
	//@NotNull
	private String gender;
	//@NotNull
	private String education;
	//@NotNull
	private String disability;
	//@NotNull
	private String disabilityDescription;
	//@NotNull
	//@Pattern(regexp="/\\S+@\\S+\\.\\S+/")
	private String email;
	//@NotNull
	private String mobileNumber;
	//@NotNull
	private String twitter;
	//@NotNull
	private String linkedIn;
	//@NotNull
	private String facebook;
	//@NotNull
	private String declaration;
	//@NotNull
	private String channel;
	
	// generic fields
	private String status;
	private Date creationTime;
	private String createdBy;
	private Date lastModificationTime;
	private String LastUpdatedBy;
	
	// supportive fields
	private int firstResults;
	private int maxResults;
	private int totalResults;
	private boolean orderAsc;
	private String orderBy = "id";	
	private HashMap<String, Object> filter;
	

public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}
	// for results
	private List<TalentOld> talentList = new ArrayList<TalentOld>();
	// to add new talent
	public static ArrayList<String> getAddFields() {
		
		ArrayList<String> talentFields = new ArrayList<String>();
		talentFields.add(0, "fullName");
		talentFields.add(1, "dateOfBirth");
		talentFields.add(2, "residence");
		talentFields.add(3, "gender");
		talentFields.add(4, "education");
		talentFields.add(5, "disability");
		talentFields.add(6, "email");
		talentFields.add(7, "mobileNumber");
		talentFields.add(8, "twitter");
		talentFields.add(9, "linkedIn");
		talentFields.add(10, "facebook");
		talentFields.add(11, "declaration");
		talentFields.add(12, "channel");
		return talentFields;

	}
	
	// to view all talents
	public static ArrayList<String> getTableFields() {
		
		ArrayList<String> talentFields = new ArrayList<String>();
		talentFields.add(0, "fullName");
		talentFields.add(1, "education");
		talentFields.add(2, "creationTime");

		return talentFields;

	}

	// to view all talents
	public static ArrayList<String> getAllFields() {
		
		ArrayList<String> talentFields = new ArrayList<String>();
		talentFields.add(0, "fullName");
		talentFields.add(1, "dateOfBirth");
		talentFields.add(2, "residence");
		talentFields.add(3, "gender");
		talentFields.add(4, "education");
		talentFields.add(5, "disability");
		talentFields.add(6, "email");
		talentFields.add(7, "mobileNumber");
		talentFields.add(8, "twitter");
		talentFields.add(9, "linkedIn");
		talentFields.add(10, "facebook");
		talentFields.add(11, "declaration");
		talentFields.add(12, "channel");
		talentFields.add(6, "status");
		talentFields.add(7, "creationTime");
		talentFields.add(8, "createdBy");
		talentFields.add(9, "lastModificationTime");
		talentFields.add(10, "LastUpdatedBy");
		return talentFields;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getDisabilityDescription() {
		return disabilityDescription;
	}

	public void setDisabilityDescription(String disabilityDescription) {
		this.disabilityDescription = disabilityDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModificationTime() {
		return lastModificationTime;
	}

	public void setLastModificationTime(Date lastModificationTime) {
		this.lastModificationTime = lastModificationTime;
	}

	public String getLastUpdatedBy() {
		return LastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		LastUpdatedBy = lastUpdatedBy;
	}

	public int getFirstResults() {
		return firstResults;
	}

	public void setFirstResults(int firstResults) {
		this.firstResults = firstResults;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public boolean isOrderAsc() {
		return false;
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

	public HashMap<String, Object> getFilter() {
		return filter;
	}

	public void setFilter(HashMap<String, Object> filter) {
		this.filter = filter;
	}

	
	public List<TalentOld> getTalentList() {
		return talentList;
	}

	public void setTalentList(List<TalentOld> talentList) {
		this.talentList = talentList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LastUpdatedBy == null) ? 0 : LastUpdatedBy.hashCode());
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((disability == null) ? 0 : disability.hashCode());
		result = prime * result + ((disabilityDescription == null) ? 0 : disabilityDescription.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((facebook == null) ? 0 : facebook.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((filter == null) ? 0 : filter.hashCode());
		result = prime * result + firstResults;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((declaration == null) ? 0 : declaration.hashCode());
		result = prime * result + ((lastModificationTime == null) ? 0 : lastModificationTime.hashCode());
		result = prime * result + ((linkedIn == null) ? 0 : linkedIn.hashCode());
		result = prime * result + maxResults;
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + (orderAsc ? 1231 : 1237);
		result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
		result = prime * result + ((residence == null) ? 0 : residence.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((talentList == null) ? 0 : talentList.hashCode());
		result = prime * result + totalResults;
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
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
		TalentOld other = (TalentOld) obj;
		if (LastUpdatedBy == null) {
			if (other.LastUpdatedBy != null)
				return false;
		} else if (!LastUpdatedBy.equals(other.LastUpdatedBy))
			return false;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
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
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (disability == null) {
			if (other.disability != null)
				return false;
		} else if (!disability.equals(other.disability))
			return false;
		if (disabilityDescription == null) {
			if (other.disabilityDescription != null)
				return false;
		} else if (!disabilityDescription.equals(other.disabilityDescription))
			return false;
		if (education == null) {
			if (other.education != null)
				return false;
		} else if (!education.equals(other.education))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (facebook == null) {
			if (other.facebook != null)
				return false;
		} else if (!facebook.equals(other.facebook))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (filter == null) {
			if (other.filter != null)
				return false;
		} else if (!filter.equals(other.filter))
			return false;
		if (firstResults != other.firstResults)
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (declaration == null) {
			if (other.declaration != null)
				return false;
		} else if (!declaration.equals(other.declaration))
			return false;
		if (lastModificationTime == null) {
			if (other.lastModificationTime != null)
				return false;
		} else if (!lastModificationTime.equals(other.lastModificationTime))
			return false;
		if (linkedIn == null) {
			if (other.linkedIn != null)
				return false;
		} else if (!linkedIn.equals(other.linkedIn))
			return false;
		if (maxResults != other.maxResults)
			return false;
		if (mobileNumber == null) {
			if (other.mobileNumber != null)
				return false;
		} else if (!mobileNumber.equals(other.mobileNumber))
			return false;
		if (orderAsc != other.orderAsc)
			return false;
		if (orderBy == null) {
			if (other.orderBy != null)
				return false;
		} else if (!orderBy.equals(other.orderBy))
			return false;
		if (residence == null) {
			if (other.residence != null)
				return false;
		} else if (!residence.equals(other.residence))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (talentList == null) {
			if (other.talentList != null)
				return false;
		} else if (!talentList.equals(other.talentList))
			return false;
		if (totalResults != other.totalResults)
			return false;
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Talent [id=" + id + ", filePath=" + filePath + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
				+ ", residence=" + residence + ", gender=" + gender + ", education=" + education + ", disability="
				+ disability + ", disabilityDescription=" + disabilityDescription + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", twitter=" + twitter + ", linkedIn=" + linkedIn + ", facebook="
				+ facebook + ", declaration=" + declaration + ", channel=" + channel + ", status="
				+ status + ", creationTime=" + creationTime + ", createdBy=" + createdBy + ", lastModificationTime="
				+ lastModificationTime + ", LastUpdatedBy=" + LastUpdatedBy + ", firstResults=" + firstResults
				+ ", maxResults=" + maxResults + ", totalResults=" + totalResults + ", orderAsc=" + orderAsc
				+ ", orderBy=" + orderBy + ", filter=" + filter + ", talentList=" + talentList + "]";
	}




	
}
