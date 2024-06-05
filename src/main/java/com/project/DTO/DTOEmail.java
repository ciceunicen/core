package com.project.DTO;

import lombok.Data;

@Data
public class DTOEmail {

	private String mailFrom;
	private String mailTo;
	private String subject;
	private String name;
	private String tokenPassword;
	
	public DTOEmail() {
	}

	public DTOEmail(String mailFrom, String mailTo, String subject, String name, String tokenPassword) {
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.subject = subject;
		this.name = name;
		this.tokenPassword = tokenPassword;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTokenPassword() {
		return tokenPassword;
	}

	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}

	@Override
	public String toString() {
		return "DTOEmail [mailFrom=" + mailFrom + ", mailTo=" + mailTo + ", subject=" + subject + ", name=" + name
				+ ", tokenPassword=" + tokenPassword + "]";
	}
	
}
