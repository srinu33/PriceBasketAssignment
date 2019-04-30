# PriceBasketAssignment

Description

This application takes items to be purchased as command line arguments, 
and calculate the SubTotal, Offers and Total after applying discounts.

The goods that can be purchased, which are all priced in GBP, are:

Soup – 65p per tin
Bread – 80p per loaf
Milk – £1.30 per bottle
Apples – £1.00 per bag

Current special offers:

Apples have a 10% discount off their normal price this week
Buy 2 tins of soup and get a loaf of bread for half price

The program should accept a list of items in the basket and output the subtotal, the special offer discounts and the final price. Input should be via the command line in the form PriceBasket item1 item2 item3 …

For example:

PriceBasket Apple Milk Bread 
Output should be to the console, for example:

Subtotal: £3.10 
Apples 10% off: -10p 
Total: £3.00 
If no special offers are applicable the code should output:

Subtotal: £1.30 
(No offers available) 
Total price: £1.30

How To Run the Project:

You must have Maven and Java 8 installed. Unzip the PriceBasket project and build the project using Maven:

mvn clean package

You can now run the application:

Open the Project in any IDE and Run the main class(SpringBoot app PriceBasket.java) by passing VM arguments.

Open Command Promp/Terminal and go to /target folder.
Run below command

target>java -jar PriceBasket-0.0.1-SNAPSHOT Apple Milk Bread

