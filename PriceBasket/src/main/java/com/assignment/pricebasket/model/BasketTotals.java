package com.assignment.pricebasket.model;

import java.math.BigDecimal;
import java.util.Map;

public class BasketTotals {

	private BigDecimal subTotal;

	private Map<Item, BigDecimal> offerTotals;

	private BigDecimal total;

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Map<Item, BigDecimal> getOfferTotals() {
		return offerTotals;
	}

	public void setOfferTotals(Map<Item, BigDecimal> offerTotals) {
		this.offerTotals = offerTotals;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "BasketTotals [subTotal=" + subTotal + ", offerTotals="
				+ offerTotals + ", total=" + total + "]";
	}
	
	
	
}
