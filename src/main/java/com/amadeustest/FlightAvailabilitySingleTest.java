package com.amadeustest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import com.amadeus.Amadeus;
import com.amadeus.Response;
import com.amadeus.exceptions.ResponseException;

public class FlightAvailabilitySingleTest {
	
	public static void main(String args[]) throws ResponseException, IOException {
	
		
		 List durationArray = new ArrayList();
		 List numberArray = new ArrayList();
		 List carrierCodeArray = new ArrayList();
		 List arrivalCodeArray = new ArrayList();
		 List arrivalTimeArray = new ArrayList();
		 List departureCodeArray = new ArrayList(); 
		 List departureTimeArray = new ArrayList();
		 List operatorCarrierCodeArray = new ArrayList(); 
		 List aircraftCodeArray = new ArrayList();
		 List totalSeatsArray = new ArrayList();


		// Amadeus authorization creds to generate access token
			//Amadeus amadeus = Amadeus.builder("emcOTJUDtdaLuXc5euAHaLNAKHDNFknX", "2aEMOAogWDuGj55C").build();
		 Amadeus amadeus=Amadeus.builder(AllAPICalls.getApiKey(), AllAPICalls.getApiSecret()).build(); 
				// Creation of Request body
				String body = "{\"originDestinations\":[{\"id\":\"1\",\"originLocationCode\":\"EDI\","
								+ "\"destinationLocationCode\":\"LTN\",\"departureDateTime\":{\"date\":\"2021-11-22\"}}],\"travelers\":[{\"id\":\"1\",\"travelerType\":\"ADULT\"}],"
								+ "\"searchCriteria\" : \r\n" + "          {\r\n" + "              \"flightFilters\": {\r\n"
								+ "                 \"carrierRestrictions\" : {\r\n"
								+ "                            \"includedCarrierCodes\" : [\"KL\",\"AF\",\"UA\",\"EK\",\"BA\",\"EY\",\"AA\",\"WN\",\"U2\",\"AC\",\"6E\"]\r\n"
								+ "                                         },\r\n" + "                \"connectionRestriction\": {\r\n"
								+ "                            \"maxNumberOfConnections\" : \"0\"\r\n"
								+ "                                          }\r\n" + "                                }\r\n"
								+ "          }\r\n" + "  ,\r\n" + "\"sources\":[\"GDS\"]}";

				/*String body = "{\"originDestinations\":[{\"id\":\"1\",\"originLocationCode\":\"LHR\","
						+ "\"destinationLocationCode\":\"JFK\",\"departureDateTime\":{\"date\":\"2021-11-10\"}}],\"travelers\":[{\"id\":\"1\",\"travelerType\":\"ADULT\"}],"
						+ "\"searchCriteria\" : \r\n" + "          {\r\n" + "              \"flightFilters\": {\r\n"
						+ "                 \"carrierRestrictions\" : {\r\n"
						+ "                                         },\r\n" + "                \"connectionRestriction\": {\r\n"
						+ "                            \"maxNumberOfConnections\" : \"0\"\r\n"
						+ "                                          }\r\n" + "                                }\r\n"
						+ "          }\r\n" + "  ,\r\n" + "\"sources\":[\"GDS\"]}";
		*/
				// API call to get response
				Response response = amadeus.post("/v1/shopping/availability/flight-availabilities", body);
				if(response.getStatusCode()!=200) {
					System.out.println("System Error..!! Please try again");
				}
				else
				{
				String jsonResponse = response.getResult().toString();
				// Printing Data json
				System.out.println(jsonResponse);
				// Parsing JSON response
				JSONObject obj = new JSONObject(jsonResponse);
				JSONArray data = obj.getJSONArray("data");
				for (int i = 0; i < data.length(); i++) {

					String duration = (String) data.getJSONObject(i).get("duration");

					JSONArray segments = data.getJSONObject(i).getJSONArray("segments");
					for (int j = 0; j < segments.length(); j++) {
						String number = (String) segments.getJSONObject(j).get("number");

						String carrierCode = (String) segments.getJSONObject(j).get("carrierCode");
						System.out.println(carrierCode);

						JSONObject arrival = segments.getJSONObject(j).getJSONObject("arrival");
						String iataCode = arrival.getString("iataCode");

						String arrivalTime = arrival.getString("at");

						JSONObject departure = segments.getJSONObject(j).getJSONObject("departure");
						String depiataCode = departure.getString("iataCode");

						String departureTime = departure.getString("at");
						String opcarrierCode;
						if(segments.getJSONObject(j).has("operating")) {
							JSONObject operating = segments.getJSONObject(j).getJSONObject("operating");
							opcarrierCode = operating.getString("carrierCode");
						}
						else
						{
							opcarrierCode = "opcarrier code not present";
						}

						JSONObject aircraft = segments.getJSONObject(j).getJSONObject("aircraft");
						String aircraftCode = aircraft.getString("code");

						int totalSeats = 0;
						JSONArray availabilityClasses = segments.getJSONObject(j).getJSONArray("availabilityClasses");
						for (int k = 0; k < availabilityClasses.length(); k++) {
							int numberOfBookableSeats = (int) availabilityClasses.getJSONObject(k).get("numberOfBookableSeats");
							totalSeats = totalSeats + numberOfBookableSeats;
						}
						//Adding all the data to arraylists
						durationArray.add(duration);
						numberArray.add(number);
						carrierCodeArray.add(carrierCode);
						arrivalCodeArray.add(iataCode);
						arrivalTimeArray.add(arrivalTime);
						departureCodeArray.add(depiataCode);
						departureTimeArray.add(departureTime);
						operatorCarrierCodeArray.add(opcarrierCode);
						aircraftCodeArray.add(aircraftCode);
						totalSeatsArray.add(totalSeats);
					}
				}
				XSSFWorkbook workbook = new XSSFWorkbook();
				if(workbook.getNumberOfSheets()==0) {
				XSSFSheet sheet = workbook.createSheet("FlightAvailability");
				//if(sheet.getLastRowNum() == 0  && sheet.getRow(0)==null)
				//{
				System.out.println("inside if");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("SNo");
				header.createCell(1).setCellValue("Duration");
				header.createCell(2).setCellValue("Departure Iata Code");
				header.createCell(3).setCellValue("Departure Time");
				header.createCell(4).setCellValue("Arrival Iata Code");
				header.createCell(5).setCellValue("Arrival Time");
				header.createCell(6).setCellValue("Carrier Code");
				header.createCell(7).setCellValue("Aircraft Number");
				header.createCell(8).setCellValue("Aircraft Code");
				header.createCell(9).setCellValue("No of Bookable Seats");
				header.createCell(10).setCellValue("Operator Carrier Code");
				//Checking if all the arraylists are of same size 
				if(durationArray.size()!=departureCodeArray.size() || durationArray.size()!= departureTimeArray.size()
						|| durationArray.size()!=arrivalCodeArray.size() || durationArray.size() != arrivalTimeArray.size()
						|| durationArray.size() != carrierCodeArray.size() || durationArray.size() != numberArray.size()
						|| durationArray.size() != aircraftCodeArray.size() || durationArray.size() != totalSeatsArray.size()
						|| durationArray.size() != operatorCarrierCodeArray.size()) {

					throw new IllegalStateException("Some data Missing..!! Please try Again..!!");
				}
				//Looping through the arrayLists 
				for(int m=0;m<durationArray.size();m++) {
					Row row = sheet.createRow((short) m+1);
					row.createCell(0).setCellValue(m+1);
					row.createCell(1).setCellValue((String) durationArray.get(m));
					row.createCell(2).setCellValue((String)departureCodeArray.get(m));
					row.createCell(3).setCellValue((String)departureTimeArray.get(m));
					row.createCell(4).setCellValue((String)arrivalCodeArray.get(m));
					row.createCell(5).setCellValue((String)arrivalTimeArray.get(m));
					row.createCell(6).setCellValue((String)carrierCodeArray.get(m));
					row.createCell(7).setCellValue((String)numberArray.get(m));
					row.createCell(8).setCellValue((String)aircraftCodeArray.get(m));
					row.createCell(9).setCellValue((Integer)totalSeatsArray.get(m));
					row.createCell(10).setCellValue((String)operatorCarrierCodeArray.get(m));	
				}
				}
				else
				{
					System.out.println("inside else");
					for(int m=0;m<durationArray.size();m++) {
					XSSFSheet sheet = workbook.getSheetAt(0);
					int rowNum = sheet.getLastRowNum();
					Row row = sheet.createRow(rowNum);
					row.createCell(0).setCellValue(m+1);
					row.createCell(1).setCellValue((String) durationArray.get(m));
					row.createCell(2).setCellValue((String)departureCodeArray.get(m));
					row.createCell(3).setCellValue((String)departureTimeArray.get(m));
					row.createCell(4).setCellValue((String)arrivalCodeArray.get(m));
					row.createCell(5).setCellValue((String)arrivalTimeArray.get(m));
					row.createCell(6).setCellValue((String)carrierCodeArray.get(m));
					row.createCell(7).setCellValue((String)numberArray.get(m));
					row.createCell(8).setCellValue((String)aircraftCodeArray.get(m));
					row.createCell(9).setCellValue((Integer)totalSeatsArray.get(m));
					row.createCell(10).setCellValue((String)operatorCarrierCodeArray.get(m));	
					}
				}
				
				try {
					FileOutputStream outputStream = new FileOutputStream("D://FlightAvailabiltySingleTest.xlsx");
					workbook.write(outputStream); 
					}
				finally {
					workbook.close();
				}
				System.out.println("Data inserted Successfully");	

	}
	}

}
