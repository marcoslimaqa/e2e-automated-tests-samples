package io.github.marcoslimaqa.pages.ui.desktop;

import org.sikuli.script.Key;

import io.github.marcoslimaqa.pages.BasePage;
import io.github.marcoslimaqa.sikulifactory.FindBy;
import io.github.marcoslimaqa.sikulifactory.SikuliElement;
import io.github.marcoslimaqa.sikulifactory.SikuliFactory;
import io.github.marcoslimaqa.util.TestRule;

public class ProcessExplorerPage extends BasePage {

	@FindBy(image="save-button.png")
	protected SikuliElement saveButton;
	
	public ProcessExplorerPage() {
		SikuliFactory.initElements(TestRule.getSikuli(), this);
	}

	public boolean assertImageExists(String image) {
		return assertImageExists(image, 70);
	}

	public void runAsAdministrator(String program) {
		sikuli.type(Key.ALT + "f" + "r");
		log("Run as administrator screen");
		wait(1);
		sikuli.type(program);
		sikuli.type(Key.ENTER);
		wait(2);
		log("Program [" + program + "] was runned as administrator");
	}

	public boolean isProgramPresentOnPath(String expectedProgram, String path) {
		posicionarMouseCentroTela().click();
		String[] subprogram = path.split(" > ");
		for (String program : subprogram) {
			sikuli.type(program);
			wait(1);
		}
		String programDisplayed = getText();
		if (programDisplayed.contains(expectedProgram)) {
			return true;
		}
		return false;
	}

	public void killProcess() {
		sikuli.type(Key.DELETE);
		log("The delete key was pressed");
		sikuli.type(Key.ENTER);
		log("the process was killed");
	}

	public void waitApplication() {
		waitWindow("Process Explorer", 15);
		saveButton.wait(15);
	}

}