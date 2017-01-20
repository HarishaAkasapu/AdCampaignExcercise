package com.adserver.service;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Harisha Akasapu
 * POJO for Campaign.
 */
public class Campaign {
    private String partnerId;
    private String adContent;
    private int duration;
    private Timestamp createdTs;
 
    public Campaign(){}
    
    public Campaign(String partnerId, String adContent, int duration){
    	this.partnerId = partnerId;
    	this.adContent = adContent;
    	this.duration = duration;
    }
    @JsonProperty("partner_id")
    public String getPartnerId() {
        return partnerId;
    }
 
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    @JsonProperty("ad_content") 
    public String getAdContent() {
        return adContent;
    }
 
    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }
 
    public int getDuration() {
        return duration;
    }
 
    public void setDuration(int duration) {
        this.duration = duration;
    }
    @JsonIgnore
    public Timestamp getCreatedTs() {
        return createdTs;
    }
 
    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }
}
