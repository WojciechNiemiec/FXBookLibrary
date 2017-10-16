package com.wniemiec.booklibrary.business.renting;

import com.wniemiec.booklibrary.business.book_copy.BookCopy;
import com.wniemiec.booklibrary.business.reader.Reader;
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
