package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.place.NameContainsKeywordsPredicate;
import seedu.address.model.place.Place;
import seedu.address.testutil.EditPlaceDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Beaver";
    public static final String VALID_NAME_BOB = "Bob Cougar";
    public static final String VALID_NAME_CODY = "Cody Dog";
    public static final String VALID_NAME_DICK = "Dick Eagle";
    public static final String VALID_RATING_AMY = "5";
    public static final String VALID_RATING_BOB = "1";
    public static final String VALID_RATING_CODY = "4";
    public static final String VALID_RATING_DICK = "2";
    public static final String VALID_DESCRIPTION_AMY = "I love this place";
    public static final String VALID_DESCRIPTION_BOB = "I hate this place";
    public static final String VALID_DESCRIPTION_CODY = "This place is great";
    public static final String VALID_DESCRIPTION_DICK = "This place is shit";
    public static final String VALID_ADDRESS_AMY = "Block 123, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 321, Bobby Street 3";
    public static final String VALID_ADDRESS_CODY = "Block 456, Cody Street 1";
    public static final String VALID_ADDRESS_DICK = "Block 654, Dick Street 6";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CODY = " " + PREFIX_NAME + VALID_NAME_CODY;
    public static final String NAME_DESC_DICK = " " + PREFIX_NAME + VALID_NAME_DICK;
    public static final String RATING_DESC_AMY = " " + PREFIX_RATING + VALID_RATING_AMY;
    public static final String RATING_DESC_BOB = " " + PREFIX_RATING + VALID_RATING_BOB;
    public static final String RATING_DESC_CODY = " " + PREFIX_RATING + VALID_RATING_CODY;
    public static final String RATING_DESC_DICK = " " + PREFIX_RATING + VALID_RATING_DICK;
    public static final String DESCRIPTION_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String DESCRIPTION_CODY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CODY;
    public static final String DESCRIPTION_DICK = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_DICK;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ADDRESS_DESC_CODY = " " + PREFIX_ADDRESS + VALID_ADDRESS_CODY;
    public static final String ADDRESS_DESC_DICK = " " + PREFIX_ADDRESS + VALID_ADDRESS_DICK;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING + "4a"; // 'a' not allowed in ratings
    public static final String INVALID_DESCRIPTION = " " + PREFIX_DESCRIPTION + ".I love this place";
    // must begin with alphabet
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPlaceDescriptor DESC_AMY;
    public static final EditCommand.EditPlaceDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPlaceDescriptorBuilder().withName(VALID_NAME_AMY)
                .withRating(VALID_RATING_AMY).withDescription(VALID_DESCRIPTION_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPlaceDescriptorBuilder().withName(VALID_NAME_BOB)
                .withRating(VALID_RATING_BOB).withDescription(VALID_DESCRIPTION_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            CommandResult expectedCommandResult, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered place list and selected place in {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Place> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPlaceList());
        Place expectedSelectedPlace = actualModel.getSelectedPlace();

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPlaceList());
            assertEquals(expectedSelectedPlace, actualModel.getSelectedPlace());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the place at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPlaceAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPlaceList().size());

        Place place = model.getFilteredPlaceList().get(targetIndex.getZeroBased());
        final String[] splitName = place.getName().fullName.split("\\s+");
        model.updateFilteredPlaceList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPlaceList().size());
    }

    /**
     * Deletes the first place in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPlace(Model model) {
        Place firstPlace = model.getFilteredPlaceList().get(0);
        model.deletePlace(firstPlace);
        model.commitAddressBook();
    }

}
