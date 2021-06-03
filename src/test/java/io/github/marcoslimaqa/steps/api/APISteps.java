package io.github.marcoslimaqa.steps.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.WriterOutputStream;
import org.hamcrest.Matchers;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.marcoslimaqa.pages.BasePage;
import io.github.marcoslimaqa.util.Utils;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APISteps extends BasePage {

	RequestSpecification request;
	Response response;
	String SESSIONID;
	StringWriter writer;
	public static Map<String,String> extractedValues = new HashMap<String,String>();

	private RequestSpecification givenBaseUri(String url) {
		request = given().baseUri(url);
		writer = new StringWriter();
		@SuppressWarnings("deprecation")
		PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);
		request.config(RestAssured.config().logConfig(new LogConfig(captor, true)));
		return request;
	}
	
	@Given("^url \"(.*)\"$")
	public void url(String url) {
		request = givenBaseUri(url);
	}

	@Given("^url in env (.*)$")
	public void urlByEnv(String env) {
		request = givenBaseUri(System.getProperty(env, "localhost"));
	}

	@Given("^url in propertie (.*)$")
	public void urlByProp(String prop) {
		request = givenBaseUri(Utils.getTestProperty(prop));
	}

	@And("^path \"(.*)\"$")
	public void path(String path) {
		if (path.contains("{")) {
			String pathParameter = Utils.substringRegexGroup1("\\{(.*)\\}", path);
			if (extractedValues.containsKey(pathParameter)) {
				path = path.replaceAll("\\{.*\\}", extractedValues.get(pathParameter));
			}
		}
		request = request.basePath(path);
	}

	@And("^header ([^\\s]+) = (.+)")
	public void headerParam(String field, String param) {
		if (param.contains("{")) {
			String extractedParam = Utils.substringRegexGroup1("\\{(.*)\\}", param);
			if (extractedValues.containsKey(extractedParam)) {
				param = param.replaceAll("\\{.*\\}", extractedValues.get(extractedParam));
			}
		}
		request = request.header(field, param);
	}

	@And("^query ([^\\s]+) = (.+)$")
	public void queryParam(String field, String param) {
		request = request.queryParam(field, param.replaceAll("\"", "").replaceAll(" ", "%20")).urlEncodingEnabled(false);
	}

	@And("^form \"(.*)\" value \"(.*)\"$")
	public void formParam(String field, String param) {
		request = request.formParam(field, param);
	}

	@And("^body$")
	public void body(String body) {
		request = request.body(body);
	}

	private RequestSpecification when() {
		return request.when().log().all(true);
	}

	@When("^method POST$")
	public void post() {
		response = when().post();
		response.then().log().all(true);
		logInfo("<pre>" + writer.toString() + "</pre>");
		System.out.println(writer.toString());
	}

	@When("^method GET$")
	public void get() {
		response = when().get();
		response.then().log().all(true);
		logInfo("<pre>" + writer.toString() + "</pre>");
		System.out.println(writer.toString());
	}
	
	@When("^method PUT$")
	public void put() {
		response = when().put();
		response.then().log().all(true);
		logInfo("<pre>" + writer.toString() + "</pre>");
		System.out.println(writer.toString());
	}
	
	@When("^method PATCH$")
	public void patch() {
		response = when().patch();
		response.then().log().all(true);
		logInfo("<pre>" + writer.toString() + "</pre>");
		System.out.println(writer.toString());
	}
	
	@When("^method DELETE$")
	public void delete() {
		response = when().delete();
		response.then().log().all(true);
		logInfo("<pre>" + writer.toString() + "</pre>");
		System.out.println(writer.toString());
	}
	
	private ValidatableResponse then() {
		ValidatableResponse validatableResponse = response.then();
		return validatableResponse;
	}
	
	@Then("^extract \"(.*)\"$")
	public void extract(String fieldPath) {
		String value = then().extract().body().jsonPath().getString(fieldPath).replace("[", "").replace("]", "");
		extractedValues.put(fieldPath, value);
	}

	@Then("^status (\\d+)$")
	public void status(int status) {
		then().assertThat().statusCode(status);
	}

	@Then("^match response \"(.*)\" = \"(.*)\"$")
	public void matchResponse(String fieldPath, String param) {
		then().assertThat().body(fieldPath, Matchers.equalTo(param));
	}
	
	@Then("^match response \"(.*)\" = (null)$")
	public void matchResponseNull(String fieldPath, String param) {
		then().assertThat().body(fieldPath, Matchers.nullValue());
	}
	
	@Then("match response \"(.*)\" = (^\\d+$)")
	public void matchResponseInt(String fieldPath, int param) {
		then().assertThat().body(fieldPath, Matchers.equalTo(param));
	}
	
	@Then("^match response \"(.*)\" = ([+-]?\\d*\\.?\\d+)$")
	public void matchResponseDouble(String fieldPath, String param) {
		String result = then().extract().body().jsonPath().getString(fieldPath);
		Assert.assertTrue("Expected: " + param + " Actual: " + result, result.equals(param));
	}
	
    @Then("^match response contains \"(.*)\"$")
    public void matchResponseContains(String param) {
    	then().assertThat().body(Matchers.containsString(param));
    }

	@Then("^match response \"(.*)\" contains item \"(.*)\"$")
	public void matchResponseContainsItem(String fieldPath, String param) {
		then().assertThat().body(fieldPath, hasItem(param));
	}

	@Then("^match response \"(.*)\" contains only item \"(.*)\"$")
	public void matchResponseContainsOnlyItem(String fieldPath, String param) {
		then().assertThat().body(fieldPath, everyItem(equalTo(param)));
	}

	@Then("^match response \"(.*)\" contains \"(.*)\"$")
	public void matchResponseContains(String fieldPath, String param) {
		then().assertThat().body(fieldPath, Matchers.containsString(param));
	}

	@Then("^match header \"(.*)\" = \"(.*)\"$")
	public void matchHeader(String fieldPath, String param) {
		then().assertThat().header(fieldPath, Matchers.equalTo(param));
	}

	@Then("^match response to schema \"(.*)\"$")
	public void responseContains(String schema) {
		then().assertThat().body(matchesJsonSchemaInClasspath("schema/" + schema));
	}
	
}