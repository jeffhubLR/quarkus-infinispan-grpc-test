package org.acme;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;
import java.util.Set;

public class Book {
    private final String title;
    private final String description;
    private final int publicationYear;
    private final Set<Author> authors;

    @ProtoFactory
    public Book(String title, String description, int publicationYear, Set<Author> authors) {
        this.title = Objects.requireNonNull(title);
        this.description = Objects.requireNonNull(description);
        this.publicationYear = publicationYear;
        this.authors = Objects.requireNonNull(authors);
    }

    @ProtoField(number = 1)
    public String getTitle() {
        return title;
    }

    @ProtoField(number = 2)
    public String getDescription() {
        return description;
    }

    @ProtoField(number = 3, defaultValue = "-1")
    public int getPublicationYear() {
        return publicationYear;
    }

    @ProtoField(number = 4)
    public Set<Author> getAuthors() {
        return authors;
    }
}