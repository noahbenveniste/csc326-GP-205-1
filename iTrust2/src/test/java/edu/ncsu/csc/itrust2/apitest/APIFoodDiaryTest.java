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
import edu.ncsu.csc.itrust2.models.persistent.DiaryEntry;
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
     * Tests APi Food dairy controller handle the bad request
     *
     * @throws Exception
     */
    @Test
    public void testFoodDiaryControllerAPIBadRequest () throws Exception {

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

        mvc.perform( delete( "/api/v1/diaryentries" ) );

        final DiaryEntryForm entry = new DiaryEntryForm();

        /* Create the request */
        mvc.perform( post( "/api/v1/diaryentries" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( entry ) ) ).andExpect( status().isBadRequest() );
        
        mvc.perform( delete( "/api/v1/diaryentries" ) );
        
        
    }
    /**
     * Tests adding diaries
     * @throws Exception
     */
    @Test
    public void testAddingEntries () throws Exception {
    	final UserForm hcp = new UserForm( "hcp", "123456", Role.ROLE_HCP, 1 );
        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( hcp ) ) );

        final UserForm patient = new UserForm( "patient", "123456", Role.ROLE_PATIENT, 1 );
        mvc.perform( post( "/api/v1/users" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( patient ) ) );

        mvc.perform( delete( "/api/v1/diaryentries" ) );
        
        final DiaryEntryForm entry = new DiaryEntryForm();
        entry.setDate( Calendar.getInstance() );
        entry.setMeal( Meal.BREAKFAST );
        entry.setName( "name" );
        entry.setServings( 1 );
        entry.setCalories( 1 );
        entry.setFatGrams( 1 );
        entry.setSodium( 1 );
        entry.setCarbs( 1 );
        entry.setSugars( 1 );
        entry.setFibers( 1 );
        entry.setProtein( 1 );
        entry.setPatient( "patient" );
        
        mvc.perform( post( "/api/v1/diaryentries" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( entry ) ) ).andExpect(status().isOk());
    }

    /**
     * Tests APi Food diary controller
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

        mvc.perform( delete( "/api/v1/diaryentries" ) );

        final DiaryEntryForm entry = new DiaryEntryForm();
        entry.setDate( Calendar.getInstance() );
        entry.setMeal( Meal.BREAKFAST );
        entry.setName( "name" );
        entry.setServings( 1 );
        entry.setCalories( 1 );
        entry.setFatGrams( 1 );
        entry.setSodium( 1 );
        entry.setCarbs( 1 );
        entry.setSugars( 1 );
        entry.setFibers( 1 );
        entry.setProtein( 1 );
        entry.setPatient( "patient" );

        /* Create the request */
        mvc.perform( post( "/api/v1/diaryentries" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( entry ) ) );

        mvc.perform( get( "/api/v1/diaryentries" ) )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) );

        mvc.perform( get( "/api/v1/diaryentries/" + patient.getUsername() ) )
                .andExpect( content().contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) );

        mvc.perform( get( "/api/v1/diaryentries/" + "someBody" ) );

        mvc.perform( post( "/api/v1/diaryentries" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( entry) ) ).andExpect(status().isBadRequest());
        
        
        // /*
        // * We need the ID of the diary entry that actually got _saved_
        // * when calling the API above. This will get it
        // */
        // final Long id = DiaryEntry.getFoodDiaryEntriesForPatient(
        // patient.getUsername() ).get( 0 ).getId();
        //
        // mvc.perform( get( "/api/v1/diaryentries/" + id ) ).andExpect(
        // status().isOk() )
        // .andExpect( content().contentType(
        // MediaType.APPLICATION_JSON_UTF8_VALUE ) );

    }
}
