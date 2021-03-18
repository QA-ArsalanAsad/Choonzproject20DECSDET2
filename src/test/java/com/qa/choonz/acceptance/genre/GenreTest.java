package com.qa.choonz.acceptance.genre;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Genre.feature",
        glue = "com.qa.choonz.acceptance.genre.stepdefs"
)
public class GenreTest {
}
