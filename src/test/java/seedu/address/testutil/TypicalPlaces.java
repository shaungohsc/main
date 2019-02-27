package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CODY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DICK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CODY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DICK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CODY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DICK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_CODY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DICK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.place.Place;

/**
 * A utility class containing a list of {@code Place} objects to be used in tests.
 */
public class TypicalPlaces {

    public static final Place ALICE = new PlaceBuilder()
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withDescription("No description")
            .withPhone("94351253")
            .withTags("friends")
            .build();
    public static final Place BENSON = new PlaceBuilder()
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withDescription("I love this place")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .build();
    public static final Place CARL = new PlaceBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withDescription("I love the coffee here")
            .withAddress("wall street")
            .build();
    public static final Place DANIEL = new PlaceBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withDescription("I love the ambiance here")
            .withAddress("10th street")
            .withTags("friends")
            .build();
    public static final Place ELLE = new PlaceBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withDescription("I love the night life here")
            .withAddress("michegan ave")
            .build();
    public static final Place FIONA = new PlaceBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withDescription("This place exudes luxury")
            .withAddress("little tokyo")
            .build();
    public static final Place GEORGE = new PlaceBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withDescription("This place has the best amenities")
            .withAddress("4th street")
            .build();

    // Manually added
    public static final Place HOON = new PlaceBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withDescription("I dislike this place")
            .withAddress("little india")
            .build();
    public static final Place IDA = new PlaceBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withDescription("I hate this place")
            .withAddress("chicago ave")
            .build();

    // Manually added - Place's details found in {@code CommandTestUtil}
    public static final Place AMY = new PlaceBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withDescription(VALID_DESCRIPTION_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();
    public static final Place BOB = new PlaceBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withDescription(VALID_DESCRIPTION_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    public static final Place CODY = new PlaceBuilder()
            .withName(VALID_NAME_CODY)
            .withPhone(VALID_PHONE_CODY)
            .withDescription(VALID_DESCRIPTION_CODY)
            .withAddress(VALID_ADDRESS_CODY)
            .withTags(VALID_TAG_HUSBAND)
            .build();
    public static final Place DICK = new PlaceBuilder()
            .withName(VALID_NAME_DICK)
            .withPhone(VALID_PHONE_DICK)
            .withDescription(VALID_DESCRIPTION_DICK)
            .withAddress(VALID_ADDRESS_DICK)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPlaces() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical places.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Place place : getTypicalPlaces()) {
            ab.addPlace(place);
        }
        return ab;
    }

    public static List<Place> getTypicalPlaces() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}