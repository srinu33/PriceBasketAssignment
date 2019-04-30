package com.assignment.pricebasket.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.pricebasket.PriceBasket;
import com.assignment.pricebasket.service.BasketService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PriceBasket.class)
public class BasketServiceTest {
	
	@Autowired
    private BasketService basketService;
	
	@Test
	public void testEmptyBasket() {
		String[] items =  null;
		
		StringBuffer output = basketService.getBasketTotals(items);
		
		assertEquals("Please enter input in below format \nPriceBasket item1 item2 item3 … \nFor example: PriceBasket Apples Milk Bread", output.toString());
		
	}
	
	@Test
	public void testInvalidBasket() {
		String[] items =  {"test", "invaliditem"};
		
		StringBuffer output = basketService.getBasketTotals(items);
		
		assertEquals("Please enter input in below format \nPriceBasket item1 item2 item3 … \nFor example: PriceBasket Apples Milk Bread", output.toString());
		
	}
	
	@Test
	public void testNoOffersBasket() {
		String[] items =  {"Milk", "Soup"};
		
		StringBuffer output = basketService.getBasketTotals(items);
		
		assertEquals("Subtotal: £1.95\n(no offers available)\nTotal: £1.95\n", output.toString());
		
	}
	
	@Test
	public void testSingleOffersBasket() {
		String[] items =  {"Apples", "Milk", "Bread",};
		StringBuffer output = basketService.getBasketTotals(items);
		
		assertEquals("Subtotal: £3.10\nApples 10% off: -10p\nTotal: £3.00\n", output.toString());
	}
	

	
	@Test
	public void testMultipleOffersBasket() {
		String[] items =  {"Bread", "Bread", "Apples", "Milk", "Soup", "Soup"};
		StringBuffer output = basketService.getBasketTotals(items);
		
		assertEquals("Subtotal: £5.20\nBread 50% off: -80p\nApples 10% off: -10p\nMilk 20% off: -26p\nSoup 10% off: -13p\nTotal: £3.91\n", output.toString());
	}
	
	@Test
	public void testMultipleOffersForSameItemBasket() {
		String[] items =  {"Bread", "Bread", "Bread", "Bread", "Soup"};
		StringBuffer output = basketService.getBasketTotals(items);
		//There 2 offers available for Soup, if you buy 2 breads 10%, 4 bread 20%. In this test we have 4 Breads, so applying 20% discount
		assertEquals("Subtotal: £3.85\nSoup 20% off: -13p\nTotal: £3.72\n", output.toString());
	}
	
}


