package com.ind.resource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResult {

	private String fileName;

	private double tfIdfRanking;

	private List<String> lines;
	
	public SearchResult() {
		
	}
	public SearchResult(String name,double rank) {
		this.fileName=name;
		this.tfIdfRanking=rank;
		lines=new ArrayList<String>();
	}
	
	public String getFileName() {
		return fileName;
	}

	@XmlElement(name="fileName")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getTfIdfRanking() {
		return tfIdfRanking;
	}

	@XmlElement(name="tfIdfRanking")
	public void setTfIdfRanking(double ranking) {
		this.tfIdfRanking = ranking;
	}

	public List<String> getLines() {
		return lines;
	}

	@XmlElement(name="searchResults")
	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	public void addLine(String line){
		lines.add(line);
	}

}
