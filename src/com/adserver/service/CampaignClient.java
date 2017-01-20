package com.adserver.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Harisha Akasapu
 * REST client to verify and validate the Request and Response of each REST services.
 * This could be replaced with JUnits for efficient and continuous unit testing.
 */
@Component("campaignClient")
public class CampaignClient {
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Method to invoke REST service for campaign creation.
	 * URI: http://localhost:8080/ad
	 * @param campaign
	 * @return
	 */
	public Object createCampaign(Campaign campaign) {
		return restTemplate.postForObject("http://localhost:8080/ad", campaign, Object.class);
	}
	
	/**
	 * Method to invoke REST service for getting campaign by Partner Id.
	 * @param partnerId
	 * URI: http://localhost:8080/ad/{partnerId}
	 * @return
	 */
	public Object getCampaign(String partnerId) {
		Map<String, String> params = new HashMap<String, String>();
	    	params.put("partnerId", partnerId);
	    
		return restTemplate.getForObject("http://localhost:8080/ad/{partnerId}", Object.class, params);	    
	}

	/**
	 * Method to invoke REST service for getting all active campaigns.
	 * URI: http://localhost:8080/ad/all
	 * @return
	 */
	public Object getAllCampaigns() {
		return restTemplate.getForObject("http://localhost:8080/ad/all", Object.class);
	}
	
	/**
	 * Method to invoke and test client operations and verbose Request and Response.
	 * This could be replaced with JUnits for efficient and continuous unit testing.
	 */
	public void testAdCampaigns() {
		Object response = null;
		ObjectMapper mapper = new ObjectMapper();
		String partnerId = "1";
		Campaign campaign = new Campaign(partnerId, "Ad for Campaign1", 10);
		/*Create a new campaign*/
		response = this.createCampaign(campaign);
	
		try {
	    		System.out.println("Create Campaign with Partner ID-"+campaign.getPartnerId()+" Request::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(campaign));
			System.out.println("Create Campaign with Partner ID-"+partnerId+": Response::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
	
			partnerId = "1";
			campaign = new Campaign(partnerId, "Ad for Campaign1", 20);
			/*Create a new campaign with same Partner Id- To validate error handling "Campaign already exists for this Partner"*/
			response = this.createCampaign(campaign);
	    		System.out.println("Create Campaign with Partner ID-"+campaign.getPartnerId()+" Request::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(campaign));
			System.out.println("Create Campaign with Partner ID-"+partnerId+": Response::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
			
			partnerId = "2";
			campaign = new Campaign(partnerId, "Ad for Campaign2", 20);
			/*Create a new campaign with different Partner Id*/
			response = this.createCampaign(campaign);
	    		System.out.println("Create Campaign with Partner ID-"+campaign.getPartnerId()+" Request::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(campaign));
			System.out.println("Create Campaign with Partner ID-"+partnerId+": Response::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

			/*Get all existing active campaigns*/
			response = this.getAllCampaigns();
			System.out.println("Get All Campaigns: Response::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

			/*Get active campaign for the given partner Id*/
			response = this.getCampaign(partnerId);
			System.out.println("Get Campaign for Partner ID-"+partnerId+": Response::"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
		} catch (Exception e) {
			System.out.println("Exception encountered while testing the Campaign");
		}
	}
}
