package com.wniemiec.booklibrary.business.book_copy;

import com.wniemiec.booklibrary.business.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.business.release.Release;
import com.wniemiec.booklibrary.business.renting.Renting;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "book_copy")
@Data
@EqualsAndHashCode(callSuper = true)
public class BookCopy extends AbstractEntity {
    @Column(name = "book_condition")
    private String bookCondition;

    @ManyToOne
    private Release release;

    @OneToMany(mappedBy = "bookCopy")
    private List<Renting> rentings;
}
