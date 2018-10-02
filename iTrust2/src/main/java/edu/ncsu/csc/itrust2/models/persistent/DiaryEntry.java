package edu.ncsu.csc.itrust2.models.persistent;

import java.util.Calendar;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import edu.ncsu.csc.itrust2.forms.patient.DiaryEntryForm;
import edu.ncsu.csc.itrust2.models.enums.Meal;

/**
 * Diary Entry object. Only patients can create and edit diary entries,
 * HCP's can only view patient diary entries.
 * 
 * @author Jonathan Oh
 *
 */
public class DiaryEntry {
	

	/** type of meal: Breakfast, Lunch, Dinner. can have multiple. */
    @NotNull
    @Enumerated ( EnumType.STRING )
    private Meal meal;

	/** date the entry was logged. mm/dd/yyyy */
    private Calendar date ;
	/** name of the entry */
	private String name;
	/** number of servings */
	private int servings;
	/** number of calories */
	private int calories;
	/** grams of fat per serving */
	private int fatGrams;
	/** milligrams of sodium per serving */
	private int sodium;
	/** grams of carbs per serving */
	private int carbs;
	/** grams of sugars per serving */
	private int sugars;
	/** grams of fiber per serving */ 
	private int fibers;
	/** grams of protein per serving */
	private int protein;

	/** Generic constructor to create a quick entry with
	 * 0 for the fields.
	 */
	public DiaryEntry() {
		this.date = Calendar.getInstance();;
		this.meal = Meal.BREAKFAST;
		this.name = "name";
		this.servings = 0;
		this.calories = 0;
		this.fatGrams = 0;
		this.sodium = 0;
		this.carbs = 0;
		this.sugars = 0;
		this.fibers = 0;
		this.protein = 0;
	}

	/**
	 * Constructor to create a diary entry from provided parameters.
	 * 
	 * @param date date of meal
	 * @param meal type of meal
	 * @param name name of entry
	 * @param servings number of servings/serving
	 * @param calories number of calories/serving
	 * @param fatGrams grams of fat/serving
	 * @param sodium MILLIGRAMS of sodium/serving
	 * @param carbs grams of carbs/serving
	 * @param sugars grams of sugars/serving
	 * @param fibers grams of fibers/serving
	 * @param protein grams of protein/serving
	 */
	public DiaryEntry(String date, String meal, String name, int servings,
			int calories, int fatGrams, int sodium, int carbs, int sugars,
			int fibers, int protein) {
		this.meal = Meal.BREAKFAST;
		this.name = "name";
		this.name = name;
		this.servings = servings;
		this.calories = calories;
		this.fatGrams = fatGrams;
		this.sodium = sodium;
		this.carbs = carbs;
		this.sugars = sugars;
		this.fibers = fibers;
		this.protein = protein;
	}

	/**
	 * Constructor to create a DiaryEntry from a DiaryEntryForm
	 * @param form DiaryEntryForm object to create a DiaryEntry from
	 */
	public DiaryEntry(DiaryEntryForm form) {
		this.date = form.getEntryDate();
		this.meal = form.getEntryMeal();
		this.name = form.getEntryName();
		this.servings = form.getEntryServings();
		this.calories = form.getEntryCalories();
		this.fatGrams = form.getEntryFatGrams();
		this.sodium = form.getEntrySodium();
		this.carbs = form.getEntryCarbs();
		this.sugars = form.getEntrySugars();
		this.fibers = form.getEntryFibers();
		this.protein = form.getEntryProtein();
	}

	/**
	 * Gets date of diary entry.
	 * @return date of entry
	 */
	public Calendar getDate() {
		return this.date;
	}

	/**
	 * Gets type of meal (breakfast/lunch/dinner)
	 * @return type of meal
	 */
	public Meal getMeal() {
		return this.meal;
	}

	/**
	 * Gets name of entry
	 * @return name of entry
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets number of servings in entry.
	 * @return number of servings.
	 */
	public int getServings() {
		return this.servings;
	}

	/**
	 * Gets grams of calories in meal.
	 * @return grams of calories
	 */
	public int getCalories() {
		return this.calories;
	}

	/**
	 * Gets grams of fat in meal.
	 * @return grams of fat
	 */
	public int getFatGrams() {
		return this.fatGrams;
	}

	/**
	 * Gets milligrams of sodium in meal.
	 * @return milligrams of sodium.
	 */
	public int getSodium() {
		return this.sodium;
	}

	/**
	 * Gets grams of carbs per serving
	 * @return grams of carbs
	 */
	public int getCarbs() {
		return this.carbs;
	}

	/**
	 * Gets grams of sugars per serving
	 * @return grams of sugar
	 */
	public int getSugars() {
		return this.sugars;
	}

	/**
	 * Gets grams of fiber per serving
	 * @return grams of fiber
	 */
	public int getFibers() {
		return this.fibers;
	}

	/**
	 * Gets grams of protein per serving
	 * @return grams of protein
	 */
	public int getProtein() {
		return this.protein;
	}

	/**
	 * Sets date of the entry
	 * @param date date of entry
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Sets the meal of entry
	 * @param meal type of meal (breakfast, lunch, dinner)
	 */
	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	/**
	 * Sets the name of entry 
	 * @param name name of entry
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the number of servings in the entry
	 * @param servings servings in entry
	 */
	public void setServings(int servings) {
		this.servings = servings;
	}

	/**
	 * Sets the number of calories in the entry
	 * @param calories calories in entry
	 */
	public void setCalories(int calories) {
		this.calories = calories;
	}

	/**
	 * Sets the number of grams of fat in the entry
	 * @param fatGrams grams of fat in entry
	 */
	public void setFatGrams(int fatGrams) {
		this.fatGrams = fatGrams;
	}

	/**
	 * Sets the number of MILLIgrams of sodium in the entry
	 * @param sodium milligrams of sodium
	 */
	public void setSodium(int sodium) {
		this.sodium = sodium;
	}

	/**
	 * Sets the number of carbs in the entry
	 * @param carbs number of carbs in entry
	 */
	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	/**
	 * Sets the number of grams of sugar in entry
	 * @param sugars grams of sugar in entry
	 */
	public void setSugars(int sugars) {
		this.sugars = sugars;
	}

	/**
	 * Sets the number of grams of fiber in entry
	 * @param fibers grams of fiber in entry
	 */
	public void setFibers(int fibers) {
		this.fibers = fibers;
	}

	/**
	 * Sets the number of grams of protein in entry
	 * @param protein grams of protein in entry
	 */
	public void setProtein(int protein) {
		this.protein = protein;
	}
}
