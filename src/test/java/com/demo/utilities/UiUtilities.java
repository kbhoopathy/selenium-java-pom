package com.demo.utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.webdriver_manager.DriverManager;
import org.openqa.selenium.support.ui.Select;

public class UiUtilities {

	private static final Logger LOGGER = LogManager.getLogger(UiUtilities.class);

	private static UiUtilities uiUtilitiesInstance;

	private UiUtilities() {
	}

	public static UiUtilities getInstance() {
		if (uiUtilitiesInstance == null) {
			uiUtilitiesInstance = new UiUtilities();
		}
		return uiUtilitiesInstance;
	}

	public static void waitUntilElementIsVisible(By element) {
		LOGGER.info("Waiting for the element to visible" + element.toString());
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void waitUntilFrameToBeAvailableAndSwitchToIt(String frame) {
		LOGGER.info("Waiting for the frame to be available " + frame);
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void waitUntilElementToBeClickable(WebElement element) {
		LOGGER.info("Waiting for the element to be clickable" + element.toString());
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void switchToChildTab() {
		LOGGER.info("Switching to Child Tab");
		Set<String> windows = DriverManager.getDriver().getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String patName = itr.next();
		String chldName = itr.next();
		DriverManager.getDriver().switchTo().window(chldName);
	}

	public void closeChildTabAndswitchToParentTab() {
		LOGGER.info("Closing Child tab and switching to Parent Tab");
		Set<String> windows = DriverManager.getDriver().getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String patName = itr.next();
		String chldName = itr.next();
		DriverManager.getDriver().close();
		DriverManager.getDriver().switchTo().window(patName);
	}

	public void selectValue(List<WebElement> elements, String value) {
		for (WebElement element : elements) {
			if (element.getText().equals(value)) {
				LOGGER.info("selected value is: " + element.getText());
				element.click();
			}
		}
	}

	public void selectDropdown(WebElement element, String type, String value) {
		Select dropDown = new Select(element);
		if (type.equals("ByVisibleText")) {
			dropDown.selectByVisibleText(value);
		} else if (type.equals("ByValue")) {
			dropDown.selectByValue(value);
		} else if (type.equals("ByIndex")) {
			dropDown.selectByIndex(Integer.parseInt(value));
		}else {
			LOGGER.error("Pass any one type from the following: ByVisibleText/ByValue/ByIndex");
		}
			
	}

}
