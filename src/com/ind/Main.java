package com.ind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		//Parser.parse("redis-unstable");
		readOBJ1("f1.txt");
		readOBJ2("f2.txt");
		QueryProcessor p = new QueryProcessor();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("enter query");
		String query = "";
		while (!(query = br.readLine()).equals("exit")) {
			p.parseQuery(query);
			p.processResult();
		}
		
		//writeTO(Indexes.getFileToLineNumberMap(),"f1.txt");
		//writeTO(Indexes.getWordToFileNameMap(),"f2.txt");
		
	}

	public static void writeTO(Object o,String name){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {

			fout = new FileOutputStream(name);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(o);
		}catch(Exception e){
			
		}
		finally{
			try {
				fout.close();
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void readOBJ1(String name){
		FileInputStream fi=null;
		ObjectInputStream oi=null;
		try {
			fi = new FileInputStream(new File(name));
			oi = new ObjectInputStream(fi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Map<Long, String>> pr1=null;
		// Read objects
		try {
			 pr1 = (Map<String, Map<Long, String>>) oi.readObject();
			 Indexes.setFileToLineNumberMap(pr1);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void readOBJ2(String name){
		FileInputStream fi=null;
		ObjectInputStream oi=null;
		try {
			fi = new FileInputStream(new File(name));
			oi = new ObjectInputStream(fi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Map<String, List<Long>>> pr1=null;
		// Read objects
		try {
			 pr1 = (Map<String, Map<String, List<Long>>>) oi.readObject();
			 Indexes.setWordToFileNameMap(pr1);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
