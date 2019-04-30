package com.assignment.pricebasket.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Offer {

	private String id;
	private BigDecimal discount;
	private Item item;
	private LocalDate expiryDate;
	private Map<Item, Integer> requiredItems;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Map<Item, Integer> getRequiredItems() {
		return requiredItems;
	}
	public void setRequiredItems(Map<Item, Integer> requiredItems) {
		this.requiredItems = requiredItems;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discount == null) ? 0 : discount.hashCode());
		result = prime * result
				+ ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((requiredItems == null) ? 0 : requiredItems.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (requiredItems == null) {
			if (other.requiredItems != null)
				return false;
		} else if (!requiredItems.equals(other.requiredItems))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", discount=" + discount + ", item=" + item
				+ ", expiryDate=" + expiryDate + ", requiredItems="
				+ requiredItems + "]";
	}
	
}
