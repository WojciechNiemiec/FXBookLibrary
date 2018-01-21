package com.wniemiec.booklibrary.business.book;

import com.wniemiec.booklibrary.business.author.Author_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

public class BookSpecifications {
    public static Specification<Book> titleLike(String string) {
        return (root, query, cb) -> cb.like(root.get(Book_.title), string + '%');
    }

    public static Specification<Book> authorLike(String string) {
        return (root, query, cb) -> cb.like(root.join(Book_.authors, JoinType.LEFT).get(Author_.name), string + '%');
    }
}
