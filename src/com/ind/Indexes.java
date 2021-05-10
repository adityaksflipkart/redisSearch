package com.ind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Indexes {
	// for stroring file to line mapping {offline processing}
	private static Map<String, Map<Long, String>> fileToLineNumber;
	
	// for storing posting list of each word
	private static Map<String, Map<String, List<Long>>> wordToFileName;
	
	
	
	static {
		fileToLineNumber = new TreeMap<String, Map<Long, String>>();
		wordToFileName = new TreeMap<String, Map<String, List<Long>>>();
		
	}

	public static void addWordToIndex(String word, String fileName, long lineNumber) {
		if (!wordToFileName.containsKey(word)) {
			wordToFileName.put(word, new TreeMap<String, List<Long>>());
		}
		if (!wordToFileName.get(word).containsKey(fileName)) {
			wordToFileName.get(word).put(fileName, new ArrayList<Long>());
		}
		wordToFileName.get(word).get(fileName).add(lineNumber);
	}

	public static void addLineToFile(String fileName, long lineNuber, String line) {
		if (!fileToLineNumber.containsKey(fileName)) {
			fileToLineNumber.put(fileName, new HashMap<Long, String>());
		}
		fileToLineNumber.get(fileName).put(lineNuber, line);
	}
	public static void printPostingList(){
		
		for (String word : wordToFileName.keySet()) {
			for (String fileName : wordToFileName.get(word).keySet()) {
				for (Long lineNumber : wordToFileName.get(word).get(fileName)) {
					System.out.println("word "+word +"  fileName "+fileName +" line number "+lineNumber);
				}
			}
		}
		System.out.println(wordToFileName.size());
		
	}
	public static void printFileMap(){
		for (String key : fileToLineNumber.keySet()) {
			System.out.println("file name "+key);
			for (Long lineno: fileToLineNumber.get(key).keySet()) {
				System.out.println(lineno +" "+fileToLineNumber.get(key).get(lineno));
			}
		}
		System.out.println("total map size "+ fileToLineNumber.size());
	}
	public static Map<String, List<Long>> getPostingList(String word){
		return wordToFileName.get(word);
	}
	public static Map<Long, String> getLinesFromFile(String fileName){
		return fileToLineNumber.get(fileName);
	}
	public static int getFileToLineNumberMapSize(){
		return fileToLineNumber.size();
	}
	public static Map<String, Map<String, List<Long>>> getWordToFileNameMap(){
		return wordToFileName;
	}
	public static Map<String, Map<Long, String>> getFileToLineNumberMap (){
		return fileToLineNumber;
	}
	public static void setWordToFileNameMap(Map<String, Map<String, List<Long>>> mp){
		 wordToFileName=mp;
	}
	public static void setFileToLineNumberMap (Map<String, Map<Long, String>> mp){
		 fileToLineNumber=mp;
	}
}
