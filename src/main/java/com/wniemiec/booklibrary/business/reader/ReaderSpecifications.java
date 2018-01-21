package com.wniemiec.booklibrary.business.reader;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ReaderSpecifications {
    public static Specification<Reader> peselEqual(BigDecimal value) {
        return (root, query, cb) -> cb.equal(root.get(Reader_.pesel), value);
    }

    public static Specification<Reader> nameLike(String string) {
        return (root, query, cb) -> cb.like(root.get(Reader_.name), string + '%');
    }

    public static Specification<Reader> surnameLike(String string) {
        return (root, query, cb) -> cb.like(root.get(Reader_.surname), string + '%');
    }
}
