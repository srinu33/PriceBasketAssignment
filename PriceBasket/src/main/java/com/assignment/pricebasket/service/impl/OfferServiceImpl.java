package com.assignment.pricebasket.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.pricebasket.model.Item;
import com.assignment.pricebasket.model.Offer;
import com.assignment.pricebasket.service.ItemService;
import com.assignment.pricebasket.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	Map<String, Offer> offers = new LinkedHashMap<String, Offer>();
	

	@Autowired
    private ItemService itemService;
	
	/**
	 * Preparing Test data here, instead we can use In-memory (eg:H2 DB) or configuration files
	 */
	@PostConstruct
    public void initOffers(){
		//Offer 1 - Apples have 10% off their normal price
		Offer apples10PercentOffer = new Offer();
		apples10PercentOffer.setItem(itemService.getItem("Apples"));
		apples10PercentOffer.setDiscount(new BigDecimal(0.10));
		apples10PercentOffer.setExpiryDate(LocalDate.of(2019, 05, 07));
		
		offers.put("Apples10PercentOffer", apples10PercentOffer);
		
		//Offer 2 - Milk have 50% off their normal price last week -- Expired Offer
		Offer milkExpiredOffer = new Offer();
		milkExpiredOffer.setItem(itemService.getItem("Milk"));
		milkExpiredOffer.setDiscount(new BigDecimal(0.50));
		milkExpiredOffer.setExpiryDate(LocalDate.of(2019, 04, 29));
		
		offers.put("MilkExpiredOffer", milkExpiredOffer);
		
		//Offer 2 - Buy 2 tins of soup and get a loaf of bread for half price
		Offer breadHalfPriceOffer = new Offer();
		breadHalfPriceOffer.setItem(itemService.getItem("Bread"));
		breadHalfPriceOffer.setDiscount(new BigDecimal(0.5));
		Map<Item, Integer> requiredItemsForBreadOffer = new HashMap<Item, Integer>();
		requiredItemsForBreadOffer.put(itemService.getItem("Soup"), 2);
		breadHalfPriceOffer.setRequiredItems(requiredItemsForBreadOffer);
		offers.put("BreadHalfPriceOffer", breadHalfPriceOffer);
		
		//Offer 4 - 20% discount on Milk, when you buy 2 Bread Loaf and 1 Soup
		Offer milkOffer = new Offer();
		milkOffer.setItem(itemService.getItem("Milk"));
		milkOffer.setDiscount(new BigDecimal(0.2));
		Map<Item, Integer> requiredItems3 = new HashMap<Item, Integer>();
		requiredItems3.put(itemService.getItem("Bread"), 2);
		requiredItems3.put(itemService.getItem("Soup"), 1);
		milkOffer.setRequiredItems(requiredItems3);
		
		offers.put("MilkOffer", milkOffer);
		
		//Offer 1 - 10% discount on Apples when you buy 2 Bread loaf
		Offer soup10PercentOffer = new Offer();
		soup10PercentOffer.setItem(itemService.getItem("Soup"));
		soup10PercentOffer.setDiscount(new BigDecimal(0.10));
		Map<Item, Integer> requiredItemsForSoup10Offer = new HashMap<Item, Integer>();
		requiredItemsForSoup10Offer.put(itemService.getItem("Bread"), 2);
		soup10PercentOffer.setRequiredItems(requiredItemsForSoup10Offer);
		
		offers.put("Soup10PercentOffer", soup10PercentOffer);
		
		//Offer 2 - 20% discount on Apples when you buy 4 Bread loaf
		Offer soup20PercentOffer = new Offer();
		soup20PercentOffer.setItem(itemService.getItem("Soup"));
		soup20PercentOffer.setDiscount(new BigDecimal(0.2));
		Map<Item, Integer> requiredItemsForSoup20Offer = new HashMap<Item, Integer>();
		requiredItemsForSoup20Offer.put(itemService.getItem("Bread"), 4);
		soup20PercentOffer.setRequiredItems(requiredItemsForSoup20Offer);
		
		offers.put("Soup20PercentOffer", soup20PercentOffer);
		
    }
	
	/**
	 * Method to return available offer for a given Item
	 */
	@Override
	public List<Offer> getOffers(Item item) {
		List<Offer> itemOffers = new LinkedList<Offer>();
		for (Offer offer : offers.values()) {
			if (item.equals(offer.getItem())) {
				itemOffers.add(offer);
			}
		}
		return itemOffers;
	}
	
	/**
	 * Method to check whether the offer contains any pre-condition or applicable straight away.
	 */
	@Override
	public boolean isOfferApplicable(Map<Item, Integer> itemsMap, Item item, Offer offer) {
		
		if(null != offer.getExpiryDate() && offer.getExpiryDate().isBefore(LocalDate.now())) {
			return false;
		}
		
		Map<Item, Integer> requiredItems = offer.getRequiredItems();
		
		if (requiredItems == null || requiredItems.isEmpty()) {
			//this offer doesn't have any pre-condition, applicable always
			return true;
		}
		
		for (Entry<Item, Integer> requiredItemEntry : requiredItems.entrySet()) {
			Item requiredItem = requiredItemEntry.getKey();
			Integer requiredQty = requiredItemEntry.getValue();
			Integer itemQty = itemsMap.get(requiredItem);
			if (itemQty != null && itemQty >= requiredQty) {
				//Your basket meat the pre-condition on this offer, so checking further conditions
				continue;
			} else {
				//Your basket doesn't meet the pre-condition on this offer, so not applicable
				return false;
			}
		}
		return true;
	}

}
