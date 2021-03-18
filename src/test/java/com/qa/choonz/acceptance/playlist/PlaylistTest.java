package com.qa.choonz.acceptance.playlist;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Playlist.feature",
        glue = "com.qa.choonz.acceptance.playlist.stepdefs"
)
public class PlaylistTest {
}
