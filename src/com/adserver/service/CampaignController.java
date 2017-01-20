package com.adserver.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Harisha Akasapu
 * Spring Controller to expose REST services for the Ad Campaign.
 */
@RestController
public class CampaignController {
	/**
	 * REST service to create Ad campaign through Business Manager.
	 * @param campaign
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/ad", method = RequestMethod.POST)
    public Object createCampaign(@RequestBody Campaign campaign) throws Exception {
    	if(campaign.getPartnerId() !=null && (CampaignManager.getCampaign(campaign.getPartnerId()) != null)){
    		throw new Exception("Campaign already exists for this Partner");	
    	} else{
    		CampaignManager.addCampaign(campaign);
    	}
    	return new ResponseDomain(HttpStatus.OK.value(), "Success", "Campaign added successfully");
    }
    
    /**
     * REST service to get campaign by Partner Id through Business Manager.
     * @param partnerId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ad/{partnerId}", method = RequestMethod.GET)
    public Object getCampaign(@PathVariable(value = "partnerId") String partnerId) throws Exception {    	
    	return CampaignManager.getCampaign(partnerId);
    }
    
    /**
     * REST service to get all active campaigns through Business Manager.
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ad/all", method = RequestMethod.GET)
    public Object getAllCampaigns() throws Exception{    	
    	return CampaignManager.getAllActiveCampaigns();
    }
}