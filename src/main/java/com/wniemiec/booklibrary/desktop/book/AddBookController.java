package com.wniemiec.booklibrary.desktop.book;

import com.sun.javafx.collections.ObservableListWrapper;
import com.wniemiec.booklibrary.business.author.AuthorDTO;
import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.book.BookRepository;
import com.wniemiec.booklibrary.business.publisher.Publisher;
import com.wniemiec.booklibrary.business.publisher.PublisherDTO;
import com.wniemiec.booklibrary.business.publisher.PublisherRepository;
import com.wniemiec.booklibrary.desktop.book.author.AddEditAuthorController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddBookController implements Initializable {
    private final BookRepository bookRepository = new BookRepository();
    private final PublisherRepository publisherRepository = new PublisherRepository();

    @FXML
    private TextField ISBN;

    @FXML
    private TextField title;

    @FXML
    private ComboBox<PublisherDTO> publisher;

    @FXML
    private TextArea description;

    private ObservableList<AuthorDTO> authors = new ObservableListWrapper<>(new ArrayList<>());

    @FXML
    private void saveBook(ActionEvent e) {
        Book book = new Book();
        book.setISBN(ISBN.getText());
        book.setTitle(title.getText());
        book.setDescription(description.getText());

        Publisher newPublisher = new Publisher();
        newPublisher.setId(publisher.getSelectionModel().getSelectedItem().getId());
        book.setPublisher(newPublisher);

        bookRepository.save(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<PublisherDTO> publishers = publisherRepository.findAll();

        publisher.setItems(new ObservableListWrapper<>(publishers));
    }

    @FXML
    private void addEditAuthor() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("author/add_edit_author.fxml"));

        Parent load = loader.load();

        AddEditAuthorController controller = loader.getController();
        controller.setSelectedAuthors(authors);

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Add/Edit Author");
        stage.show();
    }
}
