package com.assignment.pricebasket.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.i18n.LocaleContextHolder;

import com.assignment.pricebasket.model.BasketTotals;
import com.assignment.pricebasket.model.Item;

/**
 * Utility class to convert the calculates values into required format
 * @author Srinivasa Kandula
 *
 */
public class DisplayUtil {
	
	private static final String TOTAL = "Total: ";
	private static final String SUBTOTAL = "Subtotal: ";
	private static final String PENS = "p";
	private static final String NO_OFFERS_AVAILABLE = "(no offers available)";
	private final static String OFFERS_MSG = "%s %s off: -%s";
	private final static String DECIMAL_FORMAT = "#.##";

	/**
	 * Method to prepare a formatted string for a given Basket Totals object.
	 * @param basketTotals
	 * @return
	 */
	public static StringBuffer displayDetails(BasketTotals basketTotals) {
		
		StringBuffer outputMessage = new StringBuffer();
		outputMessage.append(SUBTOTAL + getCurrencyStr(basketTotals.getSubTotal()) + "\n");
		outputMessage.append(getOffersStr(basketTotals.getOfferTotals()));
		outputMessage.append(TOTAL + getCurrencyStr(basketTotals.getTotal()) + "\n");
		
		return outputMessage;
	}
	
	/**
	 * Method to prepare offers String for given Offers 
	 * @param offerTotals
	 * @return
	 */
	protected static String getOffersStr(Map<Item, BigDecimal> offerTotals) {
		StringBuffer sb = new StringBuffer();
		if (offerTotals == null || offerTotals.isEmpty()) {
			sb.append(NO_OFFERS_AVAILABLE).append("\n");
		} else {
			for (Entry<Item, BigDecimal> itemEntry : offerTotals.entrySet()) {
				Item item = itemEntry.getKey();
				BigDecimal totalDiscount = itemEntry.getValue();
				String formattedTotalDiscount = getCurrencyStr(totalDiscount);
				String discountPercentage = new DecimalFormat(DECIMAL_FORMAT).format(item.getDiscountPercentage().multiply(new BigDecimal(100)).doubleValue())+ "%";
				sb.append(String.format(OFFERS_MSG, item.getName(), discountPercentage, formattedTotalDiscount)).append("\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * Method to return a String equivalent for a given BigDecimal value
	 * @param value
	 * @return
	 */
	protected static String getCurrencyStr(BigDecimal value) {
		//If the total value not less than a ONE then convert into Lower Currency
		if (BigDecimal.ONE.compareTo(value) <= 0) {
			return NumberFormat.getCurrencyInstance(LocaleContextHolder.getLocale()).format(value);
		} else {
			return value.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).toString() + PENS;
		}
	}
}
