package com.qa.choonz.acceptance.track;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Track.feature",
        glue = "com.qa.choonz.acceptance.track.stepdefs"
)
public class TrackTest {
}
