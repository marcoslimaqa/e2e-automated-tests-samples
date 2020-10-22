package io.github.marcoslimaqa.pages.ui.web;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.marcoslimaqa.pages.BasePage;
import io.github.marcoslimaqa.util.TestRule;

public class ProductsPage extends BasePage {
	
	@FindBy(css = ".inventory_item")
	private List<WebElement> products; 
	
	@FindBy(xpath = "//button[text()='ADD TO CART']")
	private List<WebElement> addToCartButtons;
	
	@FindBy(id = "shopping_cart_container")
	private WebElement cartButton; 
	
	public ProductsPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	public boolean isProductsDisplayed() {
		int productsCount = products.size();
		if (productsCount > 1) {
			log("The products was presented correctly");
			return true;
		}
		return false;
	}

	public void addProductsToCart(int productsCount) {
		for (int i = 0; i < productsCount; i++) {
			waitElement(addToCartButtons.get(i), 15).click();
		}
		log("Added [" + productsCount + "] products to cart");
	}

	public void accessCart() {
		cartButton.click();
		log("Cart was accessed");
	}

}