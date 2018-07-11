package com.test.controller;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.VO.DeleteRequestVO;
import com.test.VO.ResponseVO;
import com.test.VO.SearchRequestVO;
import com.test.VO.UpdateRequestVO;
import com.test.service.SolrSearchService;

@RestController
@RequestMapping("/rest")
public class SolrPOCController { 
	@Autowired
	private SolrSearchService solrSearchService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO> searchFromSolr(@RequestBody SearchRequestVO requestVO)
			throws NamingException {
		return new ResponseEntity<ResponseVO>(solrSearchService.search(requestVO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO> updateToSolr(@RequestBody UpdateRequestVO requestVO)
			throws NamingException {
		return new ResponseEntity<ResponseVO>(solrSearchService.update(requestVO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<ResponseVO> deleteFromSolr(@RequestBody DeleteRequestVO requestVO)
			throws NamingException {
		return new ResponseEntity<ResponseVO>(solrSearchService.delete(requestVO), HttpStatus.OK);
	}
}
