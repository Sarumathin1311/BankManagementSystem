package com.mutualfund.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class MutualFund {

	@Id
	private Integer fundId;
	@Column
	private String fundName;
	@Digits(integer = 6, fraction = 2, message = "{javax.validation.constraints.Digits.message}")
	private BigDecimal amount;
	@CreationTimestamp
	public LocalDateTime timestamp;

	@ManyToOne
	@JoinColumn(name = "panNo")
	private ApplicationUser user;
	@ManyToOne
	@JoinColumn(name = "accountNo")
	private Account account;

	public Integer getFundId() {
		return fundId;
	}

	public void setFundId(Integer fundId) {
		this.fundId = fundId;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
/*
	public ApplicationUser getUser() {
		return user;
	}
*/
	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "MutualFund [fundId=" + fundId + ", fundName=" + fundName + ", amount=" + amount + ", timestamp="
				+ timestamp + ", user=" + user + ", account=" + account + "]";
	}

	public MutualFund(Integer fundId, String fundName,
			@Digits(integer = 6, fraction = 2, message = "{javax.validation.constraints.Digits.message}") BigDecimal amount,
			LocalDateTime timestamp, ApplicationUser user, Account account) {
		super();
		this.fundId = fundId;
		this.fundName = fundName;
		this.amount = amount;
		this.timestamp = timestamp;
		this.user = user;
		this.account = account;
	}

	public MutualFund() {
		super();

	}

}