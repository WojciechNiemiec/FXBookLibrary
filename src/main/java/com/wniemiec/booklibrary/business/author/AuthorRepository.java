package com.wniemiec.booklibrary.business.author;

import com.wniemiec.booklibrary.business.abstracts.AbstractRepository;

import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class AuthorRepository extends AbstractRepository<Author, Long, AuthorDTO> {
    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    protected Class<AuthorDTO> getTargetDTOClass() {
        return AuthorDTO.class;
    }

    @Override
    public CompoundSelection<AuthorDTO> getConstruct(Root<Author> root, CriteriaBuilder cb) {
        return cb.construct(AuthorDTO.class, root.get(Author_.id), root.get(Author_.name), root.get(Author_.surname));
    }
}
