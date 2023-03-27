package com.demo.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.demo.utilities.UiUtilities;

public class DemoPage {

	private static DemoPage DemoPageInstance;
	private static final Logger LOGGER = LogManager.getLogger(DemoPage.class);

	private DemoPage() {
	}

	public static DemoPage getInstance() {
		if (DemoPageInstance == null) {
			DemoPageInstance = new DemoPage();
		}
		return DemoPageInstance;
	}

	@FindBy(name = "radioButton")
	private WebElement radioOne;

	@FindBy(id = "autocomplete")
	private WebElement country;

	@FindBy(className = "ui-menu-item-wrapper")
	private List<WebElement> autoCompleteOptions;

	@FindBy(id = "dropdown-class-example")
	private WebElement dropDown;

	@FindBy(id = "openwindow")
	private WebElement openNewWindow;

	@FindBy(id = "opentab")
	private WebElement openNewTab;

	@FindBy(id = "name")
	private WebElement name;

	@FindBy(id = "alertbtn")
	private WebElement alertButton;

	@FindBy(id = "confirmbtn")
	private WebElement confirmAlertButton;

	public void getRadioOne() {
		radioOne.click();
	}

	public void selectCountry(String value) {
		country.sendKeys(value);
		UiUtilities.getInstance().selectValue(autoCompleteOptions, value);
	}

	public void selectValueAtDropDown(String value) {
		UiUtilities.getInstance().selectDropdown(dropDown, "ByValue", value);
	}
}
