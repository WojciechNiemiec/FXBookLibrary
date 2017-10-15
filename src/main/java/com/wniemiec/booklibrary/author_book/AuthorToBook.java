package com.wniemiec.booklibrary.author_book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "author_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorToBook implements Serializable {
    @EmbeddedId
    private AuthorToBookKey primaryKey;
}
