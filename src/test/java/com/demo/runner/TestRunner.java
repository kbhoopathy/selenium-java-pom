package com.demo.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.demo.stepdefinitions",
		"com.demo.hooks" }, monochrome = true, plugin = { "pretty",
				"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}, tags = "@demo" )

public class TestRunner {

}