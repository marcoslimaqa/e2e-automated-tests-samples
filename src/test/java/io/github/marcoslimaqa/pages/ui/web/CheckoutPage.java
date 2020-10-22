package io.github.marcoslimaqa.pages.ui.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.marcoslimaqa.pages.BasePage;
import io.github.marcoslimaqa.util.TestRule;

public class CheckoutPage extends BasePage {
	
	@FindBy(id = "first-name")
	private WebElement firstNameText; 
	
	@FindBy(id = "last-name")
	private WebElement lastNameText; 
	
	@FindBy(id = "postal-code")
	private WebElement postalCodeText; 
	
	@FindBy(xpath = "//input[@value='CONTINUE']")
	private WebElement continueButton; 
	
	@FindBy(xpath = "//a[text()='FINISH']")
	private WebElement finishButton; 
	
	@FindBy(css = ".complete-header")
	private WebElement completeHeader; 
	
	public CheckoutPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	public void addCheckoutInformation(String name, String lastName, String zipCode) {
		firstNameText.sendKeys(name);
		lastNameText.sendKeys(lastName);
		postalCodeText.sendKeys(zipCode);
		log("Added checkout information");
	}

	public void continueCheckout() {
		continueButton.click();
		log("Checkout continued");
	}

	public void finishCheckout() {
		finishButton.click();
		log("Checkout finished");
	}

	public boolean isMessageDisplayed(String expectedMessage) {
		if (expectedMessage.equals(completeHeader.getText())) {
			log("The message [" + expectedMessage + "] was displayed as expected.");
			return true;
		}
		return false;
	}

}