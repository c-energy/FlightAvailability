package com.flightdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.amadeus.exceptions.ResponseException;
import com.amadeustest.AllAPICalls;

public class FlightDataApiCall {
	private static Logger logger = Logger.getLogger(FlightDataApiCall.class);

	private static String apiKey = "<<apikey>>";
	private static String apiSecret = "<<apisecret>>";
	static List datesArray = new ArrayList();

	public static String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public static String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public static List setDatesList() {
		BufferedReader reader; 
		try {
			String currentDir = System.getProperty("user.dir");
			reader = new BufferedReader(new FileReader(currentDir+"\\resources\\TestList.txt"));
			String line = reader.readLine();
			while(line != null) {
				logger.info(line);
				datesArray.add(line);
				line = reader.readLine();
			}
			reader.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return datesArray;
	}


	public static void mainTask() throws ResponseException, IOException, EncryptedDocumentException, InvalidFormatException {
		setDatesList();
		//For logging purpose
		BasicConfigurator.configure();
		FlightDataBAAirlines f=new FlightDataBAAirlines();
		f.allAPICalls();
	}
	
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, ResponseException, IOException {
		mainTask();
	}
	
/*	public static void main(String[] args){
		Timer timer = new Timer();
		TimerTask tt = new TimerTask(){
		public void run(){
			Calendar cal = Calendar.getInstance(); 

			int hour = cal.get(Calendar.HOUR_OF_DAY);//get the hour number of the day, from 0 to 23

			if(hour == 23 || hour == 14 || hour == 18 || hour == 21){
				logger.info("Timer Job executed :: " + new Date() + " :: " + Thread.currentThread().getName());
				try {
					mainTask();
				} catch (EncryptedDocumentException | InvalidFormatException | ResponseException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			}
		}
	};
	timer.schedule(tt, 1000, 1000*5);//	delay the task 1 second, and then run task every five seconds
}*/


}
