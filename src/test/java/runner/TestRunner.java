package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {"resources/appFeatures"},
		glue = {"stepDefinitions"},
		tags = ("@orderProcess"),

				plugin = {"pretty",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
						},
				monochrome = true
						
						)


public class TestRunner{
	
}