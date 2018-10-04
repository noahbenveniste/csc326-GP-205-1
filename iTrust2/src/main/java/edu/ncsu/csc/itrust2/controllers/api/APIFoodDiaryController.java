package edu.ncsu.csc.itrust2.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.itrust2.models.persistent.DiaryEntry;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;

/**
 * Class that provides REST API endpoints for the food diary entry model. In
 * all requests made to this controller, the {id} provided is a numeric ID that
 * is the primary key of the entry in question 
 *
 * @author shuzheng wang
 * @author Jonathan Oh
 *
 */
@RestController 
@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class APIFoodDiaryController extends APIController {

    /**
     * Retrieves a list of all food diary entries in the database
     *
     * @return list of food diary entries
     */
    @GetMapping ( BASE_PATH + "/diaryentries" )
    public List<DiaryEntry> getFoodDiaryEntries () {
        return DiaryEntry.getFoodDiaryEntries();
    }

    /**
     * Retrieves the DiaryEntries for a patient wanting to access their DiaryEntries
     *
     * @return list of DiaryEntries for the logged in patient
     */
    @GetMapping ( BASE_PATH + "/diaryentries" )
    public List<DiaryEntry> getFoodDiaryEntriesForPatient () {
        return DiaryEntry.getDiaryEntriesByName( LoggerUtil.currentUser() ).stream()
        		.collect( Collectors.toList() );
    }

    /**
     * Retrieves the DiaryEntries for a HCP wanting to access a patient's DiaryEntries
     * @param patientName name of the patient to get the Diary Entries of
     * @return list of DiaryEntries for the patient with the given name
     */
    @GetMapping ( BASE_PATH + "/diaryentries/{name}" )
    public List<DiaryEntry> getFoodDiaryEntriesForHCP(@PathVariable final String name) {
    	return DiaryEntry.getDiaryEntriesByName(name);
    }
    

}
