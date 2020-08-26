package com.mutualfund.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
@Entity
public class ApplicationUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id

	private String panNo;
	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*")

	private String firstName;
	@NotEmpty
	@Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*")

	private String LastName;
	@Column(unique = true)
	@NotEmpty(message = "Email is mandatory")
	@Email(message = "email should be in proper format")
	private String emailId;
	@Column(unique = true)
	private Long contactNo;

	private String password;
	@Transient
	private String confirmPassword;
	@OneToMany
	private List<Account> account;

//		@AssertTrue(message = "confirmPassword field should be match with password field")
//		private boolean isValid() {
//			return this.confirmPassword.matches(this.password);
//		}

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Date dob;

public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

/*	public String getLastName() {
		return LastName;
	}*/

	public void setLastName(String lastName) {
		LastName = lastName;
	}

/*	public String getEmailId() {
		return emailId;
	}*/

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/*
	 * public Long getContactNo() { return contactNo; }
	 */

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

/*	public String getPassword() {
		return password;
	}*/

	public void setPassword(String password) {
		this.password = password;
	}

/*	public String getConfirmPassword() {
		return confirmPassword;
	}*/

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

/*	public Date getDob() {
		return dob;
	}*/

	public void setDob(Date dob) {
		this.dob = dob;
	}

/*	public List<Account> getAccount() {
		return account;
	}*/

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "ApplicationUser [firstName=" + firstName + ", LastName=" + LastName + ", emailId=" + emailId
				+ ", contactNo=" + contactNo + ", panNo=" + panNo + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", account=" + account + ", dob=" + dob + "]";
	}

	public ApplicationUser(String panNo, @NotEmpty @Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*") String firstName,
			@NotEmpty @Pattern(regexp = "[a-zA-Z]{2}[A-Za-z\\s]*") String lastName,
			@NotEmpty(message = "Email is mandatory") @Email(message = "email should be in proper format") String emailId,
			Long contactNo, String password, String confirmPassword, List<Account> account, Date dob) {
		super();
		this.panNo = panNo;
		this.firstName = firstName;
		LastName = lastName;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.account = account;
		this.dob = dob;
	}

	public ApplicationUser() {
		super();

	}

}