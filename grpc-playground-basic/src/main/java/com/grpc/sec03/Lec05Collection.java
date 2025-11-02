package com.grpc.sec03;

import com.grpc.models.sec03.Book;
import com.grpc.models.sec03.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Lec05Collection {
    private static Logger log = LoggerFactory.getLogger(Lec05Collection.class);

    public static void main(String[] args) {
        var book1 = Book.newBuilder()
                .setTitle("Book One")
                .setAuthor("Author A")
                .setPublicationYear(2001)
                .build();

        var book2 = Book.newBuilder()
                .setTitle("Book Two")
                .setAuthor("Author B")
                .setPublicationYear(2002)
                .build();

        var book3 = Book.newBuilder()
                .setTitle("Book Three")
                .setAuthor("Author C")
                .setPublicationYear(2003)
                .build();

        var library = Library.newBuilder()
                .setName("City Library")
                .addAllBooks(Arrays.asList(book1, book2, book3))
                .build();

        log.info("{}", library);
    }
}
