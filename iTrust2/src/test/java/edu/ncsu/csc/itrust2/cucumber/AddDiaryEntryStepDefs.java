package edu.ncsu.csc.itrust2.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc.itrust2.models.persistent.DiaryEntry;

/**
 * Step definitions for DiaryEntry feature
 *
 * @author Alex Johnson (abjohns6)
 * @author kpresle
 */
public class AddDiaryEntryStepDefs extends CucumberTest {

    private final String BASE_URL = "http://localhost:8080/iTrust2";

    /**
     * Patient is logged in and viewing food diary
     */
    @Given ( "^there are no diary entries for (.+)$" )
    public void noFoodDiary ( final String patient ) {
        setup();
        DiaryEntry.deleteAll( DiaryEntry.class );
        assertEquals( 0, DiaryEntry.getFoodDiaryEntriesForPatient( patient ).size() );
    }

    /**
     * Patient login and opens their food diary
     */
    @When ( "I navigate to the diary entry page" )
    public void loginAndOpenFoodDiary () {
        driver.get( BASE_URL );
        waitForAngular();
        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('fooddiary').click();" );
        waitForAngular();
        assertEquals( BASE_URL + "/patient/foodDiary/myFoodDiary", driver.getCurrentUrl() );
    }

    /**
     * Submits a valid diary entry
     */
    @When ( "^I submit an entry on (.+) that I ate (.+) servings of (.+) for (.+) that had: (.+) calories, (.+)g fat, (.+)mg sodium, (.+)g carbs, (.+)g sugar, (.+)g fiber and (.+)g protein.$" )
    public void fillDiaryFields ( final String date, final String servings, final String name, final String meal,
            final String calories, final String fat, final String sodium, final String carbs, final String sugar,
            final String fiber, final String protein ) {
        // Use default date for now
        // final WebElement dateEle = driver.findElement( By.name( "date" ) );
        // dateEle.clear();
        // dateEle.sendKeys( date );
        final WebElement nameEle = driver.findElement( By.name( "name" ) );
        nameEle.clear();
        nameEle.sendKeys( name );
        final WebElement servingsEle = driver.findElement( By.name( "servings" ) );
        servingsEle.clear();
        servingsEle.sendKeys( servings );
        final WebElement mealEle = driver.findElement( By.name( "meal" ) );
        final Select dropdown = new Select( mealEle );
        dropdown.selectByVisibleText( meal );
        final WebElement caloriesEle = driver.findElement( By.name( "calories" ) );
        caloriesEle.clear();
        caloriesEle.sendKeys( calories );
        final WebElement fatEle = driver.findElement( By.name( "fatGrams" ) );
        fatEle.clear();
        fatEle.sendKeys( fat );
        final WebElement sodiumEle = driver.findElement( By.name( "sodium" ) );
        sodiumEle.clear();
        sodiumEle.sendKeys( sodium );
        final WebElement carbsEle = driver.findElement( By.name( "carbs" ) );
        carbsEle.clear();
        carbsEle.sendKeys( carbs );
        final WebElement sugarEle = driver.findElement( By.name( "sugars" ) );
        sugarEle.clear();
        sugarEle.sendKeys( sugar );
        final WebElement fiberEle = driver.findElement( By.name( "fibers" ) );
        fiberEle.clear();
        fiberEle.sendKeys( fiber );
        final WebElement proteinEle = driver.findElement( By.name( "protein" ) );
        proteinEle.clear();
        proteinEle.sendKeys( protein );

        driver.findElement( By.className( "btn-primary" ) ).click();
    }

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

    @Then ( "^the entry is not added to my food diary$" )
    public void diaryEntryNotAdded () {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        assertEquals( 0, entries.size() );
    }

    @And ( "^the entry form is not cleared$" )
    public void formCleared () {
        waitForAngular();
        // check that each one of the form values is not empty
        final WebElement nameEle = driver.findElement( By.name( "name" ) );
        assertFalse( nameEle.getAttribute( "value" ).isEmpty() );
        final WebElement servingsEle = driver.findElement( By.name( "servings" ) );
        assertFalse( servingsEle.getAttribute( "value" ).isEmpty() );
        final WebElement mealEle = driver.findElement( By.name( "meal" ) );
        assertFalse( mealEle.getAttribute( "value" ).isEmpty() );
        final WebElement caloriesEle = driver.findElement( By.name( "calories" ) );
        assertFalse( caloriesEle.getAttribute( "value" ).isEmpty() );
        final WebElement fatEle = driver.findElement( By.name( "fatGrams" ) );
        assertFalse( fatEle.getAttribute( "value" ).isEmpty() );
        final WebElement sodiumEle = driver.findElement( By.name( "sodium" ) );
        assertFalse( sodiumEle.getAttribute( "value" ).isEmpty() );
        final WebElement carbsEle = driver.findElement( By.name( "carbs" ) );
        assertFalse( carbsEle.getAttribute( "value" ).isEmpty() );
        final WebElement sugarEle = driver.findElement( By.name( "sugars" ) );
        assertFalse( sugarEle.getAttribute( "value" ).isEmpty() );
        final WebElement fiberEle = driver.findElement( By.name( "fibers" ) );
        assertFalse( fiberEle.getAttribute( "value" ).isEmpty() );
        final WebElement proteinEle = driver.findElement( By.name( "protein" ) );
        assertFalse( proteinEle.getAttribute( "value" ).isEmpty() );
    }

    @Then ( "^Then my daily macro-nutrient totals are: <caloriesTotal> "
            + "calories, <fatTotal>g fat, <sodiumTotal>mg sodium, <carbsTotal>g carbs, <sugarTotal>g "
            + "sugar, <fiberTotal>g fiber and <proteinTotal>g protein.$" )
    public void totalsAreCalculated ( String calories, String fat, String sodium, String carbs, String sugar,
            String fiber, String protein ) {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        // 2 diary entry rows, the second one is the totals
        final WebElement totals = entries.get( 1 );
        assertTrue( totals.findElement( By.name( "caloriesCell" ) ).getText().contains( calories ) );
        assertTrue( totals.findElement( By.name( "fatCell" ) ).getText().contains( fat ) );
        assertTrue( totals.findElement( By.name( "sodiumCell" ) ).getText().contains( sodium ) );
        assertTrue( totals.findElement( By.name( "carbsCell" ) ).getText().contains( carbs ) );
        assertTrue( totals.findElement( By.name( "sugarCell" ) ).getText().contains( sugar ) );
        assertTrue( totals.findElement( By.name( "fiberCell" ) ).getText().contains( fiber ) );
        assertTrue( totals.findElement( By.name( "proteinCell" ) ).getText().contains( protein ) );
    }
}
