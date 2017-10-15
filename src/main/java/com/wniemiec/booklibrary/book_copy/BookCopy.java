package com.wniemiec.booklibrary.book_copy;

import com.wniemiec.booklibrary.abstracts.AbstractEntity;
import com.wniemiec.booklibrary.release.Release;
import com.wniemiec.booklibrary.renting.Renting;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "book_copy")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookCopy extends AbstractEntity {
    @Column(name = "book_condition")
    private String bookCondition;

    @ManyToOne
    @Column(name = "release_id")
    private Release release;

    @OneToMany
    private List<Renting> rentings;
}
