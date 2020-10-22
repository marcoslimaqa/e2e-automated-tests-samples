package io.github.marcoslimaqa.pages.ui.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.marcoslimaqa.pages.BasePage;
import io.github.marcoslimaqa.util.TestRule;

public class LoginPage extends BasePage {

	@FindBy(id = "user-name")
	protected WebElement username;
	
	@FindBy(id = "password")
	protected WebElement password;
	
	@FindBy(id = "login-button")
	protected WebElement loginButton;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	protected WebElement errorMessage;
	
	public LoginPage() {
		PageFactory.initElements(TestRule.getDriver(), this);
	}

	public void doLogin(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
		log("Login");
	}

	public boolean isMessageDisplayed(String message) {
		String displayedMessage = waitElement(errorMessage, 10).getText();
		if (displayedMessage.contains(message)) {
			log("The message [" + message + "] was presented correctly");
			return true;
		}
		return false;
	}

}