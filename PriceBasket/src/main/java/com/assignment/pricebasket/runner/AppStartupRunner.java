package com.assignment.pricebasket.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.assignment.pricebasket.service.BasketService;

@Component
public class AppStartupRunner implements CommandLineRunner {
    
	@Autowired
    private BasketService basketService;
	
    @Override
    public void run(String...args) throws Exception {
    	//Display the information message in case there are no items added to the basket
    	if (args == null || args.length == 0) {
			System.out.println("Please enter input in below format \nPriceBasket item1 item2 item3 … \nFor example: PriceBasket Apples Milk Bread");
			return;
    	} 
    	
    	System.out.println(basketService.getBasketTotals(args));
    }
    
   
}