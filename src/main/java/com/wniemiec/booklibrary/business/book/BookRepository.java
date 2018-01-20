package com.wniemiec.booklibrary.business.book;

import com.wniemiec.booklibrary.business.abstracts.AbstractRepository;
import com.wniemiec.booklibrary.business.author.Author;
import com.wniemiec.booklibrary.business.author.Author_;
import com.wniemiec.booklibrary.business.genre.Genre;
import com.wniemiec.booklibrary.business.genre.Genre_;
import com.wniemiec.booklibrary.business.publisher.Publisher_;
import com.wniemiec.booklibrary.business.util.IdToValue;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepository extends AbstractRepository<Book, Long, BookDTO> {

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    protected Class<BookDTO> getTargetDTOClass() {
        return BookDTO.class;
    }

    @Override
    public CompoundSelection<BookDTO> getConstruct(Root<Book> root, CriteriaBuilder cb) {
        return cb.construct(BookDTO.class, root.get(Book_.id), root.get(Book_.ISBN),
                root.get(Book_.title), root.join(Book_.publisher, JoinType.LEFT).get(Publisher_.name));
    }

    @Override
    protected List<BookDTO> decorateResults(List<BookDTO> undecorated, EntityManager entityManager) {
        List<Long> ids = undecorated.stream().map(BookDTO::getId).collect(Collectors.toList());
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        List<IdToValue> authorsToIds = getAuthorsToIds(ids, cb, entityManager);
        List<IdToValue> genresToIds = getGenresToIds(ids, cb, entityManager);

        return undecorated.stream()
                .peek(book -> book.setAuthorsNames(getMatching(authorsToIds, book)))
                .peek(book -> book.setGenresNames(getMatching(genresToIds, book)))
                .collect(Collectors.toList());
    }

    private List<IdToValue> getAuthorsToIds(List<Long> ids, CriteriaBuilder cb, EntityManager entityManager) {
        CriteriaQuery<IdToValue> authorQuery = cb.createQuery(IdToValue.class);
        Root<Author> author = authorQuery.from(Author.class);
        Path<Long> authorToBookId = author.join(Author_.books).get(Book_.id);

        authorQuery.select(cb.construct(IdToValue.class, authorToBookId,
                cb.concat(author.get(Author_.name), cb.concat(" ", author.get(Author_.surname)))))
                .where(authorToBookId.in(ids));

        return entityManager.createQuery(authorQuery)
                .getResultList();
    }

    private List<IdToValue> getGenresToIds(List<Long> ids, CriteriaBuilder cb, EntityManager entityManager) {
        CriteriaQuery<IdToValue> genreQuery = cb.createQuery(IdToValue.class);
        Root<Genre> genre = genreQuery.from(Genre.class);
        Path<Long> genreToBookId = genre.join(Genre_.books).get(Book_.id);

        genreQuery.select(cb.construct(IdToValue.class, genreToBookId, genre.get(Genre_.name)))
                .where(genreToBookId.in(ids));

        return entityManager.createQuery(genreQuery)
                .getResultList();
    }

    private Set<String> getMatching(List<IdToValue> idToValues, BookDTO book) {
        return idToValues.stream()
                .filter(idToValue -> idToValue.getId().equals(book.getId()))
                .map(IdToValue::getValue)
                .collect(Collectors.toSet());
    }
}