package edu.ncsu.csc.itrust2.models.persistent;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.criterion.Criterion;

import edu.ncsu.csc.itrust2.forms.patient.DiaryEntryForm;
import edu.ncsu.csc.itrust2.models.enums.Meal;
import edu.ncsu.csc.itrust2.models.enums.Role;

/**
 * Diary Entry object. Only patients can create and edit diary entries,
 * HCP's can only view patient diary entries.
 * 
 * @author Jonathan Oh
 * @author shuzheng Wang
 *
 */
@Entity
@Table ( name = "DiaryEntry" )
public class DiaryEntry extends DomainObject<DiaryEntry> {

    /**
     * Retrieve a List of all foodDiaryEntries from the database. Can be
     * filtered further once retrieved. Will return the foodDiaryEntries sorted
     * by date.
     *
     * @return A List of all foodDiaryEntries saved in the database
     */
    @SuppressWarnings ( "unchecked" )
    public static List<DiaryEntry> getFoodDiaryEntries () {
        final List<DiaryEntry> entries = (List<DiaryEntry>) getAll( DiaryEntry.class );
        entries.sort( ( x1, x2 ) -> x1.getDate().compareTo( x2.getDate() ) );
        return entries;
    }

    /**
     * Retrieves all food diary entries for the Patient provided.
     *
     * @param patientName
     *            Name of the patient
     * @return All of their food diary entries
     */
    public static List<DiaryEntry> getFoodDiaryEntriesForPatient ( final String patientName ) {
        return getWhere( eqList( "patient", User.getByNameAndRole( patientName, Role.ROLE_PATIENT ) ) );
    }

    /**
     * Retrieves all food diary entries for the HCP provided.
     *
     * @param patientName
     *            Name of the patient
     * @return All of their food diary entries
     */
    public static List<DiaryEntry> getFoodDiaryEntriesForHCP ( final String patientName ) {
        return getWhere( eqList( "patient", User.getByNameAndRole( patientName, Role.ROLE_PATIENT ) ) );
    }
    /**
     * Retrieve an diary entry by its numerical ID.
     *
     * @param id
     *            The ID (as assigned by the DB) of the diary entry
     * @return The diary entry, if found, or null if not found.
     */
    public static DiaryEntry getById ( final Long id ) {
        try {
            return getWhere( eqList( ID, id ) ).get( 0 );
        }
        catch ( final Exception e ) {
            return null;
        }
    }

    /**
     * Retrieve a List of food diary entries that meets the given where clause.
     * Clause is expected to be valid SQL.
     *
     * @param where
     *            List of Criterion to and together and search for records by
     * @return The matching list
     */
    @SuppressWarnings ( "unchecked" )
    private static List<DiaryEntry> getWhere ( final List<Criterion> where ) {
        return (List<DiaryEntry>) getWhere( DiaryEntry.class, where );
    }

    /** type of meal: Breakfast, Lunch, Dinner. can have multiple. */
    @NotNull
    @Enumerated ( EnumType.STRING )

    private Meal meal;

	/** date the entry was logged. mm/dd/yyyy */
    @NotNull
    private Calendar date ;
	/** name of the entry */
    @NotNull
	private String name;
	/** number of servings */
    @NotNull
	private int servings;
	/** number of calories */
    @NotNull
	private int calories;
	/** grams of fat per serving */
    @NotNull
	private int fatGrams;
	/** milligrams of sodium per serving */
    @NotNull
	private int sodium;
	/** grams of carbs per serving */
    @NotNull
	private int carbs;
	/** grams of sugars per serving */
    @NotNull
	private int sugars;
	/** grams of fiber per serving */
    @NotNull
	private int fibers;
	/** grams of protein per serving */
    @NotNull    
	private int protein;

