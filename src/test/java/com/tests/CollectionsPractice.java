package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class CollectionsPractice {
	
	@Test(enabled=false)
	public void hashMap() {
		HashMap<String,Integer> hashmap=new HashMap<String,Integer>();
		hashmap.put("s", 2);
		hashmap.put("a", 5);
		hashmap.put("i", 29);
		hashmap.replace("i", 2);
		
		System.out.println(hashmap.get("i"));
	}
	
	
	
	@Test(enabled=false)
	public void listReverseOrder() {
		LinkedList<String> li=new LinkedList<String>();
		li.add("sai");
		li.add("ram");
		li.add("abc");
		li.add("def");
		li.add("ghi");
		ListIterator<String> itr = li.listIterator(li.size());
		while(itr.hasPrevious()) {
			System.out.println(itr.previous());
		}
	}
	
	@Test(enabled=false)
	public void linkedList() {
		LinkedList<String> li=new LinkedList<String>();
		li.add("s");
		li.add("a");
		li.add("i");
		li.add(" ");
		int idx= li.indexOf(" ");
		li.remove(idx);
		li.add("r");
		li.add("a");
		li.add("m ");
		Iterator<String> iterator= li.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next());
		}
	}
	
	@Test(enabled=false)
	public void arrayList() {
		ArrayList<Integer> al=new ArrayList<Integer>();
		al.add(5);
		al.add(37);
		al.add(347);
		
		Iterator<Integer> iterator = al.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	

}
