package io.github.marcoslimaqa.steps.ui.web;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.marcoslimaqa.pages.ui.web.LoginPage;
import io.github.marcoslimaqa.util.TestRule;
import io.github.marcoslimaqa.util.Utils;

public class LoginSteps {

	@Given("^that I accessed the site$")
	public void accessSite() {
		TestRule.openApplicationChrome(Utils.getTestProperty("test.ui"));
	}
	
	@Given("^that I accessed the site using a smartphone$")
	public void accessSiteSmartphone() {
		TestRule.openApplicationChromeMobile(Utils.getTestProperty("test.ui"));
	}
	
	@When("^logged in with user \"(.*)\" and password \"(.*)\"$")
	public void login(String user, String pass) {
		LoginPage loginPage = new LoginPage();
		loginPage.doLogin(user, pass);
	}
	
	@Then("^it should display the message \"(.*)\"$")
	public void isMessageDisplayed(String message) {
		LoginPage loginPage = new LoginPage();
		Assert.assertTrue("The message should have appeared.", 
				loginPage.isMessageDisplayed(message));
	}
	
	@Given("^that I am logged on the site$")
	public void loginSuccessfuly() {
		accessSite();
		login("standard_user","secret_sauce");
	}
	
	@Given("^that I am logged on the site using a smartphone$")
	public void loginSuccessfulySmartphone() {
		accessSiteSmartphone();
		login("standard_user","secret_sauce");
	}
	
}
