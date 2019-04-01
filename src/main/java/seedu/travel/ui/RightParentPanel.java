package seedu.travel.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.travel.commons.core.LogsCenter;
import seedu.travel.model.place.Place;

/**
 * Panel containing the list of places.
 */
public class RightParentPanel extends UiPart<Region> {
    private static final String FXML = "RightParentPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RightParentPanel.class);

    private ListView<Place> placeListView; // not in use

    @FXML
    private VBox parentPanelPlaceholder;

    private DisplayListPanel displayListPanel;

    public RightParentPanel(ObservableList<Place> placeList, ObservableValue<Place> selectedPlace,
                            Consumer<Place> onSelectedPlaceChange, ReadOnlyProperty<Boolean> chartDisplayed) {
        super(FXML);
        displayListPanel = new DisplayListPanel(placeList, onSelectedPlaceChange);
        parentPanelPlaceholder.getChildren().setAll(displayListPanel.getRoot());

        chartDisplayed.addListener((observable, oldValue, newValue) -> {
            // display the chart

            logger.info("old Value" + oldValue + "New value " + newValue);
            if (newValue) {
                // pass through
                logger.info("SimpleBooleanValue Changed ");
                displayListPanel = new DisplayListPanel(placeList, onSelectedPlaceChange);
                parentPanelPlaceholder.getChildren().removeAll();
                parentPanelPlaceholder.getChildren().setAll(displayListPanel.getRoot());
            } else {
                if (selectedPlace.getValue() == null) {
                    ExpandedPlacePanel empty = new ExpandedPlacePanel();
                    parentPanelPlaceholder.getChildren().removeAll();
                    parentPanelPlaceholder.getChildren().setAll(empty.getRoot());

                    return;
                }
                ExpandedPlacePanel updated = new ExpandedPlacePanel(selectedPlace.getValue());
                parentPanelPlaceholder.getChildren().removeAll();
                parentPanelPlaceholder.getChildren().setAll(updated.getRoot());
            }
        });

        selectedPlace.addListener((observable, oldValue, newValue) -> {
            if (selectedPlace.getValue() == null) {
                ExpandedPlacePanel empty = new ExpandedPlacePanel();
                parentPanelPlaceholder.getChildren().removeAll();
                parentPanelPlaceholder.getChildren().setAll(empty.getRoot());
                return;
            }

                ExpandedPlacePanel updated = new ExpandedPlacePanel(selectedPlace.getValue());
                parentPanelPlaceholder.getChildren().removeAll();
                parentPanelPlaceholder.getChildren().setAll(updated.getRoot());

            }
        );

    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Place} using a {@code PlaceCard}.
     */
    class PlaceListViewCell extends ListCell<Place> {
        @Override
        protected void updateItem(Place place, boolean empty) {
            super.updateItem(place, empty);

            if (empty || place == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PlaceCard(place, getIndex() + 1).getRoot());
            }
        }
    }

}
