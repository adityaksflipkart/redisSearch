package com.ind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import com.ind.resource.AllResults;
import com.ind.resource.SearchResult;

public class QueryProcessor {

	private List<String> queryToken;

	public QueryProcessor() {
		queryToken = new ArrayList<String>();
	}

	public void parseQuery(String query) {
		queryToken.clear();
		Stemmer stemmer = new Stemmer();
		StringTokenizer st = new StringTokenizer(query, Constants.DELIMITER_LIST);

		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			token = token.replaceAll("[^0-9\\x00-\\x7f]", "").replaceAll("\\s+", "").replaceAll("^\\d*", "");
			String word = token.toLowerCase();

			if (!word.isEmpty() && word.length() >= 3 && !Constants.STOP_WORDS.contains(word)) {

				stemmer.add(word.toCharArray(), word.length());
				stemmer.stem();
				word = stemmer.toString();
				queryToken.add(word);
			}
		}
	}

	public AllResults processResult() {
		Map<String, List<Set<Long>>> resultMap = new HashMap<String, List<Set<Long>>>();
		
		AllResults results=new AllResults();

		List<Entry<String, Double>> documentRank = new ArrayList<Entry<String, Double>>(findRank().entrySet());
		Collections.sort(documentRank, new Comparator<Entry<String, Double>>() {

			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		for (Entry<String, Double> entry : documentRank) {
			boolean fl = false;
			Set<Long> commonLines = new HashSet<Long>();
			Set<Long> differentLines = new HashSet<Long>();

			for (String word : queryToken) {
				Map<String, List<Long>> postingListMap = Indexes.getPostingList(word);

				if (!fl && commonLines.isEmpty()) {
					if (postingListMap.containsKey(entry.getKey())) {
						commonLines.addAll(postingListMap.get(entry.getKey()));
						fl = true;
					}
				} else {
					if (postingListMap.containsKey(entry.getKey())) {
						commonLines.retainAll(postingListMap.get(entry.getKey()));
					}
				}

				if (postingListMap.containsKey(entry.getKey())) {
					differentLines.addAll(postingListMap.get(entry.getKey()));
				}
			}

			differentLines.removeAll(commonLines);
			resultMap.put(entry.getKey(), new ArrayList<Set<Long>>());
			resultMap.get(entry.getKey()).add(commonLines);
			resultMap.get(entry.getKey()).add(differentLines);
		}

		for (Entry<String, Double> entry : documentRank) {
			String fileName = entry.getKey().split("#")[1];
			boolean fl=false;
			SearchResult s=new  SearchResult(fileName, entry.getValue());
			for (Long n : resultMap.get(entry.getKey()).get(0)) {
				/*if(!fl){
					System.out.println("--------------------**  "+fileName + " rank " + entry.getValue()+"  **-------------------------------");*/
					fl=true;
				//}
				s.addLine(Indexes.getLinesFromFile(entry.getKey()).get(n));
				//System.out.println(Indexes.getLinesFromFile(entry.getKey()).get(n));
			}
			if(fl){
				results.addResult(s);
			}
		}
		for (Entry<String, Double> entry : documentRank) {
			String fileName = entry.getKey().split("#")[1];
			boolean fl=false;
			SearchResult s=new  SearchResult(fileName, entry.getValue());
			for (Long n : resultMap.get(entry.getKey()).get(1)) {
				/*if(!fl){
					System.out.println("------------------**  "+fileName + " rank " + entry.getValue()+"  ***-------------------------------");*/
					fl=true;
				//}
				s.addLine(Indexes.getLinesFromFile(entry.getKey()).get(n));
				//System.out.println(Indexes.getLinesFromFile(entry.getKey()).get(n));
			}
			if(fl){
				results.addResult(s);
			}
			
		}
		return results;
	}

	private Map<String, Double> findRank() {

		Map<String, Map<String, Double>> documentRank = new HashMap<String, Map<String, Double>>();
		Set<String> commonDocuments = new HashSet<String>();

		Map<String, Double> combinedRank = new HashMap<String, Double>();

		boolean fl = false;

		for (String word : queryToken) {

			Map<String, List<Long>> postingListMap = Indexes.getPostingList(word);
			double idf = Math.log((Indexes.getFileToLineNumberMapSize() / postingListMap.size()));

			if (!fl && commonDocuments.isEmpty()) {
				commonDocuments.addAll(postingListMap.keySet());
				fl = true;
			} else {
				commonDocuments.retainAll(postingListMap.keySet());
			}

			for (String fileName : postingListMap.keySet()) {

				double tf = postingListMap.get(fileName).size();
				double rank = tf * idf;

				if (!documentRank.containsKey(word)) {
					documentRank.put(word, new HashMap<String, Double>());
				}
				documentRank.get(word).put(fileName, rank);

			}
		}

		if (commonDocuments.isEmpty()) {
			for (String word : queryToken) {
				Map<String, List<Long>> postingListMap = Indexes.getPostingList(word);
				commonDocuments.addAll(postingListMap.keySet());
			}
		}

		for (String fileName : commonDocuments) {
			double rankSum = 0;
			for (String word : queryToken) {
				if (documentRank.get(word).containsKey(fileName)) {
					rankSum += documentRank.get(word).get(fileName);
				}
			}
			combinedRank.put(fileName, rankSum);
		}

		return combinedRank;
	}

}
