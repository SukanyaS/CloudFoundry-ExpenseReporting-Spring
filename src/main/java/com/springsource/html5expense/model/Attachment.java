package com.springsource.html5expense.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Attachment implements Serializable{
	


	@Id
	@GeneratedValue
	private Long id;
	
	private String fileName;
	
	@Lob
	private Blob content;
	
	private String contentType;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileNmae() {
		return fileName;
	}

	public void setFileNmae(String fileNmae) {
		this.fileName = fileNmae;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public Attachment(){
		
	}
	public Attachment(String fileName,String contentType,Blob content){
		this.content = content;
		this.contentType = contentType;
		this.fileName = fileName;
	}

}
