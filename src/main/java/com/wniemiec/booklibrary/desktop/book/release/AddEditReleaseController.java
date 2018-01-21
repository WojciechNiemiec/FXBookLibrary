package com.wniemiec.booklibrary.desktop.book.release;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class AddEditReleaseController {

    @FXML
    private Label label;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<ZonedDateTime> dates;

    public ObservableList<ZonedDateTime> getDatesHandle() {
        return dates.getItems();
    }

    public void add(ActionEvent e) {
        ZonedDateTime releaseDate = ZonedDateTime.of(datePicker.getValue(), LocalTime.MIN, ZoneId.systemDefault());
        dates.getItems().add(releaseDate);
    }

    public void remove(ActionEvent e) {
        ZonedDateTime selectedDate = dates.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(selectedDate)) {
            dates.getItems().remove(selectedDate);
        }
    }

    public void save(ActionEvent e) {

    }
}
