package io.github.marcoslimaqa.steps.ui.web;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.marcoslimaqa.pages.ui.web.CheckoutPage;

public class CheckoutSteps {
	
	@And("^I add the checkout information name \"(.*)\" last name \"(.*)\" zip code \"(.*)\"$")
	public void addCheckoutInformation(String name, String lastName, String zipCode) {
		CheckoutPage checkoutPage = new CheckoutPage();
		checkoutPage.addCheckoutInformation(name, lastName, zipCode);
	}
	
	@When("^I complete the purchase$")
	public void completePurchase() {
		CheckoutPage checkoutPage = new CheckoutPage();
		checkoutPage.continueCheckout();
		checkoutPage.finishCheckout();
	}
	
	@Then("^it should display the finish message \"(.*)\"$")
	public void isMessageDisplayed(String expectedMessage) {
		CheckoutPage checkoutPage = new CheckoutPage();
		Assert.assertTrue("The message should have appeared.", 
				checkoutPage.isMessageDisplayed(expectedMessage));
	}
	
}
