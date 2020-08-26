package com.registration.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Account {

	@Id
	@Size(min = 10, max = 10)
	private String accountNo;
	private String ifsc;
	@Pattern(regexp = "[a-zA-Z_.]*")
	private String bankName;
	private String micrCode;

	@OneToMany
	List<MutualFund> mutualfund;

	@ManyToOne
	@JoinColumn(name = "panNo")
	private ApplicationUser user;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public ApplicationUser getUser() {
		return user;
	}

	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	/*
	 * public List<MutualFund> getMutualfund() { return mutualfund; }
	 */

	public void setMutualfund(List<MutualFund> mutualfund) {
		this.mutualfund = mutualfund;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", ifsc=" + ifsc + ", bankName=" + bankName + ", micrCode="
				+ micrCode + ", mutualfund=" + mutualfund + ", user=" + user + "]";
	}

	public Account(@Size(min = 10, max = 10) String accountNo, String ifsc,
			@Pattern(regexp = "[a-zA-Z_.]*") String bankName, String micrCode, List<MutualFund> mutualfund,
			ApplicationUser user) {
		super();
		this.accountNo = accountNo;
		this.ifsc = ifsc;
		this.bankName = bankName;
		this.micrCode = micrCode;
		this.mutualfund = mutualfund;
		this.user = user;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

}