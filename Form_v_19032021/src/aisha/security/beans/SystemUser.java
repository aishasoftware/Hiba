package aisha.security.beans;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import aisha.bean.BasicBean;
import aisha.bean.Talent;

public class SystemUser extends BasicBean implements UserDetails{
	
public static final long serialVersionUID = 9025349022275184774L;
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

public static ArrayList<String> getTableFields() {
	ArrayList<String> beanFields = new ArrayList<String>();
	beanFields.add(0, "id");
	beanFields.add(1, "userName");
	beanFields.add(2, "userRole");
	beanFields.add(3, "creationTime");
	beanFields.add(4, "status");
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
            		grantedAuthorityList.add(new SimpleGrantedAuthority(userRole));

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

/*            	public Set<Authority> getSystenUserAuthorities() {
            		return systenUserAuthorities;
            	}

            	public void setSystenUserAuthorities(Set<Authority> systenUserAuthorities) {
            		this.systenUserAuthorities = systenUserAuthorities;
            	}*/

            	@Override
            	public String toString() {
            		StringBuilder builder = new StringBuilder();
            		builder.append("SystemUser [userName=");
            		builder.append(userName);
            		builder.append(", userFullName=");
            		builder.append(userFullName);
            		builder.append(", password=");
            		builder.append(password);
            		builder.append(", confirmedPassword=");
            		builder.append(confirmedPassword);
            		builder.append(", passwordHint=");
            		builder.append(passwordHint);
            		builder.append(", passwordHintAnswer=");
            		builder.append(passwordHintAnswer);
            		builder.append(", resetPasswordSessionID=");
            		builder.append(resetPasswordSessionID);
            		builder.append(", email=");
            		builder.append(email);
            		builder.append(", enabled=");
            		builder.append(enabled);
            		builder.append(", hasLoggedOut=");
            		builder.append(hasLoggedOut);
            		builder.append(", userDefaultDevice=");
            		builder.append(userDefaultDevice);
            		builder.append(", lastDeviceUsed=");
            		builder.append(lastDeviceUsed);
            		builder.append(", lastLoginDate=");
            		builder.append(lastLoginDate);
            		builder.append(", creationDate=");
            		builder.append(creationDate);
            		builder.append(", activationDate=");
            		builder.append(activationDate);
            		builder.append(", expireDate=");
            		builder.append(expireDate);
            		builder.append(", disabledDate=");
            		builder.append(disabledDate);
            		builder.append(", userKey=");
            		builder.append(userKey);
            		builder.append(", userType=");
            		builder.append(userType);
            		builder.append(", userRole=");
            		builder.append(userRole);
            		builder.append(", loginTryCount=");
            		builder.append(loginTryCount);
            		builder.append(", systenUserAuthorities=");
            		builder.append("]");
            		return builder.toString();
            	}

            	public String getResetPasswordSessionID() {
            		return resetPasswordSessionID;
            	}

            	public void setResetPasswordSessionID(String resetPasswordSessionID) {
            		this.resetPasswordSessionID = resetPasswordSessionID;
            	}

           
}