package aisha.bean;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import aisha.util.CurrentUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value={ "username", "getAuthorities","accountNonLocked","accountNonExpired","credentialsNonExpired","authorities" })
public class PlatformUser extends BasicBean implements UserDetails{
	
public static final long serialVersionUID = 9025349022275184774L;
private int version;
private String email;
private boolean enabled;
private boolean hasLoggedOut;
private String userDefaultDevice;
private String lastDeviceUsed;
private Date lastLoginDate;
private Date creationDate ;
private Date activationDate;
private Date expireDate;
private Date disabledDate;
private String userKey;
private String userType;
private String userRole;
private int loginTryCount;
private String userName;
private String userFullName;
private String password;
private String confirmedPassword;
private String passwordHint;
private String passwordHintAnswer;
private String resetPasswordSessionID;
private String subscriptionId;
private boolean defaultPassowrd;
private List<String> connections = new ArrayList<String>();

public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}
public List<String> getConnections() {
	return connections;
}
public void setConnections(List<String> connections) {
	this.connections = connections;
}
public boolean isDefaultPassowrd() {
	return defaultPassowrd;
}
public void setDefaultPassowrd(boolean defaultPassowrd) {
	this.defaultPassowrd = defaultPassowrd;
}
public static ArrayList<String> getPublicFields() {

	ArrayList<String> publicFields = new ArrayList<String>();

	publicFields.add(0, "userName");
	publicFields.add(1, "userFullName");
	publicFields.add(2, "email");
	return publicFields;

}

public static ArrayList<String> getProtectedFields() {

	ArrayList<String> publicFields = new ArrayList<String>();

	publicFields.add(0, "userName");
	publicFields.add(1, "userFullName");
	publicFields.add(2, "email");
	return publicFields;

}

public static ArrayList<String> getAdminFields() {

	ArrayList<String> publicFields = new ArrayList<String>();

	publicFields.add(0, "status");
	return publicFields;

}


public static ArrayList<String> getViewFields() {

	ArrayList<String> publicFields = new ArrayList<String>();
	publicFields.add(0, "userName");
	publicFields.add(1, "userFullName");
	publicFields.add(2, "email");
	if(CurrentUser.getUserType().equals("Investor"))
		publicFields.add(3, "userRoleInvestor");
	return publicFields;

}
public static ArrayList<String> getAddFields() {

	ArrayList<String> publicFields = new ArrayList<String>();

	publicFields.add(0, "userName");
	publicFields.add(1, "userFullName");
	publicFields.add(2, "email");
	if(CurrentUser.getUserType().equals("Investor"))
		publicFields.add(3, "userRole");

	return publicFields;

}

public static ArrayList<String> getSearchFields() {

	ArrayList<String> publicFields = new ArrayList<String>();
	publicFields.add(0, "fromCreate");
	publicFields.add(1, "toCreate");
	publicFields.add(2, "userName");
	publicFields.add(3, "userRole");
	
	return publicFields;

}

