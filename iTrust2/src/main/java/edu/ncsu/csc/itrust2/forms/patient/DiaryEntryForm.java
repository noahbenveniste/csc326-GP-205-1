package edu.ncsu.csc.itrust2.forms.patient;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
/** 
 * This is the in-memory object that is used for saving a DiaryEntry form.
 * It is vaildated and converted into an AppointmentRequest object for persistence.  
 * @author shuzheng wang
 *
 */

public class DiaryEntryForm {

	/**
	 * Populate the FoodDairy form from the Dairy entry object
	 * 
	 * @param de
	 * 			The foodDairy form
	 * The diary entry class is missing.
	 */
	public DiaryEntryForm( final DiaryEntry de) {
		final SimpleDateFormat dateTemp = new SimpleDateFormat( "MM/dd/YYYY", Locale.ENGLISH );
		setEntryDate( dateTemp.format( de.getDate().getTime()));
		setEntryMeal( de.getMeal());
		setEntryName( de.getName());
		setEntryServings( de.getServings() );
		setEntryCalories( de.getCalories() );
		setEntryFatGrams( de.getFatGrams() );
		setEntrySodium( de.getSodium() );
		setEntryCarbs( de.getCarbs() );
		setEntrySugars( de.getSugars() );
		setEntryFibers( de.getFibers() );
		setEntryProtein( de.getProtein() );
	}
	/** the Entry date **/
	private String entryDate;
	
	/** the Entry meal **/
	private String entryMeal;
	
	/** the Entry name **/
	private String entryName;
	
	/** the Entry Servings */
	private int entryServings;
	
	/** the Entry calories **/
	private int entryCalories;
	
	/** the Entry fat grams **/
	private int entryFatGrams;
	
	/** the Entry sodium **/
	private int entrySodium;
	
	/**
	 * Don't use this one. For Hibernate/Thymelaef
	 */
	public DiaryEntryForm() {
		
	}

	/**
	 * Get the Entry date of the form
	 * 
	 * @return the entry date of the form
	 */
	public String getEntryDate() {
		return entryDate;
	}
	
	/**
	 * Get the Entry date of the form
	 * 
	 * @param entryDate the entry date of the form
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	/**
	 * Get the entry meal of the form
	 * 
	 * @return the entry meal of the form
	 */
	public String getEntryMeal() {
		return entryMeal;
	}
	
	/**
	 * set the entry meal of the form
	 * 
	 * @param entryMeal the entry meal of the form
	 */
	public void setEntryMeal(String entryMeal) {
		this.entryMeal = entryMeal;
	}
	
	/**
	 * get the entry name of the form
	 * 
	 * @return entry name of the form
	 */
	public String getEntryName() {
		return entryName;
	}
	
	/**
	 * set the entry name of the form
	 * 
	 * @param entryName the entry name of the form
	 */
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	
	/**
	 * get the entry servings form the form
	 * 
	 * @return the entry serving of the form
	 */
	public int getEntryServings() {
		return entryServings;
	}
	
	/**
	 * set the entry servings form the form
	 * 
	 * @param entryServings the entry serving of the form
	 */
	public void setEntryServings(int entryServings) {
		this.entryServings = entryServings;
	}
	
	/**
	 * get the entry calories from the form
	 * 
	 * @return the entry calories of the form
	 */
	public int getEntryCalories() {
		return entryCalories;
	}
	
	/**
	 * set the entry calories of the form
	 * 
	 * @param entryCalories the entry calories of the form
	 */
	public void setEntryCalories(int entryCalories) {
		this.entryCalories = entryCalories;
	}
	
	/**
	 * get the entry Fat grams of the form
	 * 
	 * @return the entry fat grams of the form
	 */
	public int getEntryFatGrams() {
		return entryFatGrams;
	}
	
	/**
	 * set the entry fat grams of the form
	 * 
	 * @param entryFatGrams the entry fat grams of the form
	 */
	public void setEntryFatGrams(int entryFatGrams) {
		this.entryFatGrams = entryFatGrams;
	}
	
	/**
	 * get the entry sodium of the form
	 * 
	 * @return the entry sodium of the form
	 */
	public int getEntrySodium() {
		return entrySodium;
	}
	
	/**
	 * set the entry sodium of form
	 * 
	 * @param entrySodium the entry sodium of the form
	 */
	public void setEntrySodium(int entrySodium) {
		this.entrySodium = entrySodium;
	}
	
	
}
