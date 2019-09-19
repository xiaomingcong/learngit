package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

	public static void main(String[] args) throws Exception {
		InputStream inputStream1 = IOUtils.class.getClassLoader().getResourceAsStream("add.txt");
		String path = System.getProperty("user.dir");
		System.out.println(inputStream1.available() + path);
		String lineTxt;
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream1));
		while ((lineTxt = br.readLine()) != null) {
			if(lineTxt.contains("、")) {
				String[] names = lineTxt.split("、");
				for (String name : names) {
					System.out.println(name);
				}
			}

		}
		br.close();
	}
	
	public static List<String> get(String fileName) throws Exception {
		List<String> list = new ArrayList<String>();
		InputStream inputStream1 = IOUtils.class.getClassLoader().getResourceAsStream(fileName);
		String lineTxt;
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream1));
		while ((lineTxt = br.readLine()) != null) {
			if(lineTxt.contains("、")) {
				String[] names = lineTxt.split("、");
				for (String name : names) {
					list.add(name);
				}
			}

		}
		br.close();
		return list;
		
	}

}
