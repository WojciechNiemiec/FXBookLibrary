package com.wniemiec.booklibrary.business.reader;

import com.wniemiec.booklibrary.business.abstracts.AbstractRepository;
import com.wniemiec.booklibrary.business.address.Address;
import com.wniemiec.booklibrary.business.address.Address_;

import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

public class ReaderRepository extends AbstractRepository<Reader, Long, ReaderDTO> {
    @Override
    protected Class<Reader> getEntityClass() {
        return Reader.class;
    }

    @Override
    protected Class<ReaderDTO> getTargetDTOClass() {
        return ReaderDTO.class;
    }

    @Override
    public CompoundSelection<ReaderDTO> getConstruct(Root<Reader> root, CriteriaBuilder cb) {
        Join<Reader, Address> address = root.join(Reader_.address);
        return cb.construct(ReaderDTO.class,
                root.get(Reader_.id),
                root.get(Reader_.pesel),
                root.get(Reader_.name),
                root.get(Reader_.surname),
                address.get(Address_.city),
                address.get(Address_.postalCode));
    }
}
