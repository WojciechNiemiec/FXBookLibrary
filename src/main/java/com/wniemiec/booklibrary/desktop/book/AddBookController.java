package com.wniemiec.booklibrary.desktop.book;

import com.sun.javafx.collections.ObservableListWrapper;
import com.wniemiec.booklibrary.business.author.AuthorDTO;
import com.wniemiec.booklibrary.business.author.AuthorRepository;
import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.book.BookRepository;
import com.wniemiec.booklibrary.business.genre.GenreDTO;
import com.wniemiec.booklibrary.business.genre.GenreRepository;
import com.wniemiec.booklibrary.business.publisher.Publisher;
import com.wniemiec.booklibrary.business.publisher.PublisherDTO;
import com.wniemiec.booklibrary.business.publisher.PublisherRepository;
import com.wniemiec.booklibrary.desktop.util.AddRemoveController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    private final BookRepository bookRepository = new BookRepository();
    private final PublisherRepository publisherRepository = new PublisherRepository();
    private final AuthorRepository authorRepository = new AuthorRepository();
    private final GenreRepository genreRepository = new GenreRepository();

    @FXML
    private TextField ISBN;

    @FXML
    private TextField title;

    @FXML
    private ComboBox<PublisherDTO> publisher;

    @FXML
    private TextArea description;

    private ObservableList<AuthorDTO> authors;

    private ObservableList<GenreDTO> genres;

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
        List<AuthorDTO> authors = authorRepository.findAll();

        AddRemoveController<AuthorDTO> controller = new AddRemoveController<>(authors);
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../util/add_edit_window.fxml"));
        loader.setController(controller);

        Parent load = loader.load();
        this.authors = controller.getSelectedObjectsHandle();

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Add/Edit Author");
        stage.show();
    }

    public void addEditGenre(ActionEvent e) throws IOException {
        List<GenreDTO> genres = genreRepository.findAll();

        AddRemoveController<GenreDTO> controller = new AddRemoveController<>(genres);
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../util/add_edit_window.fxml"));
        loader.setController(controller);

        Parent load = loader.load();
        this.genres = controller.getSelectedObjectsHandle();

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Add/Edit Genres");
        stage.show();
    }

    public void addEditRelease(ActionEvent e) {

    }
}
