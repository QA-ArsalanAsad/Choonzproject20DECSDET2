package com.qa.choonz.acceptance.accountmanagement.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefs {

    private static WebDriver webDriver;

    private static WebElement webElement;

    @Before
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chrome/chromedriver.exe");

        webDriver = new ChromeDriver();
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        webDriver.get("http://localhost:8082");
    }

    @When("I click the register button")
    public void iClickTheRegisterButton() {
        webElement = webDriver.findElement(By.id("account-settings"));
        webElement.click();
        webElement = webDriver.findElement(By.cssSelector("#account-management > li:nth-child(2) > a:nth-child(1)"));
        webElement.click();
    }

    @And("I enter my desired details")
    public void iEnterMyDesiredDetails() {
        webElement = webDriver.findElement(By.id("username-input"));
        webElement.sendKeys("henry");
        webElement = webDriver.findElement(By.id("password-input"));
        webElement.sendKeys("password");
        webElement = webDriver.findElement(By.id("re-enter-password-input"));
        webElement.sendKeys("password");
    }

    @And("I hit the submit button")
    public void iHitTheSubmitButton() {
        webElement = webDriver.findElement(By.id("submit-detail-button"));
        webElement.click();
    }

    @Then("I have successfully registered")
    public void iHaveSuccessfullyRegistered() {
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
    }

    @When("I enter my details")
    public void iEnterMyDetails() {
        webElement = webDriver.findElement(By.id("username-input"));
        webElement.sendKeys("henry");
        webElement = webDriver.findElement(By.id("password-input"));
        webElement.sendKeys("password");
        webElement = webDriver.findElement(By.id("submit-detail-button"));
        webElement.click();
    }

    @Then("I have successfully logged in")
    public void iHaveSuccessfullyLoggedIn() {
    }

    @When("I hit the logout button")
    public void iHitTheLogoutButton() {
        webElement = webDriver.findElement(By.id("account-settings"));
        webElement.click();
        webElement = webDriver.findElement(By.cssSelector(".dropdown-item"));
        webElement.click();
    }

    @Then("I have successfully logged out")
    public void iHaveSuccessfullyLoggedOut() {
    }

    @After
    public void teardown() {
        webDriver.close();
    }
}
