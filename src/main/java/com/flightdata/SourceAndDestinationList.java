package com.flightdata;

import java.util.ArrayList;
import java.util.List;

public class SourceAndDestinationList {
	public static List<String> getSourceList(){
		List<String> sourceList = new ArrayList<>();
		//Emirates List
		sourceList.add("DXB");
		sourceList.add("DXB");
		sourceList.add("LHR");
		sourceList.add("LHR");
		sourceList.add("LHR");
		sourceList.add("LHR");
		sourceList.add("DXB");
		sourceList.add("DXB");
		sourceList.add("DXB");
		sourceList.add("AMS");
		
		//United Airlines list 
		sourceList.add("SFO");
		sourceList.add("IAH");
		sourceList.add("IAH");
		sourceList.add("SFO");
		sourceList.add("NYC");
		sourceList.add("IAH");
		sourceList.add("SFO");
		sourceList.add("IAH");
		sourceList.add("IAH");
		sourceList.add("LAX");
		sourceList.add("ORD");
		
		return sourceList;
	}
	
	public static List<String> getDestinationList(){
		List<String> destinationList = new ArrayList<>();
		//Emirates List
		destinationList.add("YYZ");
		destinationList.add("LHR");
		destinationList.add("WLG");
		destinationList.add("SAN");
		destinationList.add("DEL");
		destinationList.add("HYD");
		destinationList.add("AMS");
		destinationList.add("CDG");
		destinationList.add("HYD");
		destinationList.add("DEL");
		
		//United Airlines List
		destinationList.add("LHR");
		destinationList.add("LHR");
		destinationList.add("CDG");
		destinationList.add("CDG");
		destinationList.add("PUJ");
		destinationList.add("PUJ");
		destinationList.add("AKL");
		destinationList.add("AKL");
		destinationList.add("LAX");
		destinationList.add("SFO");
		destinationList.add("SJC");
		
		return destinationList;
	}

}
