package com.assignment.pricebasket.service;

import java.util.List;
import java.util.Map;

import com.assignment.pricebasket.model.Item;
import com.assignment.pricebasket.model.Offer;

public interface OfferService {
	
	/**
	 * Method to return List of offers for an Item
	 * 
	 * @param item
	 * @return
	 */
	List<Offer> getOffers(Item item);
	
	/**
	 * Method to check whether the offer contains any pre-condition or applicable straight away.
	 *
	 * @param itemsMap
	 * @param item
	 * @param offer
	 * @return
	 */
	boolean isOfferApplicable(Map<Item, Integer> itemsMap, Item item, Offer offer);
}
