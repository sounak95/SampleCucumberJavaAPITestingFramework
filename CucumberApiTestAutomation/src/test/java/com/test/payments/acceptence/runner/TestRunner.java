package com.test.payments.acceptence.runner;

import java.io.File;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cucumber.listener.Reporter;
import com.test.payments.acceptence.stepdefinition.MasterHooks;
import com.test.payments.acceptence.utils.ReusableMethods;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@RunWith(Cucumber.class)

@CucumberOptions(features = { "src/test/resources/feature/endToEnd" }, glue = {
		"com.test.payments.acceptence.stepdefinition" }, monochrome = true, dryRun = false, tags = {}, 
				plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json", "junit:target/cucumber-reports/Cucumber.xml",
		"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"})

public class TestRunner extends AbstractTestNGCucumberTests {
	@AfterClass
	public static void writeExtentReport() throws IOException {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "//src//test//java//com//test//payments//acceptence//utils//ReportsConfig.xml"));
		ReusableMethods.copyLatestExtentReport();
	}
	
	@BeforeClass
	public static void beforeAll() throws IOException, InterruptedException
	{
		MasterHooks.getb2btoken();
	}
	
	
}