package io.github.marcoslimaqa.steps.ui.web;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.marcoslimaqa.pages.ui.web.ProductsPage;

public class ProductsSteps {
	
	@Then("^I should be able to view the products$")
	public void isProductsDisplayed() {
		ProductsPage productsPage = new ProductsPage();
		Assert.assertTrue("The products should have appeared.", 
				productsPage.isProductsDisplayed());
	}
	
	@And("^add (.*) products to the cart$")
	public void addProductsToCart(int productsCount) {
		ProductsPage productsPage = new ProductsPage();
		productsPage.addProductsToCart(productsCount);
	}
	
	@When("^access the cart$")
	public void accessCart() {
		ProductsPage productsPage = new ProductsPage();
		productsPage.accessCart();
	}
	
}
