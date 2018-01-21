package com.wniemiec.booklibrary.desktop.book;

import com.sun.javafx.collections.ObservableListWrapper;
import com.wniemiec.booklibrary.business.author.Author;
import com.wniemiec.booklibrary.business.author.AuthorDTO;
import com.wniemiec.booklibrary.business.author.AuthorRepository;
import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.book.BookRepository;
import com.wniemiec.booklibrary.business.genre.Genre;
import com.wniemiec.booklibrary.business.genre.GenreDTO;
import com.wniemiec.booklibrary.business.genre.GenreRepository;
import com.wniemiec.booklibrary.business.publisher.Publisher;
import com.wniemiec.booklibrary.business.publisher.PublisherDTO;
import com.wniemiec.booklibrary.business.publisher.PublisherRepository;
import com.wniemiec.booklibrary.business.release.Release;
import com.wniemiec.booklibrary.desktop.book.release.AddEditReleaseController;
import com.wniemiec.booklibrary.desktop.util.AddRemoveController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class AddEditBookController implements Initializable {
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

    @FXML
    private Label authorCount;

    @FXML
    private Label genresCount;

    @FXML
    private Label releasesCount;

    private ObservableList<AuthorDTO> authors;

    private ObservableList<GenreDTO> genres;

    private ObservableList<ZonedDateTime> releaseDates;

    @FXML
    private void saveBook(ActionEvent e) {
        Book book = new Book();
        book.setISBN(ISBN.getText());
        book.setTitle(title.getText());
        book.setDescription(description.getText());

        Publisher newPublisher = new Publisher();
        newPublisher.setId(publisher.getSelectionModel().getSelectedItem().getId());
        book.setPublisher(newPublisher);

        if (CollectionUtils.isNotEmpty(authors)) {
            Set<Author> newAuthors = authors.stream().map(dto -> {
                Author author = new Author();
                author.setId(dto.getId());
                return author;
            }).collect(Collectors.toSet());
            book.setAuthors(newAuthors);
        }

        if (CollectionUtils.isNotEmpty(genres)) {
            Set<Genre> newGenres = genres.stream().map(dto -> {
                Genre genre = new Genre();
                genre.setId(dto.getId());
                return genre;
            }).collect(Collectors.toSet());
            book.setGenres(newGenres);
        }

        if (CollectionUtils.isNotEmpty(releaseDates)) {
            book.setReleases(releaseDates.stream()
                    .map(date -> Release.builder()
                            .book(book)
                            .releaseDate(date)
                            .build()
                    ).collect(Collectors.toSet()));
        }

        bookRepository.save(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<PublisherDTO> publishers = publisherRepository.findAll();

        publisher.setItems(new ObservableListWrapper<>(publishers));
    }

    @FXML
    private void addEditAuthor() throws IOException {
        List<AuthorDTO> allAuthors = authorRepository.findAll();

        AddRemoveController<AuthorDTO> controller = new AddRemoveController<>(allAuthors);
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../util/add_edit_window.fxml"));
        loader.setController(controller);

        Parent load = loader.load();
        authors = controller.getSelectedObjectsHandle();
        controller.setObjectsCounter(authorCount);

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Add/Edit Author");
        stage.show();
    }

    public void addEditGenre(ActionEvent e) throws IOException {
        List<GenreDTO> allGenres = genreRepository.findAll();

        AddRemoveController<GenreDTO> controller = new AddRemoveController<>(allGenres);
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../util/add_edit_window.fxml"));
        loader.setController(controller);

        Parent load = loader.load();
        genres = controller.getSelectedObjectsHandle();
        controller.setObjectsCounter(genresCount);

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Add/Edit Genres");
        stage.show();
    }

    public void addEditRelease(ActionEvent e) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("release/add_edit_release.fxml"));
        Parent load = loader.load();

        AddEditReleaseController controller = loader.getController();
        releaseDates = controller.getDatesHandle();

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Add/Edit Releases");
        stage.show();
    }
}
