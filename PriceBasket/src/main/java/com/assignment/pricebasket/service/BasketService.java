package com.assignment.pricebasket.service;


public interface BasketService {
	
	/**
	 * Method to calculate the SubTotal, Total and offers if applicable for a given Basket
	 * @param basket
	 * @return BasketTotals
	 */
	StringBuffer getBasketTotals(String...items);
}
