package com.ind.resource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AllResults {

	private List<SearchResult> allResults;
	public AllResults() {
		allResults=new ArrayList<SearchResult>();
	}
	public List<SearchResult> getAllResults() {
		return allResults;
	}

	@XmlElement(name="SearchResult")
	public void setAllResults(List<SearchResult> allResults) {
		this.allResults = allResults;
	}
	
	public void addResult(SearchResult s){
		allResults.add(s);
	}
	
}
