package com.qa.choonz.acceptance.album.stepdefs;

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

    public void createArtist() throws InterruptedException {
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Artist");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("artist-create-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("artist-modal-name"));
        webElement.sendKeys("Test Artist For Album");
        webElement = webDriver.findElement(By.id("artist-modal-submit"));
        webElement.click();
    }
    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        webDriver.get("http://localhost:8082");
    }

    @Given("That I search for an album")
    public void thatISearchForAnAlbum() throws InterruptedException {
        createArtist();
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Album");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
    }

    @When("I click the add link")
    public void iClickTheAddLink() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("album-create-button"));
        webElement.click();
    }

    @And("I fill out the modal")
    public void iFillOutTheModal() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("album-modal-name"));
        webElement.sendKeys("Test Album");
        // TODO Click the submit button
    }

    @Then("I have successfully created an album")
    public void iHaveSuccessfullyCreatedAnAlbum() throws InterruptedException {
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("artist-modal-name"));
        webElement.sendKeys("Test Album");
    }

    @Given("That I search for the album name")
    public void thatISearchForTheAlbumName() {
    }

    @Given("That I can see the album I want to change")
    public void thatICanSeeTheAlbumIWantToChange() {
    }

    @Then("The album has been update")
    public void theAlbumHasBeenUpdate() {
    }

    @Given("That I can see the album I want to delete")
    public void thatICanSeeTheAlbumIWantToDelete() {
    }

    @Then("The album has been deleted")
    public void theAlbumHasBeenDeleted() {
    }
}

