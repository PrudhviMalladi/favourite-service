package com.optimum.favourite.model;

public class User {

	private int userId;
	
	private String userName;
	
	private String userPwd;
	
	private String userRole;
	
	private int userAge;
	
	private String userEmail;
	
	private long userContact;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userName, String userPwd, String userRole, int userAge, String userEmail,
			long userContact) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userRole = userRole;
		this.userAge = userAge;
		this.userEmail = userEmail;
		this.userContact = userContact;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getUserContact() {
		return userContact;
	}

	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userRole=" + userRole + ", userAge=" + userAge
				+ ", userEmail=" + userEmail + ", userContact=" + userContact + "]";
	}
	
}

