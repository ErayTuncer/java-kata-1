package org.echocat.kata.java.part1.repository;

import org.echocat.kata.java.part1.entity.Author;
import org.echocat.kata.java.part1.entity.Book;
import org.echocat.kata.java.part1.entity.ImmutableBook;
import org.echocat.kata.java.part1.tool.CSVFileReader;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class BookRepository {

    private final List<Book> bookList = new LinkedList<>();

    public static BookRepository create(AuthorRepository authorRepository) {
        BookRepository repository = new BookRepository();
        repository.readBooksFromFile(authorRepository);
        return repository;
    }

    private BookRepository() {
        // use create instead
    }

    private void readBooksFromFile(AuthorRepository authorRepository) {
        bookList.clear();
        CSVFileReader.readCSVFile("books.csv").forEach(csvLine -> {
            List<Author> authorList = new LinkedList<>();
            String[] emails = csvLine[2].split(",");
            Arrays.stream(emails).forEach(email -> authorList.add(authorRepository.findAuthorByEmail(email)));

            bookList.add(ImmutableBook.builder().title(csvLine[0]).isbn(csvLine[1]).addAllAuthors(authorList).description(csvLine[3]).build());
        });
    }

    public Optional<Book> findBookByISBN(String isbn) {
        return bookList.stream().filter(book -> book.isbn().equals(isbn)).findAny();
    }

    public Set<Book> findBooksByAuthor(Author author) {
        Set<Book> books = new HashSet<>();
        return bookList.stream().filter(book -> book.authors().contains(author)).collect(Collectors.toSet());
    }

}
