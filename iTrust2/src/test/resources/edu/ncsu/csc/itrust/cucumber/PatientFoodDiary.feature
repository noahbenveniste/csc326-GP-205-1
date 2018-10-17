Feature: Create a food diary entry
	As a patient
	I want to add a food diary entry
	So I can log my daily macro-nutrient totals and monitor my eating habits.

Scenario Outline: Submit valid diary entry
Given there are no diary entries for patient
When I log in as a patient 
When I navigate to the diary entry page
When I submit an entry on <date> that I ate <servings> servings of <food> for <meal> that had: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein.
Then the entry is added to my food diary

Examples:
  | date       | food  | meal   | servings | calories | fat | sodium | carbs | sugar | fiber | protein | 
  | 01/01/2018 | bagel | LUNCH  | 1        | 300      | 10  | 200    | 30    | 5     | 12    | 6       | 
  | 02/14/2018 | apple | SNACK  | 2        | 250      | 0   | 150    | 12    | 8     | 8     | 0       | 
  | 04/01/2018 | bread | DINNER | 1        | 100      | 1   | 180    | 20    | 2     | 10    | 2       |

Scenario Outline: Submit invalid diary entry
Given there are no diary entries for patient
When I log in as a patient 
When I navigate to the diary entry page
When I submit an entry on <date> that I ate <servings> servings of <food> for <meal> that had: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein.
Then the entry is not added to my food diary
And the entry form is not cleared

Examples:
  | date       | food  | meal   | servings | calories | fat | sodium | carbs | sugar | fiber | protein | 
  | 01/01/2018 | bagel | LUNCH  | -1       | 300      | 10  | 200    | 30    | 5     | 12    | 6       | 
  | 01/01/2018 | bagel | LUNCH  | 1        | -10      | 10  | 200    | 30    | 5     | 12    | 6       | 
  | 01/01/2018 | bagel | LUNCH  | 1        | 300      | -1  | -100   | 30    | 5     | 12    | 6       | 
  | 01/01/2018 | bagel | LUNCH  | 1        | 300      | 10  | 200    | -30   | 5     | 12    | 6       | 
  | 01/01/2018 | bagel | LUNCH  | 1        | 300      | 10  | 200    | 30    | -5    | 12    | 6       | 
  | 01/01/2018 | bagel | LUNCH  | 1        | 300      | 10  | 200    | 30    | 5     | -12   | 6       |  
  | 01/01/2018 | bagel | LUNCH  | 1        | 300      | 10  | 200    | 30    | 5     | 12    | -6      |   

Scenario Outline: view my daily totals
Given there are no diary entries for patient
When I log in as a patient 
When I navigate to the diary entry page
When I submit an entry on 1/01/2018 that I ate <servings> servings of Apple for SNACK that had: <calories> calories, <fat>g fat, <sodium>mg sodium, <carbs>g carbs, <sugar>g sugar, <fiber>g fiber and <protein>g protein.
Then the daily macro-nutrient totals for <servings> servings are:  <caloriesTotal> calories, <fatTotal>g fat, <sodiumTotal>mg sodium, <carbsTotal>g carbs, <sugarTotal>g sugar, <fiberTotal>g fiber and <proteinTotal>g protein.

Examples:
  | servings | calories | fat | sodium | carbs | sugar | fiber | protein | caloriesTotal | fatTotal | sodiumTotal | carbsTotal | sugarTotal | fiberTotal | proteinTotal | 
  | 2        | 300      | 10  | 200    | 30    | 5     | 12    | 6       | 600           | 20       | 400         | 60         | 10         | 24         | 12           | 
  | 3        | 250      | 0   | 150    | 12    | 8     | 8     | 0       | 750           | 10       | 450         | 36         | 24         | 24         | 0            |  
  | 4        | 100      | 1   | 180    | 20    | 2     | 10    | 2       | 400           | 8        | 720         | 80         | 8          | 40         | 8            | 
