package aisha.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BasicBean {
	private int firstPage;
	private int maxResult;
	private int totalResult;
	private boolean orderAsc;
	private String orderBy;
	private long id = 1;
	private String status;
	private Timestamp creationTime;
	private Timestamp LastUpdateTime;
	private String createdBy;
	private String userId;
	private String LastUpdatedBy;
	private HashMap<String, Object> searchCriteria;
	private List<BasicBean> results = new ArrayList<>();
	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	public Timestamp getLastUpdateTime() {
		return LastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		LastUpdateTime = lastUpdateTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLastUpdatedBy() {
		return LastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		LastUpdatedBy = lastUpdatedBy;
	}
	public HashMap<String, Object> getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(HashMap<String, Object> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public List<BasicBean> getResults() {
		return results;
	}
	public void setResults(List<BasicBean> results) {
		this.results = results;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((LastUpdateTime == null) ? 0 : LastUpdateTime.hashCode());
		result = prime * result
				+ ((LastUpdatedBy == null) ? 0 : LastUpdatedBy.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + firstPage;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + maxResult;
		result = prime * result + (orderAsc ? 1231 : 1237);
		result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime * result
				+ ((searchCriteria == null) ? 0 : searchCriteria.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + totalResult;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		BasicBean other = (BasicBean) obj;
		if (LastUpdateTime == null) {
			if (other.LastUpdateTime != null)
				return false;
		} else if (!LastUpdateTime.equals(other.LastUpdateTime))
			return false;
		if (LastUpdatedBy == null) {
			if (other.LastUpdatedBy != null)
				return false;
		} else if (!LastUpdatedBy.equals(other.LastUpdatedBy))
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
		if (firstPage != other.firstPage)
			return false;
		if (id != other.id)
			return false;
		if (maxResult != other.maxResult)
			return false;
		if (orderAsc != other.orderAsc)
			return false;
		if (orderBy == null) {
			if (other.orderBy != null)
				return false;
		} else if (!orderBy.equals(other.orderBy))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		if (searchCriteria == null) {
			if (other.searchCriteria != null)
				return false;
		} else if (!searchCriteria.equals(other.searchCriteria))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalResult != other.totalResult)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;

		return true;
	}
	@Override
	public String toString() {
		return "BasicBean [firstPage=" + firstPage + ", maxResult=" + maxResult
				+ ", totalResult=" + totalResult + ", orderAsc=" + orderAsc
				+ ", orderBy=" + orderBy + ", id=" + id + ", status=" + status
				+ ", creationTime=" + creationTime + ", LastUpdateTime="
				+ LastUpdateTime + ", createdBy=" + createdBy + ", userId="
				+ userId + ", LastUpdatedBy=" + LastUpdatedBy
				+ ", searchCriteria=" + searchCriteria 
				+ ", results=" + results + ", toString()=" + super.toString()
				+ "]";
	}

}