	/** Generic constructor to create a quick entry with
	 * 0 for the fields. this should never be used
	 */
	public DiaryEntry() {
		this.date = Calendar.getInstance();
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
		this.patient = new User();
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
	public DiaryEntry(Calendar date, Meal meal, String name, int servings,
			int calories, int fatGrams, int sodium, int carbs, int sugars,
			int fibers, int protein, User patient) {
		this.date = date;
		this.meal = meal;
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
		this.patient = patient;
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
		setPatient( User.getByNameAndRole( form.getEntryPatient(), Role.ROLE_PATIENT ) );
	}


    /**
     * The Patient who is associated with this entry form
     */
    @NotNull
    @ManyToOne
    @JoinColumn ( name = "patient_id", columnDefinition = "varchar(100)" )
    private User patient;

    /**
     * Retrieves the User object for the Patient for the diary entry form
     *
     * @return The associated Patient
     */
    public User getPatient () {
        return patient;
    }

    /**
     * Sets the Patient for the diary entry form
     *
     * @param patient
     *            The User object for the Patient on the entry
     */
    public void setPatient ( final User patient ) {
        this.patient = patient;
    }

    /**
     * Gets date of diary entry.
     *
     * @return date of entry
     */
    public Calendar getDate () {
        return this.date;
    }

    /**
     * Gets type of meal (breakfast/lunch/dinner)
     *
     * @return type of meal
     */
    public Meal getMeal () {
        return this.meal;
    }

    /**
     * Gets name of entry
     *
     * @return name of entry
     */
    public String getName () {
        return this.name;
    }

    /**
     * Gets number of servings in entry.
     *
     * @return number of servings.
     */
    public int getServings () {
        return this.servings;
    }

    /**
     * Gets grams of calories in meal.
     *
     * @return grams of calories
     */
    public int getCalories () {
        return this.calories;
    }

    /**
     * Gets grams of fat in meal.
     *
     * @return grams of fat
     */
    public int getFatGrams () {
        return this.fatGrams;
    }

    /**
     * Gets milligrams of sodium in meal.
     *
     * @return milligrams of sodium.
     */
    public int getSodium () {
        return this.sodium;
    }

    /**
     * Gets grams of carbs per serving
     *
     * @return grams of carbs
     */
    public int getCarbs () {
        return this.carbs;
    }

    /**
     * Gets grams of sugars per serving
     *
     * @return grams of sugar
     */
    public int getSugars () {
        return this.sugars;
    }

    /**
     * Gets grams of fiber per serving
     *
     * @return grams of fiber
     */
    public int getFibers () {
        return this.fibers;
    }

    /**
     * Gets grams of protein per serving
     *
     * @return grams of protein
     */
    public int getProtein () {
        return this.protein;
    }

    /**
     * Sets date of the entry
     *
     * @param date
     *            date of entry
     */
    public void setDate ( final Calendar date ) {
        this.date = date;
    }

    /**
     * Sets the meal of entry
     *
     * @param meal
     *            type of meal (breakfast, lunch, dinner)
     */
    public void setMeal ( final Meal meal ) {
        this.meal = meal;
    }

    /**
     * Sets the name of entry
     *
     * @param name
     *            name of entry
     */
    public void setName ( final String name ) {
        this.name = name;
    }

    /**
     * Sets the number of servings in the entry
     *
     * @param servings
     *            servings in entry
     */
    public void setServings ( final int servings ) {
        this.servings = servings;
    }

    /**
     * Sets the number of calories in the entry
     *
     * @param calories
     *            calories in entry
     */
    public void setCalories ( final int calories ) {
        this.calories = calories;
    }

    /**
     * Sets the number of grams of fat in the entry
     *
     * @param fatGrams
     *            grams of fat in entry
     */
    public void setFatGrams ( final int fatGrams ) {
        this.fatGrams = fatGrams;
    }

    /**
     * Sets the number of MILLIgrams of sodium in the entry
     *
     * @param sodium
     *            milligrams of sodium
     */
    public void setSodium ( final int sodium ) {
        this.sodium = sodium;
    }

    /**
     * Sets the number of carbs in the entry
     *
     * @param carbs
     *            number of carbs in entry
     */
    public void setCarbs ( final int carbs ) {
        this.carbs = carbs;
    }

    /**
     * Sets the number of grams of sugar in entry
     *
     * @param sugars
     *            grams of sugar in entry
     */
    public void setSugars ( final int sugars ) {
        this.sugars = sugars;
    }

    /**
     * Sets the number of grams of fiber in entry
     *
     * @param fibers
     *            grams of fiber in entry
     */
    public void setFibers ( final int fibers ) {
        this.fibers = fibers;
    }

    /**
     * Sets the number of grams of protein in entry
     *
     * @param protein
     *            grams of protein in entry
     */
    public void setProtein ( final int protein ) {
        this.protein = protein;
    }

    /**
     * ID of the Diary entry form
     */
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private Long id;

    /**
     * Sets the ID of the entry form
     *
     * @param id
     *            The new ID of the entry form. For Hibernate.
     */
    public void setId ( final Long id ) {
        this.id = id;
    }

    @Override
    public Long getId () {
        return id;
    }
}
