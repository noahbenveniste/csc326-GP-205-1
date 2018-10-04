package edu.ncsu.csc.itrust2.models.enums;

/**
 * For keeping track of various types of users that are known to the system.
 * Different users have different functionality.
 *
 * @author Shuzheng Wang
 *
 */
public enum Meal {
	
	/** the meal for breakfast **/
	BREAKFAST ("breakfast"),
	
	/** the meal for lunch **/
	LUNCH (" lunch "),
	
	/** the meal for dinner **/
	DINNER(" dinner "),
	
	/** the meal for snack **/
	SNACK(" snack ");
	
	/** the meal name in string type **/
	private String mealName;
	
	/**
	 * The enum constructor for meal
	 * 
	 * @param mealName
	 */
    private Meal ( final String mealName ) {
        this.mealName = mealName;
    }
    
    /**
     * Gets the meal name
     *
     * @return meal name
     */
    public String mealName () {
        return this.mealName;
    }
	
	
	

}
