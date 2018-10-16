package edu.ncsu.csc.itrust2.cucumber;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

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
        driver.get( BASE_URL );
        DiaryEntry.deleteAll( DiaryEntry.class );
        assertEquals( 0, DiaryEntry.getFoodDiaryEntriesForPatient( patient ).size() );
    }

    /**
     * Patient login and opens their food diary
     */
    @When ( "I navigate to the diary entry page" )
    public void loginAndOpenFoodDiary () {
        /*
         * final WebElement username = driver.findElement( By.name( "username" )
         * ); username.clear(); username.sendKeys( user ); final WebElement
         * password = driver.findElement( By.name( "password" ) );
         * password.clear(); password.sendKeys( "123456" ); final WebElement
         * submit = driver.findElement( By.className( "btn" ) ); submit.click();
         * final WebElement dropdown = driver.findElement( By.id(
         * "patient-dropdown" ).cssSelector( "dropdown-toggle" ) );
         * dropdown.click(); final WebElement diaryBtn = driver.findElement(
         * By.id( "fooddiary" ) ); diaryBtn.click();
         */

        ( (JavascriptExecutor) driver ).executeScript( "document.getElementById('fooddiary').click();" );
        assertEquals( BASE_URL + "/patient/foodDiary/myFoodDiary", driver.getCurrentUrl() );
    }

    /**
     * Submits a valid diary entry
     */
    @When ( "^I submit an entry on (.+) that I ate (.+) servings of (.+) for (.+) that had: (.+) calories, (.+)g fat, (.+)mg sodium, (.+)g carbs, (.+)g sugar, (.+)g fiber and (.+)g protein.$" )
    public void fillDiaryFields ( final String date, final String name, final String servings, final String meal,
            final String fat, final String sodium, final String carbs, final String sugar, final String fiber,
            final String protein ) {
        final WebElement dateEle = driver.findElement( By.name( "date" ) );
        dateEle.clear();
        dateEle.sendKeys( date );
        final WebElement nameEle = driver.findElement( By.name( "name" ) );
        nameEle.clear();
        nameEle.sendKeys( name );
        final WebElement servingsEle = driver.findElement( By.name( "servings" ) );
        servingsEle.clear();
        servingsEle.sendKeys( servings );
        final WebElement mealEle = driver.findElement( By.name( "mealType" ) );
        mealEle.clear();
        mealEle.sendKeys( meal );
        final WebElement caloriesEle = driver.findElement( By.name( "caloriesPerServing" ) );
        caloriesEle.clear();
        caloriesEle.sendKeys( meal );
        final WebElement fatEle = driver.findElement( By.name( "fat" ) );
        fatEle.clear();
        fatEle.sendKeys( fat );
        final WebElement sodiumEle = driver.findElement( By.name( "sodium" ) );
        sodiumEle.clear();
        sodiumEle.sendKeys( sodium );
        final WebElement carbsEle = driver.findElement( By.name( "carbs" ) );
        carbsEle.clear();
        carbsEle.sendKeys( carbs );
        final WebElement sugarEle = driver.findElement( By.name( "sugar" ) );
        sugarEle.clear();
        sugarEle.sendKeys( sugar );
        final WebElement fiberEle = driver.findElement( By.name( "fiber" ) );
        fiberEle.clear();
        fiberEle.sendKeys( fiber );
        final WebElement proteinEle = driver.findElement( By.name( "protein" ) );
        proteinEle.clear();
        proteinEle.sendKeys( protein );

        driver.findElement( By.id( "submit" ) ).click();
    }

    /**
     * Check the food diary was added correctly
     */
    @Then ( "^the entry is added to my food diary$" )
    public void createdSuccessfully () {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        assertEquals( 1, entries.size() );
        tearDown();
    }

    @Then ( "^the entry is not added to my food diary$" )
    public void diaryEntryNotAdded () {
        waitForAngular();
        final List<WebElement> entries = driver.findElements( By.name( "diaryTableRow" ) );
        assertEquals( 1, entries.size() );
        tearDown();
    }

    @And ( "^the entry form is not cleared$" )
    public void formCleared () {
        waitForAngular();
    }

    @Then ( "^my daily macro-nutrient totals are calculated for the <servings> servings I ate$" )
    public void totalsAreCalculated () {
        waitForAngular();
    }
}
