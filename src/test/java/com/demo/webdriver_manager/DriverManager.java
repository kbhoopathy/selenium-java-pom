package com.demo.webdriver_manager;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.demo.constants.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	private static WebDriver driver = null;
	private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

	public static WebDriver getDriver() {
		return driver;
	}

	public static void launchBrowser() {
		try {
			switch (Constants.browser) {
			case "chrome":
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "downloads");

				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--ignore-ssl-errors=yes");
				options.addArguments("--ignore-certificate-errors");

				WebDriverManager.chromedriver().setup();
				LOGGER.info("Launching " + Constants.browser + " browser");
				driver = new ChromeDriver(options);
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				LOGGER.info("Launching " + Constants.browser + " browser");
				driver = new FirefoxDriver();
				break;

			case "ie":
				WebDriverManager.iedriver().setup();
				LOGGER.info("Launching " + Constants.browser + " browser");
				driver = new InternetExplorerDriver();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				LOGGER.info("Launching " + Constants.browser + " browser");
				driver = new EdgeDriver();
				break;

			default:
				WebDriverManager.chromedriver().setup();
				LOGGER.info("Launching " + Constants.browser + " browser");
				driver = new ChromeDriver();
			}
		} catch (Exception e) {
			LOGGER.info(e);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}

}
