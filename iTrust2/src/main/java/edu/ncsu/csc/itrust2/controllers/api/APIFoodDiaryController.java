
package edu.ncsu.csc.itrust2.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.itrust2.forms.patient.DiaryEntryForm;
import edu.ncsu.csc.itrust2.models.enums.TransactionType;
import edu.ncsu.csc.itrust2.models.persistent.DiaryEntry;
import edu.ncsu.csc.itrust2.models.persistent.DomainObject;
import edu.ncsu.csc.itrust2.models.persistent.Patient;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;

/**
 * Class that provides REST API endpoints for the food diary entry model. In all
 * requests made to this controller, the {id} provided is a numeric ID that is
 * the primary key of the entry in question
 *
 * @author shuzheng wang
 * @author Jonathan Oh
 *
 */
@RestController
@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class APIFoodDiaryController extends APIController {

    // /**
    // * Retrieves a list of all food diary entries in the database
    // *
    // * @return list of food diary entries
    // */
    // @GetMapping ( BASE_PATH + "/diaryentries" )
    // public List<DiaryEntry> getFoodDiaryEntries () {
    // return DiaryEntry.getFoodDiaryEntries();
    // }

    /**
     * Retrieves the DiaryEntries for a patient wanting to access their
     * DiaryEntries
     *
     * @return list of DiaryEntries for the logged in patient
     */
    @GetMapping ( BASE_PATH + "/diaryentries" )
    public List<DiaryEntry> getFoodDiaryEntriesForPatient () {
        LoggerUtil.log( TransactionType.DIARY_ENTRY_ViEWED, LoggerUtil.currentUser(),
                "patient retrieved diary entries for patient with username " );
        return DiaryEntry.getFoodDiaryEntriesForPatient( LoggerUtil.currentUser() ).stream()
                .collect( Collectors.toList() );
    }

    /**
     * Retrieves the DiaryEntries for a HCP wanting to access a patient's
     * DiaryEntries
     *
     * @param id
     *            name of the patient to get the Diary Entries of
     * @return list of DiaryEntries for the patient with the given name
     */
    @SuppressWarnings ( "all" )
    @GetMapping ( BASE_PATH + "/diaryentries/{patient_id}" )
    public List<DiaryEntry> getFoodDiaryEntriesForHCP ( @PathVariable ( "patient_id" ) final String id ) {
        final Patient patient = new Patient( id );
        if ( patient == null ) {
            return null;
        }
        LoggerUtil.log( TransactionType.DIARY_ENTRY_VIEWEDBYHCP, LoggerUtil.currentUser(), id,
                "HCP retrieved diary entries for patient with username " + id );
        return DiaryEntry.getFoodDiaryEntriesForHCP( id );
    }

    // /**
    // * Retrieves the Diary entry specified by the ID provided
    // *
    // * @param id
    // * The (numeric) ID of the diary entry desired
    // * @return The diary entry corresponding to the ID provided or
    // * HttpStatus.NOT_FOUND if no such diary entry could be found
    // */
    // @GetMapping ( BASE_PATH + "/diaryentries/{id}" )
    // public ResponseEntity getDiaryEntryByID ( @PathVariable ( "id" ) final
    // Long id ) {
    // final DiaryEntry entry = DiaryEntry.getById(id);
    // if ( null != entry ) {
    // LoggerUtil.log( TransactionType.DIARY_ENTRY_ViEWED, entry.getPatient() );
    // }
    // return null == entry
    // ? new ResponseEntity( errorResponse( "No food dairy found for id " + id
    // ),
    // HttpStatus.NOT_FOUND )
    // : new ResponseEntity( entry, HttpStatus.OK );
    // }

    /**
     * Deletes _all_ of the Diary entries stored in the system. Exercise caution
     * before calling this method.
     *
     * @return reponse
     */
    @DeleteMapping ( BASE_PATH + "/diaryentries" )
    public ResponseEntity deleteAllFoodDiaries () {
        try {
            DomainObject.deleteAll( DiaryEntry.class );
            return new ResponseEntity( successResponse( "Successfully deleted all FoodDiaryEntry" ), HttpStatus.OK );
        }
        catch ( final Exception e ) {
            return new ResponseEntity( errorResponse( "Could not delete one or more DiaryEntry " + e.getMessage() ),
                    HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Creates an food Dairy from the RequestBody provided. Record is
     * automatically saved in the database.
     *
     * @param entryF
     *            The DiaryEntryForm to be parsed into an DiaryEntry and stored
     * @return The parsed and validated Diary Entry created from the Form
     *         provided, HttpStatus.CONFLICT if a entry already exists with the
     *         ID of the provided entry, or HttpStatus.BAD_REQUEST if another
     *         error occurred while parsing or saving the entry provided
     */
    @PostMapping ( BASE_PATH + "/diaryentries" )
    public ResponseEntity createDiaryEntry ( @RequestBody final DiaryEntryForm entryF ) {
        try {
            // Gets the currently logged in user
            // entryF.setPatient( LoggerUtil.currentUser() );
            entryF.setPatient( "patient" );
            final DiaryEntry entry = new DiaryEntry( entryF );
            if ( null != DiaryEntry.getById( entry.getId() ) ) {
                return new ResponseEntity(
                        errorResponse( "Diary Entry with the id " + entry.getId() + " already exists" ),
                        HttpStatus.CONFLICT );
            }
            entry.save();
            LoggerUtil.log( TransactionType.DIARY_ENTRY_SUBMITTED, entry.getPatient() );
            return new ResponseEntity( entry, HttpStatus.OK );

        }
        catch ( final Exception e ) {
            return new ResponseEntity( errorResponse(
                    "Error occured while validating or saving " + entryF.toString() + " because of " + e.getMessage() ),
                    HttpStatus.BAD_REQUEST );
        }

    }

}
