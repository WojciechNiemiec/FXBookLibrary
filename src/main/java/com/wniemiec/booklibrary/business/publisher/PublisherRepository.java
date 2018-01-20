package com.wniemiec.booklibrary.business.publisher;

import com.wniemiec.booklibrary.business.abstracts.AbstractRepository;

import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class PublisherRepository extends AbstractRepository<Publisher, Long, PublisherDTO> {

    @Override
    protected Class<Publisher> getEntityClass() {
        return Publisher.class;
    }

    @Override
    protected Class<PublisherDTO> getTargetDTOClass() {
        return PublisherDTO.class;
    }

    @Override
    public CompoundSelection<PublisherDTO> getConstruct(Root<Publisher> root, CriteriaBuilder cb) {
        return cb.construct(PublisherDTO.class, root.get(Publisher_.id), root.get(Publisher_.name));
    }
}
