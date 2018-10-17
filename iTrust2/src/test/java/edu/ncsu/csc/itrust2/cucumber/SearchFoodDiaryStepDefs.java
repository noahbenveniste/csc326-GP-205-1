package edu.ncsu.csc.itrust2.cucumber;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust2.models.enums.Meal;
import edu.ncsu.csc.itrust2.models.persistent.DiaryEntry;
import edu.ncsu.csc.itrust2.models.persistent.User;

/**
 * Step definitions for the search functionality of the DiaryEntry feature
 *
 * @author Alex Johnson (abjohns6)
 * @author kpresle
 */
public class SearchFoodDiaryStepDefs extends CucumberTest {

    private final String BASE_URL = "http://localhost:8080/iTrust2";

    /**
     * Adds a diary entry to the database
     *
     * @throws ParseException
     */
    @Given ( "^(.+) has a diary entry on (.+) for (\\d+) servings of (.+) for (.+) that has: (\\d+) calories, (\\d+)g fat, (\\d+)mg sodium, (\\d+)g carbs, (\\d+)g sugar, (\\d+)g fiber and (\\d+)g protein$" )
    public void addDiaryEntry ( String patient, final String date, final int servings, final String name,
            final String meal, final int calories, final int fat, final int sodium, final int carbs, final int sugar,
            final int fiber, final int protein ) throws ParseException {
        DiaryEntry.deleteAll( DiaryEntry.class );
        setup();
        Meal m = null;
        if ( meal.equals( "BREAKFAST" ) ) {
            m = Meal.BREAKFAST;
        }
        else if ( meal.equals( "LUNCH" ) ) {
            m = Meal.LUNCH;
        }
        else if ( meal.equals( "SNACK" ) ) {
            m = Meal.SNACK;
        }
        else if ( meal.equals( "DINNER" ) ) {
            m = Meal.DINNER;
        }

        final Date d = ( new SimpleDateFormat( "MM/dd/yyyy" ) ).parse( date );
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis( d.getTime() );
        final DiaryEntry de = new DiaryEntry( c, m, name, servings, calories, fat, sodium, carbs, sugar, fiber, protein,
                User.getByName( patient ) );
        de.save();

        driver.get( BASE_URL );
        waitForAngular();
    }

    /**
     * HCP login and opens their food diary
     */
    @When ( "^I search for (.+)'s diary entries$" )
    public void searchForDiaryEntries ( String patient ) {
        driver.get( BASE_URL );
        waitForAngular();
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('HCPfooddiarydirectory').click();" );
        waitForAngular();
        assertEquals( BASE_URL + "/hcp/foodDiaryDirectory.html", driver.getCurrentUrl() );
        // add patient name
        final WebElement nameEle = driver.findElement( By.name( "searchPatientName" ) );
        nameEle.clear();
        nameEle.sendKeys( patient );
        // search for entries
        driver.findElement( By.name( "submit" ) ).click();
    }

}
