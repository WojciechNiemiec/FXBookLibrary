package com.wniemiec.booklibrary.business.util;

import com.wniemiec.booklibrary.business.author.Author;
import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.book_copy.BookCopy;
import com.wniemiec.booklibrary.business.country.Country;
import com.wniemiec.booklibrary.business.genre.Genre;
import com.wniemiec.booklibrary.business.publisher.Publisher;
import com.wniemiec.booklibrary.business.reader.Reader;
import com.wniemiec.booklibrary.business.release.Release;
import com.wniemiec.booklibrary.business.renting.Renting;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/library")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "root")
                    .setProperty("hibernate.hbm2ddl.auto", "saveOrUpdate")
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(Author.class)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(BookCopy.class)
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(Genre.class)
                    .addAnnotatedClass(Publisher.class)
                    .addAnnotatedClass(Reader.class)
                    .addAnnotatedClass(Release.class)
                    .addAnnotatedClass(Renting.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}