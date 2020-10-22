package io.github.marcoslimaqa.tests;

import org.junit.ClassRule;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.marcoslimaqa.util.TestRule;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@ui-desktop-test", glue = {""}, 
	monochrome = true, dryRun = false, plugin = { "json:target/cucumber.json", "rerun:target/rerun.txt" })
public class UIDesktopTest {

	@ClassRule
	public static TestRule testRule = new TestRule();
}