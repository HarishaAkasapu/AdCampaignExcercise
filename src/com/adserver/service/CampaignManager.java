package com.adserver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Harisha Akasapu
 * Business Manager to store, process campaign data in memory.
 * In this approach data is non-persistent and for the application to be fault tolerant it should further rely on persistent storage mechanism.
 */
@Component
public class CampaignManager {
    private static List<Campaign> campaigns = new ArrayList<>();
	 	     
    /**
    * Business method to add new campaign to the existing collection.
    * @param currentCampaign
    */
    public static void addCampaign(Campaign currentCampaign) {
    	if(currentCampaign != null){
    		currentCampaign.setCreatedTs(new Timestamp(System.currentTimeMillis()));
    		campaigns.add(currentCampaign);
    	}
    }

    /**
     * Business method to get an existing campaign by partner Id from the existing collection.
     * @param partnerId
     * @return Campaign
     * @throws Exception
     */
    public static Campaign getCampaign(String partnerId) throws Exception{
    	Campaign returnCampaign = null;
    	if(partnerId != null && !partnerId.isEmpty()){
	    	for (Iterator<Campaign> iterator = campaigns.iterator(); iterator.hasNext();) {
	    		Campaign campaign = iterator.next();

	    		/*Validate partner Id already exists*/
	    		if(campaign.getPartnerId().equals(partnerId)){
	    			if(isCampaignActive(campaign)){
		    			returnCampaign = campaign;	    				
	    			} else{
	    				iterator.remove();
	    				throw new Exception("No active Campaign exists for this Partner."+partnerId);
	    			}
	    		}
	        }
    	}
    	return returnCampaign;
    }
    
    /**
     * Business method to get all existing active campaigns from the existing collection.
     * @return List<Campaign>
     */
    public static List<Campaign> getAllActiveCampaigns() {
    	for (Iterator<Campaign> iterator = campaigns.iterator(); iterator.hasNext();) {
    		Campaign campaign = iterator.next();
   
    		if(!isCampaignActive(campaign)){
				iterator.remove();
			}
    	}
    	return campaigns;
    }
    
    /**
     * Utility method to validate if campaign is active.
     * @param campaign
     * @return boolean
     */
    private static boolean isCampaignActive(Campaign campaign) {
    	boolean activeFlag = false;
    	Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(campaign.getCreatedTs().getTime());

        // add duration
        cal.add(Calendar.SECOND, campaign.getDuration());
       
    	if(Calendar.getInstance().before(cal)){
    		activeFlag = true;
    	}
    	return activeFlag;
    }
}
