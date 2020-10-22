package io.github.marcoslimaqa.pages.ui.web;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.marcoslimaqa.pages.BasePage;
import io.github.marcoslimaqa.util.TestRule;

public class CartPage extends BasePage {
	
	@FindBy(css = ".cart_item")
	private List<WebElement> cartItem; 
	
	@FindBy(css = ".checkout_button")
	private WebElement checkoutButton; 
	
	public CartPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	public boolean containsCartExpectedProductsCount(int expectedProductsCount) {
		if (expectedProductsCount == cartItem.size()) {
			log(expectedProductsCount + " products were added to the cart as expected.");
			return true;
		}
		return false;
	}

	public void accessCheckout() {
		checkoutButton.click();
		log("Checkout was accessed.");
	}

}