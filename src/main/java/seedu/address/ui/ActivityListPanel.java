package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.timetable.Activity;
import seedu.address.model.person.timetable.TimeTable;

//@@author windrichie

/**
 * Panel containing the list of activities (Timetable).
 */
public class ActivityListPanel extends UiPart<Region> {
    private static final String FXML = "ActivityListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ActivityListPanel.class);

    @FXML
    private ListView<Activity> activityListView;

    public ActivityListPanel(TimeTable timetable) {
        super(FXML);

        String[][] activityArray = timetable.getTimeTableArray();
        ArrayList<Activity> activityList = new ArrayList<>();
        for (int row = 0; row < activityArray.length; row++) {
            for (int col = 0; col < activityArray[row].length; col++) {
                if (activityArray[row][col] != null) {
                    // System.out.println("Adding activities to list: " + activityArray[row][col]);
                    activityList.add(new Activity(activityArray[row][col], row, col / 2));
                }
            }
        }
        System.out.println(activityList.size());
        ObservableList<Activity> observableActivityList = FXCollections.observableArrayList(activityList);
        // System.out.println(observableActivityList.size());
        activityListView.setItems(observableActivityList);
        activityListView.setCellFactory(listView -> new ActivityListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class ActivityListViewCell extends ListCell<Activity> {
        @Override
        protected void updateItem(Activity activity, boolean empty) {
            super.updateItem(activity, empty);

            if (empty || activity == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ActivityCard(activity, getIndex() + 1).getRoot());
            }
        }
    }

}
