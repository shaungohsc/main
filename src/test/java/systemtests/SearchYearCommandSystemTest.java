package systemtests;

import static org.junit.Assert.assertFalse;
import static seedu.travel.commons.core.Messages.MESSAGE_PLACES_LISTED_OVERVIEW;
import static seedu.travel.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.travel.testutil.TypicalPlaces.ALICE;
import static seedu.travel.testutil.TypicalPlaces.BENSON;
import static seedu.travel.testutil.TypicalPlaces.CARL;
import static seedu.travel.testutil.TypicalPlaces.DANIEL;
import static seedu.travel.testutil.TypicalPlaces.GEORGE;
import static seedu.travel.testutil.TypicalPlaces.KEYWORD_MATCHING_2016;

import org.junit.Test;

import seedu.travel.commons.core.index.Index;
import seedu.travel.logic.commands.DeleteCommand;
import seedu.travel.logic.commands.RedoCommand;
import seedu.travel.logic.commands.SearchYearCommand;
import seedu.travel.logic.commands.UndoCommand;
import seedu.travel.model.Model;

public class SearchYearCommandSystemTest extends TravelBuddySystemTest {

    @Test
    public void find() {
        /* Case: find multiple places in travel buddy, command with leading spaces and trailing spaces
         * -> 3 places found
         */
        String command = "   " + SearchYearCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_2016 + "   ";
        Model expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, ALICE, CARL, DANIEL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: repeat previous search command where place list is displaying the places we are finding
         * -> 3 places found
         */
        command = SearchYearCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_2016;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 keywords -> 5 places found */
        command = SearchYearCommand.COMMAND_WORD + " 2016 2017";
        ModelHelper.setFilteredList(expectedModel, ALICE, BENSON, CARL, DANIEL, GEORGE);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 keywords in reversed order -> 5 places found */
        command = SearchYearCommand.COMMAND_WORD + " 2017 2016";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 keywords with 1 repeat -> 5 places found */
        command = SearchYearCommand.COMMAND_WORD + " 2017 2016 2017";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, 2 matching keywords and 1 non-matching keyword
         * -> 5 places found
         */
        command = SearchYearCommand.COMMAND_WORD + " 2016 2017 1996";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple places in travel book, valid range of years
         * -> 5 places found
         */
        command = SearchYearCommand.COMMAND_WORD + " 2016-2017";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: undo previous search rating command -> rejected */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: redo previous search rating command -> rejected */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: find same places in travel book after deleting 1 of them -> 2 places found */
        executeCommand(DeleteCommand.COMMAND_WORD + " 1");
        assertFalse(getModel().getTravelBuddy().getPlaceList().contains(ALICE));
        command = SearchYearCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_2016;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, CARL, DANIEL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find place in travel book, keyword is not found in travel book -> 0 places found */
        command = SearchYearCommand.COMMAND_WORD + " 1997";
        ModelHelper.setFilteredList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find while a place is selected -> selected card deselected */
        showAllPlaces();
        selectPlace(Index.fromOneBased(1));
        assertFalse(getPlaceListPanel().getHandleToSelectedCard().getName().equals(CARL.getName().fullName));
        command = SearchYearCommand.COMMAND_WORD + " 2017";
        ModelHelper.setFilteredList(expectedModel, BENSON, GEORGE);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardDeselected();

        /* Case: find place in empty travel book -> 0 places found */
        deleteAllPlaces();
        command = SearchYearCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_2016;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, CARL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        command = "sEaRcHyEaR 2016";
        assertCommandFailure(command, MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code Messages#MESSAGE_PLACES_LISTED_OVERVIEW} with the number of people in the filtered list,
     * and the model related components equal to {@code expectedModel}.
     * These verifications are done by
     * {@code TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
     * selected card updated accordingly, depending on {@code cardStatus}.
     * @see TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel) {
        String expectedResultMessage = command.trim().concat(": ").concat(String.format(
                MESSAGE_PLACES_LISTED_OVERVIEW, expectedModel.getFilteredPlaceList().size()));

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
     * @see TravelBuddySystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
