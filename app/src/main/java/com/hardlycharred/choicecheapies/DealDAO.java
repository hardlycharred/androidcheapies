/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hardlycharred.choicecheapies;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charlie Hard
 */
public class DealDAO {

    private static List<Deal> currentDeals = new ArrayList<Deal>();
    private static List<Deal> expiredDeals = new ArrayList<Deal>();

    public List<Deal> getCurrentDeals() {
        return currentDeals;
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

}
