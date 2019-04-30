package com.assignment.pricebasket.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.assignment.pricebasket.model.Item;
import com.assignment.pricebasket.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	Map<String, Item> items = new HashMap<String, Item>();
	
	/**
	 * Preparing Test data here, instead we can use In-memory (eg:H2 DB) or configuration files
	 */
	@PostConstruct
    public void initItems(){
		items.put("apples", new Item("Apples", new BigDecimal(1.00)));
		items.put("bread", new Item("Bread", new BigDecimal(0.80)));
		items.put("soup", new Item("Soup", new BigDecimal(0.65)));
		items.put("milk", new Item("Milk", new BigDecimal(1.30)));
		
    }

	@Override
	public Item getItem(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Item Name should not be null.");
		}
		return items.get(name.toLowerCase());
	}

}
