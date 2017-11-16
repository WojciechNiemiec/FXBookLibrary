package com.wniemiec.booklibrary.desktop.book;

import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.book.BookRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddBookController {
    private final BookRepository bookRepository = new BookRepository();

    @FXML
    private TextField ISBN;

    @FXML
    private TextField title;

    @FXML
    private ComboBox<String> publisher;

    @FXML
    private TextArea description;

    @FXML
    private void saveBook(ActionEvent e) {
        Book book = new Book();
        book.setISBN(ISBN.getText());
        book.setTitle(title.getText());
        book.setDescription(description.getText());

        bookRepository.save(book);
    }
}
