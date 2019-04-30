package com.assignment.pricebasket.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Basket {

	private Map<Item, Integer> items = new LinkedHashMap<Item, Integer>();

	public Map<Item, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Item, Integer> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		
		Integer count = items.get(item);
		if (count == null) {
			items.put(item, 1);
		} else {
			items.put(item, count + 1);
		}
	}

	@Override
	public String toString() {
		return "Basket [items=" + items + "]";
	}
	
	
}
