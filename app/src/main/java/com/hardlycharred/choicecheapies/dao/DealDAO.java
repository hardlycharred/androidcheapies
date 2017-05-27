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

    private static List<Deal> currentDeals = new ArrayList<Deal>();
    private static List<Deal> expiredDeals = new ArrayList<Deal>();
    private Integer currentPage = 0;

    public List<Deal> getCurrentDeals() {
        return currentDeals;
    }

    public static void setCurrentDeals(List<Deal> currentDeals) {
        DealDAO.currentDeals = currentDeals;
    }

    public void addDeal(Deal d) {
        if (d.getExpired()) {
            expiredDeals.add(d);
        } else {
            currentDeals.add(d);

        }
    }

    public void removeDeal(int i) {
        currentDeals.remove(i);
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
