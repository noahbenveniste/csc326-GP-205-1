package edu.ncsu.csc.itrust2.unit;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import edu.ncsu.csc.itrust2.forms.patient.DiaryEntryForm;
import edu.ncsu.csc.itrust2.models.enums.Meal;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.persistent.DiaryEntry;
import edu.ncsu.csc.itrust2.models.persistent.User;

public class DiaryEntryTest {

	@Test
	public void testDiaryEntryTest() {
		//the first diary entry
		DiaryEntry entry = new DiaryEntry();
		Long id = 1L;
		Calendar date = Calendar.getInstance();
		entry.setDate(date); entry.setMeal(Meal.BREAKFAST); entry.setName("name");
		entry.setServings(1); entry.setCalories(1); entry.setFatGrams(1); entry.setSodium(1);
		entry.setCarbs(1); entry.setSugars(1); entry.setFibers(1); entry.setProtein(1);
		entry.setId(id); 
		
		//the second diary entry
		DiaryEntry entry2 = new DiaryEntry(date, Meal.BREAKFAST, "name", 1,
			1, 1, 1, 1, 1, 1, 1);
		
		//the third diary entry
		DiaryEntryForm form = new DiaryEntryForm();
		DiaryEntry entry3 = new DiaryEntry(form);
		
		//testing set patient, get patient
		User user = new User("patient", "password", Role.ROLE_PATIENT, 1);
		entry2.setPatient(user);
		assertEquals( "patient", entry2.getPatient().getUsername() );
		
		//tests the date of first entry
		assertEquals(date, entry.getDate()); 
		//tests the meal of first entry
		assertEquals(Meal.BREAKFAST, entry.getMeal());
		//tests the name of first entry
		assertEquals("name", entry.getName());
		//tests the servings of first entry
		assertEquals(1, entry.getServings());
		//tests the calories of first entry
		assertEquals(1, entry.getCalories());
		//tests the fat grams of first entry
		assertEquals(1, entry.getFatGrams());
		//tests the sodium of first entry
		assertEquals(1, entry.getSodium());
		//tests the carbs of first entry
		assertEquals(1, entry.getCarbs());
		//tests the sugars of first entry
		assertEquals(1, entry.getSugars());
		//tests the fibers of first entry
		assertEquals(1, entry.getFibers());
		//tests the protein of first entry
		assertEquals(1, entry.getProtein());
		//tests the id of first entry
		assertEquals(id, entry.getId());
		
	}
}
