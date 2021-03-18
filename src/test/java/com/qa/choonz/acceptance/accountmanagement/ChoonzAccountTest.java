package com.qa.choonz.acceptance.accountmanagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/AccountManagement.feature",
        glue = "com.qa.choonz.acceptance.accountmanagement.stepdefs"
)

public class ChoonzAccountTest {
}
