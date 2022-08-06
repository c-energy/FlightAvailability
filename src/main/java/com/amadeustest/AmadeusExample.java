package com.amadeustest;

import com.amadeus.Amadeus;
import com.amadeus.Params;

import com.amadeus.exceptions.ResponseException;

import com.amadeus.resources.Airline;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.shopping.FlightOffers;


public class AmadeusExample {
	public static void main(String[] args) throws ResponseException {
		Amadeus amadeus = Amadeus
				.builder("emcOTJUDtdaLuXc5euAHaLNAKHDNFknX","2aEMOAogWDuGj55C")
				.build();
		 char myCharacter = "piper".charAt(3);
		/* API call which is working fine */
		 
		  Airline[] airlines = amadeus.referenceData.airlines.get(Params
				.with("airlineCodes", "BA"));

		if(airlines[0].getResponse().getStatusCode()!=200) {
			System.out.println("Wrong status code for given Search: " + airlines[0]
					.getResponse().getStatusCode());
			System.exit(-1);
		}

		System.out.println(airlines[0]);
        		
		
		//FlightOffers API call giving Authentication issue
		/*FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get
				(Params.with("originLocationCode","LHR").
				and("destinationLocationCode", "JFK").
				and("departureDate", "2021-10-30").
				and("adults", 1));

		if(flightOffers[0].getResponse().getStatusCode()!=200) {
			System.out.println("Wrong status code for given search: "+flightOffers[0].getResponse().getStatusCode());
			System.exit(-1);
		}
		System.out.println(flightOffers[0]);
		/* Error
		 * {"fault":{"faultstring":"Invalid API call as no apiproduct match found",
			"detail":{"errorcode":"keymanagement.service.InvalidAPICallAsNoApiProductMatchFound"}}}*/
		
	
		// Flight Offers Search v2 GET
		/*FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOfferSearch.get(
		              Params.with("originLocationCode", "SYD")
		                      .and("destinationLocationCode", "BKK")
		                      .and("departureDate", "2022-06-01")
		                      .and("returnDate", "2022-06-08")
		                      .and("adults", 2)
		                      .and("max", 3));
		System.out.println(flightOffersSearches[0]);*/
		
		

	}
} 