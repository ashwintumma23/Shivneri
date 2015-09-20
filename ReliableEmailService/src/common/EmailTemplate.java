package common;

import java.io.Serializable;

/**
 * EmailTemplate: Generic template for the emails
 * 
 */
public class EmailTemplate implements Serializable{
		

	private static final long serialVersionUID = 1L;
	
	private String to = "";
	private String from = "";
	private String cc = "";
	private String bcc = "";
	private String subject = "";
	private String body = "";
	
	public EmailTemplate(EmailTemplate et){
		this.to = et.getTo();
		this.from = et.getFrom();
		this.subject = et.getSubject();
		this.body = et.getBody();		
	}
	
	public EmailTemplate(String to, String from, String subject, String body) {
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.body = body;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
