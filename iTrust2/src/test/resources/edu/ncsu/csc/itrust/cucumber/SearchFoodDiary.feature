Feature: Search and view patient's food diaries
	As an HCP
	I want to view patients food diaries
	So can monitor their eating habits and guide them towards their goals.

Scenario Outline: View patient's diary entries
Given <pt> has a diary entry on <date> for <servings> servings of <food> for <meal> that has: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein
When I log into iTrust2 as a: HCP
When I search for <pt>'s diary entries
Then I see a diary entry on <date> that for <servings> servings of <food> for <meal> that had: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein

Examples:
  | pt       | date       | food  | meal   | servings | calories | fat | sodium | carbs | sugar | fiber | protein | 
  | patient  | 01/01/2018 | bagel | LUNCH  | 1        | 300      | 10  | 200    | 30    | 5     | 12    | 6       | 
  | patient  | 02/14/2018 | apple | SNACK  | 2        | 250      | 0   | 150    | 12    | 8     | 8     | 0       | 
  | patient  | 04/01/2018 | bread | DINNER | 1        | 100      | 1   | 180    | 20    | 2     | 10    | 2       |
  
Scenario Outline: View patient's daily totals
Given <pt> has a diary entry on 1/01/2018 for <servings> servings of Apple for SNACK that has: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein
When I log into iTrust2 as a: HCP
When I search for <pt>'s diary entries
Then the daily macro-nutrient totals are: <caloriesTotal> calories, <fatTotal>g fat, <sodiumTotal>mg sodium, <carbsTotal>g carbs, <sugarTotal>g sugar, <fiberTotal>g fiber and <proteinTotal>g protein

Examples:
  | pt       | servings | calories | fat | sodium | carbs | sugar | fiber | protein | caloriesTotal | fatTotal | sodiumTotal | carbsTotal | sugarTotal | fiberTotal | proteinTotal | 
  | patient  | 2        | 300      | 10  | 200    | 30    | 5     | 12    | 6       | 600           | 20       | 400         | 60         | 10         | 24         | 12           | 
  | patient  | 3        | 250      | 0   | 150    | 12    | 8     | 8     | 0       | 750           | 0        | 450         | 36         | 24         | 24         | 0            |  
  | patient  | 4        | 100      | 1   | 180    | 20    | 2     | 10    | 2       | 400           | 4        | 720         | 80         | 8          | 40         | 8            | 