package com.test.service;

import java.io.IOException;
import java.util.Collections;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.VO.DeleteRequestVO;
import com.test.VO.ResponseVO;
import com.test.VO.SearchRequestVO;
import com.test.VO.UpdateRequestVO;

@Service
public class SolrSearchService {

	@Autowired
	SolrUtil solrUtil;

	private static final String collection = "UserSearchCloud";

	public ResponseVO search(SearchRequestVO requestVO) {
		CloudSolrClient solrClient = solrUtil.createConnection();
		String query = requestVO.getQuery();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(query);
		solrQuery.setRows(50);
		solrQuery.set("collection", collection);
		solrQuery.set("wt", "json");
		SolrDocumentList documentList = solrUtil.getSolrResponse(solrQuery, collection, solrClient);
		ResponseVO responseVO = new ResponseVO();
		if(documentList != null && documentList.size() >0){
			responseVO.setDocumentList(documentList);
			responseVO.setMessage("Success");
		}else{
			responseVO.setMessage("Failure");
			responseVO.setErrorMessage("Records Not Found");
		}
		return responseVO;
	}

	public ResponseVO update(UpdateRequestVO requestVO) {
		CloudSolrClient solrClient = solrUtil.createConnection();
		UpdateResponse response = new UpdateResponse();
		
		SolrDocument sdoc1 = null;
		String id = requestVO.getId();
		solrClient.setDefaultCollection(collection);
		SolrInputDocument sdoc = new SolrInputDocument();
		try {
			sdoc1 = solrClient.getById(id);
		} catch (SolrServerException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(sdoc1 != null){
			sdoc.setField("FIRST_NAME",requestVO.getFirstName() != null ? requestVO.getFirstName() : sdoc1.get("FIRST_NAME"));
			sdoc.setField("WORK_EMAIL",requestVO.getWorkEmail() != null ? requestVO.getWorkEmail() : sdoc1.get("WORK_EMAIL"));
			sdoc.setField("LAST_NAME",requestVO.getLastName() != null ? requestVO.getLastName() : sdoc1.get("LAST_NAME"));
			sdoc.setField("ADDRESS1",requestVO.getAddress1() != null ? requestVO.getAddress1() : sdoc1.get("ADDRESS1"));
			sdoc.setField("ADDRESS2",requestVO.getAddress2() != null ? requestVO.getAddress2() : sdoc1.get("ADDRESS2"));
			sdoc.setField("PHONE1",requestVO.getPhone1() != null ? requestVO.getPhone1() : sdoc1.get("PHONE1"));
			sdoc.setField("JOB_TITLE",requestVO.getJobTitle() != null ? requestVO.getJobTitle() : sdoc1.get("JOB_TITLE"));
			sdoc.setField("COMPANY_NAME",requestVO.getCompanyName() != null ? requestVO.getCompanyName() : sdoc1.get("COMPANY_NAME") );
			sdoc.setField("CITY",requestVO.getCity() != null ? requestVO.getCity() : sdoc1.get("CITY"));
			sdoc.setField("PHONE2",requestVO.getPhone2() != null ? requestVO.getPhone2() : sdoc1.get("PHONE2"));
			sdoc.setField("USER_NAME",requestVO.getUserName() != null ? requestVO.getUserName() : sdoc1.get("USER_NAME"));
			sdoc.setField("id",sdoc1.get("id"));
			sdoc.setField("_version_","0");
			try {
				solrClient.add(sdoc);
				response = solrClient.commit();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ResponseVO responseVO = new ResponseVO();
		if(response != null && response.getResponse() != null){
			responseVO.setMessage("Document Updated");
		}else{
			responseVO.setErrorMessage("Document Not Found");
		}
		return responseVO;
	}
	
	public ResponseVO delete(DeleteRequestVO requestVO) {
		CloudSolrClient solrClient = solrUtil.createConnection();
		UpdateResponse response = new UpdateResponse();
		try {
			solrClient.setDefaultCollection(collection);
			response = solrClient.deleteById(requestVO.getId());
		} catch (SolrServerException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ResponseVO responseVO = new ResponseVO();
		if(response != null){
			responseVO.setMessage("Document Deleted");
		}
		return responseVO;
	}

}
