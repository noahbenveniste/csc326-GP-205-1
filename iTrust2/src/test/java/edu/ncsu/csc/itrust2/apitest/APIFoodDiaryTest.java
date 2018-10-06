package edu.ncsu.csc.itrust2.apitest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.ncsu.csc.itrust2.config.RootConfiguration;
import edu.ncsu.csc.itrust2.forms.admin.UserForm;
import edu.ncsu.csc.itrust2.forms.patient.DiaryEntryForm;
import edu.ncsu.csc.itrust2.models.enums.Meal;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.mvc.config.WebMvcConfiguration;

/**
 * Test for the API functionality for interacting with food diary 
 *
 * @author Shuzheng Wang
 *
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { RootConfiguration.class, WebMvcConfiguration.class } )
@WebAppConfiguration
public class APIFoodDiaryTest {

    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    /**
     * Sets up tests
     */
    @Before
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();
    }
    
    /**
     * Tests APi Food dairy controller
     *
     * @throws Exception
     */
    @Test
    public void testFoodDiaryControllerAPI () throws Exception {

        /*
         * Create a HCP and a Patient to use. If they already exist, this will
         * do nothing
         */
        final UserForm hcp = new UserForm( "hcp", "123456", Role.ROLE_HCP, 1 );
        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( hcp ) ) );

        final UserForm patient = new UserForm( "patient", "123456", Role.ROLE_PATIENT, 1 );
        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( patient ) ) );

        mvc.perform( delete( "/api/v1/appointmentrequests" ) );

        final DiaryEntryForm entry = new DiaryEntryForm();
        entry.setEntryDate( Calendar.getInstance()); entry.setEntryMeal(Meal.BREAKFAST); entry.setEntryName("name");
		entry.setEntryServings(1); entry.setEntryCalories(1); entry.setEntryFatGrams(1); entry.setEntrySodium(1);
		entry.setEntryCarbs(1); entry.setEntrySugars(1); entry.setEntryFibers(1); entry.setEntryProtein(1);
		entry.setEntryPatient("patient");

        /* Create the request */
        mvc.perform( post( "/api/v1/diaryentries" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( entry ) ) );

        mvc.perform( get( "/api/v1/diaryentries" ) )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) );


    }
}
