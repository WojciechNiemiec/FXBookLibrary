package com.wniemiec.booklibrary.renting;

import com.wniemiec.booklibrary.book_copy.BookCopy;
import com.wniemiec.booklibrary.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentingKey {
    @Column(name = "book_copy_id", nullable = false)
    private BookCopy bookCopy;

    @Column(name = "reader_id", nullable = false)
    private Reader reader;
}
