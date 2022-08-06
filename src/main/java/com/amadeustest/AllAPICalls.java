package com.amadeustest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.amadeus.Amadeus;
import com.amadeus.Response;
import com.amadeus.exceptions.ResponseException;

public class AllAPICalls {
	
	private static Logger logger = Logger.getLogger(AllAPICalls.class);

	private static String apiKey = "7IKQgKIa5Sbp5zMiYnsGZA90oGH5AdRF";
	private static String apiSecret = "LN7GEurYvpNyYfEy";
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
			reader = new BufferedReader(new FileReader(currentDir+"\\resources\\InputDatesList.txt"));
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


	public static void main(String[] args) throws ResponseException, IOException, EncryptedDocumentException, InvalidFormatException {
		setDatesList();
		BasicConfigurator.configure();
		FlightAvailabilityMultipleCallsAMStoDEL f=new FlightAvailabilityMultipleCallsAMStoDEL();
		f.allAPICalls();

		FlightAvailabilityMultipleCallsAUHtoHYD f1 = new FlightAvailabilityMultipleCallsAUHtoHYD();
		f1.allAPICalls();

		FlightAvailabilityMultipleCallsCDGtoYYZ f2 = new FlightAvailabilityMultipleCallsCDGtoYYZ();
		f2.allAPICalls();

		FlightAvailabilityMultipleCallsDELtoAMS f3 = new FlightAvailabilityMultipleCallsDELtoAMS();
		f3.allAPICalls();

		FlightAvailabilityMultipleCallsDXBtoLHR f4 = new FlightAvailabilityMultipleCallsDXBtoLHR();
		f4.allAPICalls();

		FlightAvailabilityMultipleCallsHYDtoAUH f5 = new FlightAvailabilityMultipleCallsHYDtoAUH();
		f5.allAPICalls();

		FlightAvailabilityMultipleCallsIAHtoSFO f6 = new FlightAvailabilityMultipleCallsIAHtoSFO();
		f6.allAPICalls();

		FlightAvailabilityMultipleCallsJFKtoLHR f7 = new FlightAvailabilityMultipleCallsJFKtoLHR();
		f7.allAPICalls();

		FlightAvailabilityMultipleCallsJFKtoSFO f8 = new FlightAvailabilityMultipleCallsJFKtoSFO();
		f8.allAPICalls();

		FlightAvailabilityMultipleCallsLHRtoDXB f9 = new FlightAvailabilityMultipleCallsLHRtoDXB();
		f9.allAPICalls();

		FlightAvailabilityMultipleCallsLHRtoJFK f10 = new FlightAvailabilityMultipleCallsLHRtoJFK();
		f10.allAPICalls();

		FlightAvailabilityMultipleCallsSFOtoIAH f11 = new FlightAvailabilityMultipleCallsSFOtoIAH();
		f11.allAPICalls();

		FlightAvailabilityMultipleCallsSFOtoJFK f12 = new FlightAvailabilityMultipleCallsSFOtoJFK();
		f12.allAPICalls();

		FlightAvailabilityMultipleCallsSFOtoYYZ f13 = new FlightAvailabilityMultipleCallsSFOtoYYZ();
		f13.allAPICalls();

		FlightAvailabilityMultipleCallsYYZtoCDG f14 = new FlightAvailabilityMultipleCallsYYZtoCDG();
		f14.allAPICalls();

		FlightAvailabilityMultipleCallsYYZtoSFO f15 = new FlightAvailabilityMultipleCallsYYZtoSFO();
		f15.allAPICalls();
	}



	/*public static void main(String[] args){
		Timer timer = new Timer();
		TimerTask tt = new TimerTask(){
			public void run(){
				Calendar cal = Calendar.getInstance(); 

				int hour = cal.get(Calendar.HOUR_OF_DAY);//get the hour number of the day, from 0 to 23

				if(hour == 12){
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
	}
*/
	}
