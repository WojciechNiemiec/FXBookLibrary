package com.wniemiec.booklibrary.desktop.util;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class AddRemoveController<T> implements Initializable {

    private List<T> objectsResource;

    @FXML
    private ListView<T> allObjectsList;

    @FXML
    private ListView<T> selectedObjectsList;

    private Label objectsCounter;

    public AddRemoveController(List<T> objectsResource) {
        this.objectsResource = objectsResource;
    }

    public ObservableList<T> getSelectedObjectsHandle() {
        return selectedObjectsList.getItems();
    }

    public void setObjectsCounter(Label objectsCounter) {
        this.objectsCounter = objectsCounter;
    }

    public void addToSelected(ActionEvent e) {
        MultipleSelectionModel<T> model = allObjectsList.getSelectionModel();
        T selectedAuthor = model.getSelectedItem();

        if (Objects.nonNull(selectedAuthor)) {
            allObjectsList.getItems().remove(selectedAuthor);
            selectedObjectsList.getItems().add(selectedAuthor);
            refreshLabels();
        }
    }

    public void removeFromSelected(ActionEvent e) {
        MultipleSelectionModel<T> model = selectedObjectsList.getSelectionModel();
        T selectedAuthor = model.getSelectedItem();

        if (Objects.nonNull(selectedAuthor)) {
            selectedObjectsList.getItems().remove(selectedAuthor);
            allObjectsList.getItems().add(selectedAuthor);
            refreshLabels();
        }
    }

    private void refreshLabels() {
        if (Objects.nonNull(objectsCounter)) {
            objectsCounter.setText(String.valueOf(selectedObjectsList.getItems().size()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allObjectsList.setItems(new ObservableListWrapper<>(objectsResource));
    }
}
