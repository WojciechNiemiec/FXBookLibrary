package com.wniemiec.booklibrary.business.renting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class RentingId implements Serializable {
    @Column(name = "book_copy_id")
    private Long bookCopyId;

    @Column(name = "reader_id")
    private Long readerId;
}
