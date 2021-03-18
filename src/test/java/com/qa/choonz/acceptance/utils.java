package com.qa.choonz.acceptance;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class utils {

    private static WebDriver webDriver;

    private static WebElement webElement;

    public static void registerAndLogin() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8082");
        webElement = webDriver.findElement(By.id("account-settings"));
        webElement.click();
        webElement = webDriver.findElement(By.cssSelector("#account-management > li:nth-child(2) > a:nth-child(1)"));
        webElement.click();
        webElement = webDriver.findElement(By.id("username-input"));
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        webElement.sendKeys(generatedString);
        webElement = webDriver.findElement(By.id("password-input"));
        webElement.sendKeys("password");
        webElement = webDriver.findElement(By.id("re-enter-password-input"));
        webElement.sendKeys("password");
        webElement = webDriver.findElement(By.id("submit-detail-button"));
        webElement.click();
        webElement = webDriver.findElement(By.id("username-input"));
        webElement.sendKeys(generatedString);
        webElement = webDriver.findElement(By.id("password-input"));
        webElement.sendKeys("password");
        webElement = webDriver.findElement(By.id("submit-detail-button"));
        webElement.click();
        webDriver.close();
    }

    public static void createArtist() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8082");
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Artist");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("artist-create-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("artist-modal-name"));
        webElement.sendKeys("Test Artist");
        webElement = webDriver.findElement(By.id("artist-modal-submit"));
        webElement.click();
        webDriver.close();
    }

    public static void createGenre() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8082");
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Genre");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("genre-create-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("genre-modal-name"));
        webElement.sendKeys("Test Genre");
        webElement = webDriver.findElement(By.id("genre-modal-desc"));
        webElement.sendKeys("Test Genre Description");
        webElement = webDriver.findElement(By.id("genre-modal-submit"));
        webElement.click();
        webDriver.close();
    }

    public static void createAlbum() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8082");
        webElement = webDriver.findElement(By.id("select-bar"));
        webElement.sendKeys("Album");
        webElement = webDriver.findElement(By.id("search-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("album-create-button"));
        webElement.click();
        Thread.sleep(500);
        webElement = webDriver.findElement(By.id("album-modal-name"));
        webElement.sendKeys("Test Album");
        webElement = webDriver.findElement(By.id("album-artist-modal-name"));
        webElement.sendKeys("Test Artist");
        webElement = webDriver.findElement(By.id("album-genre-modal-name"));
        webElement.sendKeys("Test Genre");
        webElement = webDriver.findElement(By.id("album-modal-submit"));
        webElement.click();
        Thread.sleep(1000);
        webDriver.close();
    }
}
