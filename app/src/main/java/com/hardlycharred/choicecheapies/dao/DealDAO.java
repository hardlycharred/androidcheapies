/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hardlycharred.choicecheapies.dao;

import com.hardlycharred.choicecheapies.domain.Deal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charlie Hard
 */
public class DealDAO {

    private static List<Deal> allDeals = new ArrayList<Deal>(25);
    //    private static List<Deal> expiredDeals = new ArrayList<Deal>(25);
    private Integer currentPage = 0;

    public List<Deal> getCurrentDeals() {
        return allDeals;
    }

    public static void setCurrentDeals(List<Deal> currentDeals) {
        DealDAO.allDeals = currentDeals;
    }
//
//    public static List<Deal> getExpiredDeals() {
//        return allDeals;
//    }

//    public static void setExpiredDeals(List<Deal> expiredDeals) {
//        DealDAO.expiredDeals = expiredDeals;
//    }

//    public void addDeal(Deal d) {
//        if (d.getExpired()) {
//            expiredDeals.add(d);
//        } else {
//            currentDeals.add(d);
//        }
//    }

    public void addDeal(Deal d) {
        if (d.getExpired()) {
            d.setTitle("EXPIRED: " + d.getTitle());
        }
        allDeals.add(d);
    }

    public void removeDeal(int i) {
        allDeals.remove(i);
    }

    public String getCurrentPageURL() {
        if (currentPage == 0) {
            return "https://www.cheapies.nz/deals";
        } else {
            return "https://www.cheapies.nz/deals?page=" + currentPage;
        }
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void incrementCurrentPage() {
        if (currentPage < 49) {
            currentPage++;
        }
    }

    public void decrementCurrentPage() {
        if (currentPage > 0) {
            currentPage--;
        }
    }
}
