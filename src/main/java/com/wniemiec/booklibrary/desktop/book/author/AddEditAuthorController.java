package com.wniemiec.booklibrary.desktop.book.author;

import com.sun.javafx.collections.ObservableListWrapper;
import com.wniemiec.booklibrary.business.author.AuthorDTO;
import com.wniemiec.booklibrary.business.author.AuthorRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddEditAuthorController implements Initializable {
    @FXML
    private ListView<AuthorDTO> allAuthorsList;

    @FXML
    private ListView<AuthorDTO> selectedAuthorsList;


    public void setSelectedAuthors(ObservableList<AuthorDTO> authorsHandle) {
        selectedAuthorsList.setItems(authorsHandle);
    }

    public void addToSelected(ActionEvent e) {
        MultipleSelectionModel<AuthorDTO> model = allAuthorsList.getSelectionModel();
        AuthorDTO selectedAuthor = model.getSelectedItem();
        allAuthorsList.getItems().remove(selectedAuthor);
        selectedAuthorsList.getItems().add(selectedAuthor);
    }

    public void removeFromSelected(ActionEvent e) {
        MultipleSelectionModel<AuthorDTO> model = selectedAuthorsList.getSelectionModel();
        AuthorDTO selectedAuthor = model.getSelectedItem();
        selectedAuthorsList.getItems().remove(selectedAuthor);
        allAuthorsList.getItems().add(selectedAuthor);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<AuthorDTO> authors = new AuthorRepository().findAll();

        allAuthorsList.setItems(new ObservableListWrapper<>(authors));
    }
}
