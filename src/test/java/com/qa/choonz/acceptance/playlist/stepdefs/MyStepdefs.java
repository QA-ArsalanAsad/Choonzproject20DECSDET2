package com.qa.choonz.acceptance.playlist.stepdefs;

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

    @Given("That I search for an playlist")
    public void thatISearchForAnPlaylist() {
        // This function has to be called since playlist creation requires
        // a user to be logged in
        utils.registerAndLogin();
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Playlist");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
    }

    @When("I click the add link")
    public void iClickTheAddLink() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("playlist-create-button"));
        webElement.click();
    }

    @And("I fill out the modal")
    public void iFillOutTheModal() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("playlist-modal-name"));
        webElement.sendKeys("Test Playlist");
        webElement = webDriver.findElement(By.id("playlist-modal-desc"));
        webElement.sendKeys("Test Playlist Description");
    }

    @Then("I have successfully created a playlist")
    public void iHaveSuccessfullyCreatedAPlaylist() {
        webElement = webDriver.findElement(By.id("playlist-modal-submit"));
        webElement.click();
    }

    @Given("That I search for the playlist name")
    public void thatISearchForThePlaylistName() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("input-bar"));
        webElement.sendKeys("Test Playlist");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
    }

    @Then("They appear in the search results")
    public void theyAppearInTheSearchResults() {
        // TODO Verify that the artist appears
    }

    @Given("That I can see the playlist I want to change")
    public void thatICanSeeThePlaylistIWantToChange() {
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

    @Then("The playlist has been update")
    public void thePlaylistHasBeenUpdate() {
        // TODO Implement update methods
    }

    @Given("That I can see the playlist I want to delete")
    public void thatICanSeeThePlaylistIWantToDelete() {
        // TODO Verify that playlist exists
    }

    @When("I click the cross icon")
    public void iClickTheCrossIcon() throws InterruptedException {
        // If there aren't any artists already in the database this is 1 if something already exists this will be higher
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("delete-playlists-1"));
        webElement.click();
    }

    @Then("The playlist has been deleted")
    public void thePlaylistHasBeenDeleted() {
        // TODO Verify delete
    }

    @After
    public void teardown() {
        webDriver.close();
    }
}
