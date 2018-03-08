package com.ActiveRisk.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.ActiveRisk.constants.Constants;


public class FileUtil 
{	
	// Adding data to ArrayList from file
		public static ArrayList<String> readToArrayList(String fileName) {
			ArrayList<String> dataList = new ArrayList<String>();
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(Constants.csvDataFileFolderPath + "/" + fileName);
				br = new BufferedReader(fr);
				String data;
				while ((data = br.readLine()) != null) {
					dataList.add(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return dataList;
		}	
}
