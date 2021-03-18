package com.qa.choonz.acceptance.album;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Album.feature",
        glue = "com.qa.choonz.acceptance.album.stepdefs"
)
public class AlbumTest {
}
