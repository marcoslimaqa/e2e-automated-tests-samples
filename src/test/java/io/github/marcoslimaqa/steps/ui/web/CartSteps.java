package io.github.marcoslimaqa.steps.ui.web;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.github.marcoslimaqa.pages.ui.web.CartPage;

public class CartSteps {
	
	@Then("^the cart must contain (.*) products$")
	public void containsCartExpectedProductsCount(int expectedProductsCount) {
		CartPage cartPage = new CartPage();
		Assert.assertTrue("The cart must have [" + expectedProductsCount + "] products.", 
				cartPage.containsCartExpectedProductsCount(expectedProductsCount));
	}
	
	@And("^access the checkout$")
	public void accessCheckout() {
		CartPage cartPage = new CartPage();
		cartPage.accessCheckout();
	}
	
}
