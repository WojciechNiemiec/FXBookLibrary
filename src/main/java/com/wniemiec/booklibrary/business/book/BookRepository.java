package com.wniemiec.booklibrary.business.book;

import com.wniemiec.booklibrary.business.abstracts.AbstractRepository;
import com.wniemiec.booklibrary.business.author.Author_;
import com.wniemiec.booklibrary.business.book_copy.BookCopy;
import com.wniemiec.booklibrary.business.book_copy.BookCopy_;
import com.wniemiec.booklibrary.business.genre.Genre;
import com.wniemiec.booklibrary.business.genre.Genre_;
import com.wniemiec.booklibrary.business.publisher.Publisher_;
import com.wniemiec.booklibrary.business.release.Release_;
import com.wniemiec.booklibrary.business.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class BookRepository extends AbstractRepository<Book, Long, BookDTO> {

    private EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    @Override
    public void delete(Long id) {
        invokeInSession((session) -> session.delete(session.load(Book.class, id)));
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    protected Class<BookDTO> getTargetDTOClass() {
        return BookDTO.class;
    }

    @Override
    public CompoundSelection<BookDTO> getConstruct(Root<Book> root, CriteriaQuery<BookDTO> query, CriteriaBuilder cb) {
        return cb.construct(BookDTO.class,
                root.get(Book_.ISBN),
                root.get(Book_.title),
//                cb.construct(SetImpl.class, root.join(Book_.authors, JoinType.LEFT).get(Author_.name)),
//                cb.construct(SetImpl.class, root.join(Book_.genres).get(Genre_.name)),
                root.join(Book_.publisher, JoinType.LEFT).get(Publisher_.name)
//                cb.greaterThan(cb.countDistinct(root.join(Book_.releases).join(Release_.bookCopies)), 0L)
        );
    }
}

class SetImpl extends HashSet<String> {
    SetImpl(Collection<String> strings) {
        this.addAll(strings);
    }

    SetImpl(String... strings) {
        this.addAll(Arrays.asList(strings));
    }

    SetImpl(String string) {
        this("","","");
        this.add(string);
    }
}