public static ArrayList<String> getPrivateFields() {

	ArrayList<String> publicFields = new ArrayList<String>();

	publicFields.add(0, "userName");
	publicFields.add(1, "userFullName");
	publicFields.add(2, "email");
	return publicFields;

}
public static ArrayList<String> getConnectionsFields() {

	ArrayList<String> publicFields = new ArrayList<String>();
	publicFields.add(0, "userName");
	publicFields.add(1, "userFullName");
	publicFields.add(2, "email");
	return publicFields;

}
public static ArrayList<String> getTableFields() {
	ArrayList<String> beanFields = new ArrayList<String>();
	
	beanFields.add(0, "userName");
	beanFields.add(1, "userRole");
	beanFields.add(2, "creationTime");
	beanFields.add(3, "status");
    return beanFields;
}
            	public String getPasswordHintAnswer() {
            		return passwordHintAnswer;
            	}

            	public void setPasswordHintAnswer(String passwordHintAnswer) {
            		this.passwordHintAnswer = passwordHintAnswer;
            	}


            	@Override
            	public Collection<? extends GrantedAuthority> getAuthorities() {

            		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER_" + userRole));

            		// for (Authority authorities : systenUserAuthorities) {
            		// grantedAuthorityList.add(new GrantedAuthorityImpl(authorities
            		// .getAuthority()));
            		// }
            		return grantedAuthorityList;
            	}


            	@Override
            	public String getUsername() {
            		// TODO Auto-generated method stub
            		return this.userName;
            	}

            	@Override
            	public boolean isAccountNonExpired() {
            		// TODO Auto-generated method stub
            		return true;
            	}

            	@Override
            	public boolean isAccountNonLocked() {
            		if (this.loginTryCount == 3)
            			return false;
            		else
            			return true;
            	}

            	@Override
            	public boolean isCredentialsNonExpired() {
            		// TODO Auto-generated method stub
            		return true;
            	}

            	public String getUserName() {
            		return userName;
            	}

            	public void setUserName(String userName) {
            		this.userName = userName;
            	}

            	public String getUserFullName() {
            		return userFullName;
            	}

            	public void setUserFullName(String userFullName) {
            		this.userFullName = userFullName;
            	}

            	public String getPassword() {
            		return password;
            	}

            	public void setPassword(String password) {
            		this.password = password;
            	}

            	public String getConfirmedPassword() {
            		return confirmedPassword;
            	}

            	public void setConfirmedPassword(String confirmedPassword) {
            		this.confirmedPassword = confirmedPassword;
            	}

            	public String getPasswordHint() {
            		return passwordHint;
            	}

            	public void setPasswordHint(String passwordHint) {
            		this.passwordHint = passwordHint;
            	}

            	public String getEmail() {
            		return email;
            	}

            	public void setEmail(String email) {
            		this.email = email;
            	}

            	public boolean isEnabled() {
            		return enabled;
            	}

            	public String getUserDefaultDevice() {
            		return userDefaultDevice;
            	}

            	public boolean isHasLoggedOut() {
            		return hasLoggedOut;
            	}

            	public void setHasLoggedOut(boolean hasLoggedOut) {
            		this.hasLoggedOut = hasLoggedOut;
            	}

            	public void setEnabled(boolean enabled) {
            		this.enabled = enabled;
            	}

            	public void setUserDefaultDevice(String userDefaultDevice) {
            		this.userDefaultDevice = userDefaultDevice;
            	}

            	public String getLastDeviceUsed() {
            		return lastDeviceUsed;
            	}

            	public void setLastDeviceUsed(String lastDeviceUsed) {
            		this.lastDeviceUsed = lastDeviceUsed;
            	}

            	@DateTimeFormat(pattern = "yyyy-MM-dd")
            	public Date getLastLoginDate() {
            		return lastLoginDate;
            	}

            	public void setLastLoginDate(Date lastLogginDate) {
            		this.lastLoginDate = lastLogginDate;
            	}

            	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            	public Date getCreationDate() {
            		return creationDate;
            	}

            	public void setCreationDate(Date creationDate) {
            		this.creationDate = creationDate;
            	}

            	@DateTimeFormat(pattern = "yyyy-MM-dd")
            	public Date getActivationDate() {
            		return activationDate;
            	}

            	public void setActivationDate(Date activationDate) {
            		this.activationDate = activationDate;
            	}

            	@DateTimeFormat(pattern = "yyyy-MM-dd")
            	public Date getExpireDate() {
            		return expireDate;
            	}

            	public void setExpireDate(Date expireDate) {
            		this.expireDate = expireDate;
            	}

            	@DateTimeFormat(pattern = "yyyy-MM-dd")
            	public Date getDisabledDate() {
            		return disabledDate;
            	}

            	public void setDisabledDate(Date disabledDate) {
            		this.disabledDate = disabledDate;
            	}

            	public String getUserKey() {
            		return userKey;
            	}

            	public void setUserKey(String userKey) {
            		this.userKey = userKey;
            	}

            	public String getUserType() {
            		return userType;
            	}

            	public void setUserType(String userType) {
            		this.userType = userType;
            	}

            	public int getLoginTryCount() {
            		return loginTryCount;
            	}

            	public void setLoginTryCount(int loginTryCount) {
            		this.loginTryCount = loginTryCount;
            	}

            	/**
            	 * @return the userRole
            	 */
            	public String getUserRole() {
            		return userRole;
            	}

            	/**
            	 * @param userRole
            	 *            the userRole to set
            	 */
            	public void setUserRole(String userRole) {
            		this.userRole = userRole;
            	}
				public String getResetPasswordSessionID() {
					return resetPasswordSessionID;
				}
				public void setResetPasswordSessionID(String resetPasswordSessionID) {
					this.resetPasswordSessionID = resetPasswordSessionID;
				}
				public String getSubscriptionId() {
					return subscriptionId;
				}
				public void setSubscriptionId(String subscriptionId) {
					this.subscriptionId = subscriptionId;
				}
				public static long getSerialversionuid() {
					return serialVersionUID;
				}
				@Override
				public int hashCode() {
					final int prime = 31;
					int result = super.hashCode();
					result = prime
							* result
							+ ((activationDate == null) ? 0 : activationDate
									.hashCode());
					result = prime
							* result
							+ ((confirmedPassword == null) ? 0
									: confirmedPassword.hashCode());
					result = prime
							* result
							+ ((creationDate == null) ? 0 : creationDate
									.hashCode());
					result = prime
							* result
							+ ((disabledDate == null) ? 0 : disabledDate
									.hashCode());
					result = prime * result
							+ ((email == null) ? 0 : email.hashCode());
					result = prime * result + (enabled ? 1231 : 1237);
					result = prime
							* result
							+ ((expireDate == null) ? 0 : expireDate.hashCode());
					result = prime * result + (hasLoggedOut ? 1231 : 1237);
					result = prime
							* result
							+ ((lastDeviceUsed == null) ? 0 : lastDeviceUsed
									.hashCode());
					result = prime
							* result
							+ ((lastLoginDate == null) ? 0 : lastLoginDate
									.hashCode());
					result = prime * result + loginTryCount;
					result = prime * result
							+ ((password == null) ? 0 : password.hashCode());
					result = prime
							* result
							+ ((passwordHint == null) ? 0 : passwordHint
									.hashCode());
					result = prime
							* result
							+ ((passwordHintAnswer == null) ? 0
									: passwordHintAnswer.hashCode());
					result = prime
							* result
							+ ((resetPasswordSessionID == null) ? 0
									: resetPasswordSessionID.hashCode());
					result = prime
							* result
							+ ((subscriptionId == null) ? 0 : subscriptionId
									.hashCode());
					result = prime
							* result
							+ ((userDefaultDevice == null) ? 0
									: userDefaultDevice.hashCode());
					result = prime
							* result
							+ ((userFullName == null) ? 0 : userFullName
									.hashCode());
					result = prime * result
							+ ((userKey == null) ? 0 : userKey.hashCode());
					result = prime * result
							+ ((userName == null) ? 0 : userName.hashCode());
					result = prime * result
							+ ((userRole == null) ? 0 : userRole.hashCode());
					result = prime * result
							+ ((userType == null) ? 0 : userType.hashCode());
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
					PlatformUser other = (PlatformUser) obj;
					if (activationDate == null) {
						if (other.activationDate != null)
							return false;
					} else if (!activationDate.equals(other.activationDate))
						return false;
					if (confirmedPassword == null) {
						if (other.confirmedPassword != null)
							return false;
					} else if (!confirmedPassword
							.equals(other.confirmedPassword))
						return false;
					if (creationDate == null) {
						if (other.creationDate != null)
							return false;
					} else if (!creationDate.equals(other.creationDate))
						return false;
					if (disabledDate == null) {
						if (other.disabledDate != null)
							return false;
					} else if (!disabledDate.equals(other.disabledDate))
						return false;
					if (email == null) {
						if (other.email != null)
							return false;
					} else if (!email.equals(other.email))
						return false;
					if (enabled != other.enabled)
						return false;
					if (expireDate == null) {
						if (other.expireDate != null)
							return false;
					} else if (!expireDate.equals(other.expireDate))
						return false;
					if (hasLoggedOut != other.hasLoggedOut)
						return false;
					if (lastDeviceUsed == null) {
						if (other.lastDeviceUsed != null)
							return false;
					} else if (!lastDeviceUsed.equals(other.lastDeviceUsed))
						return false;
					if (lastLoginDate == null) {
						if (other.lastLoginDate != null)
							return false;
					} else if (!lastLoginDate.equals(other.lastLoginDate))
						return false;
					if (loginTryCount != other.loginTryCount)
						return false;
					if (password == null) {
						if (other.password != null)
							return false;
					} else if (!password.equals(other.password))
						return false;
					if (passwordHint == null) {
						if (other.passwordHint != null)
							return false;
					} else if (!passwordHint.equals(other.passwordHint))
						return false;
					if (passwordHintAnswer == null) {
						if (other.passwordHintAnswer != null)
							return false;
					} else if (!passwordHintAnswer
							.equals(other.passwordHintAnswer))
						return false;
					if (resetPasswordSessionID == null) {
						if (other.resetPasswordSessionID != null)
							return false;
					} else if (!resetPasswordSessionID
							.equals(other.resetPasswordSessionID))
						return false;
					if (subscriptionId == null) {
						if (other.subscriptionId != null)
							return false;
					} else if (!subscriptionId.equals(other.subscriptionId))
						return false;
					if (userDefaultDevice == null) {
						if (other.userDefaultDevice != null)
							return false;
					} else if (!userDefaultDevice
							.equals(other.userDefaultDevice))
						return false;
					if (userFullName == null) {
						if (other.userFullName != null)
							return false;
					} else if (!userFullName.equals(other.userFullName))
						return false;
					if (userKey == null) {
						if (other.userKey != null)
							return false;
					} else if (!userKey.equals(other.userKey))
						return false;
					if (userName == null) {
						if (other.userName != null)
							return false;
					} else if (!userName.equals(other.userName))
						return false;
					if (userRole == null) {
						if (other.userRole != null)
							return false;
					} else if (!userRole.equals(other.userRole))
						return false;
					if (userType == null) {
						if (other.userType != null)
							return false;
					} else if (!userType.equals(other.userType))
						return false;
					return true;
				}
				@Override
				public String toString() {
					return "PlatformUser [email=" + email + ", enabled="
							+ enabled + ", hasLoggedOut=" + hasLoggedOut
							+ ", userDefaultDevice=" + userDefaultDevice
							+ ", lastDeviceUsed=" + lastDeviceUsed
							+ ", lastLoginDate=" + lastLoginDate
							+ ", creationDate=" + creationDate
							+ ", activationDate=" + activationDate
							+ ", expireDate=" + expireDate + ", disabledDate="
							+ disabledDate + ", userKey=" + userKey
							+ ", userType=" + userType + ", userRole="
							+ userRole + ", loginTryCount=" + loginTryCount
							+ ", userName=" + userName + ", userFullName="
							+ userFullName + ", password=" + password
							+ ", confirmedPassword=" + confirmedPassword
							+ ", passwordHint=" + passwordHint
							+ ", passwordHintAnswer=" + passwordHintAnswer
							+ ", resetPasswordSessionID="
							+ resetPasswordSessionID + ", subscriptionId="
							+ subscriptionId + ", defaultPassowrd="
							+ defaultPassowrd + "]";
				}


/*            	public Set<Authority> getSystenUserAuthorities() {
            		return systenUserAuthorities;
            	}

            	public void setSystenUserAuthorities(Set<Authority> systenUserAuthorities) {
            		this.systenUserAuthorities = systenUserAuthorities;
            	}*/


           
}