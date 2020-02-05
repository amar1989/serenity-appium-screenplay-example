package com.offline.city.map.serenity.abilities;

import com.google.common.eventbus.Subscribe;
import net.serenitybdd.core.eventbus.Broadcaster;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.RefersToActor;
import net.serenitybdd.screenplay.events.*;
import net.serenitybdd.screenplay.exceptions.ActorCannotBrowseTheWebException;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.PageObjectDependencyInjector;
import net.thucydides.core.webdriver.SerenityWebdriverManager;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import net.thucydides.core.webdriver.WebdriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseAMobileDevice extends PageObject implements Ability, RefersToActor {

    private final WebdriverManager webdriverManager;

    private Actor actor;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UseAMobileDevice(WebDriver webDriver) {
        super(webDriver);
        this.webdriverManager = ThucydidesWebDriverSupport.getWebdriverManager();
        registerForEventNotification();
    }

    private void registerForEventNotification(){
        Broadcaster.getEventBus().register(this);
    }

    public static UseAMobileDevice as(Actor actor){
        if(actor.abilityTo(UseAMobileDevice.class) == null){
            throw new ActorCannotBrowseTheWebException(actor.getName());
        }
        return actor.abilityTo(UseAMobileDevice.class).asActor(actor);
    }

    public static UseAMobileDevice with(WebDriver webDriver){return new UseAMobileDevice(webDriver);}

    public <T extends PageObject> T onPage(Class<T> pageObjectClass) {
        return on(pageObjectClass);
    }

    public <T extends PageObject> T onPageElement(Class<T> pageObjectClass) {
        return on(pageObjectClass);
    }
    public <T extends PageObject> T on(Class<T> pageObjectClass) {
        return Pages.instrumentedPageObjectUsing(pageObjectClass, getDriver());
    }

    @Subscribe
    public void perform(ActorPerforms performAction) {
        if (messageIsForThisActor(performAction)) {
            WebDriver driver = webdriverManager.getWebdriver();
            PageObjectDependencyInjector injector = new PageObjectDependencyInjector(new Pages(driver));
            injector.injectDependenciesInto(performAction.getPerformable());
        }
    }

    @Subscribe public void prepareQuestion(ActorAsksQuestion questionEvent) {
        if (messageIsForThisActor(questionEvent)) {
            WebDriver driver = webdriverManager.getWebdriver();
            PageObjectDependencyInjector injector = new PageObjectDependencyInjector(new Pages(driver));
            injector.injectDependenciesInto(questionEvent.getQuestion());
        }
    }

    @Subscribe public void beginPerformance(ActorBeginsPerformanceEvent performanceEvent) {
        if (messageIsForThisActor(performanceEvent)) {
            SerenityWebdriverManager.inThisTestThread().setCurrentActiveDriver(getDriver());
        }
    }

    @Subscribe public void endPerformance(ActorEndsPerformanceEvent performanceEvent) {
        if (messageIsForThisActor(performanceEvent)) {
            SerenityWebdriverManager.inThisTestThread().clearCurrentActiveDriver();
        }
    }

    private boolean messageIsForThisActor(ActorPerformanceEvent event) {
        return event.getName().equals(actor.getName());
    }

    @Override
    public <T extends Ability> T asActor(Actor actor) {
        this.actor = actor;
        return (T) this;
    }

    @Override
    public String toString() {
        return "use a mobile device";
    }
}
