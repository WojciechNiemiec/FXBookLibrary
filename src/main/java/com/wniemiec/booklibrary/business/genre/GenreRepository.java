package com.wniemiec.booklibrary.business.genre;

import com.wniemiec.booklibrary.business.abstracts.AbstractRepository;

import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class GenreRepository extends AbstractRepository<Genre, Long, GenreDTO> {

    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }

    @Override
    protected Class<GenreDTO> getTargetDTOClass() {
        return GenreDTO.class;
    }

    @Override
    public CompoundSelection<GenreDTO> getConstruct(Root<Genre> root, CriteriaBuilder cb) {
        return cb.construct(GenreDTO.class, root.get(Genre_.id), root.get(Genre_.name));
    }
}
