package com.test.VO;

import org.apache.solr.common.SolrDocumentList;

public class ResponseVO {

	private String message;
 	private SolrDocumentList documentList;
 	private String errorMessage;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SolrDocumentList getDocumentList() {
		return documentList;
	}
	public void setDocumentList(SolrDocumentList documentList) {
		this.documentList = documentList;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
