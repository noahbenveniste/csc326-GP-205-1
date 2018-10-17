package edu.ncsu.csc.itrust2.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;

/**
 * Step definitions for DiaryEntry feature
 *
 * @author Alex Johnson (abjohns6)
 * @author kpresle
 */
public class VerifyDiaryEnryTableStepDefs extends CucumberTest {

    private final String BASE_URL = "http://localhost:8080/iTrust2";

    /**
     * Check the food diary was added correctly
     */
    @Then ( "^the entry is added to my food diary$" )
    public void createdSuccessfully () {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        // diary entry row and daily total row
        assertEquals( 2, entries.size() );
        tearDown();
    }

    /**
     * Confirms no food diary entries were added
     */
    @Then ( "^the entry is not added to my food diary$" )
    public void diaryEntryNotAdded () {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        assertEquals( 0, entries.size() );
    }

    /**
     * Checks that the totals for serving was calculated correctly
     */
    @Then ( "^the daily macro-nutrient totals are: (.+) calories, (.+)g fat, (.+)mg sodium, (.+)g carbs, (.+)g sugar, (.+)g fiber and (.+)g protein.$" )
    public void totalsAreCalculated ( String calories, String fat, String sodium, String carbs, String sugar,
            String fiber, String protein ) {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        // 2 diary entry rows, the second one is the totals
        final WebElement totals = entries.get( 1 );
        assertEquals( fat, totals.findElement( By.name( "fatCell" ) ).getText() );
        assertEquals( sodium, totals.findElement( By.name( "sodiumCell" ) ).getText() );
        assertEquals( carbs, totals.findElement( By.name( "carbsCell" ) ).getText() );
        assertEquals( sugar, totals.findElement( By.name( "sugarCell" ) ).getText() );
        assertEquals( fiber, totals.findElement( By.name( "fiberCell" ) ).getText() );
        assertEquals( protein, totals.findElement( By.name( "proteinCell" ) ).getText() );
    }

    /**
     * Check that the diary form was cleared successfully
     */
    @Then ( "^I see a diary entry on (.+) that for (.+) servings of (.+) for (.+) that had: (.+) calories, (.+)g fat, (.+)mg sodium, (.+)g carbs, (.+)g sugar, (.+)g fiber and (.+)g protein.$" )
    public void entryIsIncludedInTable ( String date, String servings, String name, String meal, String calories,
            String fat, String sodium, String carbs, String sugar, String fiber, String protein ) {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        // 2 diary entry rows, the first one is the row
        final WebElement totals = entries.get( 0 );
        assertEquals( date, totals.findElement( By.name( "dateCell" ) ).getText() );
        assertEquals( servings, totals.findElement( By.name( "servingsCell" ) ).getText() );
        assertEquals( name, totals.findElement( By.name( "nameCell" ) ).getText() );
        assertEquals( meal, totals.findElement( By.name( "mealCell" ) ).getText() );
        assertEquals( fat, totals.findElement( By.name( "fatCell" ) ).getText() );
        assertEquals( sodium, totals.findElement( By.name( "sodiumCell" ) ).getText() );
        assertEquals( carbs, totals.findElement( By.name( "carbsCell" ) ).getText() );
        assertEquals( sugar, totals.findElement( By.name( "sugarCell" ) ).getText() );
        assertEquals( fiber, totals.findElement( By.name( "fiberCell" ) ).getText() );
        assertEquals( protein, totals.findElement( By.name( "proteinCell" ) ).getText() );
    }
}
