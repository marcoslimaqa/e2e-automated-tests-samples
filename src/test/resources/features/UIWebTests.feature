@ui-web-test
Feature: Ecommerce shopping - saucedemo.com

  Scenario: Try to log in with a locked user
		Given that I accessed the site
		When logged in with user "locked_out_user" and password "secret_sauce"
		Then it should display the message "Sorry, this user has been locked out."

  Scenario: Login as a standard user
		Given that I accessed the site
		When logged in with user "standard_user" and password "secret_sauce"
		Then I should be able to view the products

  Scenario Outline: Add products to cart
		Given that I am logged on the site
		And add <product count> products to the cart
		When access the cart
		Then the cart must contain <product count> products
		
	Examples:
		| product count |
		|				2				|
		|				3				|
  
  Scenario: Buy a product
		Given that I am logged on the site
		And add 1 products to the cart
		And access the cart
		And access the checkout
		And I add the checkout information name "joao" last name "silva" zip code "99887766"
		When I complete the purchase
		Then it should display the finish message "THANK YOU FOR YOUR ORDER"
		
  Scenario: Buy a product using a smartphone
		Given that I am logged on the site using a smartphone
		And add 1 products to the cart
		And access the cart
		And access the checkout
		And I add the checkout information name "joao" last name "silva" zip code "99887766"
		When I complete the purchase
		Then it should display the finish message "THANK YOU FOR YOUR ORDER"