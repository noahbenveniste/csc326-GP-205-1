Feature: Search and view patient's food diaries
	As an HCP
	I want to view patients food diaries
	So can monitor their eating habits and guide them towards their goals.

Scenario Outline: View patient's diary entries
Given <pt> has a diary entry on <date> that he ate <servings> servings of <food> for <meal> that had: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein.
When I search for <pt>'s diary entries
Then I see a diary entry on <date> that he ate <servings> servings of <food> for <meal> that had: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein.

Examples:
  | pt       | date       | food  | meal   | servings | calories | fat | sodium | carbs | sugar | fiber | protein | 
  | patient  | 01/01/2018 | bagel | lunch  | 1        | 300      | 10  | 200    | 30    | 5     | 12    | 6       | 
  | patient  | 02/14/2018 | apple | snack  | 2        | 250      | 0   | 150    | 12    | 8     | 8     | 0       | 
  | patient  | 04/01/2018 | bread | dinner | 1        | 100      | 1   | 180    | 20    | 2     | 10    | 2       |
  
Scenario Outline: View patient's daily totals
Given I am viewing <pt>'s food diary
When <pt> has a diary entry entry on 1/01/2018 for <servings> servings of Apple for SNACK that had: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein.
Then I can see the daily macro-nutrient totals for the <servings> servings they ate

Examples:
  | pt       | servings | calories | fat | sodium | carbs | sugar | fiber | protein | 
  | patient  | 2        | 300      | 10  | 200    | 30    | 5     | 12    | 6       | 
  | patient  | 3        | 250      | 0   | 150    | 12    | 8     | 8     | 0       | 
  | patient  | 4        | 100      | 1   | 180    | 20    | 2     | 10    | 2       |