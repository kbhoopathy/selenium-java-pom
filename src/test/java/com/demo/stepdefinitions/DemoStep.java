package com.demo.stepdefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.demo.constants.Constants;
import com.demo.pages.DemoPage;
import com.demo.webdriver_manager.DriverManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;

public class DemoStep {

	WebDriver driver = DriverManager.getDriver();
	private static final Logger LOGGER = LogManager.getLogger(DemoStep.class);

	@Given("I open demo URL")
	public void i_open_demo_url() {
		try {
			driver.get(Constants.appUrl);
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	@When("I click on radio button")
	public void i_click_on_radio_button() {
		DemoPage.getInstance().getRadioOne();
	}

	@When("I select country from the auto complete option")
	public void i_select_country_from_the_auto_complete_option() {
		DemoPage.getInstance().selectCountry("India");
	}

	@When("I select an option from dropdown")
	public void i_select_an_option_from_dropdown() throws InterruptedException {
		DemoPage.getInstance().selectValueAtDropDown("option2");
	}

}
