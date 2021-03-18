package com.qa.choonz.acceptance.genre.stepdefs;

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

    @Given("That I search for an genre")
    public void thatISearchForAnGenre() {
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Genre");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
    }

    @When("I click the add link")
    public void iClickTheAddLink() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("genre-create-button"));
        webElement.click();
    }

    @And("I fill out the modal")
    public void iFillOutTheModal() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("genre-modal-name"));
        webElement.sendKeys("Test Genre");
        webElement = webDriver.findElement(By.id("genre-modal-desc"));
        webElement.sendKeys("Test Genre Description");
    }

    @Then("I have successfully created an genre")
    public void iHaveSuccessfullyCreatedAnGenre() {
        webElement = webDriver.findElement(By.id("genre-modal-submit"));
        webElement.click();
    }

    @Given("That I search for the genre name")
    public void thatISearchForTheGenreName() {
        webElement = webDriver.findElement(By.id("input-bar"));
        webElement.sendKeys("Test Genre");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
    }

    @Then("They appear in the search results")
    public void theyAppearInTheSearchResults() {
        // TODO Verify that the artist appears
    }

    @Given("That I can see the genre I want to change")
    public void thatICanSeeTheGenreIWantToChange() {
        // TODO Implement update methods
    }

    @When("I click the pencil icon")
    public void iClickThePencilIcon() {
        // TODO Implement update methods
    }

    @And("I fill out the change modal")
    public void iFillOutTheChangeModal() {
        // TODO Implement update methods
    }

    @Then("The genre has been update")
    public void theGenreHasBeenUpdate() {
        // TODO Implement update methods
    }

    @Given("That I can see the genre I want to delete")
    public void thatICanSeeTheGenreIWantToDelete() {
        // TODO Implement update methods
    }

    @When("I click the cross icon")
    public void iClickTheCrossIcon() throws InterruptedException {
        // If there aren't any genres already in the database this is 1 if something already exists this will be higher
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("delete-genre-1"));
        webElement.click();
    }

    @Then("The genre has been deleted")
    public void theGenreHasBeenDeleted() {
        // TODO Verify delete
    }

    @After
    public void teardown() {
        webDriver.close();
    }
}
