package com.wniemiec.booklibrary.desktop;

import com.sun.javafx.collections.ObservableSequentialListWrapper;
import com.wniemiec.booklibrary.business.book.BookRepository;
import com.wniemiec.booklibrary.business.book.BookSpecifications;
import com.wniemiec.booklibrary.business.book.Book_;
import com.wniemiec.booklibrary.business.reader.ReaderRepository;
import com.wniemiec.booklibrary.desktop.book.BookModel;
import com.wniemiec.booklibrary.desktop.reader.ReaderModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.data.jpa.domain.Specifications;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MainWindowController {

    private final BookRepository bookRepository = new BookRepository();
    private final ReaderRepository readerRepository = new ReaderRepository();

    public TextField bookSearchField;

    @FXML
    private TableView<BookModel> bookTable;

    @FXML
    private TableView<ReaderModel> readerTable;

    @FXML
    private void searchBooks(ActionEvent e) {
        String param = bookSearchField.getText();

        List<BookModel> models = bookRepository.findAll(Specifications
                .where(BookSpecifications.titleLike(param))
                .or(BookSpecifications.authorLike(param)))
                .stream()
                .map(BookModel::new)
                .collect(Collectors.toList());

        bookTable.setItems(new ObservableSequentialListWrapper<>(models));
    }

    @FXML
    private void searchReaders(ActionEvent e) {
        List<ReaderModel> models = readerRepository.findAll()
                .stream()
                .map(ReaderModel::new)
                .collect(Collectors.toList());

        readerTable.setItems(new ObservableSequentialListWrapper<>(models));
    }

    @FXML
    private void addBook() throws IOException {
        Stage stage = new Stage();
        Parent load = FXMLLoader.load(getClass().getResource("book/add_book_window.fxml"));

        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Add Book");
        stage.show();
    }

    @FXML
    private void deleteBook() throws IOException {
        Long bookId = bookTable.getSelectionModel().getSelectedItem().getId();
        bookRepository.delete(bookId);
    }
}
