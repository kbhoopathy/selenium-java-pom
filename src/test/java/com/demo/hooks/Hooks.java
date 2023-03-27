package com.demo.hooks;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.demo.utilities.CommonUtils;
import com.demo.webdriver_manager.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class Hooks {

	private static final Logger LOGGER = LogManager.getLogger(Hooks.class);

	@Before()
	public void beforeScenario() {
		try {
			CommonUtils.getInstance().loadProperties();
			LOGGER.info("Loading properties from config.properties file");

			if (DriverManager.getDriver() == null) {
				LOGGER.info("Driver is Instantiated");
				DriverManager.launchBrowser();
				CommonUtils.getInstance().initWebElemets();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void deleteFiles() {
		File fileLocation = new File("downloads");
		for (File file : fileLocation.listFiles()) {
			LOGGER.info("FileName : " + file);
			file.delete();
		}
	}

	@After
	public void afterScenario() {
		DriverManager.getDriver().close();
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshot(byte[] screenShot) {
		return screenShot;
	}

	@After
	public void endStep(Scenario scenario) {
		if (scenario.isFailed()) {
			Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(
					((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
		}
	}

}
