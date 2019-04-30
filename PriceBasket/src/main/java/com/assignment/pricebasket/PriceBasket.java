package com.assignment.pricebasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * This is main class for this PriceBasket application.
 * 
 * The program will accept a list of items in the basket and output the subtotal, 
 * the special offer discounts and the final price.
 * 
 *  For example: PriceBasket Apples Milk Bread
 *	
 *	Subtotal: £3.10
 *	Apples 10% off: -10p
 *  Total: £3.00
 * 
 * @author Srinivasa Kandula
 *
 */
@SpringBootApplication
public class PriceBasket {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(PriceBasket.class, args);
		
		ctx.close();
	}

}
