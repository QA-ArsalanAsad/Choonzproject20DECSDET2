package com.qa.choonz.acceptance.track.stepdefs;

import com.qa.choonz.acceptance.utils;
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

    @Given("That I search for an track")
    public void thatISearchForAnTrack() throws InterruptedException {
        // This function has to be called since a album needs an artist assigned to it
        utils.createArtist();
        utils.createGenre();
        utils.createAlbum();
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Track");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
    }

    @When("I click the add link")
    public void iClickTheAddLink() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("track-create-button"));
        webElement.click();
    }

    @And("I fill out the modal")
    public void iFillOutTheModal() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("track-modal-name"));
        webElement.sendKeys("Test Track");
        webElement = webDriver.findElement(By.id("track-modal-duration"));
        webElement.sendKeys("123");
        webElement = webDriver.findElement(By.id("track-artist-modal-name"));
        webElement.sendKeys("Test Album");
    }

    @Then("I have successfully created an track")
    public void iHaveSuccessfullyCreatedAnTrack() {
        webElement = webDriver.findElement(By.id("track-modal-submit"));
        webElement.click();
    }

    @Given("That I search for the track name")
    public void thatISearchForTheTrackName() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Track");
        webElement = webDriver.findElement(By.id("input-bar"));
        webElement.sendKeys("Test Track");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
        Thread.sleep(4000);
    }

    @Then("They appear in the search results")
    public void theyAppearInTheSearchResults() {
        // TODO Verify that the artist appears
    }

    @Given("That I can see the track I want to change")
    public void thatICanSeeTheTrackIWantToChange() {
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

    @Then("The track has been update")
    public void theTrackHasBeenUpdate() {
        // TODO Implement update methods
    }

    @Given("That I can see the track I want to delete")
    public void thatICanSeeTheTrackIWantToDelete() {
    }

    @When("I click the cross icon")
    public void iClickTheCrossIcon() throws InterruptedException {
        // If there aren't any albums already in the database this is 1 if something already exists this will be higher
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("delete-track-1"));
        webElement.click();
    }

    @Then("The track has been deleted")
    public void theTrackHasBeenDeleted() {
    }

    @After
    public void teardown() {
        webDriver.close();
    }
}
