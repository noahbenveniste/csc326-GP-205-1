package edu.ncsu.csc.itrust2.forms.patient;

import java.util.Calendar;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import edu.ncsu.csc.itrust2.models.enums.Meal;
import edu.ncsu.csc.itrust2.models.persistent.DiaryEntry;

/**
 * This is the in-memory object that is used for saving a DiaryEntry form. It is
 * vaildated and converted into an AppointmentRequest object for persistence.
 *
 * @author shuzheng wang
 *
 */

public class DiaryEntryForm {

    /**
     * Populate the FoodDairy form from the Dairy entry object
     *
     * @param de
     *            The foodDairy form The diary entry class is missing.
     */
    public DiaryEntryForm ( final DiaryEntry de ) {
        setDate( de.getDate() );
        setMeal( de.getMeal() );
        setName( de.getName() );
        setServings( de.getServings() );
        setCalories( de.getCalories() );
        setFatGrams( de.getFatGrams() );
        setSodium( de.getSodium() );
        setCarbs( de.getCarbs() );
        setSugars( de.getSugars() );
        setFibers( de.getFibers() );
        setProtein( de.getProtein() );
        setPatient( de.getPatient().getUsername() );
        if ( null != de.getId() ) {
            setId( de.getId().toString() );
        }
    }

    /** The id of the diary entry form */
    private String   id;

    /** The patient of the entry form */
    private String   patient;

    /** the Entry date **/
    private Calendar date;

    /** type of meal: Breakfast, Lunch, Dinner. can have multiple. */
    @NotNull
    @Enumerated ( EnumType.STRING )
    private Meal     meal;

    /** the Entry name **/
    private String   name;

    /** the Entry Servings */
    private int      servings;

    /** the Entry calories **/
    private int      calories;

    /** the Entry fat grams **/
    private int      fatGrams;

    /** the Entry sodium **/
    private int      sodium;

    /** the entry crads **/
    private int      carbs;

    /** the entry sugar **/
    private int      sugars;

    /** the entry Fibers **/
    private int      fibers;

    /** the entry Protein **/
    private int      protein;

    /**
     * Get the id of the appointment request
     *
     * @return the id of the appointment request
     */
    public String getId () {
        return id;
    }

    /**
     * Set the id of the appointment to request
     *
     * @param id
     *            the id of the appointment to request
     */
    public void setId ( final String id ) {
        this.id = id;
    }

    /**
     * Get the patient of the form
     *
     * @return the patient of the form
     */
    public String getPatient () {
        return patient;
    }

    /**
     * Set the patient of the form
     *
     * @param patient
     *            the patient of the form
     */
    public void setPatient ( final String patient ) {
        this.patient = patient;
    }

    /**
     * Don't use this one. For Hibernate/Thymelaef
     */
    public DiaryEntryForm () {

    }

    /**
     * Get the Entry date of the form
     *
     * @return the entry date of the form
     */
    public Calendar getDate () {
        return date;
    }

    /**
     * Get the Entry date of the form
     *
     * @param entryDate
     *            the entry date of the form
     */
    public void setDate ( Calendar date ) {
        this.date = date;
    }

    /**
     * Get the entry meal of the form
     *
     * @return the entry meal of the form
     */
    public Meal getMeal () {
        return meal;
    }

    /**
     * set the entry meal of the form
     *
     * @param meal
     *            the entry meal of the form
     */
    public void setMeal ( Meal meal ) {
        this.meal = meal;
    }

    /**
     * get the entry name of the form
     *
     * @return entry name of the form
     */
    public String getName () {
        return name;
    }

    /**
     * set the entry name of the form
     *
     * @param entryName
     *            the entry name of the form
     */
    public void setName ( String name ) {
        this.name = name;
    }

    /**
     * get the entry servings form the form
     *
     * @return the entry serving of the form
     */
    public int getServings () {
        return servings;
    }

    /**
     * set the entry servings form the form
     *
     * @param entryServings
     *            the entry serving of the form
     */
    public void setServings ( int servings ) {
        this.servings = servings;
    }

    /**
     * get the entry calories from the form
     *
     * @return the entry calories of the form
     */
    public int getCalories () {
        return calories;
    }

    /**
     * set the entry calories of the form
     *
     * @param entryCalories
     *            the entry calories of the form
     */
    public void setCalories ( int calories ) {
        this.calories = calories;
    }

    /**
     * get the entry Fat grams of the form
     *
     * @return the entry fat grams of the form
     */
    public int getFatGrams () {
        return fatGrams;
    }

    /**
     * set the entry fat grams of the form
     *
     * @param entryFatGrams
     *            the entry fat grams of the form
     */
    public void setFatGrams ( int fatGrams ) {
        this.fatGrams = fatGrams;
    }

    /**
     * get the entry sodium of the form
     *
     * @return the entry sodium of the form
     */
    public int getSodium () {
        return sodium;
    }

    /**
     * set the entry sodium of form
     *
     * @param entrySodium
     *            the entry sodium of the form
     */
    public void setSodium ( int sodium ) {
        this.sodium = sodium;
    }

    /**
     * get the entry crabs
     *
     * @return the entry crabs of the form
     */
    public int getCarbs () {
        return carbs;
    }

    /**
     * set the entry carbs
     *
     * @param entryCarbs
     *            the entry carbs
     */
    public void setCarbs ( int carbs ) {
        this.carbs = carbs;
    }

    /**
     * get the entry sugar
     *
     * @return the entry sugars
     */
    public int getSugars () {
        return sugars;
    }

    /**
     * set the entry sugars
     *
     * @param entrySugars
     *            the entry sugars
     */
    public void setSugars ( int sugars ) {
        this.sugars = sugars;
    }

    /**
     * get the entry fibers
     *
     * @return the get entry fibers
     */
    public int getFibers () {
        return fibers;
    }

    /**
     * the set entry fibers
     *
     * @param entryFibers
     *            the entry fibers
     */
    public void setFibers ( int fibers ) {
        this.fibers = fibers;
    }

    /**
     * set the entry proteins
     *
     * @return the entry protein
     *
     */
    public int getProtein () {
        return protein;
    }

    /**
     * set the entry protein
     *
     * @param entryProtein
     *            the entry protein
     *
     */
    public void setProtein ( int protein ) {
        this.protein = protein;
    }

}
