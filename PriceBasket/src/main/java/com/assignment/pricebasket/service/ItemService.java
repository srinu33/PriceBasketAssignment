package com.assignment.pricebasket.service;

import com.assignment.pricebasket.model.Item;

public interface ItemService {
	
	/**
	 * This method will return an Item for the given item name
	 * 
	 * @param name
	 * @return
	 */
	Item getItem(String name);

}
