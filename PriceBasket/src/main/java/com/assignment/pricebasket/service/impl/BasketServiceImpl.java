package com.assignment.pricebasket.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.pricebasket.model.Basket;
import com.assignment.pricebasket.model.BasketTotals;
import com.assignment.pricebasket.model.Item;
import com.assignment.pricebasket.model.Offer;
import com.assignment.pricebasket.service.BasketService;
import com.assignment.pricebasket.service.ItemService;
import com.assignment.pricebasket.service.OfferService;
import com.assignment.pricebasket.util.DisplayUtil;

@Service
public class BasketServiceImpl implements BasketService {
	
	@Autowired
    private ItemService itemService;
	
	@Autowired
	OfferService offerService;

	@Override
	public StringBuffer getBasketTotals(String...items) {
		
		Basket basket = prepareBasket(items);
		
		//Display the information message in case there are no Valid items added to the basket
		if(null == basket || null == basket.getItems() || basket.getItems().isEmpty()) {
			return new StringBuffer("Please enter input in below format \nPriceBasket item1 item2 item3 … \nFor example: PriceBasket Apples Milk Bread");
		}
		
		BasketTotals totals = new BasketTotals();
		
		BigDecimal subTotal = calculateBasketSubTotal(basket);
		Map<Item, BigDecimal> offerTotals = calculateBasketOffers(basket);
		BigDecimal total = calculateBasketTotal(subTotal, offerTotals);

		totals.setSubTotal(subTotal);
		totals.setOfferTotals(offerTotals);
		totals.setTotal(total);
		
		return DisplayUtil.displayDetails(totals);
	}
	
	/**
	 * Prepare Basket with the given item values
	 * @param items
	 * @return
	 */
	private Basket prepareBasket(String...items) {
	    if(null == items) {
	    	return null;
	    }
		
	    Basket basket = new Basket();
		Arrays.stream(items).forEach(itemName -> {
			Item item = itemService.getItem(itemName);
			if (item != null) {
				basket.addItem(item);
			}
		});

		return basket;
		
	}
	
	/**
	 * Method to calculate total price of Basket before applying any Offers
	 * @param basket
	 * @return
	 */
	private BigDecimal calculateBasketSubTotal(Basket basket) {
		BigDecimal subtotal = BigDecimal.ZERO;
		Map<Item, Integer> itesmMap = basket.getItems();
		subtotal = itesmMap.keySet().stream()
					.map(item -> item.getPrice().multiply(new BigDecimal(itesmMap.get(item))))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return subtotal;
	}
	
	/**
	 * Method will prepare a Map with all the applicable offer for the items in a Basket.
	 * 
	 * One Item is having multiple offers, will apply an offer with maximum discount
	 * 
	 * @param basket
	 * @return
	 */
	
	private Map<Item, BigDecimal> calculateBasketOffers(Basket basket) {
		Map<Item, BigDecimal> offerTotals = new LinkedHashMap<Item, BigDecimal>();
		
		Map<Item, Integer> itesmMap = basket.getItems();
		
		for (Item item : itesmMap.keySet()) {
			
			List<Offer> offers = offerService.getOffers(item);
			
			for (Offer offer : offers) {
				if (offerService.isOfferApplicable(itesmMap, item, offer)) {
					BigDecimal offerDiscount = itemTotalDiscount(itesmMap, item, offer);
					BigDecimal prevOfferDiscount = offerTotals.get(item);
					if (prevOfferDiscount == null) {
						item.setDiscountPercentage(offer.getDiscount());
						offerTotals.put(item, offerDiscount);
					} else {
						if(prevOfferDiscount.compareTo(offerDiscount) == -1) {
							item.setDiscountPercentage(offer.getDiscount());
							offerTotals.put(item, offerDiscount);
						}
					}
				}
			}
		}
		
		return offerTotals;
		
	}
	
	/**
	 * Method to calculate Total price of the basket after applying offers.
	 * @param subTotal
	 * @param offerTotals
	 * @return
	 */
	private BigDecimal calculateBasketTotal(BigDecimal subTotal, Map<Item, BigDecimal> offerTotals) {
		BigDecimal discounts = BigDecimal.ZERO;
		for (BigDecimal total : offerTotals.values()) {
			discounts = discounts.add(total);
		}
		
		return subTotal.subtract(discounts);
	}
	
	/**
	 * Method to calculate total discounted value
	 * @param itesmMap
	 * @param item
	 * @param offer
	 * @return
	 */
	private BigDecimal itemTotalDiscount(Map<Item, Integer> itesmMap,
			Item item, Offer offer) {
		return item.getPrice().multiply(offer.getDiscount()).multiply(new BigDecimal(itesmMap.get(item)));
	}
	
}
