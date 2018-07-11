package com.test.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

@Service
public class SolrUtil {
	
	CloudSolrClient solrClient;
	
	@SuppressWarnings("deprecation")
	public CloudSolrClient createConnection(){
		//You need to replace SERVERNAME with the server on which the zookeeper is running
		String zkHostString = "SERVERNAME:8997,SERVERNAME:8998,SERVERNAME:8999"; //- DEV
		if(solrClient == null){
			solrClient = new CloudSolrClient.Builder().withZkHost(zkHostString).build();
		}
		return solrClient;
	}
	
	public SolrDocumentList getSolrResponse(SolrQuery solrQuery, String collection, CloudSolrClient solrClient) {
		QueryResponse response = null;
		SolrDocumentList list = null;
		try {
			QueryRequest req = new QueryRequest(solrQuery);
			solrClient.setDefaultCollection(collection);
			response = req.process(solrClient);
			list = response.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
