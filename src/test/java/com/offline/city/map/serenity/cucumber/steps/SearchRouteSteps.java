package com.offline.city.map.serenity.cucumber.steps;

import com.offline.city.map.serenity.tasks.Go;
import com.offline.city.map.serenity.tasks.Launch;
import com.offline.city.map.serenity.tasks.Search;

import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.offline.city.map.serenity.utils.AncillaryMethods.startTheAppForActor;


public class SearchRouteSteps {

    Scenario scenario;

    @Before
    public void set_the_stage(Scenario scenario) {

        OnStage.setTheStage(new OnlineCast());
        this.scenario = scenario;
        //we store the scenario name in a Serenity session var, in order to pass it to Browserstack, to show on its Dashboard
        //we read back this session variable on the method SetCapabilities.forBrowserStack(DesiredCapabilities capabilities)
        Serenity.setSessionVariable("ScenarioName").to(scenario.getName());
    }

    @After
    public void tearDown() {
        OnStage.drawTheCurtain();
    }

    @Given("^that (.*) wants to search for a point of interest$")
    public void navigatesToJourneyConfiguration(String commuterName) throws Throwable {
        System.out.println("Hello word this is when");
        //this method is important for our mechanism of the Actors-Apps association to work
        /*startTheAppForActor(commuterName);

        OnStage.theActorCalled(commuterName)
                .attemptsTo(
                        Launch.theApp(),
                        Go.toMap()
                );*/
    }

    @Given("^s?he needs to know where (.*) is located$")
    public void needsToKnowWhereLocationIsLocated(String location) throws Throwable {
        //Search.theLocation(location);
        System.out.println("Hello word this is given");
    }

    @Then("^he should be able to see (.*) on the map$")
    public void heShouldBeAbleToSeeOnTheMap(String location) throws Throwable {
        // not important for this POC
        System.out.println("Hello word this is then");
    }
}
