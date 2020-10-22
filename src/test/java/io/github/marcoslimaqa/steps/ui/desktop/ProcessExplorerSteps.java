package io.github.marcoslimaqa.steps.ui.desktop;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.marcoslimaqa.pages.ui.desktop.ProcessExplorerPage;
import io.github.marcoslimaqa.util.TestRule;

public class ProcessExplorerSteps {

	@Given("^that I have the Process Explorer open$")
	public void openProcessExplorerApp() {
		TestRule.openApplicationSikuli(System.getProperty("user.dir") + "/app/ProcessExplorer/procexp.exe");
		ProcessExplorerPage processExplorerPage = new ProcessExplorerPage();
		processExplorerPage.waitApplication();
	}
	
	@Then("^a \"(.*)\" must be displayed$")
	public void isImageDisplayed(String image) {
		ProcessExplorerPage processExplorerPage = new ProcessExplorerPage();
		Assert.assertTrue("The image [" + image + "] should have appeared", 
				processExplorerPage.assertImageExists(image));
	}
	
	@And("^I run \"(.*)\" as administrator$")
	public void runAsAdministrator(String program) {
		ProcessExplorerPage processExplorerPage = new ProcessExplorerPage();
		processExplorerPage.runAsAdministrator(program);
	}
	
	@Then("^the \"(.*)\" program must be presented on the path \"(.*)\"$")
	public void isProgramPresentOnPath(String program, String path) {
		ProcessExplorerPage processExplorerPage = new ProcessExplorerPage();
		Assert.assertTrue("The program [" + program + "] should have appeared on the path " + path, 
				processExplorerPage.isProgramPresentOnPath(program, path));
	}
	
	@And("^I kill the process$")
	public void killProcess() {
		ProcessExplorerPage processExplorerPage = new ProcessExplorerPage();
		processExplorerPage.killProcess();
	}
	
}
