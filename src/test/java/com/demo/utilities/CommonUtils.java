package com.demo.utilities;

import java.time.Duration;
import java.util.Properties;

import com.demo.pages.DemoPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.demo.constants.Constants;
import com.demo.webdriver_manager.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {

	private static CommonUtils CommonUtilsInstance;

	private CommonUtils() {
	}

	public static CommonUtils getInstance() {
		if (CommonUtilsInstance == null) {
			CommonUtilsInstance = new CommonUtils();
		}
		return CommonUtilsInstance;
	}

	// Load data from config.properties file
	public void loadProperties() {

		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream("/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Constants.browser = properties.getProperty("browser");
		Constants.appUrl = properties.getProperty("appUrl");
	}

	public void initWebElemets() {
		PageFactory.initElements(DriverManager.getDriver(), DemoPage.getInstance());

	}
}
