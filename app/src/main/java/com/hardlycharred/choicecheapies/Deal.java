/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hardlycharred.choicecheapies;

import java.io.Serializable;

/**
 *
 * @author Charlie Hard
 */
public class Deal implements Serializable {
    
    private String title;
    private String coupon;
    private String description;
    private String cheapiesURL;
    private String dealURL;
    private Boolean expired;

    public Deal(String title, String coupon, String description, String cheapiesURL, String dealURL, Boolean expired) {
        this.title = title;
        this.coupon = coupon;
        this.description = description;
        this.cheapiesURL = cheapiesURL;
        this.dealURL = dealURL;
        this.expired = expired;
    }

    public Deal() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheapiesURL() {
        return cheapiesURL;
    }

    public void setCheapiesURL(String cheapiesURL) {
        this.cheapiesURL = cheapiesURL;
    }

    public String getDealURL() {
        return dealURL;
    }

    public void setDealURL(String dealURL) {
        this.dealURL = dealURL;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }


    // Only uses the title so only title shows up in GUI
    @Override
    public String toString() {
        return title;
    }
    
    
    
}
